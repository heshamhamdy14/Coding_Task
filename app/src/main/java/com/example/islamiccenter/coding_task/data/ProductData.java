package com.example.islamiccenter.coding_task.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ProductData implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("productDescription")
    @Expose
    private String productDescription;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("price")
    @Expose
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}