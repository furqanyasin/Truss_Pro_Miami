package com.example.trusspromiami.models.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginData {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("first_name")
    @Expose
    var firstName: String? = null

    @SerializedName("last_name")
    @Expose
    var lastName: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("api_token")
    @Expose
    var apiToken: String? = "GZaXcyvfc9fes3u0FavToEX2KM4mBdTjL35eEN4oq2mq8KUNXYtdaQOj1vji"

    @SerializedName("role")
    @Expose
    var role: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("address2")
    @Expose
    var address2: Any? = null

    @SerializedName("shipping_address")
    @Expose
    var shippingAddress: String? = null

    @SerializedName("billing_address")
    @Expose
    var billingAddress: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("zip_code")
    @Expose
    var zipCode: String? = null

    @SerializedName("cart_session")
    @Expose
    var cartSession: String? = null

    @SerializedName("license")
    @Expose
    var license: String? = null

    @SerializedName("contact")
    @Expose
    var contact: String? = null

    @SerializedName("salon")
    @Expose
    var salon: String? = null

    @SerializedName("profile_image")
    @Expose
    var profileImage: String? = null

    @SerializedName("license_image")
    @Expose
    var licenseImage: String? = null

    @SerializedName("street_address")
    @Expose
    var streetAddress: String? = null

    @SerializedName("street_address2")
    @Expose
    var streetAddress2: Any? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("county")
    @Expose
    var county: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

}