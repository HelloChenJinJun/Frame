package com.chen.larkplayer.databinding;
import com.chen.larkplayer.R;
import com.chen.larkplayer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemFragmentLocalArtistListBindingImpl extends ItemFragmentLocalArtistListBinding  {

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

    public ItemFragmentLocalArtistListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private ItemFragmentLocalArtistListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemFragmentLocalArtistListCount.setTag(null);
        this.tvItemFragmentLocalArtistListName.setTag(null);
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
            setViewModel((com.chen.larkplayer.mvvm.localartist.LocalArtistItemViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.chen.larkplayer.mvvm.localartist.LocalArtistItemViewModel ViewModel) {
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
        com.example.commonlibrary.widget.manager.music.base.ArtistItemBean viewModelArtistItemBean = null;
        android.view.View.OnClickListener viewModelOnItemClickAndroidViewViewOnClickListener = null;
        java.lang.String viewModelGetCount = null;
        com.chen.larkplayer.mvvm.localartist.LocalArtistItemViewModel viewModel = mViewModel;
        java.lang.String viewModelArtistItemBeanName = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (viewModel != null) {
                    // read viewModel.artistItemBean
                    viewModelArtistItemBean = viewModel.artistItemBean;
                    // read viewModel::onItemClick
                    viewModelOnItemClickAndroidViewViewOnClickListener = (((mViewModelOnItemClickAndroidViewViewOnClickListener == null) ? (mViewModelOnItemClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mViewModelOnItemClickAndroidViewViewOnClickListener).setValue(viewModel));
                    // read viewModel.getCount()
                    viewModelGetCount = viewModel.getCount();
                }


                if (viewModelArtistItemBean != null) {
                    // read viewModel.artistItemBean.name
                    viewModelArtistItemBeanName = viewModelArtistItemBean.getName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(viewModelOnItemClickAndroidViewViewOnClickListener);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemFragmentLocalArtistListCount, viewModelGetCount);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemFragmentLocalArtistListName, viewModelArtistItemBeanName);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.chen.larkplayer.mvvm.localartist.LocalArtistItemViewModel value;
        public OnClickListenerImpl setValue(com.chen.larkplayer.mvvm.localartist.LocalArtistItemViewModel value) {
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