package com.example.jiguang.jpusttest;

import android.util.Log;

import cn.jpush.android.service.JPushMessageReceiver;

/**
 * 别名、标签和电话相关事件处理
 */
public class BindStateReceiver extends JPushMessageReceiver {

    private static final String TAG="BindStateReceiver";

    @Override
    public void onTagOperatorResult(android.content.Context context, cn.jpush.android.api.JPushMessage jPushMessage) {
        Log.i(TAG,"onTagOperatorResult(): 标签操作结果");
        Log.i(TAG,"标签: "+jPushMessage.getTags());
    }

    @Override
    public void onCheckTagOperatorResult(android.content.Context context, cn.jpush.android.api.JPushMessage jPushMessage) {
        Log.i(TAG,"onCheckTagOperatorResult()：检查标签操作结果");
        Log.i(TAG,"结果："+jPushMessage.getTagCheckStateResult());
        Log.i(TAG,"结果："+jPushMessage.getCheckTag());

        //jPushMessage.getTagCheckStateResult();//开发者想要查询的tag与当前用户绑定的状态
        //jPushMessage.getCheckTag();//开发者想要查询绑定状态的tag
    }

    @Override
    public void onAliasOperatorResult(android.content.Context context, cn.jpush.android.api.JPushMessage jPushMessage){
        Log.i(TAG,"onAliasOperatorResult()：别名操作结果");
        Log.i(TAG,"别名："+jPushMessage.getAlias());
        Log.i(TAG,"jPushMessage.getErrorCode()=="+jPushMessage.getErrorCode());
    }

    @Override
    public void onMobileNumberOperatorResult(android.content.Context context, cn.jpush.android.api.JPushMessage jPushMessage){
        Log.i(TAG,"onMobileNumberOperatorResult()：电话号码结果");
        Log.i(TAG,"结果："+jPushMessage.getMobileNumber());



    }


}
