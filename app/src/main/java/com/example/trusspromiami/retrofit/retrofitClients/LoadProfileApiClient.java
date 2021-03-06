package com.example.trusspromiami.retrofit.retrofitClients;

import com.example.trusspromiami.helpers.AppConstants;
import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.login.LoginResponse;
import com.example.trusspromiami.models.orderPlacement.OrderResponse;
import com.example.trusspromiami.retrofit.RetrofitClient;
import com.example.trusspromiami.retrofit.TrussProServiceApi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadProfileApiClient {

    public static void getProfileApiCall(String authToken, IResponse<OrderResponse, String> listener) {
        TrussProServiceApi trussProServiceApi = RetrofitClient.getInstance().createClient();
        String token = AppConstants.BEARER + AppConstants.SPACE + authToken;
        Call<OrderResponse> call = trussProServiceApi.getProfile(token);
        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
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

    public static void placeOrderApiCall( String string) {

    }
}
