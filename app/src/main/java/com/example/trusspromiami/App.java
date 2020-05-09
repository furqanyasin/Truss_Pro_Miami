package com.example.trusspromiami;

import android.app.Application;
import android.content.Context;

import com.example.trusspromiami.helpers.LanguageHelper;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
    
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttach(base, LanguageHelper.getLanguage(base)));
    }
}
