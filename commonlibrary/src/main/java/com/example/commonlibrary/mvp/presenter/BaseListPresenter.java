package com.example.commonlibrary.mvp.presenter;

import com.example.commonlibrary.bean.BaseBean;
import com.example.commonlibrary.mvp.model.BaseModel;
import com.example.commonlibrary.mvp.view.IView;

import java.util.List;

/**
 * 项目名称:    and-incentive-sdk
 * 创建人:      陈锦军
 * 创建时间:    2019-07-05     14:40
 */
public abstract class BaseListPresenter<D,M extends BaseModel> extends RxBasePresenter<IView<BaseBean<List<D>>>, M> {
    protected int page=0;

    public BaseListPresenter(M baseModel) {
        super(baseModel);
    }


    public void getData(boolean isRefresh) {
        if (isRefresh) {
            iView.showLoading(null);
            page=0;
        }
        page++;
        getData(page);
    }

    protected abstract void getData(int page);
}
