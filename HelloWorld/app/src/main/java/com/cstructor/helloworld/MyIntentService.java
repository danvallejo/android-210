package com.cstructor.helloworld;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void addSong(Context context, String songName){
        Intent intent = new Intent(context, MyIntentService.class);

        intent.setAction("ADD-SONG");

        intent.putExtra("SONG-NAME", songName);

        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        switch (intent.getAction()){
            case "ADD-SONG":
            {
                String songName = intent.getStringExtra("SONG-NAME");
                Log.d("Add-Song-songName", songName);

                break;
            }
            case "DELETE-SONG":{
                break;
            }
        }
    }
}
