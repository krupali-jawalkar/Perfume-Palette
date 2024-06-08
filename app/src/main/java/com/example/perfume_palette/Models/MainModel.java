package com.example.perfume_palette.Models;

public class MainModel {
    int image;
    String name, description, price ;

    public MainModel(int image, String name, String description, String price) {
        this.image = image;
        this.name = name;
        this.description= description;
        this.price=price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

