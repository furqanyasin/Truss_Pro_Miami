package com.example.trusspromiami.retrofit.retrofitClients;

import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.signup.Data;
import com.example.trusspromiami.retrofit.RetrofitClient;
import com.example.trusspromiami.retrofit.TrussProServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpApiClient {


    public static void SignUpApiClient(String email, String password, String confirmPassword, IResponse<Data,String> listener){
        TrussProServiceApi trussProServiceApi = RetrofitClient.getInstance().createClient();
        Call<Data> call = trussProServiceApi.signup(email, password, confirmPassword,0);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                listener.onFailure(t.getLocalizedMessage());
            }
        });


    }
}
