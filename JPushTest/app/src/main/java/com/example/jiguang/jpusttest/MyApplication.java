package com.example.jiguang.jpusttest;

import android.app.Application;
import android.app.Notification;
import android.util.Log;

import cn.jiguang.api.JCoreInterface;
import cn.jpush.android.api.JPushInterface;

/**
 * App类
 *
 * 问题：
 * 1.收到的推送没有声音
 *
 */
public class MyApplication extends Application {
    private static final String TAG="MyApplication";

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i(TAG,"onCreate()");

        JCoreInterface.testCountryCode("us");
        JPushInterface.setDebugMode(true);

        //JPushInterface.init(getApplicationContext());//使用这个也可以
        JPushInterface.init(this);
    }
}
