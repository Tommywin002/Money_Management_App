package com.example.moneymanagement.adapter;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.Model.Account;
import com.example.moneymanagement.itemView.AccountItemView;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountItemView> {
    private List<Account> accList;
    private List<String> keys;

    public AccountAdapter(List<Account> accList, List<String> keys) {
        this.accList = accList;
        this.keys = keys;
    }
    @Override
    public AccountItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AccountItemView(parent);
    }

    @Override
    public void onBindViewHolder(AccountItemView holder, int position) {
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
