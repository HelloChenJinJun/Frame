package com.example.commonlibrary.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.commonlibrary.BaseApplication;
import com.example.commonlibrary.R;
import com.example.commonlibrary.adaptScreen.IAdaptScreen;
import com.example.commonlibrary.dagger.component.AppComponent;
import com.example.commonlibrary.mvp.presenter.BasePresenter;
import com.example.commonlibrary.utils.AppUtil;
import com.example.commonlibrary.utils.CommonLogger;
import com.example.commonlibrary.utils.Constant;
import com.example.commonlibrary.utils.StatusBarUtil;
import com.example.commonlibrary.utils.ToastUtils;
import com.example.commonlibrary.widget.GlobalViewManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * 项目名称:    Cugappplat
 * 创建人:        陈锦军
 * 创建时间:    2017/4/3      14:21
 * QQ:             1981367757
 */

public abstract class BaseActivity<T, P extends BasePresenter> extends AppCompatActivity implements IView<T>, IAdaptScreen {

    //  这里的布局view可能为空，取决于子类布局中是否含有该空布局


    protected int fragmentContainerResId = 0;
    protected Fragment currentFragment;
    private CompositeDisposable compositeDisposable;
    protected View root;
    protected View header;
    protected View statusView;
    private GlobalViewManager.IShow show;


    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }


    public AppComponent getAppComponent() {
        return BaseApplication.getAppComponent();
    }


    @Override
    public boolean isBaseOnWidth() {
        return true;
    }


    @Override
    public int getScreenSize() {
        return BaseApplication.getAppComponent().getSharedPreferences().getInt(Constant.DESIGNED_WIDTH, 0);
    }


    @Override
    public boolean cancelAdapt() {
        return true;
    }


    @Override
    public boolean needResetAdapt() {
        return getScreenSize() != (isBaseOnWidth() ? BaseApplication.getAppComponent().getSharedPreferences().getInt(Constant.DESIGNED_WIDTH, 0) :
                BaseApplication.getAppComponent().getSharedPreferences().getInt(Constant.DESIGNED_HEIGHT, 0));
    }

    @Nullable
    @Inject
    protected P presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(getContentLayout());
        root = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        header = root.findViewById(R.id.header_layout_id);
        if (needStatusPadding()) {
            if (header != null) {
                ViewGroup parent = (ViewGroup) header.getParent();
                View insertView;
                int index = parent.indexOfChild(header);
                statusView = new View(this);
                statusView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, StatusBarUtil.getStatusBarHeight(this)));
                statusView.setBackground(header.getBackground());
                if (parent instanceof LinearLayout) {
                    if (index != 0) {
                        index--;
                    }
                    insertView = statusView;
                } else {
                    LinearLayout linearLayout = new LinearLayout(this);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    linearLayout.addView(statusView);
                    index = parent.indexOfChild(header);
                    parent.removeView(header);
                    linearLayout.addView(header);
                    insertView = linearLayout;
                }
                parent.addView(insertView, index);
            } else {
                StatusBarUtil.setStatusPadding(root);
            }
        }
        if (header != null && header instanceof Toolbar) {
            setSupportActionBar((Toolbar) header);
        }
        ButterKnife.bind(this);
        initView();
        initDagger();
        if (presenter != null) {
            presenter.attachView(this);
        }
        initData();
//        设置全屏
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
        show = GlobalViewManager.getInstance().wrap((ViewGroup) root, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetryClick(v);
            }
        });
    }



    protected void onRetryClick(View view){
    }


    protected void initDagger() {

    }


    protected abstract int getContentLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected boolean needStatusPadding() {
        return true;
    }

    public void showLoadDialog(final String message) {
        ToastUtils.showShortToast(message);
    }

    public void addOrReplaceFragment(Fragment fragment) {
        addOrReplaceFragment(fragment, 0);
    }

    /**
     * 第一次加载的时候调用该方法设置resId
     */
    public void addOrReplaceFragment(Fragment fragment, int resId) {
        if (resId != 0) {
            fragmentContainerResId = resId;
        }
        if (fragment == null) {
            return;
        }
        if (currentFragment == null) {
            getSupportFragmentManager().beginTransaction().add(fragmentContainerResId, fragment).show(fragment).commitAllowingStateLoss();
            currentFragment = fragment;
        } else if (currentFragment != fragment) {
            if (fragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().hide(currentFragment).show(fragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(currentFragment).add(fragmentContainerResId, fragment).show(fragment).commitAllowingStateLoss();
            }
            currentFragment = fragment;
        }
    }


    private int backStackLayoutId = 0;

    protected void addBackStackFragment(Fragment fragment, int resId) {
        backStackLayoutId = resId;
        addBackStackFragment(fragment, true);
    }


    protected void addBackStackFragment(Fragment fragment, int resId, boolean needAddBackStack) {
        backStackLayoutId = resId;
        addBackStackFragment(fragment, needAddBackStack);
    }


    protected void addBackStackFragment(Fragment fragment, boolean needAddBackStack) {
        addBackStackFragment(fragment, needAddBackStack, (View) null);
    }


    protected void addBackStackFragment(Fragment fragment, boolean needAddBackStack, View... views) {
        if (backStackLayoutId == 0) {
            return;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


        if (views != null && views.length > 0) {
            fragmentTransaction.replace(backStackLayoutId, fragment);
            for (View item :
                    views) {
                if (item != null) {
                    fragmentTransaction.addSharedElement(item, ViewCompat.getTransitionName(item));
                }
            }
        } else {
            fragmentTransaction
                    .add(backStackLayoutId, fragment);
        }
        if (needAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }


    @Override
    public void showLoading(String loadMessage) {
        show.showLoading(GlobalViewManager.STATE_LOADING);
    }

    @Override
    public void hideLoading() {
        show.showLoading(GlobalViewManager.STATE_HIDE);
    }


    @Override
    public void showError(String errorMsg) {
        ToastUtils.showShortToast(errorMsg);
        CommonLogger.e(errorMsg);
        if (!AppUtil.isNetworkAvailable()) {
            show.showLoading(GlobalViewManager.STATE_NO_NET);
        } else {
            show.showLoading(GlobalViewManager.STATE_SEVER_ERROR);
        }
    }


    @Override
    public void showEmptyView() {
        show.showLoading(GlobalViewManager.STATE_NO_DATA);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
        if (compositeDisposable != null) {
            if (!compositeDisposable.isDisposed()) {
                compositeDisposable.dispose();
            }
            compositeDisposable.clear();
            compositeDisposable = null;
        }
        if (show != null) {
            show.onDestroy();
        }
    }


}
