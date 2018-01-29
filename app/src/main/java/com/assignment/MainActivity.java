package com.assignment;

import android.content.Intent;
import android.media.MediaActionSound;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        // init layout file (.xml)
        setContentView(R.layout.activity_main);
    }

    // This method will init options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: ");
        getMenuInflater().inflate(R.menu.menu_settings, menu); // inflate menu
        return true;
    }

    // This method will handle onOptionItemselected listener
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: ");
        if (item.getItemId() == R.id.setting) {
            Intent intent = new Intent(this, UserSettingActivity.class);  // create intent object
            startActivity(intent); // Start UserSettingsActivity
        }
        return true;
    }
}
