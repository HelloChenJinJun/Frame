package com.chen.larkplayer.databinding;
import com.chen.larkplayer.R;
import com.chen.larkplayer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemFragmentLocalVideoListBindingImpl extends ItemFragmentLocalVideoListBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    private OnClickListenerImpl mViewModelOnItemClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public ItemFragmentLocalVideoListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private ItemFragmentLocalVideoListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[1]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            );
        this.ivItemFragmentLocalVideoListImage.setTag(null);
        this.ivItemFragmentLocalVideoListMore.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemFragmentLocalVideoListSize.setTag(null);
        this.tvItemFragmentLocalVideoListTime.setTag(null);
        this.tvItemFragmentLocalVideoListTitle.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.viewModel == variableId) {
            setViewModel((com.chen.larkplayer.mvvm.localvideo.LocalVideoItemViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.chen.larkplayer.mvvm.localvideo.LocalVideoItemViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        android.view.View.OnClickListener viewModelOnItemClickAndroidViewViewOnClickListener = null;
        java.lang.String viewModelVideoPlayBeanThumbPath = null;
        java.lang.String viewModelVideoPlayBeanResolution = null;
        com.example.commonlibrary.widget.manager.video.base.VideoPlayBean viewModelVideoPlayBean = null;
        java.lang.String viewModelVideoPlayBeanTitle = null;
        java.lang.String timeUtilFormatTimeViewModelVideoPlayBeanDuration = null;
        com.chen.larkplayer.mvvm.localvideo.LocalVideoItemViewModel viewModel = mViewModel;
        long viewModelVideoPlayBeanDuration = 0;

        if ((dirtyFlags & 0x3L) != 0) {



                if (viewModel != null) {
                    // read viewModel::onItemClick
                    viewModelOnItemClickAndroidViewViewOnClickListener = (((mViewModelOnItemClickAndroidViewViewOnClickListener == null) ? (mViewModelOnItemClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mViewModelOnItemClickAndroidViewViewOnClickListener).setValue(viewModel));
                    // read viewModel.videoPlayBean
                    viewModelVideoPlayBean = viewModel.videoPlayBean;
                }


                if (viewModelVideoPlayBean != null) {
                    // read viewModel.videoPlayBean.thumbPath
                    viewModelVideoPlayBeanThumbPath = viewModelVideoPlayBean.getThumbPath();
                    // read viewModel.videoPlayBean.resolution
                    viewModelVideoPlayBeanResolution = viewModelVideoPlayBean.getResolution();
                    // read viewModel.videoPlayBean.title
                    viewModelVideoPlayBeanTitle = viewModelVideoPlayBean.getTitle();
                    // read viewModel.videoPlayBean.duration
                    viewModelVideoPlayBeanDuration = viewModelVideoPlayBean.getDuration();
                }


                // read TimeUtil.formatTime(viewModel.videoPlayBean.duration)
                timeUtilFormatTimeViewModelVideoPlayBeanDuration = com.example.commonlibrary.utils.TimeUtil.formatTime(viewModelVideoPlayBeanDuration);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.example.commonlibrary.base.CommonBind.url(this.ivItemFragmentLocalVideoListImage, viewModelVideoPlayBeanThumbPath);
            this.ivItemFragmentLocalVideoListMore.setOnClickListener(viewModelOnItemClickAndroidViewViewOnClickListener);
            this.mboundView0.setOnClickListener(viewModelOnItemClickAndroidViewViewOnClickListener);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemFragmentLocalVideoListSize, viewModelVideoPlayBeanResolution);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemFragmentLocalVideoListTime, timeUtilFormatTimeViewModelVideoPlayBeanDuration);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemFragmentLocalVideoListTitle, viewModelVideoPlayBeanTitle);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.chen.larkplayer.mvvm.localvideo.LocalVideoItemViewModel value;
        public OnClickListenerImpl setValue(com.chen.larkplayer.mvvm.localvideo.LocalVideoItemViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onItemClick(arg0); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}