package com.example.trusspromiami.retrofit.retrofitClients;

import com.example.trusspromiami.helpers.AppConstants;
import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.signup.SignupData;
import com.example.trusspromiami.models.signup.SignupResponse;
import com.example.trusspromiami.retrofit.RetrofitClient;
import com.example.trusspromiami.retrofit.TrussProServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpApiClient {


    public static void SignUpApiClient(String email, String password, String confirmPassword, IResponse<SignupData, String> listener) {
        TrussProServiceApi trussProServiceApi = RetrofitClient.getInstance().createClient();
        Call<SignupResponse> call = trussProServiceApi.signup(email, password, confirmPassword, 0);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                listener.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
