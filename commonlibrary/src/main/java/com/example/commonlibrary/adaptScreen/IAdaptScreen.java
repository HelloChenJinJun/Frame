package com.example.commonlibrary.adaptScreen;

/**
 * 项目名称:    android
 * 创建人:      陈锦军
 * 创建时间:    2018/8/22     15:07
 */
public interface IAdaptScreen {
     boolean isBaseOnWidth();
    int getScreenSize();
    boolean cancelAdapt();
    boolean needResetAdapt();
}
