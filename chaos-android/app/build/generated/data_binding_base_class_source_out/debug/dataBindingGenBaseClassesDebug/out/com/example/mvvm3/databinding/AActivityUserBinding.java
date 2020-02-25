package com.example.mvvm3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.mvvm3.model.User;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class AActivityUserBinding extends ViewDataBinding {
  @NonNull
  public final Button btnSearch;

  @NonNull
  public final EditText etUsername;

  @NonNull
  public final TextView tvId;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final LinearLayout vContent;

  @NonNull
  public final FrameLayout vEmpty;

  @NonNull
  public final FrameLayout vError;

  @NonNull
  public final FrameLayout vLoading;

  @NonNull
  public final FrameLayout vRoot;

  @Bindable
  protected User mUser;

  protected AActivityUserBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button btnSearch, EditText etUsername, TextView tvId, TextView tvName, LinearLayout vContent,
      FrameLayout vEmpty, FrameLayout vError, FrameLayout vLoading, FrameLayout vRoot) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnSearch = btnSearch;
    this.etUsername = etUsername;
    this.tvId = tvId;
    this.tvName = tvName;
    this.vContent = vContent;
    this.vEmpty = vEmpty;
    this.vError = vError;
    this.vLoading = vLoading;
    this.vRoot = vRoot;
  }

  public abstract void setUser(@Nullable User user);

  @Nullable
  public User getUser() {
    return mUser;
  }

  @NonNull
  public static AActivityUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.a_activity_user, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static AActivityUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<AActivityUserBinding>inflateInternal(inflater, com.example.mvvm3.R.layout.a_activity_user, root, attachToRoot, component);
  }

  @NonNull
  public static AActivityUserBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.a_activity_user, null, false, component)
   */
  @NonNull
  @Deprecated
  public static AActivityUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<AActivityUserBinding>inflateInternal(inflater, com.example.mvvm3.R.layout.a_activity_user, null, false, component);
  }

  public static AActivityUserBinding bind(@NonNull View view) {
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
  public static AActivityUserBinding bind(@NonNull View view, @Nullable Object component) {
    return (AActivityUserBinding)bind(component, view, com.example.mvvm3.R.layout.a_activity_user);
  }
}
