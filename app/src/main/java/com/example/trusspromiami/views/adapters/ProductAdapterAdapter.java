package com.example.trusspromiami.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trusspromiami.R;
import com.example.trusspromiami.databinding.ItemProductLayoutBinding;
import com.example.trusspromiami.helpers.AppConstants;
import com.example.trusspromiami.helpers.AppUtils;
import com.example.trusspromiami.listeners.OnItemClickInterface;
import com.example.trusspromiami.models.products.Product;

import java.util.ArrayList;

public class ProductAdapterAdapter extends RecyclerView.Adapter<ProductAdapterAdapter.ProductViewHolder> {

    private LayoutInflater mInflater;
    private ItemProductLayoutBinding itemProductLayoutBinding;
    private ArrayList<Product> mProductDataArrayList = new ArrayList();
    private OnItemClickInterface mOnItemClickInterface;

    public ProductAdapterAdapter(Context context, OnItemClickInterface onItemClickInterface) {
        this.mInflater = LayoutInflater.from(context);
        mOnItemClickInterface = onItemClickInterface;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemProductLayoutBinding = DataBindingUtil.inflate(mInflater,
                R.layout.item_product_layout, parent, false);
        return new ProductViewHolder(itemProductLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bindHolder(mProductDataArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return mProductDataArrayList.size();
    }

    public void setData(ArrayList<Product> productDataArrayList) {
        mProductDataArrayList = productDataArrayList;
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private ItemProductLayoutBinding itemProductLayoutBinding;

        public ProductViewHolder(@NonNull ItemProductLayoutBinding mItemCategoryLayoutBinding) {
            super(mItemCategoryLayoutBinding.getRoot());
            itemProductLayoutBinding = mItemCategoryLayoutBinding;
        }

        public void bindHolder(Product product) {

            itemProductLayoutBinding.tvProductName.setText(product.getTitle());

            if (product.getDiscountedPrice() == null || product.getDiscountedPrice() == AppConstants.HIPHEN) {
                itemProductLayoutBinding.tvPrice.setText(String.format("%s%s",
                        AppConstants.CURRENCY_DOLLAR_SIGN, product.getPrice()));
                itemProductLayoutBinding.tvActualPrice.setVisibility(View.GONE);
            } else {
                itemProductLayoutBinding.tvPrice.setText(String.format("%s%s",
                        AppConstants.CURRENCY_DOLLAR_SIGN, product.getDiscountedPrice()));
                itemProductLayoutBinding.tvActualPrice.setText(String.format("%s%s",
                        AppConstants.CURRENCY_DOLLAR_SIGN, product.getPrice()));
                itemProductLayoutBinding.tvPrice.setVisibility(View.VISIBLE);
                AppUtils.showStrikeOnTextView(itemProductLayoutBinding.tvActualPrice);
            }

            Glide.with(itemProductLayoutBinding.getRoot().getContext())
                    .load(product.getFullImagePath())
                    .placeholder(R.drawable.placeholder_white)
                    .into(itemProductLayoutBinding.ivProduct);

            itemProductLayoutBinding.getRoot().setOnClickListener(view -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION)
                    mOnItemClickInterface.onClickItem(getAdapterPosition());
            });

            itemProductLayoutBinding.executePendingBindings();
        }
    }
}
