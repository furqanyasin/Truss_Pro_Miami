package com.example.trusspromiami.retrofit.retrofitClients;

import com.example.trusspromiami.helpers.AppConstants;
import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.cart.CartAddResponse;
import com.example.trusspromiami.models.orderPlacement.OrderResponse;
import com.example.trusspromiami.retrofit.RetrofitClient;
import com.example.trusspromiami.retrofit.TrussProServiceApi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductToCartApiClient {


    public static void addProductToCartApiCall(String authToken, String product, String stock, String price, String discountedPrice, IResponse<CartAddResponse, String> listeners) {


        TrussProServiceApi trussProServiceApi = RetrofitClient.getInstance().createClient();
        String token = AppConstants.BEARER + AppConstants.SPACE + authToken;
        Call<CartAddResponse> call = trussProServiceApi.addToCart(token,product,stock,price,discountedPrice);
        call.enqueue(new Callback<CartAddResponse>() {
            @Override
            public void onResponse(Call<CartAddResponse> call, Response<CartAddResponse> response) {
                if (response.isSuccessful()) {
                    listeners.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listeners.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
