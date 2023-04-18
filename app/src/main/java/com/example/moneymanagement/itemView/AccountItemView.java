package com.example.moneymanagement.itemView;


import static com.example.moneymanagement.ViewModel.AccountViewModel.mcontext;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.Model.Account;
import com.example.moneymanagement.R;
import com.example.moneymanagement.Views.HomeFragment;

public class AccountItemView extends RecyclerView.ViewHolder{
    private TextView tName;
    private TextView tMoney;
    private String key;

    public AccountItemView(ViewGroup parent) {
        super(LayoutInflater.from(mcontext).inflate(R.layout.account_list_item, parent, false));
        tName = (TextView) itemView.findViewById(R.id.txtAccName);
        tMoney = (TextView) itemView.findViewById(R.id.txtAccMoney);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(HomeFragment.check == true){
                        *//*Intent intent = new Intent(mcontext, IncomeActivity.class);
                        intent.putExtra("key", key);
                        //intent.putExtra("id", tId.getText().toString());
                        intent.putExtra("name", tName.getText().toString());
                        intent.putExtra("money", tMoney.getText().toString());
                        mcontext.startActivity(intent);*//*
                }
                else{
                        *//*Intent intent = new Intent(mcontext, TransactionActivity.class);
                        intent.putExtra("key", key);
                        //intent.putExtra("id", tId.getText().toString());
                        intent.putExtra("name", tName.getText().toString());
                        intent.putExtra("money", tMoney.getText().toString());
                        mcontext.startActivity(intent);*//*
                }*/

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
