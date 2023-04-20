package com.example.moneymanagement.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.Model.Account;
import com.example.moneymanagement.Model.Transaction;
import com.example.moneymanagement.Views.HomeFragment;
import com.example.moneymanagement.Views.TransactionFragment;
import com.example.moneymanagement.adapter.TransactionAdapter;
import com.example.moneymanagement.firebaseHelper.FirebaseHelper;
import com.example.moneymanagement.firebaseHelper.FirebaseHelper_Transaction;

import java.util.List;

public class ExpendViewModel {
    public static Context mcontext;
    public TransactionAdapter transAdapter;
    public static List<Transaction> expense;
    public static List<String> ks;

    public void setConfig(RecyclerView recyclerView, Context context, List<Transaction> transactions, List<String> keys){
        mcontext = context;
        transAdapter = new TransactionAdapter(transactions, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(transAdapter);
        List<Transaction> datalist = transAdapter.getExpense();
        List<String> k = transAdapter.getKey();
        expense = datalist;
        ks = k;
        transAdapter.notifyDataSetChanged();
    }

    public void conductTheTransaction(String accName, String cateName, String money, String date, String imgId, String remainedMoney, String key){
        Transaction newTransaction = new Transaction();
        newTransaction.setAccount(accName);
        newTransaction.setCategory(cateName);
        newTransaction.setMoney(money);
        newTransaction.setDate(date);
        newTransaction.setImgId(imgId);

        Account account = new Account();
        account.setAccount_name(accName);
        account.setMoney(remainedMoney);

        addNewTransaction(newTransaction);
        updateAccount(account, key);

    }

    private void addNewTransaction(Transaction transaction){
        new FirebaseHelper_Transaction().addData(transaction, new FirebaseHelper_Transaction.DataStatus(){
            @Override
            public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {

            }

            @Override
            public void DataIsInsert() {
                Toast.makeText(TransactionFragment.context, "Add successfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void DataIsUpdate() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    private void updateAccount(Account account, String key){
        new FirebaseHelper().editData(key, account, new FirebaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Account> accounts, List<String> keys) {

            }

            @Override
            public void DataIsInsert() {

            }

            @Override
            public void DataIsUpdate() {
                Toast.makeText(TransactionFragment.context, "Update successfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
