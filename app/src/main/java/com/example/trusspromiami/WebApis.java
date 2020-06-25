package com.example.trusspromiami;

import com.example.trusspromiami.models.LoginResponce;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebApis {


    @GET("login?email=trusspro_admin@mail.com&password=12345678")
    Call<LoginResponce> loginUser();


}

