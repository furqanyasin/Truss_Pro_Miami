package com.example.trusspromiami.retrofit;

import android.util.Log;

import com.example.trusspromiami.helpers.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static RetrofitClient retrofitClient;
    private Retrofit retrofit;

    private static final String TAG = "ServiceGenerator";
    private static final String HEADER_CACHE_CONTROL = "Cache-Control";
    private static final String HEADER_PRAGMA = "Pragma";
    private static final long cacheSize = 10 * 1024 * 1024; // 10MB
    String token, cartSession;


    private RetrofitClient() {


        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(okHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitClient getInstance() {

        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    private OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor()) // used if network off OR on
                .addNetworkInterceptor(networkInterceptor()) // only used when network is on
                .addInterceptor(offlineInterceptor())
                .build();
    }

    private HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(message -> Log.d(TAG, "log: http log: " + message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    /**
     * This interceptor will be called ONLY if the network is available
     *
     * @return
     */
    private Interceptor networkInterceptor() {
        return chain -> {
            Log.d(TAG, "network interceptor: called.");

            Response response = chain.proceed(chain.request());

            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(1, TimeUnit.HOURS)
                    .build();


            return response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build();
        };
    }

    private static Interceptor offlineInterceptor() {
        return chain -> {
            Request request = chain.request();
//            Request request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
            return chain.proceed(request);
        };
    }

   /* private static Cache cache() {
        return new Cache(new File(AppConstants.mContext.getCacheDir(), AppConstants.APP_NAME), cacheSize);
    }*/

    private OkHttpClient getOkHttpClient() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        return okHttpClient;
    }

    public TrussProServiceApi createClient() {
        return retrofit.create(TrussProServiceApi.class);
    }
}
