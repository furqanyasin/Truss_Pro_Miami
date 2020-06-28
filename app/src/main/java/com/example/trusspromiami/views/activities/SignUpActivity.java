package com.example.trusspromiami.views.activities;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.trusspromiami.R;
import com.example.trusspromiami.baseClasses.BaseActivity;
import com.example.trusspromiami.databinding.ActivitySignupBinding;
import com.example.trusspromiami.helpers.SharedValues;
import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.signup.Data;
import com.example.trusspromiami.retrofit.retrofitClients.SignUpApiClient;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {


    private ActivitySignupBinding activitySignupBinding;

    String email ="", password ="", confirmPassword = "";

    private SharedValues prefrences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

        activitySignupBinding.btnSignUp.setOnClickListener(this);
        activitySignupBinding.tvSignIn.setOnClickListener(this);

        activitySignupBinding.etSignUpEmail.setText("trusspro_admin@mail.com");
        activitySignupBinding.etSignUpPassword.setText("12345678");
        activitySignupBinding.etSignUpConfirmPassword.setText("12345678");

        prefrences = new SharedValues(this);
        if (prefrences.getBooleanValue("session")) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
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
            if (checkBox==false){
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

    private IResponse<Data, String> signUpResponseStringIResponse = new IResponse<Data, String>() {
        @Override
        public void onSuccess(Data result) {
            progressDialog.hide();
            if (result != null)
                showToast(getString(R.string.signup_successfully));
        }

        @Override
        public void onFailure(String error) {
            progressDialog.hide();
            Log.d("sign_up_failure", error);
            showToast(error);

        }
    };

}
