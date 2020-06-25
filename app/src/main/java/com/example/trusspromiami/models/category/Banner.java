package com.example.trusspromiami.models.category;

import com.example.trusspromiami.helpers.AppConstants;

import java.util.ArrayList;

public class Banner {

    private String imageUrl;
    private Integer categoryId;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Banner(String imageUrl, Integer categoryId) {
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }

    public static ArrayList<Banner> getBannersList() {

        ArrayList<Banner> categories = new ArrayList<>();

        for (int iterator = 0; iterator < 5; iterator++) {
            categories.add(new Banner(AppConstants.TEST_IMAGE, iterator));
        }

        return categories;
    }
}
