package com.example.trusspromiami.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.trusspromiami.R;
import com.example.trusspromiami.baseClasses.BaseActivity;
import com.example.trusspromiami.databinding.ActivityLoginBinding;
import com.example.trusspromiami.helpers.AppConstants;
import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.login.LoginResponse;
import com.example.trusspromiami.retrofit.retrofitClients.LoginApiClient;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ActivityLoginBinding activityLoginBinding;
    String email = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        activityLoginBinding.tvSignUp.setOnClickListener(this);
        activityLoginBinding.btnLogin.setOnClickListener(this);

        activityLoginBinding.etLoginEmail.setText("trusspro_admin@mail.com");
        activityLoginBinding.etLoginPassword.setText("12345678");
    }

    @Override
    public void onClick(View v) {

        if (v == activityLoginBinding.btnLogin) {

            email = activityLoginBinding.etLoginEmail.getText().toString();
            password = activityLoginBinding.etLoginPassword.getText().toString();

            if (email.isEmpty()) {
                showToast(getString(R.string.enter_email));
                return;
            }

            if (password.isEmpty()) {
                showToast(getString(R.string.enter_password));
                return;
            }

            CallToApi();
        } else if (v == activityLoginBinding.tvSignUp) {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        }
    }

    private void CallToApi() {
        progressDialog.show();
        LoginApiClient.loginApiCall(email, password, loginResponseStringIResponse);
    }

    private IResponse<LoginResponse, String> loginResponseStringIResponse = new IResponse<LoginResponse, String>() {
        @Override
        public void onSuccess(LoginResponse result) {

            progressDialog.hide();
            if (result != null) {
                appPreference.setString(AppConstants.TOKEN, result.getData().getApiToken());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                showToast(getString(R.string.login_successfully));
                startActivity(intent);
            }
        }

        @Override
        public void onFailure(String error) {
            progressDialog.hide();
            Log.d("login_failure", error);
            showToast(error);
        }
    };
}
