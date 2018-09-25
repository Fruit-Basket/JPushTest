package com.example.jiguang.jpusttest;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.MultiActionsNotificationBuilder;

/**
 * 自定义通知样式
 *
 */
public class CustomStyle {

    private Context context;

    public CustomStyle(Context context){
        this.context=context;
    }

    /**
     * 自定义通知样式
     */
    public void costomNotificationStyle(){

        //基础自定义
        BasicPushNotificationBuilder builder =
                new BasicPushNotificationBuilder(context);
        builder.statusBarDrawable = R.drawable.notification_icon_1;// 指定最顶层状态栏小图标
        JPushInterface.setPushNotificationBuilder(
                Condition.NOTIFICATION_STYLE_1,
                builder
        );

        builder=new BasicPushNotificationBuilder(context);
        builder.statusBarDrawable=R.drawable.notification_icon_2;
        JPushInterface.setPushNotificationBuilder(
                Condition.NOTIFICATION_STYLE_2,
                builder
        );

        ///Android 8.0以上这个通知不会静音，会有声音？
        builder=new BasicPushNotificationBuilder(context);
        builder.notificationDefaults = Notification.DEFAULT_VIBRATE;
        JPushInterface.setPushNotificationBuilder(
                Condition.NOTIFICATION_STYLE_3,
                builder
        );

        builder=new BasicPushNotificationBuilder(context);
        builder.notificationFlags= Notification.FLAG_AUTO_CANCEL;//自动消失
        JPushInterface.setPushNotificationBuilder(
                Condition.NOTIFICATION_STYLE_4,
                builder
        );

        //定制带按钮的Notification样式
        MultiActionsNotificationBuilder builder2 = new MultiActionsNotificationBuilder(context);
        //添加按钮，参数(按钮图片、按钮文字、扩展数据)
        builder2.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "first", "my_extra1");
        builder2.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "second", "my_extra2");
        builder2.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "third", "my_extra3");
        JPushInterface.setPushNotificationBuilder(Condition.NOTIFICATION_STYLE_5, builder);

        /*
        //高级自定义样式
        CustomPushNotificationBuilder builder3 = new
                CustomPushNotificationBuilder(context,
                R.layout.customer_notitfication_layout,// 指定定制的 Notification Layout
                R.id.icon,
                R.id.title,
                R.id.text);
        builder.statusBarDrawable = 0;// 指定最顶层状态栏小图标
        builder3.layoutIconDrawable = 0;// 指定下拉状态栏时显示的通知图标
        JPushInterface.setPushNotificationBuilder(Condition.NOTIFICATION_STYLE_6, builder3);
        */
    }

    public void test(){
    }
}
