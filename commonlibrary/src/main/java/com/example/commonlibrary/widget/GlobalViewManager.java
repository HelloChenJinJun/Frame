package com.example.commonlibrary.widget;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.commonlibrary.R;

/**
 * 项目名称:    Frame
 * 创建人:      陈锦军
 * 创建时间:    2019-07-19     16:35
 */
public class GlobalViewManager {

    private static GlobalViewManager instance;
    public static final int STATE_HIDE = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_NO_NET = 2;
    public static final int STATE_NO_DATA = 3;
    public static final int STATE_SEVER_ERROR=4;
    public static GlobalViewManager getInstance() {
        if (instance == null) {
            instance = new GlobalViewManager();
        }
        return instance;
    }

    public IShow wrap(ViewGroup viewGroup, View.OnClickListener retryClick) {
        IShow iShow = new Wrapper(viewGroup).setRetryClick(retryClick);
        return iShow;
    }


    private static class Wrapper implements IShow {
        ViewGroup viewGroup;
        SparseArray<View> sparseArray = new SparseArray<>();
        private View currentView;
        private View.OnClickListener retryClick;


        public Wrapper setRetryClick(View.OnClickListener onClickListener) {
            this.retryClick = onClickListener;
            return this;
        }

        public Wrapper(ViewGroup viewGroup) {
            this.viewGroup = viewGroup;
            if (viewGroup.getParent() instanceof FrameLayout
                    || viewGroup.getParent() instanceof RelativeLayout) {
                this.viewGroup = (ViewGroup) viewGroup.getParent();
            } else {
                FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
                ViewGroup parent = (ViewGroup) viewGroup.getParent();
                int temp = parent.indexOfChild(viewGroup);
                parent.removeView(viewGroup);
                frameLayout.addView(viewGroup);
                parent.addView(frameLayout,temp);
                this.viewGroup=frameLayout;
            }
        }

        @Override
        public void showLoading(int status) {
            if (status==STATE_HIDE){
                removeView();
                return;
            }
            View view = sparseArray.get(status);
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(status), (ViewGroup) viewGroup.getParent(), false);
                View retry=view.findViewById(R.id.tv_empty_error_retry);
                if (retry != null && retryClick != null) {
                    retry.setOnClickListener(retryClick);
                }
                sparseArray.put(status,view);
            }
            if (viewGroup.indexOfChild(view) > 0) {
                if (viewGroup.indexOfChild(view) != viewGroup.getChildCount() - 1) {
                    view.bringToFront();
                }
            }else {
                removeView();
                addView(view);
            }
        }

        @Override
        public void onDestroy() {
                sparseArray.clear();
        }

        private void addView(View view) {
            viewGroup.addView(view);
            currentView=view;
        }

        private void removeView() {
            if (currentView != null) {
                viewGroup.removeView(currentView);
            }
        }

        private int getLayoutId(int status) {
            switch (status) {
                case STATE_LOADING:
                    return R.layout.view_empty_loading;
                case STATE_NO_DATA:
                    return R.layout.view_empty_no_data;
                case STATE_NO_NET:
                    return R.layout.view_empty_no_net;
                case STATE_SEVER_ERROR:
                    return R.layout.view_empty_sever_error;
                default:
                    return R.layout.view_empty_no_net;
            }
        }


    }


    public interface IShow {
        /**
         * 显示加载
         */
        void showLoading(int status);
        void onDestroy();
    }





}
