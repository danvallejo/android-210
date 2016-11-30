package com.cstructor.helloworld;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(MyService.class.toString(), "onCreate");

        Runnable runner = new Runnable() {
            @Override
            public void run() {
                long id = Thread.currentThread().getId();
                Log.d("Runnable ThreadID=", Long.toString(id));

                try {
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(1000);
                        Log.d(MyService.class.toString(), "run()");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stopSelf();

            }
        };

        new Thread(runner).start();
        // runner.run(); <- no new thread

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(MyService.class.toString(), "onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(MyService.class.toString(), "onBind");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
