package com.cstructor.helloworld;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class UIThreadHandler extends Handler {
    public static final int MSG_PROCESS = 1;
    private TextView textView;

    public UIThreadHandler(TextView textView){
        this.textView = textView;
    }

    @Override
    public void handleMessage(Message msg){
        switch (msg.what){
            case MSG_PROCESS:{
                textView.setText(Integer.toString(msg.arg1));
                Log.d("UIhreadHandler", "MSG_PROCESS");
                break;
            }
        }
    }
}
