package com.example.trusspromiami.retrofit.retrofitClients;

import com.example.trusspromiami.helpers.AppConstants;
import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.productDetail.ProductDetailData;
import com.example.trusspromiami.models.productDetail.ProductDetailResponse;
import com.example.trusspromiami.models.products.ProductData;
import com.example.trusspromiami.models.products.ProductResponse;
import com.example.trusspromiami.retrofit.RetrofitClient;
import com.example.trusspromiami.retrofit.TrussProServiceApi;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductApiClient {


    public static void getProductsCall(String mToken, Integer categoryId, IResponse<ProductData, String> listener) {
        TrussProServiceApi trussProServiceApi = RetrofitClient.getInstance().createClient();

        String token = AppConstants.BEARER + " " + mToken;

        Call<ProductResponse> call = trussProServiceApi.getProducts(categoryId, token);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body().getProductData());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                listener.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public static void getProductsDetail(String mToken, Integer productId, IResponse<ProductDetailData, String> listener) {
        TrussProServiceApi trussProServiceApi = RetrofitClient.getInstance().createClient();

        String token = AppConstants.BEARER + " " + mToken;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", token);
        map.put("Accept", "application/json");

        Call<ProductDetailResponse> call = trussProServiceApi.getProductDetail(map, productId);

        call.enqueue(new Callback<ProductDetailResponse>() {
            @Override
            public void onResponse(Call<ProductDetailResponse> call, Response<ProductDetailResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body().getProductDetailData());
                }
            }

            @Override
            public void onFailure(Call<ProductDetailResponse> call, Throwable t) {
                listener.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
