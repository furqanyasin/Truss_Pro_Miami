package com.example.trusspromiami.retrofit.retrofitClients;

import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.login.LoginResponse;
import com.example.trusspromiami.retrofit.RetrofitClient;
import com.example.trusspromiami.retrofit.TrussProServiceApi;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginApiClient {

    public static void loginApiCall(String email, String password, IResponse<LoginResponse, String> listener) {
//
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("email", email);
//        jsonObject.addProperty("password", password);

        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);

        TrussProServiceApi trussProServiceApi = RetrofitClient.getInstance().createClient();
        Call<LoginResponse> call = trussProServiceApi.login(map);
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
}
