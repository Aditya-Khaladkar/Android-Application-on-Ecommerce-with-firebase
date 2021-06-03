package com.example.microproject;

public class Model {

    String productName, productImage, productPrice, productDes;


    public Model(){}

    public Model(String productName, String productImage, String productPrice, String productDes) {
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productDes = productDes;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }
}
