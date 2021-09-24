package com.example.GroceryCity.models;

public class CategoryModel {

    String name;
    String description;
    String itemImageUrl;
    String offerDiscount;
    String type;


    public CategoryModel() {
    }



    public CategoryModel(String name, String description, String itemImageUrl, String offerDiscount,String type) {
        this.name = name;
        this.description = description;
        this.itemImageUrl = itemImageUrl;
        this.offerDiscount = offerDiscount;
        this.type=type;


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

    public void setOfferDiscount(String offerDiscount) {
        this.offerDiscount = offerDiscount;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getOfferDiscount() {
        return offerDiscount;
    }

    public String getType() {
        return type;
    }
}
