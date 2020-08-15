package com.example.trusspromiami.views.activities

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trusspromiami.R
import com.example.trusspromiami.baseClasses.BaseActivity
import com.example.trusspromiami.databinding.ActivityCartBinding
import com.example.trusspromiami.helpers.AppConstants
import com.example.trusspromiami.listeners.CartItemClickListener
import com.example.trusspromiami.listeners.IResponse
import com.example.trusspromiami.models.cart.CartDeleteResponse

import com.example.trusspromiami.models.cart.CartItem
import com.example.trusspromiami.models.cart.CartResponse
import com.example.trusspromiami.retrofit.retrofitClients.CartDataApiClient
import com.example.trusspromiami.views.adapters.CartAdapter
import java.lang.ref.WeakReference

class CartActivity : BaseActivity(), CartItemClickListener, View.OnClickListener {

    var activityCartBinding: ActivityCartBinding? = null
    var cartAdapter: CartAdapter? = null
    var dataModelList: ArrayList<CartItem> = ArrayList()

    var removedPosition : Int ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCartBinding = DataBindingUtil.setContentView(this, R.layout.activity_cart)
        setOnClickListener()
        initRecycler()
        setAdapter()
        getCartDataApi()

    }

    private fun setOnClickListener() {
        activityCartBinding?.cartToolbar?.ivBackArrow?.setOnClickListener(this)
        activityCartBinding?.btnAddToCart?.setOnClickListener(this)

    }

    private fun getCartDataApi() {
        CartDataApiClient.getCartDataApiCall(appPreference.getString(AppConstants.TOKEN), cartResponseStringIResponse)
    }

    private fun initRecycler() {
        var linearLayoutManager = LinearLayoutManager(this)
        activityCartBinding?.rcCart?.layoutManager = linearLayoutManager
    }

    private fun populateData(cartItemsList: List<CartItem>) {
        for (i in cartItemsList.indices) {
            dataModelList.add(cartItemsList[i])
        }
        cartAdapter?.notifyDataSetChanged()

    }

    private fun setAdapter() {
        cartAdapter = CartAdapter(WeakReference(this), dataModelList, this)
        activityCartBinding?.rcCart?.adapter = cartAdapter
    }

    private val cartResponseStringIResponse: IResponse<CartResponse?, String?> = object : IResponse<CartResponse?, String?> {
        override fun onSuccess(result: CartResponse?) {

            result?.data?.cartItems?.let { populateData(it) }

        }

        override fun onFailure(error: String?) {
            progressDialog.hide()

        }

    }
    private val cartDeleteResponseStringIResponse: IResponse<CartDeleteResponse?, String?> = object : IResponse<CartDeleteResponse?, String?> {
        override fun onSuccess(result: CartDeleteResponse?) {

            cartAdapter?.removeAt(removedPosition)

        }

        override fun onFailure(error: String?) {
            progressDialog.hide()

        }

    }

    override fun deleteCartItem(product: String?, position: Int?) {
        removedPosition = position
        CartDataApiClient.deleteCartApiCall(appPreference.getString(AppConstants.TOKEN), product, cartDeleteResponseStringIResponse)
    }


    override fun addQuantityCartItem() {

    }

    override fun minusQuantityCartItem() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back_arrow -> onBackPressed()
            R.id.btn_add_to_cart -> onPlaceOrderPressed()
        }
    }

    private fun onPlaceOrderPressed() {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}