package com.example.moneymanagement.ui.home.income;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moneymanagement.databinding.IncomeItemBinding;
import com.example.moneymanagement.model.Expense;
import com.example.moneymanagement.model.Income;
import com.example.moneymanagement.ui.accounts.AccountAdapter;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder>{

    private Context context;
    private List<Income> incomeList;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public IncomeAdapter(Context context, List<Income> incomeList) {
        this.context = context;
        this.incomeList = incomeList;
    }

    public void setDatalist(List<Income> incomeList){
        this.incomeList = incomeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IncomeItemBinding viewBinding = IncomeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new IncomeViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeViewHolder holder, int position) {
        holder.binding.txtAccount.setText(incomeList.get(position).getAccount());
        holder.binding.txtCategory.setText(incomeList.get(position).getCategory());
        holder.binding.txtMoney.setText(incomeList.get(position).getMoney());
        holder.binding.txtAccount.setText(incomeList.get(position).getAccount());
        holder.binding.txtDate.setText(incomeList.get(position).getDate());
        Glide.with(context).load(incomeList.get(position).getImgId()).into(holder.binding.imgIncome);
        holder.binding.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String account = holder.binding.txtAccount.getText().toString();
//                String category = holder.binding.txtCategory.getText().toString();
//                Toast.makeText(context, account + " - " + category, Toast.LENGTH_SHORT).show();
                if (dialog!=null){
                    dialog.onClick(holder.getLayoutPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return incomeList.size();
    }

    public class IncomeViewHolder extends RecyclerView.ViewHolder {

        private IncomeItemBinding binding;

        public IncomeViewHolder(@NonNull IncomeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
