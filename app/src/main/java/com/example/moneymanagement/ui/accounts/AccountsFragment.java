package com.example.moneymanagement.ui.accounts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymanagement.data.model.Account;
import com.example.moneymanagement.R;
import com.example.moneymanagement.ViewModel.AccountViewModel;
import com.example.moneymanagement.data.services.FirebaseHelper;
import com.example.moneymanagement.databinding.FragmentAccountsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AccountsFragment extends Fragment {

    private FragmentAccountsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();

        binding.addAccFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
                navController.navigate(R.id.addUserFragment);
            }
        });
    }

    private void initUI(){

        new FirebaseHelper().readData(new FirebaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Account> accounts, List<String> keys) {
                new AccountViewModel().setConfig(binding.revAcc, getActivity(), accounts, keys);
            }

            @Override
            public void DataIsInsert() {

            }

            @Override
            public void DataIsUpdate() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }
}