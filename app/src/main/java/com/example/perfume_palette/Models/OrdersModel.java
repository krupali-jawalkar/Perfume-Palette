package com.example.perfume_palette.Models;

public class OrdersModel {
    int orderImage;
    String soldItemName, orderPrice, orderNumber;

    public OrdersModel(int orderImage, String soldItemName, String orderPrice, String orderNumber) {
        this.orderImage = orderImage;
        this.soldItemName = soldItemName;
        this.orderPrice = orderPrice;
        this.orderNumber = orderNumber;
    }

    public OrdersModel() {

    }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

    public String getSoldItemName() {
        return soldItemName;
    }

    public void setSoldItemName(String soldItemName) {
        this.soldItemName = soldItemName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderNumber() { return orderNumber; }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
