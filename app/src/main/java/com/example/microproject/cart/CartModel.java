package com.example.microproject.cart;

public class CartModel {

    String ProductName, ProductPrice, ProductDes, ProductImage;

    public CartModel(){}

    public CartModel(String productName, String productPrice, String productDes, String productImage) {
        this.ProductName = productName;
        this.ProductPrice = productPrice;
        this.ProductDes = productDes;
        this.ProductImage = productImage;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductDes() {
        return ProductDes;
    }

    public void setProductDes(String productDes) {
        ProductDes = productDes;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }
}
