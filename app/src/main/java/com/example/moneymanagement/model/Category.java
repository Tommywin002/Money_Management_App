package com.example.moneymanagement.model;

public class Category {

    private String id;
    private String name;
    private String imgId;
    private String type;

    public Category(String name, String imgId, String type) {
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

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
