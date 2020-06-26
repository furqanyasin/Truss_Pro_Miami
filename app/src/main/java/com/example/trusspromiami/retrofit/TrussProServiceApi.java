package com.example.trusspromiami.retrofit;

import com.example.trusspromiami.models.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface TrussProServiceApi {

    @GET
    Call<String> testResponse(@Url String url);

    @GET("/api/login")
    Call<LoginResponse> login(@Query("email") String email,
                              @Query("password") String userId);

}