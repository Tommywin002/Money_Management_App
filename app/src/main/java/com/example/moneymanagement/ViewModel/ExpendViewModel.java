package com.example.moneymanagement.ViewModel;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.Model.Transaction;
import com.example.moneymanagement.adapter.TransactionAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExpendViewModel {
    public static Context mcontext;
    public TransactionAdapter transAdapter;
    public static List<Transaction> expense;
    public static List<String> ks;

    public void setConfig(RecyclerView recyclerView, Context context, List<Transaction> transactions, List<String> keys, int position){
        mcontext = context;
        transAdapter = new TransactionAdapter(transactions, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(transAdapter);
        List<Transaction> datalist = transAdapter.getExpense();
        List<String> k = transAdapter.getKey();
        if (position==0){
            Collections.sort(datalist, new Comparator<Transaction>() {
                @Override
                public int compare(Transaction e1, Transaction e2) {
                    return Integer.compare(Integer.parseInt(e1.getMoney()),Integer.parseInt(e2.getMoney()));
                }
            });
        }
        if (position==1){
            Collections.sort(datalist, new Comparator<Transaction>() {
                @Override
                public int compare(Transaction e1, Transaction e2) {
                    return Integer.compare(Integer.parseInt(e2.getMoney()),Integer.parseInt(e1.getMoney()));
                }
            });
        }
        expense = datalist;
        ks = k;
        transAdapter.notifyDataSetChanged();
    }
}
