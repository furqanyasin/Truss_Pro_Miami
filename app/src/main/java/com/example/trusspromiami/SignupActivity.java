package com.example.trusspromiami;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        login = findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == login)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }
    }
}
