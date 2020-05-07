package com.example.trusspromiami.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedValues {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedValues(Context context){
        sharedPreferences = context.getSharedPreferences("truss", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void saveLoginData(String email, String password, boolean session) {
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putBoolean("session", session);
        editor.apply();
    }

    public boolean getBooleanValue(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public String getValuesFromPrefrences(String key) {
        return sharedPreferences.getString(key, "");
    }

}
