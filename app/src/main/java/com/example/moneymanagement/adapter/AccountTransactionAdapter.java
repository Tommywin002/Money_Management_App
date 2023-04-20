package com.example.moneymanagement.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.Model.Account;
import com.example.moneymanagement.itemView.AccountTransactionItemView;

import java.util.List;

public class AccountTransactionAdapter extends RecyclerView.Adapter<AccountTransactionItemView> {

    private List<Account> accList;
    private List<String> keys;

    public AccountTransactionAdapter(List<Account> accList, List<String> keys) {
        this.accList = accList;
        this.keys = keys;
    }

    @Override
    public AccountTransactionItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AccountTransactionItemView(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountTransactionItemView holder, int position) {
        holder.bind(accList.get(position), keys.get(position));
    }

    @Override
    public int getItemCount() {
        if(accList != null){
            return accList.size();
        }
        return 0;
    }
}
