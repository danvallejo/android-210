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
