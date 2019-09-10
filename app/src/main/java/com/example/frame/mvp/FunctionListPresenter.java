package com.example.frame.mvp;

import com.example.commonlibrary.bean.BaseBean;
import com.example.commonlibrary.mvp.model.DefaultModel;
import com.example.commonlibrary.mvp.presenter.BaseListPresenter;
import com.example.frame.App;
import com.example.frame.R;
import com.example.frame.bean.FunctionBean;
import com.example.frame.mvp.download.DownloadFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 项目名称:    Frame
 * 创建人:      陈锦军
 * 创建时间:    2019-08-02     14:51
 */
public class FunctionListPresenter extends BaseListPresenter<FunctionBean,DefaultModel> {

    @Inject
    public FunctionListPresenter(DefaultModel baseModel) {
        super(baseModel);
    }

    @Override
    protected void getData(int page) {
        List<FunctionBean>  list=new ArrayList<>();
        FunctionBean download=new FunctionBean(App.getInstance().getString(R.string.download_title),
                DownloadFragment.class.getName());
        list.add(download);
        BaseBean<List<FunctionBean>>  baseBean=new BaseBean<>();
        baseBean.setData(list);
        iView.updateData(baseBean);
        iView.hideLoading();
    }
}
