package com.example.moneymanagement.model;

public class Category {

    private String id;
    private String name;
    private int imgId;
    private String type;

    public Category(String name, int imgId, String type) {
        this.name = name;
        this.imgId = imgId;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
