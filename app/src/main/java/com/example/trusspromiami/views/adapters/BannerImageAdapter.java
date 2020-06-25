package com.example.trusspromiami.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.trusspromiami.R;
import com.example.trusspromiami.databinding.ItemSliderBinding;
import com.example.trusspromiami.databinding.SliderItemBinding;
import com.example.trusspromiami.models.category.Banner;

import java.util.List;

public class BannerImageAdapter extends PagerAdapter {

    private ItemSliderBinding itemSliderBinding;
    List<Banner> homeBanner;
    Context context;
    LayoutInflater layoutInflater;

    public BannerImageAdapter(Context context, List<Banner> banners) {
        this.context = context;
        homeBanner = banners;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return homeBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        itemSliderBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_slider, container, false);

        itemSliderBinding.ivSlider.setOnClickListener(v -> {
            Toast.makeText(context, homeBanner.get(position).getCategoryId().toString(), Toast.LENGTH_SHORT).show();
        });

        Glide
                .with(context)
                .load(homeBanner.get(position).getImageUrl())
                .placeholder(R.drawable.one)
                .centerCrop()
                .into(itemSliderBinding.ivSlider);

        container.addView(itemSliderBinding.getRoot());
        return itemSliderBinding.getRoot();
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

}