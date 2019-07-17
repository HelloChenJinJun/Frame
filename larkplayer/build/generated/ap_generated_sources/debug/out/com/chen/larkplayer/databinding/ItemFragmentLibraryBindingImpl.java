package com.chen.larkplayer.databinding;
import com.chen.larkplayer.R;
import com.chen.larkplayer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemFragmentLibraryBindingImpl extends ItemFragmentLibraryBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rl_item_fragment_library_image_container, 5);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemFragmentLibraryBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private ItemFragmentLibraryBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[2]
            , (android.widget.ImageView) bindings[1]
            , (android.widget.RelativeLayout) bindings[5]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[3]
            );
        this.ivItemFragmentLibraryIcon.setTag(null);
        this.ivItemFragmentLibraryImage.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemFragmentLibraryCount.setTag(null);
        this.tvItemFragmentLibraryTitle.setTag(null);
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
            setViewModel((com.chen.larkplayer.mvvm.library.LibraryItemViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.chen.larkplayer.mvvm.library.LibraryItemViewModel ViewModel) {
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
        java.lang.String viewModelPlayListItemBeanAlbum = null;
        int viewModelPlayListItemBeanIcon = 0;
        com.chen.larkplayer.bean.LibraryListBean viewModelPlayListItemBean = null;
        java.lang.String viewModelGetSongCount = null;
        java.lang.String viewModelPlayListItemBeanTitle = null;
        com.chen.larkplayer.mvvm.library.LibraryItemViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x3L) != 0) {



                if (viewModel != null) {
                    // read viewModel.playListItemBean
                    viewModelPlayListItemBean = viewModel.playListItemBean;
                    // read viewModel.getSongCount()
                    viewModelGetSongCount = viewModel.getSongCount();
                }


                if (viewModelPlayListItemBean != null) {
                    // read viewModel.playListItemBean.album
                    viewModelPlayListItemBeanAlbum = viewModelPlayListItemBean.getAlbum();
                    // read viewModel.playListItemBean.icon
                    viewModelPlayListItemBeanIcon = viewModelPlayListItemBean.getIcon();
                    // read viewModel.playListItemBean.title
                    viewModelPlayListItemBeanTitle = viewModelPlayListItemBean.getTitle();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.example.commonlibrary.base.CommonBind.src(this.ivItemFragmentLibraryIcon, viewModelPlayListItemBeanIcon);
            com.example.commonlibrary.base.CommonBind.url(this.ivItemFragmentLibraryImage, viewModelPlayListItemBeanAlbum);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemFragmentLibraryCount, viewModelGetSongCount);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemFragmentLibraryTitle, viewModelPlayListItemBeanTitle);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}