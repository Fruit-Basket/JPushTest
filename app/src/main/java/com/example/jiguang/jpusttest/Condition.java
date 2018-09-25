package com.example.jiguang.jpusttest;

public class Condition {
    private static final Condition instance=new Condition();

    public static final boolean isDebug=false;

    //通知样式
    public static final int NOTIFICATION_STYLE_1=1;
    public static final int NOTIFICATION_STYLE_2=2;
    public static final int NOTIFICATION_STYLE_3=3;
    public static final int NOTIFICATION_STYLE_4=4;
    public static final int NOTIFICATION_STYLE_5=5;
    public static final int NOTIFICATION_STYLE_6=6;

    //别名
    public static final String ALIAS_1="alias_1";
    public static final String ALIAS_2="alias_2";
    public static final String ALIAS_3="alias_3";

    //标签
    public static final String TAG_1="tag_1";
    public static final String TAG_2="tag_2";
    public static final String TAG_3="tag_3";

    private Condition(){}

    public Condition getInstance(){
        return instance;
    }

}

