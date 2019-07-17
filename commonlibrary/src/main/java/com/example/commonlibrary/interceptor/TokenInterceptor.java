package com.example.commonlibrary.interceptor;


import com.example.commonlibrary.BaseApplication;
import com.example.commonlibrary.utils.Constant;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import javax.inject.Inject;
import java.io.IOException;

/**
 * 项目名称:    zhuayu_android
 * 创建人:      陈锦军
 * 创建时间:    2018/11/13     11:11
 */
public class TokenInterceptor implements Interceptor {


    @Inject
    public TokenInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = null;
        String token;
        String url = chain.request().url().toString();
        if (url.startsWith(Constant.BASE_URL)) {
            token = BaseApplication.getAppComponent()
                    .getSharedPreferences().getString(Constant.TOKEN, null);
            if (token != null) {
                request = chain.request().newBuilder()
                        .url(chain.request().url()).header(Constant.HEADER, token).build();
            }
        }
        Response response;
        if (request != null) {
            response = chain.proceed(request);
        } else {
            response = chain.proceed(chain.request());
        }
        return response;
    }
}
