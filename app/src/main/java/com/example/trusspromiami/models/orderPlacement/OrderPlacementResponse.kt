package com.example.trusspromiami.models.orderPlacement

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderPlacementResponse {

    @SerializedName("first_name")
    @Expose
    private val firstName: String? = null

    @SerializedName("last_name")
    @Expose
    private val lastName: String? = null

    @SerializedName("address")
    @Expose
    private val address: List<String>? = null
}