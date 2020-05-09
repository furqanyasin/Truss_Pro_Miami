package com.example.trusspromiami.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.trusspromiami.R;
import com.example.trusspromiami.baseClasses.BaseActivity;
import com.example.trusspromiami.helpers.SharedValues;
import com.example.trusspromiami.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private ActivityLoginBinding activityLoginBinding;

    private SharedValues prefrences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);


        activityLoginBinding.tvSignUp.setOnClickListener(this);
        activityLoginBinding.btnLogin.setOnClickListener(this);

        activityLoginBinding.etLoginEmail.setText("admin@gmail.com");
        activityLoginBinding.etLoginPassword.setText("123456");

        prefrences = new SharedValues(this);
        if(prefrences.getBooleanValue("session")){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public void onClick(View v) {

        if (v == activityLoginBinding.btnLogin)
        {

            String email = activityLoginBinding.etLoginEmail.getText().toString();
            String password = activityLoginBinding.etLoginPassword.getText().toString();

            Log.d("Clicked", "--------------- email : " + email);
            Log.e("Clicked", "--------------- Password : " + password);

            if (email.equalsIgnoreCase("admin@gmail.com") && password.equalsIgnoreCase("123456")) {
                prefrences.saveLoginData(email, password, true);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            } else {
                Toast.makeText(LoginActivity.this, "your username and password is incorrect!", Toast.LENGTH_LONG).show();
            }

        }else   if (v == activityLoginBinding.tvSignUp)
        {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);

        }



    }
}
