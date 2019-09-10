package com.example.frame.mvp.download;

import android.view.View;

import com.example.commonlibrary.baseadapter.adapter.BaseRecyclerAdapter;
import com.example.commonlibrary.baseadapter.listener.OnSimpleItemChildClickListener;
import com.example.commonlibrary.baseadapter.viewholder.BaseWrappedViewHolder;
import com.example.commonlibrary.mvp.view.BaseListFragment;
import com.example.commonlibrary.net.download.DownloadBean;
import com.example.commonlibrary.net.download.DownloadManager;
import com.example.commonlibrary.net.download.DownloadTask;
import com.example.commonlibrary.utils.CommonLogger;
import com.example.frame.App;
import com.example.frame.R;
import com.example.frame.adapter.DownloadAdapter;
import com.example.frame.bean.DownloadItemBean;

import javax.inject.Inject;

/**
 * 项目名称:    and-incentive-sdk
 * 创建人:      陈锦军
 * 创建时间:    2019-07-01     20:40
 */
public class DownloadFragment extends BaseListFragment<BaseWrappedViewHolder, DownloadItemBean, DownLoadPresenter> {


    @Inject
    DownloadAdapter downloadAdapter;


    @Override
    protected void initDagger() {
        App.getMainComponent().inject(this);
    }


    @Override
    protected void initData() {
        super.initData();
        root.setBackgroundColor(getContext().getResources().getColor(R.color.white));
        downloadAdapter.setOnItemClickListener(new OnSimpleItemChildClickListener() {
            @Override
            public void onItemChildClick(int position, View view, int id) {
             String url=  downloadAdapter.getData(position).url;
                DownloadManager.getInstance().download(url, new DownloadTask.DownloadProgressListener() {
                    @Override
                    public void onStart(DownloadBean downloadBean) {
                        CommonLogger.e("onStart",downloadBean.toString());
                        downloadAdapter.notifyItemChanged(position,downloadBean);
                    }

                    @Override
                    public void onError(String error) {
                        CommonLogger.e("onError",error);
                        downloadAdapter.notifyItemChanged(position,null);
                    }

                    @Override
                    public void onPause(DownloadBean downloadBean) {
                        CommonLogger.e("onPause",downloadBean.toString());
                        downloadAdapter.notifyItemChanged(position,downloadBean);
                    }

                    @Override
                    public void onProgress(DownloadBean downloadBean) {
                        CommonLogger.e("onProgress",downloadBean.toString());
                        downloadAdapter.notifyItemChanged(position,downloadBean);
                    }

                    @Override
                    public void onFinish(DownloadBean downloadBean) {
                        CommonLogger.e("onFinish",downloadBean.toString());
                        downloadAdapter.notifyItemChanged(position,downloadBean);
                    }
                });
            }
        });
    }

    @Override
    protected boolean needLoadMore() {
        return false;
    }


    @Override
    protected boolean needStatusPadding() {
        return false;
    }

    @Override
    protected boolean needRefresh() {
        return true;
    }

    @Override
    protected BaseRecyclerAdapter<DownloadItemBean, BaseWrappedViewHolder> getAdapter() {
        return downloadAdapter;
    }
}
