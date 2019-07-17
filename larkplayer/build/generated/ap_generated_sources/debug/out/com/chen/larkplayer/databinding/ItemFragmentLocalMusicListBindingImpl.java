package com.chen.larkplayer.databinding;
import com.chen.larkplayer.R;
import com.chen.larkplayer.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemFragmentLocalMusicListBindingImpl extends ItemFragmentLocalMusicListBinding  {

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

    public ItemFragmentLocalMusicListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private ItemFragmentLocalMusicListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.chen.larkplayer.widget.EqualizerView) bindings[2]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[1]
            );
        this.evItemFragmentLocalMusicListView.setTag(null);
        this.ivItemFragmentLocalMusicListFavorite.setTag(null);
        this.ivItemFragmentLocalMusicListMore.setTag(null);
        this.ivItemFragmentLocalMusicListShare.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvItemFragmentLocalMusicListDesc.setTag(null);
        this.tvItemFragmentLocalMusicListTitle.setTag(null);
        this.tvItemFragmentLocalMusicSort.setTag(null);
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
            setViewModel((com.chen.larkplayer.mvvm.localmusic.LocalMusicItemViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.chen.larkplayer.mvvm.localmusic.LocalMusicItemViewModel ViewModel) {
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
        java.lang.String viewModelGetDesc = null;
        int viewModelIsIdelViewGONEViewVISIBLE = 0;
        java.lang.String viewModelMusicPlayBeanSongName = null;
        int viewModelGetPositionInt1 = 0;
        boolean viewModelIsIdel = false;
        android.view.View.OnClickListener viewModelOnItemClickAndroidViewViewOnClickListener = null;
        int viewModelGetPosition = 0;
        int viewModelIsIdelViewVISIBLEViewGONE = 0;
        java.lang.String stringValueOfViewModelGetPositionInt1 = null;
        com.example.commonlibrary.widget.manager.music.base.MusicPlayBean viewModelMusicPlayBean = null;
        com.chen.larkplayer.mvvm.localmusic.LocalMusicItemViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x3L) != 0) {



                if (viewModel != null) {
                    // read viewModel.getDesc()
                    viewModelGetDesc = viewModel.getDesc();
                    // read viewModel.isIdel()
                    viewModelIsIdel = viewModel.isIdel();
                    // read viewModel::onItemClick
                    viewModelOnItemClickAndroidViewViewOnClickListener = (((mViewModelOnItemClickAndroidViewViewOnClickListener == null) ? (mViewModelOnItemClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mViewModelOnItemClickAndroidViewViewOnClickListener).setValue(viewModel));
                    // read viewModel.getPosition()
                    viewModelGetPosition = viewModel.getPosition();
                    // read viewModel.musicPlayBean
                    viewModelMusicPlayBean = viewModel.musicPlayBean;
                }
            if((dirtyFlags & 0x3L) != 0) {
                if(viewModelIsIdel) {
                        dirtyFlags |= 0x8L;
                        dirtyFlags |= 0x20L;
                }
                else {
                        dirtyFlags |= 0x4L;
                        dirtyFlags |= 0x10L;
                }
            }


                // read viewModel.isIdel() ? View.GONE : View.VISIBLE
                viewModelIsIdelViewGONEViewVISIBLE = ((viewModelIsIdel) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read viewModel.isIdel() ? View.VISIBLE : View.GONE
                viewModelIsIdelViewVISIBLEViewGONE = ((viewModelIsIdel) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                // read (viewModel.getPosition()) + (1)
                viewModelGetPositionInt1 = (viewModelGetPosition) + (1);
                if (viewModelMusicPlayBean != null) {
                    // read viewModel.musicPlayBean.songName
                    viewModelMusicPlayBeanSongName = viewModelMusicPlayBean.getSongName();
                }


                // read String.valueOf((viewModel.getPosition()) + (1))
                stringValueOfViewModelGetPositionInt1 = java.lang.String.valueOf(viewModelGetPositionInt1);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.evItemFragmentLocalMusicListView.setVisibility(viewModelIsIdelViewGONEViewVISIBLE);
            this.ivItemFragmentLocalMusicListFavorite.setOnClickListener(viewModelOnItemClickAndroidViewViewOnClickListener);
            this.ivItemFragmentLocalMusicListMore.setOnClickListener(viewModelOnItemClickAndroidViewViewOnClickListener);
            this.ivItemFragmentLocalMusicListShare.setOnClickListener(viewModelOnItemClickAndroidViewViewOnClickListener);
            this.mboundView0.setOnClickListener(viewModelOnItemClickAndroidViewViewOnClickListener);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemFragmentLocalMusicListDesc, viewModelGetDesc);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemFragmentLocalMusicListTitle, viewModelMusicPlayBeanSongName);
            this.tvItemFragmentLocalMusicSort.setVisibility(viewModelIsIdelViewVISIBLEViewGONE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvItemFragmentLocalMusicSort, stringValueOfViewModelGetPositionInt1);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.chen.larkplayer.mvvm.localmusic.LocalMusicItemViewModel value;
        public OnClickListenerImpl setValue(com.chen.larkplayer.mvvm.localmusic.LocalMusicItemViewModel value) {
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
        flag 2 (0x3L): viewModel.isIdel() ? View.GONE : View.VISIBLE
        flag 3 (0x4L): viewModel.isIdel() ? View.GONE : View.VISIBLE
        flag 4 (0x5L): viewModel.isIdel() ? View.VISIBLE : View.GONE
        flag 5 (0x6L): viewModel.isIdel() ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}