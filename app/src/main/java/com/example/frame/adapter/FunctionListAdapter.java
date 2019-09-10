package com.example.frame.adapter;

import com.example.commonlibrary.baseadapter.adapter.BaseRecyclerAdapter;
import com.example.commonlibrary.baseadapter.viewholder.BaseWrappedViewHolder;
import com.example.frame.R;
import com.example.frame.bean.FunctionBean;

import javax.inject.Inject;

/**
 * 项目名称:    Frame
 * 创建人:      陈锦军
 * 创建时间:    2019-08-02     14:53
 */
public class FunctionListAdapter extends BaseRecyclerAdapter<FunctionBean, BaseWrappedViewHolder> {



    @Inject
    public FunctionListAdapter() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fragment_function_list;
    }

    @Override
    protected void convert(BaseWrappedViewHolder holder, FunctionBean data) {
                holder.setText(R.id.tv_item_fragment_function_list_title,data.getTitle())
                        .setOnItemClickListener();
    }
}
