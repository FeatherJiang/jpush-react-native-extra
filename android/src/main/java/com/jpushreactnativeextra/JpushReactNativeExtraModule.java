package com.jpushreactnativeextra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = JpushReactNativeExtraModule.NAME)
public class JpushReactNativeExtraModule extends ReactContextBaseJavaModule {
    public static final String NAME = "JpushReactNativeExtra";

    public JpushReactNativeExtraModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    public void getExtras(Promise promise){
        Activity activity = getCurrentActivity();
        if (activity != null){
            Intent intent = activity.getIntent();
            if (intent != null){
                String dataSting = intent.getDataString();
                Bundle extras = intent.getExtras();
                if (extras != null){
                  extras.remove("profile");
                  Log.d("pushExtras1", extras.toString());
                  WritableMap writableMap = Arguments.fromBundle(extras);
                  Log.d("pushExtras2", writableMap.toString());
                  promise.resolve(writableMap);
                  return;
                }
                if (dataSting != null) {
                    JSONObject data = JSON.parseObject(dataSting);
                    WritableMap writableMap = Arguments.createMap();
                    for (String key : data.keySet()) {
                        writableMap.putString(key, data.getString(key));
                    }
                    Log.d("pushExtras3", writableMap.toString());
                    promise.resolve(writableMap);
                    return;
                }
                promise.reject("jpush-react-native-extra", "data or extras is null");
            }else {
                promise.reject("jpush-react-native-extra", "intent is null");
            }
        }else {
            promise.reject("jpush-react-native-extra", "getCurrentActivity is null");
        }
    }
}
