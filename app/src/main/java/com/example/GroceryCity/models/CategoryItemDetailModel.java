package com.example.GroceryCity.models;

public class CategoryItemDetailModel {

String name;
String type;
String itemImageUrl;
String price;

    public CategoryItemDetailModel(String name, String type, String itemImageUrl, String price) {
        this.name = name;
        this.type = type;
        this.itemImageUrl = itemImageUrl;
        this.price = price;
    }

    public CategoryItemDetailModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
