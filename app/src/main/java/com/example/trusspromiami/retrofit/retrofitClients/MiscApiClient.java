package com.example.trusspromiami.retrofit.retrofitClients;

import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.bannerImage.BannerData;
import com.example.trusspromiami.models.bannerImage.BannerImageResponse;
import com.example.trusspromiami.retrofit.RetrofitClient;
import com.example.trusspromiami.retrofit.TrussProServiceApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiscApiClient {


    public static void getBanners(String token, IResponse<ArrayList<BannerData>, String> listener) {
        TrussProServiceApi trussProServiceApi = RetrofitClient.getInstance().createClient();
        Call<BannerImageResponse> call = trussProServiceApi.getBanners(token);
        call.enqueue(new Callback<BannerImageResponse>() {
            @Override
            public void onResponse(Call<BannerImageResponse> call, Response<BannerImageResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<BannerImageResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
