package com.example.moneymanagement.itemView;

import static com.example.moneymanagement.ViewModel.AccountViewModel.mcontext;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.data.model.Account;
import com.example.moneymanagement.R;
import com.example.moneymanagement.ui.home.Account_transactionFragment;

public class AccountTransactionItemView extends RecyclerView.ViewHolder{

    private TextView tName;
    private TextView tMoney;
    private String key;

    public AccountTransactionItemView(ViewGroup parent) {
        super(LayoutInflater.from(mcontext).inflate(R.layout.account_item, parent, false));
        tName = (TextView) itemView.findViewById(R.id.txtAccName);
        tMoney = (TextView) itemView.findViewById(R.id.txtAccMoney);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("key", key);
                bundle.putString("name", tName.getText().toString());
                bundle.putString("money", tMoney.getText().toString());
                NavController navController = Navigation.findNavController((Activity) Account_transactionFragment.context, R.id.fragment);
                navController.navigate(R.id.transactionFragment, bundle);



            }
        });
    }

    public void bind(Account account, String key){
        tName.setText(account.getName());
        tMoney.setText(account.getMoney());
        System.out.println(account.getName());
        System.out.println(account.getMoney());
        this.key = key;
    }

}