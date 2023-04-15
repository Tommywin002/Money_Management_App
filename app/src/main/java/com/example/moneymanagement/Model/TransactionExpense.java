package com.example.moneymanagement.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TransactionExpense {
    @PrimaryKey
    private int Exid;
    @ColumnInfo(name = "Expense_name")
    private String Exname;
    @ColumnInfo(name = "Expense_money")
    private String Exmoney;
    @ColumnInfo(name = "Expense_time")
    private String Extime;
    @ColumnInfo(name = "Expense_note")
    private String Exnote;

    public int getExid() {
        return Exid;
    }

    public void setExid(int exid) {
        Exid = exid;
    }

    public String getExname() {
        return Exname;
    }

    public void setExname(String exname) {
        Exname = exname;
    }

    public String getExmoney() {
        return Exmoney;
    }

    public void setExmoney(String exmoney) {
        Exmoney = exmoney;
    }

    public String getExtime() {
        return Extime;
    }

    public void setExtime(String extime) {
        Extime = extime;
    }

    public String getExnote() {
        return Exnote;
    }

    public void setExnote(String exnote) {
        Exnote = exnote;
    }
}
