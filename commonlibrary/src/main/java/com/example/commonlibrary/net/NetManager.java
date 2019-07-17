package com.example.commonlibrary.net;

import android.os.Environment;

import com.example.commonlibrary.BaseApplication;
import com.example.commonlibrary.interceptor.HttpLogInterceptor;
import com.example.commonlibrary.net.download.DownLoadApi;
import com.example.commonlibrary.net.download.DownLoadInterceptor;
import com.example.commonlibrary.net.download.DownLoadProgressObserver;
import com.example.commonlibrary.net.download.DownloadListener;
import com.example.commonlibrary.net.download.DownloadStatus;
import com.example.commonlibrary.net.download.DownloadUtil;
import com.example.commonlibrary.net.download.FileDAOImpl;
import com.example.commonlibrary.net.download.FileInfo;
import com.example.commonlibrary.net.upload.UpLoadApi;
import com.example.commonlibrary.net.upload.UpLoadListener;
import com.example.commonlibrary.net.upload.UpLoadProgressObserver;
import com.example.commonlibrary.net.upload.UpLoadRequestBody;
import com.example.commonlibrary.utils.AppUtil;
import com.example.commonlibrary.utils.CommonLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by COOTEK on 2017/8/3.
 */

public class NetManager {
    private static NetManager instance;
    //    由于每个下载请求要监听进度，因此要添加拦截器，所以要保持不同的retrofit
    private Map<String, Retrofit> stringRetrofitMap;
    private Map<String, CompositeDisposable> compositeDisposableMap;
    private FileDAOImpl daoSession;
    private Map<String, FileInfo> newFileInfoMap;

    public static NetManager getInstance() {
        if (instance == null) {
            synchronized (NetManager.class) {
                if (instance==null) {
                    instance = new NetManager();
                }
            }
        }
        return instance;
    }

    private NetManager() {
        stringRetrofitMap = new HashMap<>();
        daoSession = FileDAOImpl.getInstance();
        compositeDisposableMap = new HashMap<>();
        newFileInfoMap = new HashMap<>();
    }


    public void upLoad(final String url, String key, final File file, UpLoadListener listener) {
        final FileInfo info;
        if (daoSession.query(url) == null) {
            info = new FileInfo(file.getAbsolutePath(), file.getName(), DownloadStatus.NORMAL, 0, 0, 0, getDownLoadCacheDir());
        } else {
            info = daoSession.query(url);
        }
        newFileInfoMap.put(file.getAbsolutePath(), info);
        Retrofit retrofit = BaseApplication.getAppComponent().getRetrofit();
        UpLoadProgressObserver upLoadProgressObserver = new UpLoadProgressObserver(info, listener);
        RequestBody requestBody = RequestBody.create(DownloadUtil.guessMimeType(file.getName()), file);
        UpLoadRequestBody upLoadRequestBody = new UpLoadRequestBody(upLoadProgressObserver, requestBody);
        MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), upLoadRequestBody);
        retrofit.create(UpLoadApi.class).upLoad(url, part).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(new RetryWhenNetworkException())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> addSubscription(disposable, file.getAbsolutePath())).map(response -> info).subscribe(upLoadProgressObserver);
    }


    public void upLoad(String url, Map<String, File> stringFileMap, List<UpLoadListener> listener) {
        if (url == null || stringFileMap == null || stringFileMap.size() == 0) {
            return;
        }
        if (stringFileMap.size() != listener.size()) {
            CommonLogger.e("设置的监听器和上传的文件的数据 不一致");
        }
        int temp = -1;
        for (Map.Entry<String, File> entry :
                stringFileMap.entrySet()) {
            temp++;
            upLoad(url, entry.getKey(), entry.getValue(), listener.get(temp));
        }
    }


    public void downLoad(final String url, DownloadListener listener) {
        if (url == null) {
            return;
        }
        FileInfo info = daoSession.query(url);
        if (info == null) {
            info = new FileInfo(url, DownloadUtil.clipFileName(url), DownloadStatus.NORMAL, 0, 0, 0, getDownLoadCacheDir());
            daoSession.insert(info);
        }
        newFileInfoMap.put(url, info);
        Retrofit retrofit;
        DownLoadProgressObserver downLoadProgressObserver = new DownLoadProgressObserver(info, listener);
        if (stringRetrofitMap.containsKey(url)) {
            retrofit = stringRetrofitMap.get(url);
        } else {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS);
            HttpLoggingInterceptor httpLogInterceptor = new HttpLoggingInterceptor(new HttpLogInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLogInterceptor);
            builder.addInterceptor(new DownLoadInterceptor(downLoadProgressObserver));
            retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(BaseApplication.getAppComponent().getGson()))
                    .client(builder.build()).baseUrl(AppUtil.getBasUrl(url)).build();
            stringRetrofitMap.put(url, retrofit);
        }
        retrofit.create(DownLoadApi.class)
                .downLoad("bytes=" + info.getLoadBytes() + "-", url)
                .subscribeOn(Schedulers.io()).map(responseBody -> writeCaches(responseBody, url))
                .unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWhenNetworkException())
                .doOnSubscribe(disposable -> addSubscription(disposable, url))
                .subscribe(downLoadProgressObserver);
    }

    private void addSubscription(Disposable disposable, String url) {
        if (compositeDisposableMap.get(url) != null) {
            compositeDisposableMap.get(url).add(disposable);
        } else {
            //一次性容器,可以持有多个并提供 添加和移除。
            CompositeDisposable disposables = new CompositeDisposable();
            disposables.add(disposable);
            compositeDisposableMap.put(url, disposables);
        }
    }


    public void unSubscrible(String key) {
        if (compositeDisposableMap == null) {
            return;
        }
        if (!compositeDisposableMap.containsKey(key)) {
            return;
        }
        if (compositeDisposableMap.get(key) != null) {
            compositeDisposableMap.get(key).dispose();
        }
        compositeDisposableMap.remove(key);
    }


    public String getDownLoadCacheDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/music_download/";
    }


    public void clearAllCache() {
        if (compositeDisposableMap != null) {
            compositeDisposableMap.clear();
        }
        if (stringRetrofitMap != null) {
            stringRetrofitMap.clear();
        }
    }


    /**
     * 写入文件
     */
    private FileInfo writeCaches(ResponseBody responseBody, String url) {
        FileInfo info = daoSession.query(url);
        try {
            RandomAccessFile randomAccessFile = null;
            FileChannel channelOut = null;
            InputStream inputStream = null;
            try {
                if (info == null) {
                    CommonLogger.e("写入缓存这里出错");
                }
                info.setStatus(DownloadStatus.DOWNLOADING);
                File file = new File(info.getPath(), info.getName());
                if (!file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                if (!file.exists()) {
                    file.createNewFile();
                }
                long allLength = 0 == info.getTotalBytes() ? responseBody.contentLength() : info.getLoadBytes() + responseBody
                        .contentLength();

                inputStream = responseBody.byteStream();
                randomAccessFile = new RandomAccessFile(file, "rwd");
                channelOut = randomAccessFile.getChannel();
                MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE,
                        info.getLoadBytes(), allLength - info.getLoadBytes());
                byte[] buffer = new byte[1024 * 4];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    mappedBuffer.put(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (channelOut != null) {
                    channelOut.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }


    public void stop(String url) {
        if (url == null) {
            return;
        }
        FileInfo info;
        info = newFileInfoMap.get(url);
        if (info != null) {
            info.setStatus(DownloadStatus.STOP);
            unSubscrible(url);
        }
        daoSession.update(info);
    }

    public void cancel(String url) {
        if (url == null) {
            return;
        }
        FileInfo newFileInfo = newFileInfoMap.get(url);
        if (newFileInfo != null) {
            newFileInfo.setStatus(DownloadStatus.CANCEL);
            unSubscrible(url);
        }
        daoSession.update(newFileInfo);
    }


    private Map<String, DownloadListener> downloadListenerMap = new HashMap<>();





    public void download(@NonNull final long start, @NonNull final long end, @NonNull final String url, final File file, DownloadListener downloadListener) {
        String str;
        if (end == -1) {
            downloadListenerMap.remove(url);
            downloadListenerMap.put(url, downloadListener);
            Observable.fromArray(url).subscribeOn(Schedulers.io())
                    .map(s -> {
                        OkHttpClient.Builder builder = new OkHttpClient.Builder();
                        builder.connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS);
                        builder.addInterceptor(new HttpLoggingInterceptor(new HttpLogInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY));
                        Request request=new Request.Builder().url(s).head().build();
                        Response response= builder.build().newCall(request).execute();
                        return Long.parseLong(response.header("Content-Length"));
                    }).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            addSubscription(d,url);
                        }

                        @Override
                        public void onNext(Long contentLength) {
                            FileInfo fileInfo = daoSession.query(url);
                            fileInfo.setTotalBytes(contentLength.intValue());
                            fileInfo.setStatus(DownloadStatus.START);
                            daoSession.update(fileInfo);
                            RandomAccessFile randomFile;
                            try {
                                randomFile = new RandomAccessFile(file, "rw");
                                randomFile.setLength(contentLength);
                                long one = contentLength / 3;
                                for (int i = 0; i < 3; i++) {
                                    download(one * i, one * (i + 1), url, file, downloadListener);
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            str = end + "";
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS);
            builder.addInterceptor(new HttpLoggingInterceptor(new HttpLogInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY));
            Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(BaseApplication.getAppComponent().getGson()))
                    .client(builder.build()).baseUrl(AppUtil.getBasUrl(url)).build();
            retrofit.create(DownLoadApi.class).downLoad("bytes=" + start + "-" + str, url).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation()).map(responseBody -> {
                FileInfo fileInfo = null;
                fileInfo = writeFile(start, end, url, responseBody.byteStream(), file);
                return fileInfo;
            }).subscribe(new Observer<FileInfo>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addSubscription(d, url);
                }

                @Override
                public void onNext(FileInfo fileInfo) {

                }

                @Override
                public void onError(Throwable e) {
                    FileInfo fileInfo = daoSession.query(url);
                    fileInfo.setStatus(DownloadStatus.ERROR);
                    daoSession.update(fileInfo);
                    if (downloadListenerMap.get(url) != null) {
                        downloadListenerMap.get(url).onError(fileInfo, e.getMessage());
                    }
                }

                @Override
                public void onComplete() {
                    FileInfo fileInfo = daoSession.query(url);
                    if (fileInfo.getLoadBytes() == fileInfo.getTotalBytes()) {
                        fileInfo.setStatus(DownloadStatus.COMPLETE);
                        daoSession.update(fileInfo);
                        if (downloadListenerMap.get(url) != null) {
                            downloadListenerMap.get(url).onComplete(fileInfo);
                            downloadListenerMap.remove(url);
                        }
                    }
                }
            });
        }
    }

    private FileInfo writeFile(long start, long end, String url, InputStream src, File file) {
        try {
            RandomAccessFile desc = new RandomAccessFile(file, "rw");
            desc.seek(start);
            byte[] arr = new byte[1024 * 4];
            int len;
            long count = 0;
            long lastTime = 0;
            long lastValue = 0;

            FileInfo fileInfo = daoSession.query(url);
            while ((len = src.read(arr)) != -1) {
                desc.write(arr, 0, len);
                count += len;
                if (System.currentTimeMillis() - lastTime > 500) {
                    if (lastTime != 0) {
                        int speed = (int) ((count - lastValue) * 1000 / (System.currentTimeMillis() - lastTime));
                        fileInfo.setSpeed(speed);
                    }
                    lastTime = System.currentTimeMillis();
                    lastValue = count;
                    fileInfo.setLoadBytes((int) (fileInfo.getLoadBytes() + count));
                    if (fileInfo.getStatus() == DownloadStatus.STOP) {
                        daoSession.update(fileInfo);
                        if (downloadListenerMap.get(url) != null) {
                            downloadListenerMap.get(url).onStop(fileInfo);
                        }
                        break;
                    }
                    if (fileInfo.getStatus() == DownloadStatus.CANCEL) {
                        daoSession.update(fileInfo);
                        if (downloadListenerMap.get(url) != null) {
                            downloadListenerMap.get(url).onCancel(fileInfo);
                        }
                        break;
                    }
                    fileInfo.setStatus(DownloadStatus.DOWNLOADING);
                    daoSession.update(fileInfo);
                    if (downloadListenerMap.get(url) != null) {
                        downloadListenerMap.get(url).onUpdate(fileInfo);
                    }
                }
            }
            return fileInfo;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
