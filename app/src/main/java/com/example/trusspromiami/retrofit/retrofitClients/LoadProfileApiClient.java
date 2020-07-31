package com.example.trusspromiami.retrofit.retrofitClients;

import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.login.LoginResponse;
import com.example.trusspromiami.retrofit.RetrofitClient;
import com.example.trusspromiami.retrofit.TrussProServiceApi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadProfileApiClient {

    public static void getProfileApiCall(String authToken, IResponse<LoginResponse, String> listener) {
        TrussProServiceApi trussProServiceApi = RetrofitClient.getInstance().createClient();
        Call<LoginResponse> call = trussProServiceApi.getProfile("Bearer " + authToken);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listener.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public static void placeOrderApiCall(@Nullable String string, @NotNull IResponse<LoginResponse, String> loginResponseStringIResponse) {

    }
}
