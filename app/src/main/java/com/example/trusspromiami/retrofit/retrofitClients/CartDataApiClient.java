package com.example.trusspromiami.retrofit.retrofitClients;

import com.example.trusspromiami.helpers.AppConstants;
import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.cart.CartDeleteResponse;
import com.example.trusspromiami.models.cart.CartResponse;
import com.example.trusspromiami.models.orderPlacement.OrderResponse;
import com.example.trusspromiami.retrofit.RetrofitClient;
import com.example.trusspromiami.retrofit.TrussProServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartDataApiClient {

    public static void getCartDataApiCall(String authToken, IResponse<CartResponse, String> listener) {
        TrussProServiceApi trussProServiceApi = RetrofitClient.getInstance().createClient();
        String token = AppConstants.BEARER + AppConstants.SPACE + authToken;
        Call<CartResponse> call = trussProServiceApi.getCartData(token);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
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

    public static void deleteCartApiCall(String authToken, String productId,IResponse<CartDeleteResponse, String> listener) {
        TrussProServiceApi trussProServiceApi = RetrofitClient.getInstance().createClient();
        String token = AppConstants.BEARER + AppConstants.SPACE + authToken;
        Call<CartDeleteResponse> call = trussProServiceApi.removeCart(token, productId);
        call.enqueue(new Callback<CartDeleteResponse>() {
            @Override
            public void onResponse(Call<CartDeleteResponse> call, Response<CartDeleteResponse> response) {
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

}
