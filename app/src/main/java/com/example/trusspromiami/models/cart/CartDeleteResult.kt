package com.example.trusspromiami.models.cart

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class CartDeleteResult {

    @SerializedName("message")
    @Expose
    private val message: String? = null

    @SerializedName("cart_count")
    @Expose
    private val cartCount: Int? = null



}