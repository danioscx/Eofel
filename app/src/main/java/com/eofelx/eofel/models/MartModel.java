package com.eofelx.eofel.models;

public class MartModel extends BaseModel {

    private int id;
    private String url;
    private String price;
    private String title;
    private String imageUrl;

    public MartModel(int id, String title, String price) { //sample constructor
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public MartModel() { // default constructor

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
