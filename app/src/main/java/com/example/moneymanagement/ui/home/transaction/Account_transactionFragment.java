package com.example.moneymanagement.ui.home.transaction;

import android.content.Context;
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
import android.widget.ImageView;

import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.FragmentAccountTransactionBinding;
import com.example.moneymanagement.databinding.FragmentAccountsBinding;
import com.example.moneymanagement.model.Account;
import com.example.moneymanagement.ui.accounts.AccountAdapter;
import com.example.moneymanagement.ui.accounts.AccountsViewModel;

import java.util.List;

public class Account_transactionFragment extends Fragment {

    private AccountAdapter accountAdapter;
    private FragmentAccountTransactionBinding binding;
    private AccountsViewModel accountsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAccountTransactionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
        initViewModel();
    }

    private void initUI(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        binding.recycleViewAcc.setLayoutManager(layoutManager);
        binding.recycleViewAcc.addItemDecoration(itemDecoration);

        binding.backImg.setOnClickListener(view->{
            NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
            navController.popBackStack();
        });
    }

    private void initViewModel(){
        accountsViewModel = new ViewModelProvider(this).get(AccountsViewModel.class);
        accountsViewModel.getAccountLiveData().observe(requireActivity(), new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                accountAdapter = new AccountAdapter(getContext(), accounts);
                binding.recycleViewAcc.setAdapter(accountAdapter);
            }
        });
    }
}