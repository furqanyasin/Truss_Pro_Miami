package com.example.trusspromiami.retrofit;

import com.example.trusspromiami.models.bannerImage.BannerImageResponse;
import com.example.trusspromiami.models.cart.CartAddResponse;
import com.example.trusspromiami.models.cart.CartDeleteResponse;
import com.example.trusspromiami.models.cart.CartResponse;
import com.example.trusspromiami.models.category.CategoriesResponse;
import com.example.trusspromiami.models.login.LoginResponse;
import com.example.trusspromiami.models.orderPlacement.OrderPlacementResponse;
import com.example.trusspromiami.models.orderPlacement.OrderResponse;
import com.example.trusspromiami.models.productDetail.ProductDetailResponse;
import com.example.trusspromiami.models.products.ProductResponse;
import com.example.trusspromiami.models.signup.SignupResponse;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface TrussProServiceApi {

    @GET
    Call<String> testResponse(@Url String url);

    @Headers("Accept: application/json")
    @GET("api/banner/list")
    Call<BannerImageResponse> getBanners(@Query("auth_token") String token);

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("api/login")
    Call<LoginResponse> login(@FieldMap HashMap<String, String> data);

    @Headers("Accept: application/json")
    @GET("api/sign-up")
    Call<SignupResponse> signup(@Query("email") String email,
                                @Query("password") String password,
                                @Query("retype_password") String confirmPassword,
                                @Query("terms_agreement") int termsAgreement);

    @Headers("Accept: application/json")
    @GET("api/categories")
    Call<CategoriesResponse> getCategories(@Query("page_number") Integer pageNumber);

    @Headers("Accept: application/json")
    @GET("api/category/{id}")
    Call<ProductResponse> getProducts(@Path("id") Integer id, @Query("auth_token") String authToken);

    @Headers("Accept: application/json")
    @GET("api/product/{id}")
    Call<ProductDetailResponse> getProductDetail(@HeaderMap Map<String, String> token, @Path("id") Integer id);

    @Headers("Accept: application/json")
    @GET("api/info")
    Call<OrderResponse> getProfile(@Query("auth_token") String authToken);

    @Headers("Accept: application/json")
    @GET("api/place-order")
    Call<OrderPlacementResponse> getOrderPlacement(@Query("auth_token") String authToken);

    @Headers("Accept: application/json")
    @POST("api/info")
    Call<LoginResponse> updateProfile();

    @Headers("Accept: application/json")
    @POST("api/cart")
    Call<CartResponse> getCartData(@Query("auth_token") String token);

    @Headers("Accept: application/json")
    @POST("/api/remove-from-cart")
    Call<CartDeleteResponse> removeCart(@Query("auth_token") String token, @Query("id") String id);

    @Headers("Accept: application/json")
    @POST("api/add-to-cart")
    Call<CartAddResponse> addToCart(@Query("auth_token") String token, @Query("id") String id, @Query("stock") String stock, @Query("price") String price, @Query("discounted_price") String discountPrice);

}