package com.cstructor.helloworld;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate");

        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.uxAutocompleteCountry);

        String[] countries = getResources().getStringArray(R.array.countries_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, countries);

        textView.setAdapter(adapter);

        Spinner spinner = (Spinner) findViewById(R.id.uxPhoneTypeSpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.phonetype_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter2);

        spinner.setOnItemSelectedListener(this);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.uxToggleButton);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

    public void onCheckboxClicked(View view) {
        CheckBox checkBox = (CheckBox) view;
        boolean checked = ((CheckBox) view).isChecked();

        Toast toast = Toast.makeText(this, "Hello toast!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 100, 100);
        toast.show();

        if (checked) {
            Log.d("onCheckboxClicked", "Checked ON!");
        } else {
            Log.d("onCheckboxClicked", "Checked OFF!");
        }
    }

    public void onSecondActivity(View view) {
        Intent secondActivityIntent = new Intent(this, SecondActivity.class);

        Bundle b = new Bundle();
        b.putString("Name", "dave");
        b.putInt("Age", 23);

        secondActivityIntent.putExtras(b);

        startActivity(secondActivityIntent);
    }

    public void onThirdActivity(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);

        startActivity(intent);
    }

    public void onAlertDialog(View v) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Message")
                .setTitle("Title");

        // 3. Add the buttons
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        // 4. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    public void onDialog(View v) {
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog_demo);

        dialog.setTitle("Dialog title");

        Button okButton = (Button)dialog.findViewById(R.id.uxSignIn);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userNameWidget = (EditText)dialog.findViewById(R.id.uxUserName);

                String userName = userNameWidget.getText().toString();

                if (userName.equalsIgnoreCase("test")) {
                    Toast.makeText(MainActivity.this,"You're logged in.", Toast.LENGTH_LONG).show();

                    dialog.dismiss();
                }
                else{
                    Toast.makeText(MainActivity.this,"UserName/Password is incorrect.", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button cancelButton = (Button)dialog.findViewById(R.id.uxCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private PopupWindow pw;

    public void onPopupWindow(View view) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.popup_example, null, false);

        Button closeButton = (Button)layout.findViewById(R.id.uxClose);

        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });

        pw = new PopupWindow(layout, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT, true);

        pw.showAtLocation(this.findViewById(R.id.uxPhoneTypeSpinner), Gravity.CENTER, 0, 0);
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



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = (String)parent.getItemAtPosition(position);
        Toast.makeText(this, "onItemSelected:" + item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "onNothing", Toast.LENGTH_SHORT).show();
    }
}
