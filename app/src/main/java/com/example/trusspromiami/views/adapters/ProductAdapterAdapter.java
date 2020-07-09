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
import com.example.trusspromiami.helpers.AppUtils;
import com.example.trusspromiami.listeners.OnItemClickInterface;
import com.example.trusspromiami.models.products.ProductData;

import java.util.ArrayList;

public class ProductAdapterAdapter extends RecyclerView.Adapter<ProductAdapterAdapter.ProductViewHolder> {

    private LayoutInflater mInflater;
    private ItemProductLayoutBinding itemProductLayoutBinding;
    private ArrayList<ProductData> mProductDataArrayList = new ArrayList();
    private OnItemClickInterface mOnItemClickInterface;

    public ProductAdapterAdapter(Context context, OnItemClickInterface onItemClickInterface) {
        this.mInflater = LayoutInflater.from(context);
        mOnItemClickInterface = onItemClickInterface;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemProductLayoutBinding = DataBindingUtil.inflate(mInflater, R.layout.item_product_layout, parent, false);
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

    public void setData(ArrayList<ProductData> productDataArrayList) {
        mProductDataArrayList = productDataArrayList;
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private ItemProductLayoutBinding itemProductLayoutBinding;

        public ProductViewHolder(@NonNull ItemProductLayoutBinding mItemCategoryLayoutBinding) {
            super(mItemCategoryLayoutBinding.getRoot());
            itemProductLayoutBinding = mItemCategoryLayoutBinding;
        }

        public void bindHolder(ProductData productData) {

            itemProductLayoutBinding.tvProductName.setText(productData.getTitle());

            if (productData.getDiscountedPrice() != "-") {
                itemProductLayoutBinding.tvPrice.setText(productData.getDiscountedPrice());
                itemProductLayoutBinding.tvActualPrice.setText(productData.getPrice());
                AppUtils.showSalePrice(itemProductLayoutBinding.tvActualPrice);
                itemProductLayoutBinding.tvPrice.setVisibility(View.VISIBLE);
            } else {
                itemProductLayoutBinding.tvPrice.setText(productData.getPrice());
                itemProductLayoutBinding.tvActualPrice.setVisibility(View.GONE);
            }

            Glide.with(itemProductLayoutBinding.getRoot().getContext())
                    .load(productData.getImage())
                    .placeholder(R.drawable.placeholder_white)
                    .fitCenter()
                    .into(itemProductLayoutBinding.ivProduct);

            itemProductLayoutBinding.getRoot().setOnClickListener(view -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION)
                    mOnItemClickInterface.onClickItem(getAdapterPosition());
            });

            itemProductLayoutBinding.executePendingBindings();
        }
    }
}
