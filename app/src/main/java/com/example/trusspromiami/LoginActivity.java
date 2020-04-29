package com.example.trusspromiami;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.trusspromiami.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogin;
    private TextView signup;
    private TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    private SharedValues prefrences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        textInputEditTextEmail = findViewById(R.id.tiet_login_email);
        textInputEditTextPassword = findViewById(R.id.tiet_login_password);

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(this);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        textInputEditTextEmail.setText("admin@gmail.com");
        textInputEditTextPassword.setText("123456");

        prefrences = new SharedValues(this);
        if(prefrences.getBooleanValue("session")){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public void onClick(View v) {

        if (v == btnLogin)
        {

            String email = textInputEditTextEmail.getText().toString();
            String password = textInputEditTextPassword.getText().toString();

            Log.d("Clicked", "--------------- email : " + email);
            Log.e("Clicked", "--------------- Password : " + password);

            if (email.equalsIgnoreCase("admin@gmail.com") && password.equalsIgnoreCase("123456")) {
                prefrences.saveLoginData(email, password, true);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            } else {
                Toast.makeText(LoginActivity.this, "your username and password is incorrect!", Toast.LENGTH_LONG).show();
            }

        }else   if (v == signup)
        {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);

        }



    }
}
