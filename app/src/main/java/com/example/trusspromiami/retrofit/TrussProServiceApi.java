package com.example.trusspromiami.retrofit;

import com.example.trusspromiami.models.category.CategoriesResponse;
import com.example.trusspromiami.models.login.LoginResponse;
import com.example.trusspromiami.models.signup.SignupResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface TrussProServiceApi {

    @GET
    Call<String> testResponse(@Url String url);

    @Headers("Accept: application/json")
    @GET("api/login")
    Call<LoginResponse> login(@Query("email") String email,
                              @Query("password") String userId);

    @Headers("Accept: application/json")
    @GET("api/sign-up")
    Call<SignupResponse> signup(@Query("email") String email,
                                @Query("password") String password,
                                @Query("retype_password") String confirmPassword,
                                @Query("terms_agreement") int termsAgreement);

    @Headers("Accept: application/json")
    @GET("api/categories")
    Call<CategoriesResponse> getCategories(@Query("page_number") Integer pageNumber);


}