package com.example.trusspromiami.views.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.trusspromiami.R
import com.example.trusspromiami.baseClasses.BaseActivity
import com.example.trusspromiami.databinding.ActivityOrderFormBinding
import com.example.trusspromiami.helpers.AppConstants
import com.example.trusspromiami.listeners.IResponse
import com.example.trusspromiami.models.login.LoginResponse
import com.example.trusspromiami.retrofit.retrofitClients.LoadProfileApiClient


class OrderFormActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityOrderFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_form)
        initSpinnerStates()
        initClickListener()
        initFieldListener()
        getProfileApi()

    }

    private fun getProfileApi() {
        LoadProfileApiClient.getProfileApiCall(appPreference.getString(AppConstants.TOKEN), loginResponseStringIResponse)
    }

    private fun initFieldListener() {

        binding.firstName.addTextChangedListener(mTextWatcher)
        binding.secondName.addTextChangedListener(mTextWatcher)
        binding.etAddress.addTextChangedListener(mTextWatcher)
        binding.etCity.addTextChangedListener(mTextWatcher)
        binding.etZipCode.addTextChangedListener(mTextWatcher)
        binding.etPhone.addTextChangedListener(mTextWatcher)
        binding.etBillingAddress.addTextChangedListener(mTextWatcher)
        binding.etShippingAddress.addTextChangedListener(mTextWatcher)
    }

    private fun initClickListener() {
        binding.btnPlaceOrder.setOnClickListener(this)
        binding.checkAddressSame.setOnClickListener(this)


    }

    private fun initSpinnerStates() {
        val arrayAdapter = ArrayAdapter(this, R.layout.spinner_item_for_my_info_screen, resources.getStringArray(R.array.states))
        binding.stateSpinner.adapter = arrayAdapter
    }

    private fun isFieldsEmpty(): Boolean {
        var isFirstNameEmpty = false
        var isSecondNameEmpty = false
        var isMobileNumberEmpty = false
        var isCityEmpty = false
        var isStreetAddressEmpty = false
        var isBillingAddressEmpty = false
        var isShippingDateEmpty = false

        if (binding.firstName.text.toString().replace("\\s+".toRegex(), "").isEmpty()) {
            isFirstNameEmpty = true
            binding.firstNameErrorTv.errorTv.text = getString(R.string.this_field_is_required_new)

        }
        if (binding.secondName.text.toString().replace("\\s+".toRegex(), "").isEmpty()) {
            isSecondNameEmpty = true
            binding.secNameErrorTv.errorTv.text = getString(R.string.this_field_is_required_new)

        }
        if (binding.etAddress.text.toString().replace("\\s+".toRegex(), "").isEmpty()) {
            isStreetAddressEmpty = true
            binding.addressErrorTv.errorTv.text = getString(R.string.this_field_is_required_new)

        }
        if (binding.etCity.text.toString().replace("\\s+".toRegex(), "").isEmpty()) {
            isCityEmpty = true
            binding.cityErrorTv.errorTv.text = getString(R.string.this_field_is_required_new)

        }
        if (binding.etZipCode.text.toString().replace("\\s+".toRegex(), "").isEmpty()) {
            isFirstNameEmpty = true
            binding.zipCodeErrorTv.errorTv.text = getString(R.string.this_field_is_required_new)

        }
        if (binding.etPhone.text.toString().replace("\\s+".toRegex(), "").isEmpty()) {
            isMobileNumberEmpty = true
            binding.phoneErrorTv.errorTv.text = getString(R.string.this_field_is_required_new)

        }
        if (binding.etBillingAddress.text.toString().replace("\\s+".toRegex(), "").isEmpty()) {
            isBillingAddressEmpty = true
            binding.addressBillingErrorTv.errorTv.text = getString(R.string.this_field_is_required_new)

        }
        if (binding.etShippingAddress.text.toString().replace("\\s+".toRegex(), "").isEmpty()) {
            isShippingDateEmpty = true
            binding.addressShippingErrorTv.errorTv.text = getString(R.string.this_field_is_required_new)

        }

        return if (binding.checkAddressSame.visibility == View.VISIBLE)
            isFirstNameEmpty || isSecondNameEmpty || isMobileNumberEmpty || isCityEmpty || isStreetAddressEmpty || isBillingAddressEmpty || isShippingDateEmpty
        else
            isFirstNameEmpty || isSecondNameEmpty || isMobileNumberEmpty || isCityEmpty || isStreetAddressEmpty || isBillingAddressEmpty

    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btn_place_order -> {
                if (isFieldsEmpty()) {
                    placeOrderClicked()
                }
            }
            R.id.check_address_same -> {
                toggleShippingAddressClicked()
            }

        }

    }

    private fun toggleShippingAddressClicked() {
        if (binding.loShippingAddress.visibility == View.VISIBLE)
            binding.loShippingAddress.visibility = View.GONE
        else
            binding.loShippingAddress.visibility = View.VISIBLE

    }

    private fun placeOrderClicked() {

        LoadProfileApiClient.placeOrderApiCall(appPreference.getString(AppConstants.TOKEN), loginResponseStringIResponse)


    }

    val mTextWatcher = object : TextWatcher {
        override fun afterTextChanged(editText: Editable?) {

            when {
                editText === binding.firstName.editableText -> {
                    binding.firstNameErrorTv.errorTv.text = ""
                }
                editText === binding.secondName.editableText -> {
                    binding.secNameErrorTv.errorTv.text = ""
                }
                editText === binding.etAddress.editableText -> {
                    binding.addressErrorTv.errorTv.text = ""
                }
                editText === binding.etCity.editableText -> {
                    binding.cityErrorTv.errorTv.text = ""
                }
                editText === binding.etZipCode.editableText -> {
                    binding.zipCodeErrorTv.errorTv.text = ""
                }
                editText === binding.etPhone.editableText -> {
                    binding.phoneErrorTv.errorTv.text = ""
                }
                editText === binding.etBillingAddress.editableText -> {
                    binding.addressBillingErrorTv.errorTv.text = ""
                }
                editText === binding.etShippingAddress.editableText -> {
                    binding.addressShippingErrorTv.errorTv.text = ""
                }

            }

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }

    private val loginResponseStringIResponse: IResponse<LoginResponse?, String?> = object : IResponse<LoginResponse?, String?> {


        override fun onSuccess(result: LoginResponse?) {
            Log.d("result", result.toString())

        }

        override fun onFailure(error: String?) {
            progressDialog.hide()
            Log.d("login_failure", error)
            showToast(error)
        }
    }


}