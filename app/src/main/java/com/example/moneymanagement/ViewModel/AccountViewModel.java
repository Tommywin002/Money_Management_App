package com.example.moneymanagement.ViewModel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.Model.Account;
import com.example.moneymanagement.adapter.AccountAdapter;

import java.util.List;

public class AccountViewModel {
    private Context mcontext;
    private AccountAdapter accountAdapter;

    // Firebase adapter <- Recycle_Account (setconfig())
    public void setConfig(RecyclerView recyclerView, Context context, List<Account> accounts, List<String> keys){
        mcontext = context;
        accountAdapter = new AccountAdapter(accounts, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(accountAdapter);
        accountAdapter.notifyDataSetChanged();
    }

}
