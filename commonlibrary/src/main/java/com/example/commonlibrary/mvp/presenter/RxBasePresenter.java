package com.example.commonlibrary.mvp.presenter;

import com.example.commonlibrary.mvp.model.BaseModel;
import com.example.commonlibrary.mvp.view.IView;
import com.example.commonlibrary.rxbus.RxBusManager;
import com.example.commonlibrary.utils.CommonLogger;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by COOTEK on 2017/8/4.
 */

public class RxBasePresenter<V extends IView, M extends BaseModel> extends BasePresenter<V, M> {


    public RxBasePresenter(M baseModel) {
        super(baseModel);
    }

    public <T> void registerEvent(Class<T> type, Consumer<T> consumer) {
        Disposable disposable = RxBusManager.getInstance().registerEvent(type, consumer, throwable -> CommonLogger.e("rx事件出错啦" + throwable.getMessage()));
        addDispose(disposable);
    }
}
