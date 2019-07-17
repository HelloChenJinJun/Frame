package com.example.commonlibrary.net.download;

import android.text.TextUtils;

import androidx.annotation.IntDef;

import com.example.commonlibrary.BaseApplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 项目名称:    Frame
 * 创建人:      陈锦军
 * 创建时间:    2019-07-31     17:21
 */
public class DownloadTask implements Runnable, Task {


    private ThreadPoolExecutor threadPoolExecutor;
    private volatile DownloadBean downloadBean;
    private DownloadTask.DownloadProgressListener downloadListener;
    private long lastRefreshTime = 0;


    public DownloadTask(DownloadBean downloadBean,DownloadProgressListener downloadProgressListener) {
        this.downloadListener=downloadProgressListener;
        this.downloadBean = downloadBean;
        threadPoolExecutor = DownloadExecutors.getsInstance().getThreadPoolExecutor();
    }


    public DownloadBean getDownloadBean() {
        return downloadBean;
    }

    @Override
    public void run() {
        Request request = new Request.Builder().url(downloadBean.getUrl()).head().get()
                .head().build();
        try {
            Response response = BaseApplication.getAppComponent().getOkHttpClient().newCall(request).execute();
            long contentSize = Long.parseLong(response.header("Content-Length"));
            downloadBean.setTotalSize(contentSize);
            List<DownloadBean.DownloadSubItem> list = new ArrayList<>();
            int threadCount = DownloadConfig.threadcount;
            long one = contentSize / threadCount;
            downloadBean.setStatus(Status.DOWNLOADING);
            try {
                for (int i = 0; i < threadCount; i++) {
                    long start = i * one;
                    long end = i == threadCount - 1 ? contentSize : (i + 1) * one;
                    DownloadBean.DownloadSubItem item = new DownloadBean.DownloadSubItem(start, end);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("bytes=").append(start).append("-").append(end);
                    BaseApplication.getAppComponent().getRetrofit().create(DownLoadApi.class).downLoad(stringBuilder.toString(),downloadBean.getUrl())
                            .subscribeOn(Schedulers.io()).map((Function<ResponseBody, DownloadBean>) responseBody -> {
                                item.setCurrentPosition(start);
                                //start downloading
                                item.setDownloadProgressListener((read, count, done) -> DownloadUtil.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (done || System.currentTimeMillis() - lastRefreshTime > DownloadConfig.REFRESH_TIME) {
                                            downloadListener.onProgress(downloadBean);
                                            lastRefreshTime = System.currentTimeMillis();
                                        }
                                    }
                                }));
                                RandomAccessFile randomAccessFile;
                                randomAccessFile = new RandomAccessFile(downloadBean.getFilePath(), "rw");
                                randomAccessFile.seek(item.getCurrentPosition());
                                download(responseBody.byteStream(), randomAccessFile, item);
                                return new DownloadBean(null,null,Status.NORMAL);
                            }).subscribe();
                    list.add(item);
                }
            } catch (Exception e) {
                e.printStackTrace();
                DownloadUtil.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        downloadBean.setStatus(Status.ERROR);
                        downloadListener.onError(e.toString());
                    }
                });
            }
            downloadBean.setSubItemList(list);
        } catch (IOException e) {
            e.printStackTrace();
            DownloadUtil.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    downloadBean.setStatus(Status.ERROR);
                    downloadListener.onError("http error"+e.toString());
                }
            });
        }
    }

    private void download(InputStream input, RandomAccessFile file, DownloadBean.DownloadSubItem item) {
        if (input == null || file == null) return;
        byte[] buffer = new byte[DownloadConfig.BUFFER_SIZE];
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(input, DownloadConfig.BUFFER_SIZE);
        int len;
        long lastTime = 0;
        int loadedSize = 0;
        long lastValue = 0;
        long total = item.getEndPosition() - item.getStartPosition();
        try {
            while ((len = input.read(buffer, 0, DownloadConfig.BUFFER_SIZE)) != -1 && downloadBean.getStatus() == Status.DOWNLOADING) {
                file.write(buffer, 0, len);
                loadedSize += len;
                if (System.currentTimeMillis() - lastTime > DownloadConfig.REFRESH_TIME) {
                    if (lastTime != 0) {
                        int speed = (int) ((loadedSize - lastValue) * 1000 / (System.currentTimeMillis() - lastTime));
                        item.setSpeed(speed);
                    }
                    item.setCurrentPosition(item.getStartPosition()+loadedSize);
                    if (item.getDownloadProgressListener() != null) {
                        item.getDownloadProgressListener().update(loadedSize, total, loadedSize == total);
                    }
                    lastTime = System.currentTimeMillis();
                    lastValue = loadedSize;
                }
            }
            if (item.getDownloadProgressListener() != null) {
                item.getDownloadProgressListener().update(loadedSize, total, loadedSize == total);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DownloadUtil.close(input);
            DownloadUtil.close(file);
        }
    }


    @Override
    public Task start() {

        if (TextUtils.isEmpty(downloadBean.getUrl()) || TextUtils.isEmpty(downloadBean.getFilePath())) {
            return this;
        }

        if (downloadBean.getStatus() == Status.DOWNLOADING || downloadBean.getStatus() == Status.START) {
            return this;
        }
        downloadBean.setStatus(Status.START);
        downloadListener.onStart(downloadBean);
        threadPoolExecutor.execute(this);
        return this;
    }

    @Override
    public Task pause() {
        downloadBean.setStatus(Status.STOP);
        downloadBean.setSpeed(0);
        return this;
    }


    public static interface DownloadProgressListener {

        public void onStart(DownloadBean downloadBean);

        public void onError(String error);

        public void onPause(DownloadBean downloadBean);

        public void onProgress(DownloadBean downloadBean);

        public void onFinish(DownloadBean downloadBean);
    }

    public static class Status {

        private Status() {
            throw new RuntimeException("DownloadStatus cannot be initialized!");
        }

        // 未下载
        public static final int NORMAL = 0;
        // 等待下载
        public static final int WAIT = 1;
        // 开始下载
        public static final int START = 2;
        // 正在下载
        public static final int DOWNLOADING = 3;
        // 停止下载
        public static final int STOP = 4;
        // 下载失败
        public static final int ERROR = 5;
        // 下载完成
        public static final int COMPLETE = 6;
        // 取消下载
        public static final int CANCEL = 7;
        // 安装中
        public static final int INSTALLING = 8;
        // 已经安装
        public static final int INSTALLED = 9;
        // 收藏状态
        public static final int COLLECT = 10;

        @Retention(RetentionPolicy.SOURCE)
        @Target({ElementType.FIELD, ElementType.PARAMETER})
        @IntDef({NORMAL, WAIT, START, DOWNLOADING, STOP, COMPLETE, CANCEL, ERROR, INSTALLING, INSTALLED, COLLECT})
        public @interface DStatus {
        }
    }
}
