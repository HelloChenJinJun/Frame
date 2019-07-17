package com.example.commonlibrary.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.commonlibrary.BaseApplication;
import com.example.commonlibrary.dagger.component.AppComponent;
import com.example.commonlibrary.mvp.presenter.BasePresenter;
import com.example.commonlibrary.utils.StatusBarUtil;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;

/**
 * 项目名称:    Cugappplat
 * 创建人:        陈锦军
 * 创建时间:    2017/4/3      14:24
 * QQ:             1981367757
 */

public abstract class BaseFragment<T, P extends BasePresenter> extends Fragment implements IView<T> {

    /**
     * 采用懒加载
     */
    protected View root;
    private boolean hasInit = false;


    @Nullable
    @Inject
    protected P presenter;
    private CompositeDisposable compositeDisposable;
    private Unbinder unbinder;


    protected boolean needRefreshData() {
        return false;
    }


    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }


    protected AppComponent getAppComponent() {
        return BaseApplication.getAppComponent();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
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
            initDagger();
            if (presenter!=null) {
                presenter.attachView(this);
            }
            initData();
            if (needStatusPadding()) {
                StatusBarUtil.setStatusPadding(getPaddingView());
            }
        }
        if (root.getParent() != null) {
            ((ViewGroup) root.getParent()).removeView(root);
        }

        return root;
    }



    protected void initDagger(){
    }


    private View getPaddingView() {
        if (needStatusPadding()) {
            return root;
        }
        return null;
    }

    protected boolean needStatusPadding() {
        return true;
    }




    protected <V extends View> V findViewById(int id) {
        if (root != null) {
            return root.findViewById(id);
        }
        return null;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (root != null && getUserVisibleHint() && !hasInit) {
            hasInit = true;
            updateView();
        }
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


    private boolean hasRecord = false;


    protected abstract int getContentLayout();


    protected abstract void initView();

    protected abstract void initData();

    protected abstract void updateView();


    @Override
    public void showLoading(String loadingMsg) {
    }


    protected void showLoadDialog(String message) {
        if (!getActivity().isFinishing()) {
            if (getActivity() instanceof BaseActivity) {
                ((BaseActivity) getActivity()).showLoadDialog(message);
            }
        }
    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void showError(String errorMsg) {

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            if (!compositeDisposable.isDisposed()) {
                compositeDisposable.dispose();
            }
        }
        if (presenter != null) {
            presenter.onDestroy();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void showEmptyView() {
    }


    public void addOrReplaceFragment(Fragment fragment) {
        addOrReplaceFragment(fragment, 0);
    }


    protected int fragmentContainerResId = 0;
    protected Fragment currentFragment;

    /**
     * 第一次加载的时候调用该方法设置resId
     *
     * @param fragment
     * @param resId
     */
    public void addOrReplaceFragment(Fragment fragment, int resId) {
        if (resId != 0) {
            fragmentContainerResId = resId;
        }
        if (fragment == null) {
            return;
        }
        if (currentFragment == null) {
            getChildFragmentManager().beginTransaction().add(resId, fragment).show(fragment).commitAllowingStateLoss();
            currentFragment = fragment;
            return;
        }
        if (fragment.isAdded()) {
            getChildFragmentManager().beginTransaction().hide(currentFragment).show(fragment).commit();
        } else {
            getChildFragmentManager().beginTransaction().hide(currentFragment).add(fragmentContainerResId, fragment).show(fragment).commitAllowingStateLoss();
        }
        currentFragment = fragment;
    }


    protected void addBackStackFragment(Fragment fragment, View... views) {
        ((BaseActivity) getActivity()).addBackStackFragment(fragment, true, views);
    }


    protected void addBackStackFragment(Fragment fragment) {
        ((BaseActivity) getActivity()).addBackStackFragment(fragment, true);
    }


    protected void addBackStackFragment(Fragment fragment, boolean needAddBackStack, View... views) {
        ((BaseActivity) getActivity()).addBackStackFragment(fragment, needAddBackStack, views);
    }


}
