// Generated by data binding compiler. Do not edit!
package com.chen.larkplayer.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.chen.larkplayer.R;
import com.chen.larkplayer.mvvm.library.LibraryViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ViewFragmentLibraryHeaderBinding extends ViewDataBinding {
  @NonNull
  public final ImageView ivItemFragmentLibraryIcon;

  @NonNull
  public final ImageView ivItemFragmentLibraryImage;

  @NonNull
  public final RelativeLayout rlViewFragmentLibraryHeaderMusicContainer;

  @NonNull
  public final RelativeLayout rlViewFragmentLibraryHeaderVideoContainer;

  @NonNull
  public final TextView tvViewFragmentLibraryHeaderListNumber;

  @NonNull
  public final TextView tvViewFragmentLibraryHeaderMusicCount;

  @NonNull
  public final TextView tvViewFragmentLibraryHeaderVideoCount;

  @Bindable
  protected LibraryViewModel.HeaderData mViewModel;

  protected ViewFragmentLibraryHeaderBinding(Object _bindingComponent, View _root,
      int _localFieldCount, ImageView ivItemFragmentLibraryIcon,
      ImageView ivItemFragmentLibraryImage,
      RelativeLayout rlViewFragmentLibraryHeaderMusicContainer,
      RelativeLayout rlViewFragmentLibraryHeaderVideoContainer,
      TextView tvViewFragmentLibraryHeaderListNumber,
      TextView tvViewFragmentLibraryHeaderMusicCount,
      TextView tvViewFragmentLibraryHeaderVideoCount) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ivItemFragmentLibraryIcon = ivItemFragmentLibraryIcon;
    this.ivItemFragmentLibraryImage = ivItemFragmentLibraryImage;
    this.rlViewFragmentLibraryHeaderMusicContainer = rlViewFragmentLibraryHeaderMusicContainer;
    this.rlViewFragmentLibraryHeaderVideoContainer = rlViewFragmentLibraryHeaderVideoContainer;
    this.tvViewFragmentLibraryHeaderListNumber = tvViewFragmentLibraryHeaderListNumber;
    this.tvViewFragmentLibraryHeaderMusicCount = tvViewFragmentLibraryHeaderMusicCount;
    this.tvViewFragmentLibraryHeaderVideoCount = tvViewFragmentLibraryHeaderVideoCount;
  }

  public abstract void setViewModel(@Nullable LibraryViewModel.HeaderData viewModel);

  @Nullable
  public LibraryViewModel.HeaderData getViewModel() {
    return mViewModel;
  }

  @NonNull
  public static ViewFragmentLibraryHeaderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.view_fragment_library_header, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ViewFragmentLibraryHeaderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ViewFragmentLibraryHeaderBinding>inflateInternal(inflater, R.layout.view_fragment_library_header, root, attachToRoot, component);
  }

  @NonNull
  public static ViewFragmentLibraryHeaderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.view_fragment_library_header, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ViewFragmentLibraryHeaderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ViewFragmentLibraryHeaderBinding>inflateInternal(inflater, R.layout.view_fragment_library_header, null, false, component);
  }

  public static ViewFragmentLibraryHeaderBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ViewFragmentLibraryHeaderBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ViewFragmentLibraryHeaderBinding)bind(component, view, R.layout.view_fragment_library_header);
  }
}
