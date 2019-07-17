package com.example.frame.adapter;

import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commonlibrary.baseadapter.adapter.BaseRecyclerAdapter;
import com.example.commonlibrary.baseadapter.viewholder.BaseWrappedViewHolder;
import com.example.commonlibrary.net.NetManager;
import com.example.commonlibrary.net.download.DownloadBean;
import com.example.commonlibrary.net.download.DownloadManager;
import com.example.commonlibrary.net.download.DownloadStatus;
import com.example.frame.R;
import com.example.frame.bean.DownloadItemBean;

import java.util.List;

import javax.inject.Inject;

/**
 * 项目名称:    and-incentive-sdk
 * 创建人:      陈锦军
 * 创建时间:    2019-07-01     20:52
 */
public class DownloadAdapter extends BaseRecyclerAdapter<DownloadItemBean, BaseWrappedViewHolder> {



    @Inject
    public DownloadAdapter() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fragment_download;
    }

    @Override
    protected void convert(BaseWrappedViewHolder holder, DownloadItemBean data) {
    }

    @Override
    protected void convert(BaseWrappedViewHolder holder, DownloadItemBean data, List<Object> payloads) {
        if (payloads != null && payloads.size() > 0) {
            updateData(holder, (DownloadBean) payloads.get(0));
            return;
        }
        updateData(holder, DownloadManager.getInstance().getDownloadBean(data.url));
        holder.setImageUrl(R.id.iv_item_fragment_download_cover, data.iconUrl)
                .setTag(R.id.btn_item_fragment_music_download_download,data.url)
                .setText(R.id.tv_item_fragment_music_download_title, data.name).setOnItemChildClickListener(R.id.btn_item_fragment_music_download_download);
    }


    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        NetManager.getInstance().clearAllCache();
    }

    private void updateData(BaseWrappedViewHolder holder, DownloadBean fileInfo) {
        int percent;
        String progress;
        if (fileInfo!=null) {
            percent = (int) (fileInfo.getLoadedSize() / (fileInfo.getTotalSize() * 1.0f) * 100);
            progress = String.format("%dMB/ %dMB", fileInfo.getLoadedSize() / (1024*1024), fileInfo.getTotalSize() / (1024*1024));
        }else {
            percent=0;
            progress="0MB/0MB ";
        }
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(progress).append(" ").append(fileInfo!=null?(fileInfo.getSpeed()*1.0f)/(1024*1024)+"M/s":"0M/s");
        ((SeekBar) holder.getView(R.id.sb_item_fragment_download_seek)).setProgress(percent);
        holder.setText(R.id.tv_item_fragment_music_download_desc, stringBuilder.toString());
        if (fileInfo!=null&&fileInfo.getStatus() == DownloadStatus.DOWNLOADING) {
            holder.setText(R.id.btn_item_fragment_music_download_download,"暂停");
        }else {
            holder.setText(R.id.btn_item_fragment_music_download_download,"开始");
        }
    }
}
