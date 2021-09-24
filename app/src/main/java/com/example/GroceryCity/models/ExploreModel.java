package com.example.GroceryCity.models;

public class ExploreModel {

    String name;
    String itemImageUrl;
    String type;

    public ExploreModel() {
    }

    public ExploreModel(String name, String itemImageUrl, String type) {
        this.name = name;
        this.itemImageUrl = itemImageUrl;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }
}
