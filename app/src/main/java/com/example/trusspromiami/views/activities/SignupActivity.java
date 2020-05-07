package com.example.trusspromiami.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trusspromiami.R;
import com.example.trusspromiami.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{


    private ActivitySignupBinding activitySignupBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

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
