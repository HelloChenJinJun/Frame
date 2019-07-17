package com.example.commonlibrary.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.commonlibrary.R;
import com.example.commonlibrary.R2;
import com.example.commonlibrary.utils.Constant;

/**
 * 项目名称:    and-incentive-sdk
 * 创建人:      陈锦军
 * 创建时间:    2019-06-26     19:01
 */
public class HolderActivity extends BaseActivity {


    private Bundle bundle;
    private String titleContent;

    @BindView(R2.id.tv_header_layout_title)
    TextView title;

    public static void start(Context context, Bundle bundle) {
        Intent intent=new Intent(context,HolderActivity.class);
        intent.putExtra(Constant.DATA,bundle);
        context.startActivity(intent);
    }



    @OnClick(R2.id.iv_header_layout_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        bundle=getIntent().getBundleExtra(Constant.DATA);
        titleContent=bundle.getString(Constant.TITLE,null);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_holder;
    }


    @Override
    protected boolean needStatusPadding() {
       return !TextUtils.isEmpty(titleContent);
    }

    @Override
    protected void initView() {
        String fragmentName=bundle.getString(Constant.FRAGMENT_NAME,null);
        if (!TextUtils.isEmpty(titleContent)) {
            title.setText(titleContent);
            statusView.setBackgroundColor(Color.parseColor("#151515"));
            header.setBackgroundColor(Color.parseColor("#151515"));
        }else {
            header.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(fragmentName)) {
            finish();
        }else {
            try {
                Fragment fragment= (Fragment) Class.forName(fragmentName).newInstance();
                fragment.setArguments(bundle);
                addOrReplaceFragment(fragment,R.id.fl_activity_holder_container);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }





    @Override
    protected void initData() {

    }

    @Override
    public void updateData(Object o) {

    }
}
