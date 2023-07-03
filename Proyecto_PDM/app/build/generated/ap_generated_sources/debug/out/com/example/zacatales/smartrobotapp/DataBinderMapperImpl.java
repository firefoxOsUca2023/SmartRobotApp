package com.example.zacatales.smartrobotapp;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.zacatales.smartrobotapp.databinding.ActivityMainBindingImpl;
import com.example.zacatales.smartrobotapp.databinding.FragmentBluetoothBindingImpl;
import com.example.zacatales.smartrobotapp.databinding.FragmentControllersBindingImpl;
import com.example.zacatales.smartrobotapp.databinding.FragmentRouteBindingImpl;
import com.example.zacatales.smartrobotapp.databinding.FragmentWelcomeBindingImpl;
import com.example.zacatales.smartrobotapp.databinding.ItemDeviceBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYMAIN = 1;

  private static final int LAYOUT_FRAGMENTBLUETOOTH = 2;

  private static final int LAYOUT_FRAGMENTCONTROLLERS = 3;

  private static final int LAYOUT_FRAGMENTROUTE = 4;

  private static final int LAYOUT_FRAGMENTWELCOME = 5;

  private static final int LAYOUT_ITEMDEVICE = 6;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(6);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.zacatales.smartrobotapp.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.zacatales.smartrobotapp.R.layout.fragment_bluetooth, LAYOUT_FRAGMENTBLUETOOTH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.zacatales.smartrobotapp.R.layout.fragment_controllers, LAYOUT_FRAGMENTCONTROLLERS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.zacatales.smartrobotapp.R.layout.fragment_route, LAYOUT_FRAGMENTROUTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.zacatales.smartrobotapp.R.layout.fragment_welcome, LAYOUT_FRAGMENTWELCOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.zacatales.smartrobotapp.R.layout.item_device, LAYOUT_ITEMDEVICE);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBLUETOOTH: {
          if ("layout/fragment_bluetooth_0".equals(tag)) {
            return new FragmentBluetoothBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_bluetooth is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCONTROLLERS: {
          if ("layout/fragment_controllers_0".equals(tag)) {
            return new FragmentControllersBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_controllers is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTROUTE: {
          if ("layout/fragment_route_0".equals(tag)) {
            return new FragmentRouteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_route is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTWELCOME: {
          if ("layout/fragment_welcome_0".equals(tag)) {
            return new FragmentWelcomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_welcome is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMDEVICE: {
          if ("layout/item_device_0".equals(tag)) {
            return new ItemDeviceBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_device is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "fragment");
      sKeys.put(2, "isLightsActionButtonPressed");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(6);

    static {
      sKeys.put("layout/activity_main_0", com.example.zacatales.smartrobotapp.R.layout.activity_main);
      sKeys.put("layout/fragment_bluetooth_0", com.example.zacatales.smartrobotapp.R.layout.fragment_bluetooth);
      sKeys.put("layout/fragment_controllers_0", com.example.zacatales.smartrobotapp.R.layout.fragment_controllers);
      sKeys.put("layout/fragment_route_0", com.example.zacatales.smartrobotapp.R.layout.fragment_route);
      sKeys.put("layout/fragment_welcome_0", com.example.zacatales.smartrobotapp.R.layout.fragment_welcome);
      sKeys.put("layout/item_device_0", com.example.zacatales.smartrobotapp.R.layout.item_device);
    }
  }
}
