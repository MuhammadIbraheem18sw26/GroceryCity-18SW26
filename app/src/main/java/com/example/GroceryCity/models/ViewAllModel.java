package com.example.GroceryCity.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable {


    String name;
    String description;
    String itemImageUrl;
    String rating;
    String type;
    int price;

    public ViewAllModel() {
    }

    public ViewAllModel(String name, String description, String itemImageUrl, String rating, String type, int price) {
        this.name = name;
        this.description = description;
        this.itemImageUrl = itemImageUrl;
        this.rating = rating;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public String getRating() {
        return rating;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }
}
