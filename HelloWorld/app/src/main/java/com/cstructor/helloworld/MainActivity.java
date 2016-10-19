package com.cstructor.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.d("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.d("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.d("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.d("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity", "onDestroy");
        super.onDestroy();
        // The activity is about to be destroyed.
    }

   public void sendMessage(View view) throws InterruptedException {

       if (view.getId() == R.id.uxImageButton){
           Toast.makeText(this, "uxImageButton was clicked!", Toast.LENGTH_LONG).show();
           Thread.sleep(10000);
       }
       else if (view.getId() == R.id.uxImageTextButton){
           Toast.makeText(this, "uxImageTextButton was clicked!", Toast.LENGTH_LONG).show();
       }
       else if (view.getId() == R.id.uxTextButton){
           Toast.makeText(this, "uxTextButton was clicked!", Toast.LENGTH_LONG).show();
       }
       else{
           Toast.makeText(this, "Something weird was clicked!", Toast.LENGTH_LONG).show();
       }
   }
}
