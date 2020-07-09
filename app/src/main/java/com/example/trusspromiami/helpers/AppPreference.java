package com.example.trusspromiami.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.trusspromiami.helpers.AppConstants.TRUSS_PRO_PREFS;

public class AppPreference {


    private static AppPreference sInstance;
    private final SharedPreferences mPref;

    private AppPreference(Context context) {
        mPref = context.getSharedPreferences(TRUSS_PRO_PREFS, Context.MODE_PRIVATE);
    }

    public static synchronized AppPreference getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AppPreference(context);
        }
        return sInstance;
    }

    public String getString(String key) {
        return mPref.getString(key, "");
    }

    public void setString(String key, String value) {
        mPref.edit()
                .putString(key, value)
                .apply();
    }

    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .apply();
    }


    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }

    public void setBoolean(String key, boolean value) {
        mPref.edit()
                .putBoolean(key, value)
                .apply();
    }

    public boolean getBoolean(String key) {
        return mPref.getBoolean(key, false);
    }

    public void setInt(String key, int value) {
        mPref.edit()
                .putInt(key, value)
                .apply();
    }

    public int getInt(String key) {
        return mPref.getInt(key, 0);
    }


    public long getLong(String key) {
        return mPref.getLong(key, 0);
    }

    public void setLong(String key, long value) {
        mPref.edit()
                .putLong(key, value)
                .apply();
    }
}