package com.example.commonlibrary.mvp.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.commonlibrary.R;
import com.example.commonlibrary.baseadapter.adapter.BaseRecyclerAdapter;
import com.example.commonlibrary.baseadapter.empty.BaseEmptyView;
import com.example.commonlibrary.baseadapter.foot.LoadMoreView;
import com.example.commonlibrary.baseadapter.foot.OnLoadMoreListener;
import com.example.commonlibrary.baseadapter.viewholder.BaseWrappedViewHolder;
import com.example.commonlibrary.bean.BaseBean;
import com.example.commonlibrary.mvp.presenter.BaseListPresenter;

import java.util.List;
import java.util.Objects;

/**
 * 项目名称:    FastFrame
 * 创建人:      陈锦军
 * 创建时间:    2019-06-14     20:28
 */

//todo   构造一个基类list
public abstract class BaseListFragment<V extends BaseWrappedViewHolder, D, P extends BaseListPresenter> extends BaseFragment<BaseBean<List<D>>, P> implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {


    protected RecyclerView display;
    protected SwipeRefreshLayout refresh;
    private BaseRecyclerAdapter<D, V> adapter;


    @Override
    protected int getContentLayout() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void initView() {
        display=findViewById(R.id.rcv_fragment_base_list_display);
        refresh=findViewById(R.id.refresh_fragment_base_list_refresh);
        if (!needRefresh()) {
            refresh.setEnabled(false);
        } else {
            refresh.setEnabled(true);
        }
        refresh.setOnRefreshListener(this);

    }

    @Override
    protected void initData() {
        display.setLayoutManager(getLayoutManager());
        adapter = getAdapter();
        if (adapter != null && needLoadMore()) {
            adapter.setLoadMoreView(new LoadMoreView(Objects.requireNonNull(getContext())), this);
        }
        display.setAdapter(adapter);
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    protected abstract boolean needLoadMore();

    protected abstract boolean needRefresh();

    protected abstract BaseRecyclerAdapter<D, V> getAdapter();

    @Override
    protected void updateView() {
        getData(true);
    }


    @Override
    public void showError(String errorMsg) {
        super.showError(errorMsg);
        refresh.setRefreshing(false);
    }

    @Override
    public void updateData(BaseBean<List<D>> listBaseBean) {
        if (listBaseBean != null) {
            if (!needRefresh() || refresh.isRefreshing()) {
                adapter.refreshData(listBaseBean.getData());
            } else {
                adapter.addData(listBaseBean.getData());
            }
        }
        if (needLoadMore()) {
            adapter.updateLoadMoreStatus(BaseEmptyView.STATUS_NO_DATA);
        }
    }


    @Override
    public void showLoading(String loadingMsg) {
        super.showLoading(loadingMsg);
        refresh.setRefreshing(true);
    }


    @Override
    public void hideLoading() {
        super.hideLoading();
        refresh.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        getData(true);
    }

    @Override
    public void loadMore() {
        getData(false);
    }


    protected void getData(boolean isRefresh) {
        presenter.getData(isRefresh);
    }
}
