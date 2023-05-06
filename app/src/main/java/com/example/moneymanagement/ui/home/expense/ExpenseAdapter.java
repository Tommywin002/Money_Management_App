package com.example.moneymanagement.ui.home.expense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moneymanagement.databinding.ExpenseItemBinding;
import com.example.moneymanagement.model.Expense;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>{

    private Context context;
    private List<Expense> expenseList;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public ExpenseAdapter(Context context, List<Expense> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }

    public void setDatalist(List<Expense> expenseList){
        this.expenseList = expenseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExpenseAdapter.ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ExpenseItemBinding viewBinding = ExpenseItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ExpenseAdapter.ExpenseViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ExpenseViewHolder holder, int position) {
        holder.binding.txtAccount.setText(expenseList.get(position).getAccount());
        holder.binding.txtCategory.setText(expenseList.get(position).getCategory());
        holder.binding.txtMoney.setText(expenseList.get(position).getMoney());
        holder.binding.txtAccount.setText(expenseList.get(position).getAccount());
        holder.binding.txtDate.setText(expenseList.get(position).getDate());
        Glide.with(context).load(expenseList.get(position).getImgId()).into(holder.binding.imgExpense);
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {

        private ExpenseItemBinding binding;

        public ExpenseViewHolder(@NonNull ExpenseItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.expenseLayout.setOnClickListener(view->{
                if (dialog != null) {
                    dialog.onClick(getLayoutPosition());
                }
            });
        }

    }
}
