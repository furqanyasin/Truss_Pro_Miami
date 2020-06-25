package com.example.trusspromiami.views.activities;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.trusspromiami.R;
import com.example.trusspromiami.RetrofitClient;
import com.example.trusspromiami.baseClasses.BaseActivity;
import com.example.trusspromiami.databinding.ActivityLoginBinding;
import com.example.trusspromiami.models.LoginResponce;
import com.example.trusspromiami.WebApis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ActivityLoginBinding activityLoginBinding;

    private WebApis webApis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        activityLoginBinding.tvSignUp.setOnClickListener(this);
        activityLoginBinding.btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == activityLoginBinding.btnLogin) {
            userLogin();

        } else if (v == activityLoginBinding.tvSignUp) {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        }


    }

    private void userLogin() {


        Call<LoginResponce> call = RetrofitClient
                .getmInstance()
                .getWebApis()
                .loginUser();

        call.enqueue(new Callback<LoginResponce>() {
            @Override
            public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(LoginActivity.this, "-------Login  Responce  successfull-----", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(LoginActivity.this, "-------Login   UNsuccessfull-----", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponce> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "-------Login  Responce UN successfull-----", Toast.LENGTH_LONG).show();

            }
        });
    }
}
