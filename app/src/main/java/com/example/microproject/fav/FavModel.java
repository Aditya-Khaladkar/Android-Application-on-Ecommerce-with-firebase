package com.example.microproject.fav;

public class FavModel {

    String FavName, FavDes, FavImg;

    public FavModel(){}

    public FavModel(String favName, String favDes, String favImg) {
        this.FavName = favName;
        this.FavDes = favDes;
        this.FavImg = favImg;
    }

    public String getFavName() {
        return FavName;
    }

    public void setFavName(String favName) {
        FavName = favName;
    }

    public String getFavDes() {
        return FavDes;
    }

    public void setFavDes(String favDes) {
        FavDes = favDes;
    }

    public String getFavImg() {
        return FavImg;
    }

    public void setFavImg(String favImg) {
        FavImg = favImg;
    }
}
