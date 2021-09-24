package com.example.GroceryCity.models;

public class RecommendedModel {

    String name;
    String description;
    String itemImageUrl;
    String rating; 
    String type;

    public RecommendedModel() {
    }



    public RecommendedModel(String name, String description, String itemImageUrl, String rating, String type) {
        this.name = name;
        this.description = description;
        this.itemImageUrl = itemImageUrl;
        this.rating = rating;
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

    public String getType() {
        return type;
    }
}
