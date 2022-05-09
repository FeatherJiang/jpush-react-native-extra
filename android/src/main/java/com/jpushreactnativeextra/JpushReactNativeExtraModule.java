package com.jpushreactnativeextra;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
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
        Log.i("IntentModule.log", "getExtras");
        Activity activity = getCurrentActivity();
        if (activity != null){
            Intent intent = activity.getIntent();
            if (intent != null){
                Log.d("push jump", "action" + intent.getAction());
                Log.d("push jump", "data" + intent.getDataString());
                Log.d("push jump", "scheme" + intent.getData());
                String dataSting = intent.getDataString();
                if (dataSting != null) {
                    JSONObject data = JSON.parseObject(dataSting);
                    WritableMap writableMap = Arguments.createMap();
                    for (String key : data.keySet()) {
                        writableMap.putString(key, data.getString(key));
                    }
                    for (String key : data.keySet()) {
                        Log.d("push jump", "data obj" + writableMap.getString(key));
                    }
                    promise.resolve(writableMap);
                } else {
                    promise.reject("jpush-react-native-extra", "data is null");
                }
            }else {
                promise.reject("jpush-react-native-extra", "intent is null");
            }
        }else {
            promise.reject("jpush-react-native-extra", "getCurrentActivity is null");
        }
    }

    public static native int nativeMultiply(int a, int b);
}
