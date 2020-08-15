package com.example.trusspromiami.models.cart

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class CartData {
    @SerializedName("cart_items")
    @Expose
    var cartItems: List<CartItem>? = null

    @SerializedName("total")
    @Expose
    var total: Double? = null

    @SerializedName("currency")
    @Expose
    var currency: String? = null


}