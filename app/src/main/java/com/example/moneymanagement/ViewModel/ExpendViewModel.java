package com.example.moneymanagement.ViewModel;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.Model.Transaction;
import com.example.moneymanagement.adapter.TransactionAdapter;

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
}
