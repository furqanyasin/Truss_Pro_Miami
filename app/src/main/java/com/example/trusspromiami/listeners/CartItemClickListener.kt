package com.example.trusspromiami.listeners

import java.text.FieldPosition

interface CartItemClickListener {
    fun deleteCartItem(product: String?, position: Int?)
    fun addQuantityCartItem()
    fun minusQuantityCartItem()
}