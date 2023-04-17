package com.example.moneymanagement.itemView;


import static com.example.moneymanagement.ViewModel.IncomeVIewModel.mcontext;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.Model.Transaction;
import com.example.moneymanagement.R;


public class IncomeItemView extends RecyclerView.ViewHolder{

    private String key;
    private TextView tCategory;
    private TextView tAccount;
    private TextView tMoney;
    private TextView tDate;
    private ImageView tImg;

    public IncomeItemView(ViewGroup parent) {
        super(LayoutInflater.from(mcontext).inflate(R.layout.income_item, parent, false));
        tCategory = (TextView) itemView.findViewById(R.id.txtCategory);
        tAccount = (TextView) itemView.findViewById(R.id.txtAccount);
        tMoney = (TextView) itemView.findViewById(R.id.txtMoney);
        tDate = (TextView) itemView.findViewById(R.id.txtDate);
        tImg = (ImageView) itemView.findViewById(R.id.imgIncome);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(mcontext, IncomeDetail.class);
                intent.putExtra("key", key);
                //intent.putExtra("id", tId.getText().toString());
                intent.putExtra("name", tAccount.getText().toString());
                intent.putExtra("category", tCategory.getText().toString());
                intent.putExtra("money", tMoney.getText().toString());
                intent.putExtra("date", tDate.getText().toString());
                intent.putExtra("imgId", tImg.getDrawable().toString());

                mcontext.startActivity(intent);*/
            }
        });

    }
    public void bind(Transaction transaction, String key){
        this.key = key;
        tCategory.setText(transaction.getCategory());
        tAccount.setText(transaction.getAccount());
        tMoney.setText(transaction.getMoney() + " đ");
        tDate.setText(transaction.getDate());
        //tImg.setImageResource(Integer.parseInt(transaction.getImgId()));
    }

}
