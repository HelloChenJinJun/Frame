package com.example.frame;

import com.example.commonlibrary.BaseApplication;
import com.example.frame.dagger.DaggerMainComponent;
import com.example.frame.dagger.MainComponent;

/**
 * 项目名称:    Frame
 * 创建人:      陈锦军
 * 创建时间:    2019-07-26     14:37
 */
public class App extends BaseApplication {

    private static MainComponent mainComponent;

    public static MainComponent getMainComponent() {
        return mainComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
       mainComponent=DaggerMainComponent.builder().appComponent(getAppComponent()).build();
    }

    @Override
    protected int getDesignedHeight() {
        return 640;
    }

    @Override
    protected int getDesignedWidth() {
        return 360;
    }
}
