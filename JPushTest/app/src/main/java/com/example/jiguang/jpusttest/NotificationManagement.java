package com.example.jiguang.jpusttest;

import android.content.Context;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;

/**
 * 通知管理
 */
public class NotificationManagement {

    private static final String TAG="NotificationManagement";

    private Context context;
    private TextView log_tv;

    public NotificationManagement(Context context,TextView log_tv){
        this.context=context;
        this.log_tv=log_tv;
    }

    public void exe(int command){
        switch(command){
            case 0://发出本地通知
                localNotification();
                break;

            case 1://清除所有本地通知
                clearLocalNotifications();
                break;

            case 2://清除所有通知
                clearAllNotifications();
                break;

            case 3:
                break;

            case 4:
                break;


            default:
        }

    }

    /**
     * 发送本地通知
     */
    private void localNotification(){
        JPushLocalNotification ln = new JPushLocalNotification();//创建本地通知对象

        //设置推送的基本信息
        ln.setBuilderId(0);//指定本地通知的样式
        ln.setTitle("本地通知：标题");
        ln.setContent("本地通知：内容");
        ln.setNotificationId(123) ;//指定本地通知ID
        ln.setBroadcastTime(System.currentTimeMillis() + 1000*30);//本地通知的时间

            /*try {
                ln.setBroadcastTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .parse("2018-5-24 15:15:00"));
            } catch (ParseException e) {
                e.printStackTrace();
            }*/

        //给推送添加额外信息
        Map<String , Object> map = new HashMap<String, Object>() ;
        map.put("name", "jpush local notification") ;
        JSONObject json = new JSONObject(map) ;
        ln.setExtras(json.toString()) ;//设置额外的数据信息,extras为json字符

        JPushInterface.addLocalNotification(context, ln);
    }

    /**
     * 清除所有本地通知
     */
    private void clearLocalNotifications(){
        JPushInterface.clearLocalNotifications (context);
    }

    /**
     * 清除所有通知
     *
     * 1.不会清除本地通知
     * 2.不会清除华为推送的通知
     */
    private void clearAllNotifications(){
        JPushInterface.clearAllNotifications(context);
        log_tv.append("clearAllNotifications()\n");
    }

    /**
     *
     */
    private void clearNotificationById(){
        //JPushInterface.clearNotificationById(context,);
    }

    /**
     * 设置接受推送的时间
     *
     * SDK会把这个时间段以外收到的推送扔掉
     */
    private void setPushTime(){
        Set<Integer> days = new HashSet<Integer>();
        days.add(1);
        days.add(2);
        days.add(3);
        days.add(4);
        days.add(5);
        JPushInterface.setPushTime(context, days, 9, 19);
        log_tv.append("setPushTime()\n");
    }

    /**
     * 设置通知免打扰时段
     */
    private void setSilenceTime(){
        JPushInterface.setSilenceTime(context,23,30,7,40);
        log_tv.append("setSilenceTime()\n");
    }

    /**
     * 设置客户端保留的最大通知条数
     */
    private void setLatestNotificationNumber(){
        JPushInterface.setLatestNotificationNumber(context,5);
    }
}
