package com.example.moneymanagement.Model;

public class Transaction {

    private String id;
    private String account;
    private String category;
    private String money;
    private String date;
    private String imgId;

    public Transaction() {

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
