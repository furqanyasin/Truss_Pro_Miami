package com.example.trusspromiami;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://demo.trusspro.pstore.website/api/";
    public static RetrofitClient mInstance;
    private Retrofit retrofit;
    private WebApis webApis;


    private RetrofitClient() {


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static synchronized RetrofitClient getmInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public WebApis getWebApis() {
        return retrofit.create(WebApis.class);
    }
}
