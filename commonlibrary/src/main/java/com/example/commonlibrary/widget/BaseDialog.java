package com.example.commonlibrary.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名称:    Frame
 * 创建人:      陈锦军
 * 创建时间:    2019-07-23     14:10
 */
public abstract class BaseDialog extends DialogFragment {

    /**
     * 采用懒加载
     */
    protected View root;
    private boolean hasInit = false;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {
            root = LayoutInflater.from(getActivity()).inflate(getContentLayout(), null);
            if (root.getParent() != null) {
                ((ViewGroup) root.getParent()).removeView(root);
            }
            if (container != null) {
                container.addView(root);
            }
            unbinder = ButterKnife.bind(this, root);
            initView();
            initData();
        }
        if (root.getParent() != null) {
            ((ViewGroup) root.getParent()).removeView(root);
        }

        return root;
    }


    /**
     * 视图真正可见的时候才调用
     */

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (root != null && isVisibleToUser) {
            if (!hasInit) {
                hasInit = true;
                updateView();
            } else if (needRefreshData()) {
                updateView();
            }
        }

    }


    protected boolean needRefreshData() {
        return false;
    }
    protected abstract int getContentLayout();


    protected abstract void initView();

    protected abstract void initData();

    protected abstract void updateView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
