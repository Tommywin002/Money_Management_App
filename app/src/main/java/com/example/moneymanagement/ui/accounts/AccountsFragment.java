package com.example.moneymanagement.ui.accounts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.FragmentAccountsBinding;
import com.example.moneymanagement.model.Account;
import com.example.moneymanagement.ui.home.transaction.TransactionFragment;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsFragment extends Fragment {

    private AccountAdapter accountAdapter;
    private FragmentAccountsBinding binding;
    private AccountsViewModel accountsViewModel;
    private final CharSequence[] dialogItem = {"Edit", "Delete"};
    public static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAccountsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        context = getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        initViewModel();

    }

    private void initUI() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
        binding.revAcc.setLayoutManager(layoutManager);
        binding.revAcc.addItemDecoration(itemDecoration);

        binding.addAccFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment);
                navController.navigate(R.id.addUserFragment);
            }
        });
    }

    private void initViewModel() {
        accountsViewModel = new ViewModelProvider(this).get(AccountsViewModel.class);
        accountsViewModel.getData().observe(requireActivity(), new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                accountAdapter = new AccountAdapter(getContext(), accounts);

                int total = 0;
                for(Account account : accounts){
                    total += Integer.parseInt(account.getMoney());
                }
                binding.totalMoney.setText(String.valueOf(total));

                accountAdapter.setDialog(new AccountAdapter.Dialog() {
                    @Override
                    public void onClick(int pos) {
                        String account = accounts.get(pos).getName();
                        String money = accounts.get(pos).getMoney();
                        String id = accounts.get(pos).getId();
                        Bundle bundle = new Bundle();
                        bundle.putString("account", account);
                        bundle.putString("money", money);
                        bundle.putString("id", id);

                        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                        dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                        editAccount(bundle);
                                        break;

                                    case 1:
                                        deleteAccount(account, money, id);
                                        break;
                                }
                            }
                        });
                        dialog.show();
                    }
                });
                binding.revAcc.setAdapter(accountAdapter);
            }
        });
    }

    private void editAccount(Bundle bundle) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        navController.navigate(R.id.detailAccountFragment, bundle);
    }

    private void deleteAccount(String account, String money, String id){
        new androidx.appcompat.app.AlertDialog.Builder(requireActivity())
                .setTitle("Are you sure?")
                .setMessage("If you press 'Yes', this account will disapear.")
                .setPositiveButton("Yes", (dialog, which) -> {
                    accountsViewModel.deleteData(account, money, id);
                    //reloadData();
                    dialog.dismiss();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss()).show();
    }

}