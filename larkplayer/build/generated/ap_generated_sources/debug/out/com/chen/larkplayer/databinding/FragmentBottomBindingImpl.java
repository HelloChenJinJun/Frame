package com.chen.larkplayer.databinding;
import com.chen.larkplayer.R;
import com.chen.larkplayer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentBottomBindingImpl extends FragmentBottomBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.iv_fragment_bottom_image, 5);
        sViewsWithIds.put(R.id.tv_fragment_bottom_title, 6);
        sViewsWithIds.put(R.id.tv_fragment_bottom_desc, 7);
        sViewsWithIds.put(R.id.sb_fragment_bottom_seek, 8);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    private OnClickListenerImpl mViewModelOnClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentBottomBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private FragmentBottomBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[2]
            , (com.chen.larkplayer.widget.RoundAngleImageView) bindings[5]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.SeekBar) bindings[8]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[6]
            );
        this.ivFragmentBottomFavorite.setTag(null);
        this.ivFragmentBottomNext.setTag(null);
        this.ivFragmentBottomPlay.setTag(null);
        this.llFragmentBottomBottomContainer.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
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
            setViewModel((com.chen.larkplayer.mvvm.bottom.BottomViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.chen.larkplayer.mvvm.bottom.BottomViewModel ViewModel) {
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
        android.view.View.OnClickListener viewModelOnClickAndroidViewViewOnClickListener = null;
        com.chen.larkplayer.mvvm.bottom.BottomViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x3L) != 0) {



                if (viewModel != null) {
                    // read viewModel::onClick
                    viewModelOnClickAndroidViewViewOnClickListener = (((mViewModelOnClickAndroidViewViewOnClickListener == null) ? (mViewModelOnClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mViewModelOnClickAndroidViewViewOnClickListener).setValue(viewModel));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.ivFragmentBottomFavorite.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.ivFragmentBottomNext.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.ivFragmentBottomPlay.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.llFragmentBottomBottomContainer.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.chen.larkplayer.mvvm.bottom.BottomViewModel value;
        public OnClickListenerImpl setValue(com.chen.larkplayer.mvvm.bottom.BottomViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onClick(arg0); 
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