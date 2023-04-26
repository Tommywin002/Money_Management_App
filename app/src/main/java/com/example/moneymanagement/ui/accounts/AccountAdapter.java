package com.example.moneymanagement.ui.accounts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.R;
import com.example.moneymanagement.model.Account;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder>{
    private Context context;
    private List<Account> accountList;
    private Dialog dialog;
    public interface Dialog{
        void onClick(int pos);
    }
    public Dialog getDialog(){
        return dialog;
    }
    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public AccountAdapter(Context context, List<Account> accountList) {
        this.context = context;
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_list_item,parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        holder.accName.setText(accountList.get(position).getName());
        holder.accMoney.setText(accountList.get(position).getMoney());
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder{
        TextView accName, accMoney;
        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            accName = itemView.findViewById(R.id.txtAccName);
            accMoney = itemView.findViewById(R.id.txtAccMoney);
        }
    }
}
