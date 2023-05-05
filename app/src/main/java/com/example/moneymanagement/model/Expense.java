package com.example.moneymanagement.model;

public class Expense {

    private String id;
    private String account;
    private String category;
    private String money;
    private String date;
    private String imgId;

    public Expense(String account, String category, String money, String date, String imgId) {
        this.account = account;
        this.category = category;
        this.money = money;
        this.date = date;
        this.imgId = imgId;
    }

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getCategory() {
        return category;
    }

    public String getMoney() {
        return money;
    }

    public String getDate() {
        return date;
    }

    public String getImgId(){return imgId;}

    public void setId(String id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImgId(String imgId){
        this.imgId = imgId;
    }
}
