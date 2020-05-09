package com.example.trusspromiami.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.example.trusspromiami.R;
import com.example.trusspromiami.databinding.ItemSliderBinding;
import com.example.trusspromiami.models.SliderModel;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private List<SliderModel> sliderModelList;
    private ItemSliderBinding itemSliderBinding;
    private LayoutInflater inflater;

    public SliderAdapter(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        inflater = LayoutInflater.from(container.getContext());

        itemSliderBinding = DataBindingUtil.inflate(inflater, R.layout.item_slider, container, false);
        ImageView banner = itemSliderBinding.bannerSlide;
        banner.setImageResource(sliderModelList.get(position).getBanner());
        container.addView(itemSliderBinding.getRoot(), 0);
        return itemSliderBinding.getRoot();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return sliderModelList.size();
    }
}
