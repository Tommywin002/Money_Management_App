package com.example.moneymanagement.ui.accounts;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.AccountListItemBinding;
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
        AccountListItemBinding viewBinding = AccountListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AccountViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        holder.binding.txtAccName.setText(accountList.get(position).getName());
        holder.binding.txtAccMoney.setText(accountList.get(position).getMoney());
        holder.binding.accItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = accountList.get(position).getId();
                String account = holder.binding.txtAccName.getText().toString();
                String money = holder.binding.txtAccMoney.getText().toString();
                Toast.makeText(context, account + " - " + money, Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("account", account);
                bundle.putString("money", money);
                bundle.putString("id", id);
                NavController navController = Navigation.findNavController((Activity) context, R.id.fragment);
                navController.navigate(R.id.transactionFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {

        private AccountListItemBinding binding;

        public AccountViewHolder(@NonNull AccountListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
