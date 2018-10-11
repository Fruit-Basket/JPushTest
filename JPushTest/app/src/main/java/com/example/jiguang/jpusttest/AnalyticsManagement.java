package com.example.jiguang.jpusttest;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;

/**
 * 统计管理
 */
public class AnalyticsManagement {

    private static final String TAG="AnalyticsManagement";

    private Context context;
    private TextView log_tv;

    public AnalyticsManagement(Context context){
        this(context,null);
    }

    public AnalyticsManagement(Context context,TextView log_tv){
        this.context=context;
        this.log_tv=log_tv;
    }

    /**
     * 在Activity.onResume()中调用
     */
    public void onResume(){
        Log.i(TAG,"onResume()");
        /**
         * 用于“用户使用时长”、“活跃用户”和“用户打开次数”的统计
         *
         * 统计所在位置
         * 用户使用时长：极光开发者服务->用户统计->活跃分析->平均使用时长
         * 活跃用户：极光开发者服务->用户统计->用户概况->活跃用户
         * 用户打开次数：极光开发者服务->用户统计->活跃分析->用户打开次数
         */
        JPushInterface.onResume(context);
        ///为何我没有实现统计分析API时，也有这些统计数据的？
    }

    /**
     * 在Activity.onStop()中调用
     */
    public void onStop(){
        Log.i(TAG,"onStop()");
        JPushInterface.onResume(context);
    }

    /**
     * SDK通过Thread.UncaughtExceptionHandler 捕获程序崩溃日志，并在程序奔溃时实时上报如果实时上报失败则会在程序下次启动时发送到服务器。
     *
     * 默认为开启状态
     */
    public void initCrashHandler() {
        Log.i(TAG,"initCrashHandler()");
        JPushInterface.initCrashHandler(context);
    }

    /**
     * 停止
     */
    public void stopCrashHandler(){
        Log.i(TAG,"stopCrashHandler()");
        JPushInterface.stopCrashHandler(context);
    }



}
