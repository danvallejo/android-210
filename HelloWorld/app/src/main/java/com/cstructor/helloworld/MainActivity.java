package com.cstructor.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    static final int NUM_ITEMS = 10;

    ViewPager mPager;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        mAdapter = new MyAdapter(getSupportFragmentManager());

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        // Watch for button clicks.
        Button button = (Button) findViewById(R.id.goto_first);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(0);
            }
        });

        button = (Button) findViewById(R.id.goto_last);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(NUM_ITEMS - 1);
            }
        });

        DbHelper db = DbHelper.getInstance(this);

        db.addSession("steve", new Date());

        DBSession[] sessions = db.getSessions();

        DBSession session = sessions[0];
        db.updateSession(session.SessionId, session.Name + "-updated", session.StartDate);

        sessions = db.getSessions();

        db.deleteSession(session.SessionId);

        sessions = db.getSessions();
    }

    public void onUpdateTextView(View view) {
        final TextView textView = (TextView) findViewById(R.id.uxTextView2);

        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 10; x++) {
                    final int newX = x;

                    SystemClock.sleep(1000);
                    Log.d("main", Integer.toString(x));

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(Integer.toString(newX));
                        }
                    });
                }
            }
        }).start();
    }

    public void onContactClick(View view) {
        // Sets the columns to retrieve for the user profile
        String[] projection = new String[]
                {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME
                };

        // Retrieves the profile from the Contacts Provider
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                projection,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");

        if (cursor.moveToFirst()) {
            while (true) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Log.d("cursor", name);
                if (!cursor.moveToNext()) {
                    break;
                }
            }
        }
    }

    public void onSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);

        startActivity(intent);
    }


    String FILENAME = "hello_file";

    int value = 16;

    public static final String PREFS_NAME = "MyPrefsFile";

    public void onSharedIncrement(View view) {
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences("com.cstructor.helloworld_preferences", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("sharedValue", ++value);

        // Commit the edits!
        editor.commit();
    }

    public void onSharedRead(View view) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        value = settings.getInt("sharedValue", -1);

        Toast.makeText(this, Integer.toString(value), Toast.LENGTH_LONG).show();
    }

    public void UpdateFile(View view) {
        String string = "hello world! " + (value++) + "!";
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            fos.write(string.getBytes());
            fos.close();
            Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void ReadFile(View view) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(getFilesDir() + "/" + FILENAME));
            String line;
            String text = "";
            while ((line = bufferedReader.readLine()) != null) {
                text += line;
            }
            TextView tv = (TextView) findViewById(R.id.uxTextView);
            tv.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_compose) {
            Toast.makeText(this, "22", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (item.getItemId() == R.id.action_search) {
            Toast.makeText(this, "action_search2", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.action_compose) {

            Toast.makeText(this, "action_compose2", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        if (item.getItemId() == R.id.action_search) {
            Toast.makeText(this, "action_search", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.action_compose) {

            Toast.makeText(this, "action_compose", Toast.LENGTH_SHORT).show();
        }

        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }
        return false;
    }

    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            if (position < 3) {
                return ArrayListFragment.newInstance(position);
            } else {
                return new ExampleFragment();
            }
        }
    }

    public static class ArrayListFragment extends ListFragment {
        int mNum;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static ArrayListFragment newInstance(int num) {
            ArrayListFragment f = new ArrayListFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_pager_list, container, false);
            View tv = v.findViewById(R.id.text);
            ((TextView) tv).setText("Fragment #" + mNum);
            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            String[] data;

            if (mNum == 0) {
                data = getResources().getStringArray(R.array.cities_array);

            } else if (mNum == 1) {
                data = getResources().getStringArray(R.array.phonetype_array);

            } else {
                data = getResources().getStringArray(R.array.countries_array);
            }

            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, data));
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Log.i("FragmentList", "Item clicked: " + id);
        }
    }

}