package com.example.trusspromiami.models.category;

import java.util.ArrayList;

public class Category {

    private String categoryTitle;
    private String imageUrl;
    private Integer categoryId;

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

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

    public Category(String categoryTitle, String imageUrl, Integer categoryId) {
        this.categoryTitle = categoryTitle;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }

    public static ArrayList<Category> getCategoryList() {

        ArrayList<Category> categories = new ArrayList<>();

        for (int iterator = 0; iterator < 10; iterator++) {
            categories.add(new Category("category" + iterator, "https://demo.trusspro.pstore.website/storage/category/aeBatAicLa8CTwfrr8q4JvFYilrFkynnyq9jzEfF.jpeg", iterator));
        }

        return categories;
    }
}
