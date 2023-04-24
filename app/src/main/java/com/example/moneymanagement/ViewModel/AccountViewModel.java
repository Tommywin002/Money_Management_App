package com.example.moneymanagement.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.data.model.Account;
import com.example.moneymanagement.ui.accounts.AddAccountFragment;
import com.example.moneymanagement.adapter.AccountAdapter;
import com.example.moneymanagement.adapter.AccountTransactionAdapter;
import com.example.moneymanagement.data.services.FirebaseHelper;

import java.util.List;

public class AccountViewModel extends ViewModel {
    public static Context mcontext;
    private AccountAdapter accountAdapter;
    private AccountTransactionAdapter accountTransactionAdapter;

    // Firebase adapter <- Recycle_Account (setconfig())
    public void setConfig(RecyclerView recyclerView, Context context, List<Account> accounts, List<String> keys){
        mcontext = context;
        accountAdapter = new AccountAdapter(accounts, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(accountAdapter);
        accountAdapter.notifyDataSetChanged();
    }

    public void setConfig2(RecyclerView recyclerView, Context context, List<Account> accounts, List<String> keys){
        mcontext = context;
        accountTransactionAdapter = new AccountTransactionAdapter(accounts, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(accountTransactionAdapter);
        accountTransactionAdapter.notifyDataSetChanged();
    }

    public void addNewAccount(String name, String money){
        Account account = new Account();
        account.setAccount_name(name);
        account.setMoney(money);
        new FirebaseHelper().addData(account, new FirebaseHelper.DataStatus(){

            @Override
            public void DataIsLoaded(List<Account> accounts, List<String> keys) {

            }

            @Override
            public void DataIsInsert() {

            }

            @Override
            public void DataIsUpdate() {
                Toast.makeText(AddAccountFragment.context, "Added successfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

}
