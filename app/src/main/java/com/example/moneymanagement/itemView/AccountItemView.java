package com.example.moneymanagement.itemView;


import static com.example.moneymanagement.ViewModel.AccountViewModel.mcontext;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.Model.Account;
import com.example.moneymanagement.R;
import com.example.moneymanagement.Views.HomeFragment;

public class AccountItemView extends RecyclerView.ViewHolder{
    private TextView tName;
    private TextView tMoney;
    private ImageView edit;
    private String key;

    public AccountItemView(ViewGroup parent) {
        super(LayoutInflater.from(mcontext).inflate(R.layout.account_list_item, parent, false));
        tName = (TextView) itemView.findViewById(R.id.txtAccName);
        tMoney = (TextView) itemView.findViewById(R.id.txtAccMoney);
        edit = (ImageView) itemView.findViewById(R.id.accMenuImg);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
