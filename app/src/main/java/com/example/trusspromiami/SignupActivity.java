package com.example.trusspromiami;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.trusspromiami.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{


    private ActivitySignupBinding activitySignupBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignupBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup);

        activitySignupBinding.btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == activitySignupBinding.tvSignIn)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        } else if (v == activitySignupBinding.btnSignUp)
        {
            //todo btn sign Up
        }
    }
}
