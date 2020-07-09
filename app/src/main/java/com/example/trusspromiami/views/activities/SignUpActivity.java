package com.example.trusspromiami.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.trusspromiami.R;
import com.example.trusspromiami.baseClasses.BaseActivity;
import com.example.trusspromiami.databinding.ActivitySignupBinding;
import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.signup.SignupData;
import com.example.trusspromiami.retrofit.retrofitClients.SignUpApiClient;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {


    private ActivitySignupBinding activitySignupBinding;

    String email, password, confirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

        activitySignupBinding.btnSignUp.setOnClickListener(this);
        activitySignupBinding.tvSignIn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (v == activitySignupBinding.tvSignIn) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        } else if (v == activitySignupBinding.btnSignUp) {
            email = activitySignupBinding.etSignUpEmail.getText().toString();
            password = activitySignupBinding.etSignUpPassword.getText().toString();
            confirmPassword = activitySignupBinding.etSignUpConfirmPassword.getText().toString();
            boolean checkBox = activitySignupBinding.checkBox.isChecked();

            if (email.isEmpty()) {
                showToast(getString(R.string.enter_email));
                return;
            }

            if (password.isEmpty()) {
                showToast(getString(R.string.enter_password));
                return;
            }
            if (confirmPassword.isEmpty()) {
                showToast(getString(R.string.confirm_password));
                return;
            }
            if (checkBox == false) {
                showToast(getString(R.string.chech_box));
                return;
            }

            CallToApi();
        }

    }

    private void CallToApi() {
        progressDialog.show();
        SignUpApiClient.SignUpApiClient(email, password, confirmPassword, signUpResponseStringIResponse);
    }

    private IResponse<SignupData, String> signUpResponseStringIResponse = new IResponse<SignupData, String>() {
        @Override
        public void onSuccess(SignupData result) {
            progressDialog.hide();
            if (result != null) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                showToast(getString(R.string.signup_successfully));
                startActivity(intent);
            }
        }

        @Override
        public void onFailure(String error) {
            progressDialog.hide();
            Log.d("sign_up_failure", error);
            showToast(error);
        }
    };

}
