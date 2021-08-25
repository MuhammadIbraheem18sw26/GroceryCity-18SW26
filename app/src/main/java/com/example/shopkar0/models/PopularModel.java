package com.example.shopkar0.models;

public class PopularModel {

    String name;
    String description;
    String itemImageUrl;
    String rating; String discount;
    String type;

    public PopularModel() {
    }

    public PopularModel(String name, String description, String itemImageUrl, String rating, String discount, String type) {
        this.name = name;
        this.description = description;
        this.itemImageUrl = itemImageUrl;
        this.rating = rating;
        this.discount = discount;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getitemImageUrl() {
        return itemImageUrl;
    }

    public String getRating() {
        return rating;
    }

    public String getDiscount() {
        return discount;
    }

    public String getType() {
        return type;
    }
}
