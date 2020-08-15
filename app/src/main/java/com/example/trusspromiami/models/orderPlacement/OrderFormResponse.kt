package com.example.trusspromiami.models.orderPlacement

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderFormResponse {
    @SerializedName("id")
    @Expose
     val id: Int? = null

    @SerializedName("first_name")
    @Expose
     val firstName: String? = null

    @SerializedName("last_name")
    @Expose
     val lastName: String? = null

    @SerializedName("email")
    @Expose
     val email: String? = null

    @SerializedName("api_token")
    @Expose
     val apiToken: String? = null

    @SerializedName("role")
    @Expose
     val role: String? = null

    @SerializedName("remember_token")
    @Expose
     val rememberToken: Any? = null

    @SerializedName("address")
    @Expose
     val address: String? = null

    @SerializedName("address2")
    @Expose
     val address2: Any? = null

    @SerializedName("shipping_address")
    @Expose
     val shippingAddress: String? = null

    @SerializedName("billing_address")
    @Expose
     val billingAddress: String? = null

    @SerializedName("status")
    @Expose
     val status: String? = null

    @SerializedName("cart_session")
    @Expose
     val cartSession: String? = null

    @SerializedName("license")
    @Expose
     val license: String? = null

    @SerializedName("contact")
    @Expose
     val contact: String? = null

    @SerializedName("salon")
    @Expose
     val salon: String? = null

    @SerializedName("profile_image")
    @Expose
     val profileImage: String? = null

    @SerializedName("license_image")
    @Expose
     val licenseImage: String? = null

    @SerializedName("street_address")
    @Expose
     val streetAddress: Any? = null

    @SerializedName("street_address2")
    @Expose
     val streetAddress2: Any? = null

    @SerializedName("city")
    @Expose
     val city: Any? = null

    @SerializedName("county")
    @Expose
     val county: Any? = null

    @SerializedName("created_at")
    @Expose
     val createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
     val updatedAt: String? = null

}