package com.example.trusspromiami.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.trusspromiami.R;
import com.example.trusspromiami.baseClasses.BaseActivity;
import com.example.trusspromiami.databinding.ActivityProductListingBinding;
import com.example.trusspromiami.helpers.AppConstants;
import com.example.trusspromiami.listeners.IResponse;
import com.example.trusspromiami.listeners.OnItemClickInterface;
import com.example.trusspromiami.models.category.CategoriesData;
import com.example.trusspromiami.models.products.ProductData;
import com.example.trusspromiami.retrofit.retrofitClients.ProductApiClient;
import com.example.trusspromiami.views.adapters.ProductAdapterAdapter;

import java.util.ArrayList;

public class ProductListing extends BaseActivity {

    private CategoriesData categoriesData;
    private ActivityProductListingBinding activityProductListingBinding;
    private ArrayList<ProductData> mProductDataArrayList = new ArrayList<>();
    private ProductAdapterAdapter productAdapterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProductListingBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_listing);
        categoriesData = (CategoriesData) getIntent().getExtras().get(AppConstants.CATEGORY_OBJ);

        activityProductListingBinding.productListingToolbar.activityTitle.setText(categoriesData.getTitle().toUpperCase());
        activityProductListingBinding.productListingToolbar.actionBtn.setVisibility(View.GONE);
        activityProductListingBinding.productListingToolbar.ivBackArrow.setOnClickListener(view -> {
            onBackPressed();
        });

        setAdapter();
        getProductsList();
    }

    private void setAdapter() {

        productAdapterAdapter = new ProductAdapterAdapter(this, onItemClickInterface);
        activityProductListingBinding.rvProduct.setAdapter(productAdapterAdapter);
        activityProductListingBinding.rvProduct.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void getProductsList() {
        progressDialog.show();
        String token = appPreference.getString(AppConstants.TOKEN);
        ProductApiClient.getProductsCall(token, categoriesData.getId(), listener);
    }

    private IResponse<ArrayList<ProductData>, String> listener = new IResponse<ArrayList<ProductData>, String>() {
        @Override
        public void onSuccess(ArrayList<ProductData> result) {

            progressDialog.hide();
            if (result != null && !result.isEmpty()) {
                mProductDataArrayList = result;
                productAdapterAdapter.setData(mProductDataArrayList);
                productAdapterAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFailure(String error) {
            progressDialog.hide();
            showToast(error);
        }
    };

    private OnItemClickInterface onItemClickInterface = new OnItemClickInterface() {
        @Override
        public void onClickItem(Integer index) {
            Intent intent = new Intent(ProductListing.this, ProductDetailActivity.class);
            intent.putExtra(AppConstants.PRODUCT, mProductDataArrayList.get(index));
            startActivity(intent);
        }
    };

    @Override
    public void onBackPressed() {
        finish();
    }
}
