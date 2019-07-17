// Generated by data binding compiler. Do not edit!
package com.chen.larkplayer.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.chen.larkplayer.R;
import com.chen.larkplayer.mvvm.localvideo.LocalVideoItemViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemFragmentLocalVideoListBinding extends ViewDataBinding {
  @NonNull
  public final ImageView ivItemFragmentLocalVideoListImage;

  @NonNull
  public final ImageView ivItemFragmentLocalVideoListMore;

  @NonNull
  public final TextView tvItemFragmentLocalVideoListSize;

  @NonNull
  public final TextView tvItemFragmentLocalVideoListTime;

  @NonNull
  public final TextView tvItemFragmentLocalVideoListTitle;

  @Bindable
  protected LocalVideoItemViewModel mViewModel;

  protected ItemFragmentLocalVideoListBinding(Object _bindingComponent, View _root,
      int _localFieldCount, ImageView ivItemFragmentLocalVideoListImage,
      ImageView ivItemFragmentLocalVideoListMore, TextView tvItemFragmentLocalVideoListSize,
      TextView tvItemFragmentLocalVideoListTime, TextView tvItemFragmentLocalVideoListTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ivItemFragmentLocalVideoListImage = ivItemFragmentLocalVideoListImage;
    this.ivItemFragmentLocalVideoListMore = ivItemFragmentLocalVideoListMore;
    this.tvItemFragmentLocalVideoListSize = tvItemFragmentLocalVideoListSize;
    this.tvItemFragmentLocalVideoListTime = tvItemFragmentLocalVideoListTime;
    this.tvItemFragmentLocalVideoListTitle = tvItemFragmentLocalVideoListTitle;
  }

  public abstract void setViewModel(@Nullable LocalVideoItemViewModel viewModel);

  @Nullable
  public LocalVideoItemViewModel getViewModel() {
    return mViewModel;
  }

  @NonNull
  public static ItemFragmentLocalVideoListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_fragment_local_video_list, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemFragmentLocalVideoListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemFragmentLocalVideoListBinding>inflateInternal(inflater, R.layout.item_fragment_local_video_list, root, attachToRoot, component);
  }

  @NonNull
  public static ItemFragmentLocalVideoListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_fragment_local_video_list, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemFragmentLocalVideoListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemFragmentLocalVideoListBinding>inflateInternal(inflater, R.layout.item_fragment_local_video_list, null, false, component);
  }

  public static ItemFragmentLocalVideoListBinding bind(@NonNull View view) {
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
  public static ItemFragmentLocalVideoListBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ItemFragmentLocalVideoListBinding)bind(component, view, R.layout.item_fragment_local_video_list);
  }
}
