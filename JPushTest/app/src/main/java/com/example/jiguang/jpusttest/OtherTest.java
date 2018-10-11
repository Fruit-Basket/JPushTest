package com.example.jiguang.jpusttest;

import android.content.Context;
import android.widget.TextView;

public class OtherTest {

    private Context context;
    private TextView log_tv;

    public OtherTest(Context context,TextView log_tv){
        this.context=context;
        this.log_tv=log_tv;
    }

    public void exe(int command){
        switch (command){
            case 0:
                test0();
                break;

            default:

        }
    }

    private void test0(){

    }

}
