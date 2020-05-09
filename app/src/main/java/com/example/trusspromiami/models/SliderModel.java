package com.example.trusspromiami.models;

import com.example.trusspromiami.R;

import java.util.ArrayList;

public class SliderModel {
    private int banner;

    public SliderModel(int banner) {
        this.banner = banner;
    }

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }

    public static ArrayList<SliderModel> getSliderList() {

        ArrayList<SliderModel> list = new ArrayList<SliderModel>();
        list.add(new SliderModel(R.drawable.one));
        list.add(new SliderModel(R.drawable.two));
        list.add(new SliderModel(R.drawable.three));
        list.add(new SliderModel(R.drawable.four));

        return list;
    }
}
