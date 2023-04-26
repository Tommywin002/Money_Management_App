package com.example.moneymanagement.ui.accounts;

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
import java.util.List;

public class AccountsFragment extends Fragment {
    AccountAdapter accountAdapter;
    FragmentAccountsBinding binding;
    AccountsViewModel accountsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAccountsBinding.inflate(inflater, container, false);
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
        binding.revAcc.setLayoutManager(layoutManager);
        binding.revAcc.addItemDecoration(itemDecoration);

        binding.addAccFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
                navController.navigate(R.id.addUserFragment);
            }
        });

    }

    private void initViewModel(){
        accountsViewModel = new ViewModelProvider(this).get(AccountsViewModel.class);
        accountsViewModel.getAccountLiveData().observe(requireActivity(), new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                accountAdapter = new AccountAdapter(getContext(), accounts);
                binding.revAcc.setAdapter(accountAdapter);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        initUI();
    }
}