package com.chen.larkplayer.databinding;
import com.chen.larkplayer.R;
import com.chen.larkplayer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ViewFragmentLibraryHeaderBindingImpl extends ViewFragmentLibraryHeaderBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.iv_item_fragment_library_icon, 5);
        sViewsWithIds.put(R.id.iv_item_fragment_library_image, 6);
        sViewsWithIds.put(R.id.tv_view_fragment_library_header_list_number, 7);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    private OnClickListenerImpl mViewModelOnItemClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public ViewFragmentLibraryHeaderBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private ViewFragmentLibraryHeaderBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.RelativeLayout) bindings[1]
            , (android.widget.RelativeLayout) bindings[3]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[4]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlViewFragmentLibraryHeaderMusicContainer.setTag(null);
        this.rlViewFragmentLibraryHeaderVideoContainer.setTag(null);
        this.tvViewFragmentLibraryHeaderMusicCount.setTag(null);
        this.tvViewFragmentLibraryHeaderVideoCount.setTag(null);
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
            setViewModel((com.chen.larkplayer.mvvm.library.LibraryViewModel.HeaderData) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.chen.larkplayer.mvvm.library.LibraryViewModel.HeaderData ViewModel) {
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
        java.lang.String viewModelGetVideoCount = null;
        com.chen.larkplayer.mvvm.library.LibraryViewModel.HeaderData viewModel = mViewModel;
        java.lang.String viewModelGetMusicCount = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (viewModel != null) {
                    // read viewModel::onItemClick
                    viewModelOnItemClickAndroidViewViewOnClickListener = (((mViewModelOnItemClickAndroidViewViewOnClickListener == null) ? (mViewModelOnItemClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mViewModelOnItemClickAndroidViewViewOnClickListener).setValue(viewModel));
                    // read viewModel.getVideoCount()
                    viewModelGetVideoCount = viewModel.getVideoCount();
                    // read viewModel.getMusicCount()
                    viewModelGetMusicCount = viewModel.getMusicCount();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.rlViewFragmentLibraryHeaderMusicContainer.setOnClickListener(viewModelOnItemClickAndroidViewViewOnClickListener);
            this.rlViewFragmentLibraryHeaderVideoContainer.setOnClickListener(viewModelOnItemClickAndroidViewViewOnClickListener);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvViewFragmentLibraryHeaderMusicCount, viewModelGetMusicCount);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvViewFragmentLibraryHeaderVideoCount, viewModelGetVideoCount);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.chen.larkplayer.mvvm.library.LibraryViewModel.HeaderData value;
        public OnClickListenerImpl setValue(com.chen.larkplayer.mvvm.library.LibraryViewModel.HeaderData value) {
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