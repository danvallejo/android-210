package com.cstructor.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle b = getIntent().getExtras();

        String name = b.getString("Name");

        Log.d("Name is ",name);
        Log.d("Age is ", Integer.toString(b.getInt("Age")));

        TextView tv = (TextView)findViewById(R.id.textView);

        tv.setText(name);
    }
}
