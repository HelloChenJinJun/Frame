package com.example.frame.mvp;

import android.os.Bundle;
import android.view.View;

import com.example.commonlibrary.baseadapter.adapter.BaseRecyclerAdapter;
import com.example.commonlibrary.baseadapter.listener.OnSimpleItemChildClickListener;
import com.example.commonlibrary.baseadapter.viewholder.BaseWrappedViewHolder;
import com.example.commonlibrary.mvp.view.BaseListFragment;
import com.example.commonlibrary.mvp.view.HolderActivity;
import com.example.commonlibrary.utils.Constant;
import com.example.frame.App;
import com.example.frame.adapter.FunctionListAdapter;
import com.example.frame.bean.FunctionBean;

import javax.inject.Inject;

/**
 * 项目名称:    Frame
 * 创建人:      陈锦军
 * 创建时间:    2019-08-02     14:49
 */
public class FunctionListFragment extends BaseListFragment<BaseWrappedViewHolder, FunctionBean,FunctionListPresenter> {


    @Inject
    FunctionListAdapter functionlistAdapter;

    @Override
    protected boolean needLoadMore() {
        return false;
    }

    @Override
    protected boolean needRefresh() {
        return false;
    }


    @Override
    protected boolean needStatusPadding() {
        return false;
    }

    @Override
    protected void initDagger() {
        App.getMainComponent().inject(this);
    }

    @Override
    protected void initData() {
        super.initData();
        functionlistAdapter.setOnItemClickListener(new OnSimpleItemChildClickListener() {
            @Override
            public void onItemChildClick(int position, View view, int id) {

            }

            @Override
            public void onItemClick(int position, View view) {
                Bundle bundle=new Bundle();
                bundle.putString(Constant.TITLE,functionlistAdapter.getData(position).getTitle());
                bundle.putString(Constant.FRAGMENT_NAME,functionlistAdapter.getData(position).getFragmentTitle());
                HolderActivity.start(view.getContext(),bundle);
            }
        });
    }

    @Override
    protected BaseRecyclerAdapter<FunctionBean, BaseWrappedViewHolder> getAdapter() {
        return functionlistAdapter;
    }
}
