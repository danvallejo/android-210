package com.cstructor.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate");

        final AutoCompleteTextView textView = (AutoCompleteTextView)findViewById(R.id.uxAutocompleteCountry);

        String[] countries = getResources().getStringArray(R.array.countries_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, countries);

        textView.setAdapter(adapter);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.uxToggleButton);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d("onCheckedChanged", "Checked on!");
                } else {
                    Log.d("onCheckedChanged", "Checked off!");
                }
            }
        });
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

    public void onCheckboxClicked(View view){
        CheckBox checkBox = (CheckBox)view;
        boolean checked = ((CheckBox)view).isChecked();

        Toast toast = Toast.makeText(this, "Hello toast!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 100, 100);
        toast.show();

        if (checked){
            Log.d("onCheckboxClicked", "Checked ON!");
        }
        else{
            Log.d("onCheckboxClicked", "Checked OFF!");
        }
    }

    public void onSecondActivity(View view){
        Intent secondActivityIntent = new Intent(this, SecondActivity.class);

        Bundle b = new Bundle();
        b.putString("Name", "dave");
        b.putInt("Age", 23);

        secondActivityIntent.putExtras(b);

        startActivity(secondActivityIntent);
    }

   public void sendMessage(View view) throws InterruptedException {

       /*if (view.getId() == R.id.uxImageButton){
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
       }*/
   }
}
