package com.example.trusspromiami.retrofit

import android.util.Log
import com.example.trusspromiami.helpers.AppConstants
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor() {
    private val retrofit: Retrofit
    var token: String? = null
    var cartSession: String? = null
    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor()) // used if network off OR on
                .addNetworkInterceptor(networkInterceptor()) // only used when network is on
                .addInterceptor(offlineInterceptor())
                .build()
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message: String -> Log.d(TAG, "log: http log: $message") })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    /**
     * This interceptor will be called ONLY if the network is available
     *
     * @return
     */
    private fun networkInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            Log.d(TAG, "network interceptor: called.")
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                    .maxAge(1, TimeUnit.HOURS)
                    .build()
            response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
        }
    }

    /* private static Cache cache() {
        return new Cache(new File(AppConstants.mContext.getCacheDir(), AppConstants.APP_NAME), cacheSize);
    }*/
    private val okHttpClient: OkHttpClient
        private get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .build()
        }

    fun createClient(): TrussProServiceApi {
        return retrofit.create(TrussProServiceApi::class.java)
    }

    companion object {
        private var retrofitClient: RetrofitClient? = null
        private const val TAG = "ServiceGenerator"
        private const val HEADER_CACHE_CONTROL = "Cache-Control"
        private const val HEADER_PRAGMA = "Pragma"
        private const val cacheSize = 10 * 1024 * 1024 // 10MB
                .toLong()

        @JvmStatic
        val instance: RetrofitClient?
            get() {
                if (retrofitClient == null) {
                    retrofitClient = RetrofitClient()
                }
                return retrofitClient
            }

        private fun offlineInterceptor(): Interceptor {
            return Interceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                chain.proceed(request)
            }
        }
    }

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(okHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}