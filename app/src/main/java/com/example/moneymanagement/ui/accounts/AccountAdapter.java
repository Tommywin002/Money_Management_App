package com.example.moneymanagement.ui.accounts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.AccountListItemBinding;
import com.example.moneymanagement.model.Account;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder>{
    private final Context context;
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

    public void setDataList(List<Account> lst){
        this.accountList = lst;
        notifyDataSetChanged();
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
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {

        private final AccountListItemBinding binding;

        public AccountViewHolder(@NonNull AccountListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.accItemLayout.setOnClickListener(view->{
                if(dialog != null){
                    dialog.onClick(getLayoutPosition());
                }
            });
            binding.accMenuImg.setOnClickListener(view->{
                if(dialog != null){
                    dialog.onClick(getLayoutPosition());
                    //showPopupMenu(view);
                }
            });

        }
    }

    private void showPopupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.inflate(R.menu.account_menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.editMnu:
                        return true;

                    case R.id.deleteMnu:
                        return true;

                    default:
                        return false;
                }

            }
        });

        popupMenu.show();
    }
}
