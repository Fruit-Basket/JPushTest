package com.example.jiguang.jpusttest;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;

/**
 * 连接管理
 */
public class ConnectionManagement {

    private Context context;
    private TextView log_tv;

    public ConnectionManagement(Context context,TextView log_tv){
        this.context=context;
        this.log_tv=log_tv;
    }

    public void exe(int command){
        switch(command){
            case 0:
                linkState();
                break;

            case 1:
                isPushStopped();
                break;

            default:
        }

    }

    private void linkState(){
        log_tv.append("长连接状态："+JPushInterface.getConnectionState(context)+"\n");
    }

    /**
     * 是否停止推送
     */
    private void isPushStopped(){
        if(JPushInterface.isPushStopped(context)){
            log_tv.append("停止推送：是\n");
        }
        else{
            log_tv.append("停止推送：否\n");
        }
    }

    /**
     * 停止推送
     *
     * 这是一个完全本地的状态操作：停止推送的状态不会保存到服务器上
     */
    private void stopPush(){
        JPushInterface.stopPush(context);
        log_tv.append("停止推送\n");
    }

    /**
     * 恢复推送
     */
    private void resumePush(){
        JPushInterface.resumePush(context);
        log_tv.append("恢复推送\n");
    }


}
