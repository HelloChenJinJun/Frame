package com.example.frame;

import android.os.Bundle;
import android.view.View;

import com.example.commonlibrary.mvp.view.BaseActivity;
import com.example.commonlibrary.mvp.view.HolderActivity;
import com.example.commonlibrary.utils.Constant;
import com.example.frame.mvp.FunctionListFragment;

import java.util.Random;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {



    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void updateData(Object o) {

    }


    @OnClick({R.id.tv_activity_main_load,R.id.tv_activity_main_function_list})
    public void onViewClick(View view){
        if (view.getId()==R.id.tv_activity_main_function_list){
            Bundle bundle=new Bundle();
            bundle.putString(Constant.TITLE,"Function");
            bundle.putString(Constant.FRAGMENT_NAME, FunctionListFragment.class.getName());
            HolderActivity.start(this,bundle);
        }else {
            deal();
        }
    }

    private void deal() {
        showLoading(null);
        root.postDelayed(() -> {
            if (new Random().nextBoolean()) {
                showError(null);
            }else {
                hideLoading();
            }
        },2000);
    }


    @Override
    protected void onRetryClick(View view) {
        deal();
    }
}
