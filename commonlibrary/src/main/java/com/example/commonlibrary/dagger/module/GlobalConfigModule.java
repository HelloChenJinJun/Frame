package com.example.commonlibrary.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.example.commonlibrary.ActivityManager;
import com.example.commonlibrary.bean.greendao.DaoMaster;
import com.example.commonlibrary.bean.greendao.DaoSession;
import com.example.commonlibrary.imageloader.base.BaseImageLoaderStrategy;
import com.example.commonlibrary.imageloader.glide.GlideImageLoaderStrategy;
import com.example.commonlibrary.interceptor.HttpLogInterceptor;
import com.example.commonlibrary.interceptor.TokenInterceptor;
import com.example.commonlibrary.mvp.model.DefaultModel;
import com.example.commonlibrary.repository.DefaultRepositoryManager;
import com.example.commonlibrary.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.greenrobot.greendao.database.Database;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

/**
 * Created by COOTEK on 2017/7/28.
 */
@Module
public class GlobalConfigModule {

    private Application application;


    public GlobalConfigModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(application, "common_library_db", null);
        Database database = devOpenHelper.getWritableDb();
        DaoMaster master = new DaoMaster(database);
        return master.newSession();
    }

    @Provides
    DefaultRepositoryManager provideRepositoryManager(Retrofit retrofit, DaoSession daoSession) {
        return new DefaultRepositoryManager<>(retrofit, daoSession);
    }

    @Provides
    DefaultModel provideDefaultModel(DefaultRepositoryManager defaultRepositoryManager) {
        return new DefaultModel(defaultRepositoryManager);
    }


    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder okHttpBuilder) {
        return okHttpBuilder.build();
    }




    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder(TokenInterceptor tokenInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS);
        //        自定义签名证书
//                        builder.sslSocketFactory(SSLConfig.getSSLSocketFactory(BaseApplication.getInstance()), (X509TrustManager) Objects.requireNonNull(SSLConfig.getTrustManager(BaseApplication.getInstance())));
//        builder.sslSocketFactory(TrustAllCerts.createSSLSocketFactory());builder
//        builder.hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier());
//        if (tokenInterceptor != null) {
//            builder.addNetworkInterceptor(tokenInterceptor);
//        }
        HttpLoggingInterceptor httpLogInterceptor = new HttpLoggingInterceptor(new HttpLogInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLogInterceptor);
        return builder;
    }


    @Singleton
    @Provides
    BaseImageLoaderStrategy provideImageLoader() {
        return new GlideImageLoaderStrategy();
    }

    @Provides
    @Singleton
    ActivityManager provideActivityManager() {
        ActivityManager activityManager = new ActivityManager(application);
        application.registerActivityLifecycleCallbacks(activityManager);
        return activityManager;
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Gson gson) {
        Retrofit.Builder builder = new Retrofit.Builder();

        return builder.baseUrl(Constant.BASE_URL).client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
    }


    @Singleton
    @Provides
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls().enableComplexMapKeySerialization();
        return gsonBuilder.create();
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences() {
        return application.getSharedPreferences("common", Context.MODE_PRIVATE);
    }




}
