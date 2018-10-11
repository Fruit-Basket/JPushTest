package com.example.jiguang.jpusttest;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

//import com.huawei.hms.update.provider.UpdateProvider;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * 点击华为厂商通道推送的App通知时，跳转到的Activity
 */
public class HuaweiActivity extends Activity {
    private static final String TAG="HuaweiActivity";

    /**消息Id**/
    private static final String KEY_MSGID = "msg_id";
    /**该通知的下发通道**/
    private static final String KEY_WHICH_PUSH_SDK = "rom_type";
    /**通知标题**/
    private static final String KEY_TITLE = "n_title";
    /**通知内容**/
    private static final String KEY_CONTENT = "n_content";
    /**通知附加字段**/
    private static final String KEY_EXTRAS = "n_extras";

    private TextView jsonContent;

    //private UpdateProvider test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
        setContentView(R.layout.activity_huawei);

        jsonContent=(TextView)findViewById(R.id.jsonContent);

        handleOpenClick();
    }

    /**
     * 处理点击事件，当前启动配置的Activity 都是使用
     * Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
     * 方式启动，只需要在 onCreat 中调用此方法进行处理
     */
    private void handleOpenClick() {
        Log.i(TAG, "handleOpenClick()");

        if (getIntent().getData() == null){
            return;
        }

        String data = getIntent().getData().toString();
        Log.w(TAG, "msg content is " + String.valueOf(data));


        if (TextUtils.isEmpty(data)) {
            return;
        }

        //解释Json字符串
        try {
            JSONObject jsonObject = new JSONObject(data);

            String msgId = jsonObject.optString(KEY_MSGID);
            byte whichPushSDK = (byte) jsonObject.optInt(KEY_WHICH_PUSH_SDK);
            String title = jsonObject.optString(KEY_TITLE);
            String content = jsonObject.optString(KEY_CONTENT);
            String extras = jsonObject.optString(KEY_EXTRAS);

            StringBuilder sb = new StringBuilder();
            sb.append("msgId:");
            sb.append(String.valueOf(msgId));
            sb.append("\n");
            sb.append("title:");
            sb.append(String.valueOf(title));
            sb.append("\n");
            sb.append("content:");
            sb.append(String.valueOf(content));
            sb.append("\n");
            sb.append("extras:");
            sb.append(String.valueOf(extras));
            sb.append("\n");
            sb.append("platform:");
            sb.append(getPushSDKName(whichPushSDK));

            jsonContent.setText(sb.toString());

            //上报点击事件
            ///如何查询这个统计？
            JPushInterface.reportNotificationOpened(this, msgId, whichPushSDK);
            Log.i(TAG,"reportNotificationOpend() finished");

        } catch (JSONException e) {
            Log.w(TAG, "parse notification error");
        }
    }

    /**
     * 根据Json中的rom_type返回对应的名字
     * @param whichPushSDK
     * @return
     */
    private String getPushSDKName(byte whichPushSDK) {
        String name;
        switch (whichPushSDK){
            case 0:
                name = "jpush";
                break;
                case 1:
                    name = "xiaomi";
                    break;
                    case 2:
                        name = "huawei";
                        break;
                        case 3:
                            name = "meizu";
                            break;
                            case 8:
                                name = "fcm";
                                break;
                                default:
                                    name = "jpush";
        }
        return name;
    }
}
