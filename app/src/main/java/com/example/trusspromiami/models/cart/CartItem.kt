package com.example.trusspromiami.models.cart

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CartItem() {

    @SerializedName("stock")
    @Expose
     var stock: String? = null

    @SerializedName("product")
    @Expose
     var product: String? = null

    @SerializedName("size")
    @Expose
     var size: Any? = null

    @SerializedName("discounted_price")
    @Expose
     var discountedPrice: Any? = null

    @SerializedName("price")
    @Expose
     var price: String? = null

    @SerializedName("min_order")
    @Expose
     var minOrder: Any? = null

    @SerializedName("max_order")
    @Expose
     var maxOrder: Any? = null

    @SerializedName("parent")
    @Expose
     var parent: Int? = null

    @SerializedName("full_image_path")
    @Expose
     var fullImagePath: String? = null

    @SerializedName("title")
    @Expose
     var title: String? = null

    @SerializedName("description")
    @Expose
     var description: String? = null

}