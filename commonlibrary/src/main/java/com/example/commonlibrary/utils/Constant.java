package com.example.commonlibrary.utils;

import com.example.commonlibrary.BuildConfig;

/**
 * Created by COOTEK on 2017/7/31.
 */


public class Constant {
    //    默认Base URL
    //    8990正式服端口、、、、、、、、8992测试服端口

    public static final String BASE_URL = BuildConfig.DEBUG ? "http://api.larkplayerapp.com" : "http://api.larkplayerapp.com";
    public static final String DESIGNED_WIDTH = "designed_width";
    public static final String DESIGNED_HEIGHT = "designed_height";
    public static final String TOKEN = "user_token";
    public static final String HEADER = "Authorization";
    public static final String INSTALL_TIME = "install_time";
    public static final String DATA = "data";
    public static final String TITLE = "title";
    public static final String FRAGMENT_NAME = "fragment_name";
}
