package com.example.moneymanagement.ui.home;

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

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymanagement.R;
import com.example.moneymanagement.model.Account;
import com.example.moneymanagement.ui.accounts.AccountsViewModel;
import com.example.moneymanagement.ui.home.expense.ExpenseAdapter;
import com.example.moneymanagement.ui.home.expense.ExpenseViewModel;
import com.example.moneymanagement.ui.home.income.IncomeAdapter;
import com.example.moneymanagement.databinding.FragmentHomeBinding;
import com.example.moneymanagement.model.Expense;
import com.example.moneymanagement.model.Income;
import com.example.moneymanagement.ui.home.income.IncomeViewModel;

import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private IncomeAdapter incomeAdapter;
    private ExpenseAdapter expenseAdapter;
    private IncomeViewModel incomeViewModel;
    private ExpenseViewModel expenseViewModel;
    private AccountsViewModel accountsViewModel;
    public static boolean check = true, check1 = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
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
        binding.rvTransaction.setLayoutManager(layoutManager);
        binding.rvTransaction.addItemDecoration(itemDecoration);

        binding.swapImg.setOnClickListener(view->{
            changeData(check1);
        });

        binding.addFBtn.setOnClickListener(view->{
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment);
            navController.navigate(R.id.account_transactionFragment);
        });

        binding.revealImg.setOnClickListener(view->{
            if(binding.totalMoneyEdt.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                binding.totalMoneyEdt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
                binding.revealImg.setImageResource(R.drawable.hide);
            }
            else{
                binding.totalMoneyEdt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                binding.revealImg.setImageResource(R.drawable.eye);
            }
        });

        binding.moreBtn.setOnClickListener(view->{
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment);
            navController.navigate(R.id.historyFragment);
        });
    }

    private void initViewModel() {
        incomeViewModel = new ViewModelProvider(this).get(IncomeViewModel.class);
        incomeViewModel.getIncomeLiveData().observe(requireActivity(), new Observer<List<Income>>() {
            @Override
            public void onChanged(List<Income> incomes) {
                incomeAdapter = new IncomeAdapter(getContext(), incomes);
                binding.rvTransaction.setAdapter(incomeAdapter);
            }
        });

        accountsViewModel = new AccountsViewModel();
        accountsViewModel.getData().observe(requireActivity(), new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> accounts) {
                int total = 0;
                for(Account account : accounts){
                    total += Integer.parseInt(account.getMoney());
                }
                binding.totalMoneyEdt.setText(String.valueOf(total));
            }
        });
    }

    private void changeData(boolean checked) {
        if(check != checked){
            incomeViewModel = new ViewModelProvider(this).get(IncomeViewModel.class);
            incomeViewModel.getIncomeLiveData().observe(requireActivity(), new Observer<List<Income>>() {
                @Override
                public void onChanged(List<Income> incomes) {
                    incomeAdapter = new IncomeAdapter(getContext(), incomes);
                    incomeAdapter.setDatalist(incomes);
                    binding.rvTransaction.setAdapter(incomeAdapter);
                }
            });
            check = true;
        }
        else{
            expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
            expenseViewModel.getExpenseLiveData().observe(requireActivity(), new Observer<List<Expense>>() {
                @Override
                public void onChanged(List<Expense> expenses) {
                    expenseAdapter = new ExpenseAdapter(getContext(), expenses);
                    expenseAdapter.setDatalist(expenses);
                    binding.rvTransaction.setAdapter(expenseAdapter);
                }
            });
            check = false;
        }
    }

}