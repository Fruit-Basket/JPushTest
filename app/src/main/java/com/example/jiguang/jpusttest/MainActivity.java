package com.example.jiguang.jpusttest;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;


public class MainActivity extends Activity {
    private static final String TAG="MainActivity";

    private Button link_state_b;

    private Button device_management_b;
    private Spinner device_management_command_s;
    private Button notification_mnangement_b;
    private Spinner notification_management_command_s;

    private Button test_b;
    private TextView test_e;

    private TextView log_tv;

    AnalyticsManagement analyticsManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate()");
        setContentView(R.layout.activity_main);

        initViews();

        analyticsManagement=new AnalyticsManagement(this);

        //申请权限
        JPushInterface.requestPermission(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"onResume()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG,"onStop()");
    }

    /**
     * 初始化界面控件
     */
    private void initViews(){
        Log.i(TAG,"initViews()");
        //先初始化log_tv控件
        log_tv=(TextView)findViewById(R.id.log_tv);
        View.OnClickListener listener=new MyOnClickListener();

        link_state_b=(Button)findViewById(R.id.link_state_b);
        link_state_b.setOnClickListener(listener);

        //设备管理
        device_management_b=(Button)findViewById(R.id.device_management_b);
        device_management_b.setOnClickListener(listener);
        device_management_command_s=(Spinner)findViewById(R.id.device_management_command_s);
        device_management_command_s.setSelection(0,true);

        //通知管理
        notification_mnangement_b=(Button)findViewById(R.id.notification_management_b);
        notification_mnangement_b.setOnClickListener(listener);
        notification_management_command_s=(Spinner)findViewById(R.id.notification_management_command_s);
        notification_management_command_s.setSelection(0,true);

        test_b=findViewById(R.id.test_b);
        test_b.setOnClickListener(listener);
        test_e=(TextView)findViewById(R.id.test_e);
    }




    private class MyOnClickListener implements View.OnClickListener{

        ConnectionManagement connectionManagement=
                new ConnectionManagement(
                        MainActivity.this,
                        log_tv
                );

        DeviceManagement deviceManagement=
                new DeviceManagement(
                        MainActivity.this,
                        log_tv
                );

        NotificationManagement notificationManagement=
                new NotificationManagement(
                        MainActivity.this,
                        log_tv
                );

        OtherTest otherTest=new OtherTest(MainActivity.this,log_tv);

        @Override
        public void onClick(View view) {
            Log.i(TAG, "onClick(View)");
            String command;

            switch (view.getId()) {
                case R.id.link_state_b:
                    connectionManagement.exe(0);
                    break;

                case R.id.device_management_b:
                    deviceManagement.exe(
                            device_management_command_s.getSelectedItemPosition()
                    );
                    break;

                case R.id.notification_management_b:
                    notificationManagement.exe(
                            notification_management_command_s.getSelectedItemPosition()
                    );
                    break;

                case R.id.test_b:
                    command=test_e.getText().toString().trim();
                    if(command!=null&& TextUtils.isEmpty(command)==false){
                        otherTest.exe(Integer.parseInt(command));
                    }
                    break;

                default:

            }
        }
    }
}
