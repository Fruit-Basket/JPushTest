package com.example.jiguang.jpusttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/**
 * 用于监听JPush SDK发出的消息相关广播
 */
public class MessageReceiver extends BroadcastReceiver {
    private static final String TAG="MessageReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"onReceive(Context,Intent)");

        Bundle bundle = intent.getExtras();

        //判断JPush SDK发出的Action类型，根据不同的Action类型做不同的处理
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "接收Registration Id : " + regId);

        }
        else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {

            StringBuilder stringBuilder=new StringBuilder("接收到自定义消息：\n");
            stringBuilder.append("消息ID："+bundle.getString(JPushInterface.EXTRA_MSG_ID)+"\n");
            stringBuilder.append("标题："+bundle.getString(JPushInterface.EXTRA_TITLE)+"\n");
            stringBuilder.append("内容："+bundle.getString(JPushInterface.EXTRA_MESSAGE)+"\n");
            stringBuilder.append("附加字段："+bundle.getString(JPushInterface.EXTRA_EXTRA)+"\n");

            Log.d(TAG,stringBuilder.toString());
        }
        else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {

            StringBuilder stringBuilder=new StringBuilder("收到了通知:\n");
            stringBuilder.append("消息ID："+bundle.getString(JPushInterface.EXTRA_MSG_ID)+"\n");
            stringBuilder.append("标题："+bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE)+"\n");
            stringBuilder.append("内容："+bundle.getString(JPushInterface.EXTRA_ALERT)+"\n");
            stringBuilder.append("附加字段："+bundle.getString(JPushInterface.EXTRA_EXTRA));

            stringBuilder.append("Notification id: "+bundle.getString(JPushInterface.EXTRA_NOTIFICATION_ID));

            //还可以从bundle中获取更多的字段

            Log.d(TAG,stringBuilder.toString());

        }
        else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "点击打开通知");
            // 在这里可以自己写代码去定义用户点击后的行为

            //测试
            if(bundle.getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA)!=null){
                Log.i(TAG,bundle.getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA));
            }
            else{
                Log.w(TAG,"bundle.getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA)==null");
            }

            //用于上报用户自定义消息被展示等客户端需要统计的事件
            JPushInterface.reportNotificationOpened(context,bundle.getString(JPushInterface.EXTRA_MSG_ID));

            Intent openIntent=new Intent(context,MainActivity.class);
            openIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(openIntent);

        }
        else if(JPushInterface.ACTION_NOTIFICATION_CLICK_ACTION.equals(intent.getAction())){
            Log.d(TAG,"用户点击了通知栏中自定义的按钮");
        }
        else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())){

            Boolean isConnect=bundle.getBoolean(JPushInterface.EXTRA_CONNECTION_CHANGE);

            StringBuilder stringBuilder=new StringBuilder("与JPush服务器连接的状态发生变化:");
            if(isConnect==true){
                stringBuilder.append("连接成功");
            }
            else{
                stringBuilder.append("连接断开");
            }
            Log.i(TAG,stringBuilder.toString());
        }
        else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
}
