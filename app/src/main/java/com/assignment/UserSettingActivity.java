package com.assignment;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class UserSettingActivity extends PreferenceActivity {
    private final String TAG = UserSettingActivity.class.toString();
    private static int prefs = R.xml.user_settings;
    private AppCompatDelegate mDelegate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getDelegate().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        getDelegate().installViewFactory();
        Log.d(TAG, "onCreate: ");
        try {
            getClass().getMethod("getFragmentManager");
            AddResourceApi11AndGreater(); // This method will inflate xml and works Api 11 and grater devices
        } catch (NoSuchMethodException e) { //Api < 11
            AddResourceApiLessThan11(); // This method will work on devices which is less than Api 11
        }
        // enable back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // set title over action bar
        getSupportActionBar().setTitle("Settings");
    }

    // This method is deprecated in Api 11
    @SuppressWarnings("deprecation")
    protected void AddResourceApiLessThan11() {

        addPreferencesFromResource(prefs);
    }

    @TargetApi(11)
    protected void AddResourceApi11AndGreater() {
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new PF()).commit();
    }

    // this will take care of all device which is Api level 11 and higher
    @TargetApi(11)
    public static class PF extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(UserSettingActivity.prefs); // Add xml resource
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        }

        @Override
        public void onPause() {
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            super.onPause();
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Log.e("key= ", key);
            Toast.makeText(getActivity(), key + "  is changed. ", Toast.LENGTH_SHORT).show();
        }
    }

    public ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        getDelegate().setSupportActionBar(toolbar);
    }

    private AppCompatDelegate getDelegate() {
        if (mDelegate == null) {
            mDelegate = AppCompatDelegate.create(this, null);
        }
        return mDelegate;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}