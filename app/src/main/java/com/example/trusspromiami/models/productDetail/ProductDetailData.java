package com.example.trusspromiami.models.productDetail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailData {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("title")
@Expose
private String title;
@SerializedName("description")
@Expose
private String description;
@SerializedName("image")
@Expose
private String image;
@SerializedName("price")
@Expose
private String price;
@SerializedName("discounted_price")
@Expose
private Object discountedPrice;
@SerializedName("currency")
@Expose
private String currency;
@SerializedName("stock")
@Expose
private String stock;
@SerializedName("rating")
@Expose
private Object rating;
@SerializedName("size")
@Expose
private Object size;
@SerializedName("min_order")
@Expose
private Object minOrder;
@SerializedName("max_order")
@Expose
private Object maxOrder;
@SerializedName("status")
@Expose
private String status;
@SerializedName("category_id")
@Expose
private String categoryId;
@SerializedName("parent_product")
@Expose
private String parentProduct;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("child_products")
@Expose
private List<Object> childProducts = null;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public String getPrice() {
return price;
}

public void setPrice(String price) {
this.price = price;
}

public Object getDiscountedPrice() {
return discountedPrice;
}

public void setDiscountedPrice(Object discountedPrice) {
this.discountedPrice = discountedPrice;
}

public String getCurrency() {
return currency;
}

public void setCurrency(String currency) {
this.currency = currency;
}

public String getStock() {
return stock;
}

public void setStock(String stock) {
this.stock = stock;
}

public Object getRating() {
return rating;
}

public void setRating(Object rating) {
this.rating = rating;
}

public Object getSize() {
return size;
}

public void setSize(Object size) {
this.size = size;
}

public Object getMinOrder() {
return minOrder;
}

public void setMinOrder(Object minOrder) {
this.minOrder = minOrder;
}

public Object getMaxOrder() {
return maxOrder;
}

public void setMaxOrder(Object maxOrder) {
this.maxOrder = maxOrder;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getCategoryId() {
return categoryId;
}

public void setCategoryId(String categoryId) {
this.categoryId = categoryId;
}

public String getParentProduct() {
return parentProduct;
}

public void setParentProduct(String parentProduct) {
this.parentProduct = parentProduct;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

public List<Object> getChildProducts() {
return childProducts;
}

public void setChildProducts(List<Object> childProducts) {
this.childProducts = childProducts;
}

}