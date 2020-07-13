package com.example.trusspromiami.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.trusspromiami.R;
import com.example.trusspromiami.baseClasses.BaseActivity;
import com.example.trusspromiami.databinding.ActivityProductDetailBinding;
import com.example.trusspromiami.helpers.AppConstants;
import com.example.trusspromiami.helpers.AppUtils;
import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.models.productDetail.ProductDetailData;
import com.example.trusspromiami.models.products.ProductData;
import com.example.trusspromiami.retrofit.retrofitClients.ProductApiClient;

public class ProductDetailActivity extends BaseActivity {

    private ActivityProductDetailBinding activityProductDetailBinding;
    private ProductData productData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityProductDetailBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_product_detail);
        productData = (ProductData) getIntent().getExtras().get(AppConstants.PRODUCT);
//        getProductDetail();
        setProductDetail();
    }

    private void setProductDetail() {
        activityProductDetailBinding.detailToolbar.activityTitle.setText(productData.getTitle().toUpperCase());
        activityProductDetailBinding.detailToolbar.tvCount.setText("10");

        activityProductDetailBinding.detailToolbar.ivActionBtn.setVisibility(View.VISIBLE);
        activityProductDetailBinding.detailToolbar.tvCount.setVisibility(View.VISIBLE);
        activityProductDetailBinding.detailToolbar.actionBtn.setVisibility(View.GONE);
        android.view.Display display = ((android.view.WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        activityProductDetailBinding.ivProductImage.getLayoutParams().height = ((int) (display.getHeight() * 0.65));
        activityProductDetailBinding.ivProductImage.getLayoutParams().width = ((int) (display.getWidth() * 1.0));

        Glide.with(this)
                .load(productData.getImage())
                .placeholder(R.drawable.placeholder_white)
                .into(activityProductDetailBinding.ivProductImage);

        if (productData.getDiscountedPrice() != null || productData.getDiscountedPrice() != "-") {
            activityProductDetailBinding.tvProductSalePrice.setText(AppConstants.CURRENCY_DOLLAR_SIGN + productData.getDiscountedPrice());
            activityProductDetailBinding.tvProductActualPrice.setText(AppConstants.CURRENCY_DOLLAR_SIGN + productData.getPrice());
            AppUtils.showStrikeOnTextView(activityProductDetailBinding.tvProductActualPrice);
        } else {
            activityProductDetailBinding.tvProductSalePrice.setText(AppConstants.CURRENCY_DOLLAR_SIGN + productData.getPrice());
            activityProductDetailBinding.tvProductActualPrice.setVisibility(View.GONE);
        }

        activityProductDetailBinding.tvProductDetail.setText(productData.getDescription());
    }

    private void getProductDetail() {
        String token = appPreference.getString(AppConstants.TOKEN);
        ProductApiClient.getProductsDetail(token, productData.getId(), response);
    }

    private IResponse<ProductDetailData, String> response = new IResponse<ProductDetailData, String>() {
        @Override
        public void onSuccess(ProductDetailData result) {
            if (result != null) {
                setProductDetail();
            }
        }

        @Override
        public void onFailure(String error) {
            Toast.makeText(ProductDetailActivity.this, error, Toast.LENGTH_SHORT).show();
        }
    };
}
