package com.example.moneymanagement.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey
    private int CateId;
    @ColumnInfo(name = "Category_name")
    private String CateName;
    @ColumnInfo(name = "Category_picture")
    private String CatePic;

    public int getCateId() {
        return CateId;
    }

    public void setCateId(int cateId) {
        CateId = cateId;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String cateName) {
        CateName = cateName;
    }

    public String getCatePic() {
        return CatePic;
    }

    public void setCatePic(String catePic) {
        CatePic = catePic;
    }
}
