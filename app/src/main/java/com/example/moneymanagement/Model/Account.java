package com.example.moneymanagement.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {
    @PrimaryKey
    private int AccId;
    @ColumnInfo(name = "Account_name")
    private String AccName;
    @ColumnInfo(name = "Account_money")
    private String AccMoney;
    @ColumnInfo(name = "Account_picture")
    private String AccPic;

    public int getAccId() {
        return AccId;
    }

    public void setAccId(int accId) {
        AccId = accId;
    }

    public String getAccName() {
        return AccName;
    }

    public void setAccName(String accName) {
        AccName = accName;
    }

    public String getAccMoney() {
        return AccMoney;
    }

    public void setAccMoney(String accMoney) {
        AccMoney = accMoney;
    }

    public String getAccPic() {
        return AccPic;
    }

    public void setAccPic(String accPic) {
        AccPic = accPic;
    }
}
