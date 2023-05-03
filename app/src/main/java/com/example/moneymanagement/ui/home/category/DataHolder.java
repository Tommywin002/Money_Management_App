package com.example.moneymanagement.ui.home.category;

public class DataHolder {

    private static final DataHolder instance = new DataHolder();
    private String categoryName, imgId, type;

    private DataHolder(){}

    public static DataHolder getInstance(){
        return instance;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
