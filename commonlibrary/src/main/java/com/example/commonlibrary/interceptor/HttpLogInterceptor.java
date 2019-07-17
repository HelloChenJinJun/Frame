package com.example.commonlibrary.interceptor;

import com.example.commonlibrary.utils.CommonLogger;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 项目名称:    FastFrame
 * 创建人:      陈锦军
 * 创建时间:    2019-05-30     10:29
 */
public class HttpLogInterceptor implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        CommonLogger.e(message);
    }
}
