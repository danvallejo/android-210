package com.cstructor.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyReceiver", "onReceive");

        Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
    }
}
