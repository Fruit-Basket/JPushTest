package com.example.jiguang.jpusttest;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * 设备管理
 */
public class DeviceManagement {
    private static final String TAG="DeviceManagement";

    private Context context;
    private TextView log_tv;

    public DeviceManagement(Context context,TextView log_tv){
        this.context=context;
        this.log_tv=log_tv;
    }

    public void exe(int command){
        switch(command){
            case 0:
                getRegistrationID();
                break;

            case 1:
                setAlias();
                break;

            case 2:
                getAlias();
                break;

            case 3:
                setTag();
                break;

            case 4:
                getAllTags();
                break;

            case 5:
                checkTagBindState();
                break;

            default:
        }

    }


    /**
     * 获取JPush SDK所在的设备RegistrationID
     *
     * @return String
     */
    private String getRegistrationID(){
        Log.i(TAG,"testGetRegistrationID()");
        String registrationID= JPushInterface.getRegistrationID(context);
        log_tv.append("registration id: "+registrationID+"\n");
        Log.i(TAG,"registration id: "+registrationID+"\n");
        return registrationID;
    }

    /**
     * 为JPush SDK所在的设备设置alias
     */
    private void setAlias(){
        Log.i(TAG,"setAlias()");
        JPushInterface.setAlias(context,0, Condition.ALIAS_1);
    }

    /**
     * 查询别名
     */
    private void getAlias(){
        ///那里获得查询结果
        JPushInterface.getAlias(context,0);
    }

    /**
     * 为JPush SDK所在的设备设置TAG
     *
     * 某个标签名字无效的话，会导致整个标签设置无效
     * 单个设备最多支持设置 1000 个标签
     */
    private void setTag(){
        Log.i(TAG,"setTag()");
        Set<String> tags=new HashSet<>();
        tags.add(Condition.TAG_1);
        tags.add(Condition.TAG_2);
        tags.add(Condition.TAG_3);

        JPushInterface.addTags(context, 0,tags);
    }

    /**
     * 查询所有标签
     */
    private void getAllTags(){
        ///哪里获取查询结果
        JPushInterface.getAllTags(context,0);
    }

    /**
     * 查询指定标签与当前用户的绑定状态
     */
    private void checkTagBindState(){
        ///哪里获取查询结果
        JPushInterface.checkTagBindState(context,0,Condition.TAG_1);
    }

}
