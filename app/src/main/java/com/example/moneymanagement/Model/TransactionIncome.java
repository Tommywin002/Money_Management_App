package com.example.moneymanagement.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TransactionIncome {
    @PrimaryKey
    private int Inid;
    @ColumnInfo(name = "Income_name")
    private String Inname;
    @ColumnInfo(name = "Income_money")
    private String Inmoney;
    @ColumnInfo(name = "Income_time")
    private String Intime;
    @ColumnInfo(name = "Income_note")
    private String Innote;

    public int getInid() {
        return Inid;
    }

    public void setInid(int inid) {
        Inid = inid;
    }

    public String getInname() {
        return Inname;
    }

    public void setInname(String inname) {
        Inname = inname;
    }

    public String getInmoney() {
        return Inmoney;
    }

    public void setInmoney(String inmoney) {
        Inmoney = inmoney;
    }

    public String getIntime() {
        return Intime;
    }

    public void setIntime(String intime) {
        Intime = intime;
    }

    public String getInnote() {
        return Innote;
    }

    public void setInnote(String innote) {
        Innote = innote;
    }
}
