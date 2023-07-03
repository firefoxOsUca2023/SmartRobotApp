// Generated by data binding compiler. Do not edit!
package com.example.zacatales.smartrobotapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.zacatales.smartrobotapp.R;
import com.example.zacatales.smartrobotapp.view.RouteView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentRouteBinding extends ViewDataBinding {
  @NonNull
  public final FloatingActionButton actionDeleteRoute;

  @NonNull
  public final FloatingActionButton actionToPreviusRouteFragment;

  @NonNull
  public final RouteView routeView;

  @NonNull
  public final TextView textViewNameAppController;

  protected FragmentRouteBinding(Object _bindingComponent, View _root, int _localFieldCount,
      FloatingActionButton actionDeleteRoute, FloatingActionButton actionToPreviusRouteFragment,
      RouteView routeView, TextView textViewNameAppController) {
    super(_bindingComponent, _root, _localFieldCount);
    this.actionDeleteRoute = actionDeleteRoute;
    this.actionToPreviusRouteFragment = actionToPreviusRouteFragment;
    this.routeView = routeView;
    this.textViewNameAppController = textViewNameAppController;
  }

  @NonNull
  public static FragmentRouteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_route, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentRouteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentRouteBinding>inflateInternal(inflater, R.layout.fragment_route, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentRouteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_route, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentRouteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentRouteBinding>inflateInternal(inflater, R.layout.fragment_route, null, false, component);
  }

  public static FragmentRouteBinding bind(@NonNull View view) {
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
  public static FragmentRouteBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentRouteBinding)bind(component, view, R.layout.fragment_route);
  }
}
