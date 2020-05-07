package com.example.trusspromiami.views.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.databinding.DataBindingUtil;

import com.example.trusspromiami.R;
import com.example.trusspromiami.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends Activity {
    public static int SPLASH_TIME_OUT = 3000;

    private ActivitySplashScreenBinding activitySplashScreenBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySplashScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
