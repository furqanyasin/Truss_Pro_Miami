package com.example.trusspromiami;

import com.google.android.material.textfield.TextInputEditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface webApis {



    @FormUrlEncoded
    @POST("login?email=trusspro_admin@mail.com&password=12345678")
    Call<Response> login(
            @Field("email") TextInputEditText etLoginEmail,
            @Field("password") TextInputEditText etLoginPassword
    );

}
