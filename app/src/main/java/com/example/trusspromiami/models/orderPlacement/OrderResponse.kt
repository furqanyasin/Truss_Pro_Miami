package com.example.trusspromiami.models.orderPlacement

import com.example.trusspromiami.models.login.LoginData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderResponse {

    @SerializedName("status")
    @Expose
     val status: Int? = null

    @SerializedName("message")
    @Expose
     val message: String? = null

    @SerializedName("data")
    @Expose
     val data: OrderFormResponse? = null
}