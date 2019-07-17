package com.chen.larkplayer.databinding;
import com.chen.larkplayer.R;
import com.chen.larkplayer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemFragmentLocalAlbumListBindingImpl extends ItemFragmentLocalAlbumListBinding  {

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
    private final androidx.cardview.widget.CardView mboundView0;
    // variables
    // values
    // listeners
    private OnClickListenerImpl mViewModelOnItemClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public ItemFragmentLocalAlbumListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private ItemFragmentLocalAlbumListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[1]
            , (android.widget.TextView) bindings[2]
            );
        this.ivItemFragmentLocalAlbumListCover.setTag(null);
        this.mboundView0 = (androidx.cardview.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemFragmentLocalAlbumListName.setTag(null);
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
            setViewModel((com.chen.larkplayer.mvvm.localalbum.LocalAlbumItemViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.chen.larkplayer.mvvm.localalbum.LocalAlbumItemViewModel ViewModel) {
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
        com.example.commonlibrary.widget.manager.music.base.AlbumItemBean viewModelAlbumItemBean = null;
        com.chen.larkplayer.mvvm.localalbum.LocalAlbumItemViewModel viewModel = mViewModel;
        java.lang.String viewModelAlbumItemBeanAlbumName = null;
        com.example.commonlibrary.imageloader.glide.okhttpconfig.AudioCover viewModelAlbumItemBeanAudioCover = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (viewModel != null) {
                    // read viewModel::onItemClick
                    viewModelOnItemClickAndroidViewViewOnClickListener = (((mViewModelOnItemClickAndroidViewViewOnClickListener == null) ? (mViewModelOnItemClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mViewModelOnItemClickAndroidViewViewOnClickListener).setValue(viewModel));
                    // read viewModel.albumItemBean
                    viewModelAlbumItemBean = viewModel.albumItemBean;
                }


                if (viewModelAlbumItemBean != null) {
                    // read viewModel.albumItemBean.albumName
                    viewModelAlbumItemBeanAlbumName = viewModelAlbumItemBean.getAlbumName();
                    // read viewModel.albumItemBean.audioCover
                    viewModelAlbumItemBeanAudioCover = viewModelAlbumItemBean.audioCover;
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.example.commonlibrary.base.CommonBind.url(this.ivItemFragmentLocalAlbumListCover, viewModelAlbumItemBeanAudioCover);
            this.mboundView0.setOnClickListener(viewModelOnItemClickAndroidViewViewOnClickListener);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemFragmentLocalAlbumListName, viewModelAlbumItemBeanAlbumName);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.chen.larkplayer.mvvm.localalbum.LocalAlbumItemViewModel value;
        public OnClickListenerImpl setValue(com.chen.larkplayer.mvvm.localalbum.LocalAlbumItemViewModel value) {
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