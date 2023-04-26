package com.example.moneymanagement.model;

public class Account {

    private String id;
    private String name;
    private String money;

    public Account(String name, String money) {
        this.name = name;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMoney() {
        return money;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccount_name(String account_name) {
        this.name = account_name;
    }

    public void setMoney(String money) {
        this.money = money;
    }


}
