package com.example.trusspromiami.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trusspromiami.R;
import com.example.trusspromiami.databinding.ItemCategoryLayoutBinding;
import com.example.trusspromiami.models.category.CategoriesData;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private LayoutInflater mInflater;
    private ItemCategoryLayoutBinding itemCategoryLayoutBinding;
    private ArrayList<CategoriesData> categories = new ArrayList();

    public CategoryAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemCategoryLayoutBinding = DataBindingUtil.inflate(mInflater, R.layout.item_category_layout, parent, false);
        return new CategoryViewHolder(itemCategoryLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bindHolder(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setData(ArrayList<CategoriesData> categoryArrayList) {
        categories = categoryArrayList;
        notifyDataSetChanged();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ItemCategoryLayoutBinding itemCategoryLayoutBinding;

        public CategoryViewHolder(@NonNull ItemCategoryLayoutBinding mItemCategoryLayoutBinding) {
            super(mItemCategoryLayoutBinding.getRoot());
            itemCategoryLayoutBinding = mItemCategoryLayoutBinding;
        }

        public void bindHolder(CategoriesData category) {

            itemCategoryLayoutBinding.tvCategoryName.setText(category.getTitle());

            Glide.with(itemCategoryLayoutBinding.getRoot().getContext())
                    .load(category.getImage())
                    .fitCenter()
                    .into(itemCategoryLayoutBinding.ivCategory);

            itemCategoryLayoutBinding.executePendingBindings();
        }
    }
}
