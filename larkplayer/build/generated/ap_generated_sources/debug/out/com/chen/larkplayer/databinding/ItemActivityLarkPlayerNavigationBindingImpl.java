package com.chen.larkplayer.databinding;
import com.chen.larkplayer.R;
import com.chen.larkplayer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemActivityLarkPlayerNavigationBindingImpl extends ItemActivityLarkPlayerNavigationBinding  {

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
    // Inverse Binding Event Handlers

    public ItemActivityLarkPlayerNavigationBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private ItemActivityLarkPlayerNavigationBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            );
        this.ivItemActivityHomeNavigationImage.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemActivityHomeNavigationName.setTag(null);
        this.tvItemActivityHomeNavigationTimer.setTag(null);
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
            setViewModel((com.chen.larkplayer.bean.NavigationItemBean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.chen.larkplayer.bean.NavigationItemBean ViewModel) {
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
        boolean viewModelTimeInt0 = false;
        int viewModelResId = 0;
        int viewModelTime = 0;
        int viewModelTimeInt0ViewVISIBLEViewGONE = 0;
        java.lang.String viewModelName = null;
        com.chen.larkplayer.bean.NavigationItemBean viewModel = mViewModel;

        if ((dirtyFlags & 0x3L) != 0) {



                if (viewModel != null) {
                    // read viewModel.resId
                    viewModelResId = viewModel.getResId();
                    // read viewModel.time
                    viewModelTime = viewModel.getTime();
                    // read viewModel.name
                    viewModelName = viewModel.getName();
                }


                // read viewModel.time > 0
                viewModelTimeInt0 = (viewModelTime) > (0);
            if((dirtyFlags & 0x3L) != 0) {
                if(viewModelTimeInt0) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }


                // read viewModel.time > 0 ? View.VISIBLE : View.GONE
                viewModelTimeInt0ViewVISIBLEViewGONE = ((viewModelTimeInt0) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.example.commonlibrary.base.CommonBind.src(this.ivItemActivityHomeNavigationImage, viewModelResId);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemActivityHomeNavigationName, viewModelName);
            this.tvItemActivityHomeNavigationTimer.setVisibility(viewModelTimeInt0ViewVISIBLEViewGONE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel
        flag 1 (0x2L): null
        flag 2 (0x3L): viewModel.time > 0 ? View.VISIBLE : View.GONE
        flag 3 (0x4L): viewModel.time > 0 ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}