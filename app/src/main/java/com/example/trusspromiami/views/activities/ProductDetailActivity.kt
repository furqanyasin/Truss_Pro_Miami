package com.example.trusspromiami.views.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.trusspromiami.R
import com.example.trusspromiami.baseClasses.BaseActivity
import com.example.trusspromiami.databinding.ActivityProductDetailBinding
import com.example.trusspromiami.helpers.AppConstants
import com.example.trusspromiami.helpers.AppUtils
import com.example.trusspromiami.helpers.IntegerChangedListener
import com.example.trusspromiami.listeners.IResponse
import com.example.trusspromiami.models.cart.CartAddResponse
import com.example.trusspromiami.models.cart.CartDeleteResponse
import com.example.trusspromiami.models.products.Product
import com.example.trusspromiami.retrofit.retrofitClients.AddProductToCartApiClient
import com.example.trusspromiami.retrofit.retrofitClients.LoadProfileApiClient

class ProductDetailActivity : BaseActivity(), View.OnClickListener {
    private var integerChangedListener: IntegerChangedListener? = null
    private var activityProductDetailBinding: ActivityProductDetailBinding? = null
    private var productData: Product? = null
    private var productCounter = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityProductDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)
        productData = intent.extras!![AppConstants.PRODUCT] as Product?
        integerChangedListener = IntegerChangedListener()
        setQuantityChangeListener()
        setProductDetail()
        setClickListener()
        setTextChangedListener()
    }

    private fun setQuantityChangeListener() {
        integerChangedListener!!.setChangeListener { changeQuantityTextView() }
    }

    private fun changeQuantityTextView() {
        activityProductDetailBinding!!.quantityText.text = integerChangedListener!!.quantityValue.toString()
    }

    private fun setTextChangedListener() {}
    private fun setClickListener() {
        activityProductDetailBinding!!.btnAdd.setOnClickListener(this)
        activityProductDetailBinding!!.btnMinus.setOnClickListener(this)
        activityProductDetailBinding!!.detailToolbar.actionBtn.setOnClickListener(this)
        activityProductDetailBinding!!.btnAddToCart.setOnClickListener(this)
        activityProductDetailBinding!!.detailToolbar.ivActionBtn.setOnClickListener(this)
    }

    private fun setProductDetail() {
        activityProductDetailBinding!!.detailToolbar.activityTitle.text = productData!!.title.toUpperCase()
        activityProductDetailBinding!!.detailToolbar.ivActionBtn.visibility = View.VISIBLE
        activityProductDetailBinding!!.detailToolbar.actionBtn.visibility = View.GONE
        val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        //        activityProductDetailBinding.ivProductImage.getLayoutParams().height = ((int) (display.getHeight() * 0.65));
//        activityProductDetailBinding.ivProductImage.getLayoutParams().width = ((int) (display.getWidth() * 1.0));
//
//        Glide.with(this)
//                .load(productData.getFullImagePath())
//                .placeholder(R.drawable.placeholder_white)
//                .into(activityProductDetailBinding.ivProductImage);
        if (productData!!.discountedPrice != null || productData!!.discountedPrice !== AppConstants.HIPHEN) {
            activityProductDetailBinding!!.tvProductSalePrice.text = AppConstants.CURRENCY_DOLLAR_SIGN + productData!!.discountedPrice
            activityProductDetailBinding!!.tvProductActualPrice.text = AppConstants.CURRENCY_DOLLAR_SIGN + productData!!.price
            AppUtils.showStrikeOnTextView(activityProductDetailBinding!!.tvProductActualPrice)
        } else {
            activityProductDetailBinding!!.tvProductSalePrice.text = AppConstants.CURRENCY_DOLLAR_SIGN + productData!!.price
            activityProductDetailBinding!!.tvProductActualPrice.visibility = View.GONE
        }
        activityProductDetailBinding!!.tvProductDetail.text = productData!!.description
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_add -> integerChangedListener!!.quantityValue = ++productCounter
            R.id.btn_minus -> minusQuantity()
            R.id.btn_add_to_cart -> addToCardActivity()
            R.id.iv_action_btn -> moveToCardActivity()
            R.id.iv_back_arrow -> onBackPressed()
        }
    }

    private fun minusQuantity() {
        if (productCounter > 1) integerChangedListener!!.quantityValue = --productCounter
    }

    private fun addToCardActivity() {
        AddProductToCartApiClient.addProductToCartApiCall(appPreference.getString(AppConstants.TOKEN), productData?.product, productData?.stock, productData?.price, productData?.discountedPrice, addProductToCartResponse)

    }

    private fun moveToCardActivity() {
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private val addProductToCartResponse: IResponse<CartAddResponse?, String?> = object : IResponse<CartAddResponse?, String?> {
        override fun onSuccess(result: CartAddResponse?) {
            Log.d("result", result.toString())
            showToast(result?.message)


        }

        override fun onFailure(error: String?) {
            progressDialog.hide()
            Log.d("login_failure", error)
            showToast(error)

        }

    }
}


