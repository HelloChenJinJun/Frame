package com.example.frame.dagger;

import com.example.commonlibrary.dagger.component.AppComponent;
import com.example.commonlibrary.dagger.scope.PerApplication;
import com.example.frame.mvp.FunctionListFragment;
import com.example.frame.mvp.download.DownloadFragment;

import dagger.Component;

/**
 * 项目名称:    Frame
 * 创建人:      陈锦军
 * 创建时间:    2019-08-02     14:59
 */
@PerApplication
@Component(dependencies = AppComponent.class,modules = MainModule.class)
public interface MainComponent {

    public void inject(FunctionListFragment functionListFragment);

    public void inject(DownloadFragment downloadFragment);
}
