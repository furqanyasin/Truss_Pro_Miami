package com.example.trusspromiami.retrofit.retrofitClients;

import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.category.CategoriesData;
import com.example.trusspromiami.models.category.CategoriesResponse;
import com.example.trusspromiami.models.signup.SignupData;
import com.example.trusspromiami.models.signup.SignupResponse;
import com.example.trusspromiami.retrofit.RetrofitClient;
import com.example.trusspromiami.retrofit.TrussProServiceApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesApiClient {


    public static void getCategoriesCall(Integer pageNumber, IResponse<ArrayList<CategoriesData>, String> listener) {
        TrussProServiceApi trussProServiceApi = RetrofitClient.getInstance().createClient();
        Call<CategoriesResponse> call = trussProServiceApi.getCategories(pageNumber);
        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                listener.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
