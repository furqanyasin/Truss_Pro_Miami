package com.example.trusspromiami.models.cart

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class CartResponse {

    @SerializedName("status")
    @Expose
     var status: Int? = null

    @SerializedName("message")
    @Expose
     var message: String? = null

    @SerializedName("data")
    @Expose
     var data: CartData? = null

   
}