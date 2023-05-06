package com.example.moneymanagement.ui.history;

import android.app.AlertDialog;
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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;

import com.example.moneymanagement.databinding.FragmentAccountsBinding;
import com.example.moneymanagement.databinding.FragmentHistoryBinding;
import com.example.moneymanagement.model.Account;
import com.example.moneymanagement.model.Expense;
import com.example.moneymanagement.R;
import com.example.moneymanagement.model.Income;
import com.example.moneymanagement.ui.accounts.AccountAdapter;
import com.example.moneymanagement.ui.accounts.AccountsViewModel;
import com.example.moneymanagement.ui.home.expense.ExpenseAdapter;
import com.example.moneymanagement.ui.home.income.IncomeAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private ExpenseAdapter expenseAdapter;
    private IncomeAdapter incomeAdapter;
    private ExpenseViewModel expenseViewModel;
    private IncomeViewModel incomeViewModel;
    private int lastSelected = 0;
    private boolean incomeSelected = true;
    private String searchKey = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        initIncomeViewModel(searchKey,lastSelected);
    }

    private void initIncomeViewModel(String searchKey,int position) {
        incomeViewModel = new ViewModelProvider(this).get(IncomeViewModel.class);
        incomeViewModel.getIncomeLiveData().observe(requireActivity(), new Observer<List<Income>>() {
            @Override
            public void onChanged(List<Income> incomes) {
                if (!Objects.equals(searchKey, "")){
                    incomes = searchIncome(searchKey, incomes);
                }
                incomeAdapter = new IncomeAdapter(getContext(), sortIncome(incomes,position) );
                List<Income> finalIncomes = incomes;
                incomeAdapter.setDialog(new IncomeAdapter.Dialog() {
                    @Override
                    public void onClick(int pos) {
                        final CharSequence[] dialogItem = {"Detail","Delete"};
                        String account = finalIncomes.get(pos).getAccount();
                        String money = finalIncomes.get(pos).getMoney();
                        String id = finalIncomes.get(pos).getId();
                        Bundle bundle = new Bundle();
                        bundle.putString("account", account);
                        bundle.putString("money", money);
                        bundle.putString("id", id);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                        dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                switch (i){
                                    case 0:
                                        incomeViewModel.deleteIncome(finalIncomes.get(pos).getId());
                                        break;
                                }
                            }
                        });
                        dialog.show();
                    }
                });
                binding.rvTransactionHistory.setAdapter(incomeAdapter);
            }
        });
    }


    private void initExpenseViewModel(String searchKey,int position) {
        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
        expenseViewModel.getExpenseLiveData().observe(requireActivity(), new Observer<List<Expense>>() {
            @Override
            public void onChanged(List<Expense> expenses) {
                if (!Objects.equals(searchKey, "")){
                    expenses = searchExpense(searchKey,expenses);
                }
                expenseAdapter = new ExpenseAdapter(getContext(), sortExpense(expenses,position));
                List<Expense> finalExpenses = expenses;
                expenseAdapter.setDialog(new ExpenseAdapter.Dialog() {
                    @Override
                    public void onClick(int pos) {
                        final CharSequence[] dialogItem = {"Detail","Delete"};
                        String account = finalExpenses.get(pos).getAccount();
                        String money = finalExpenses.get(pos).getMoney();
                        String id = finalExpenses.get(pos).getId();
                        Bundle bundle = new Bundle();
                        bundle.putString("account", account);
                        bundle.putString("money", money);
                        bundle.putString("id", id);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                        dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                switch (i){
                                    case 0:
                                        expenseViewModel.deleteExpense(finalExpenses.get(pos).getId());
                                        break;
                                }
                            }
                        });
                        dialog.show();
                    }
                });
                binding.rvTransactionHistory.setAdapter(expenseAdapter);
            }
        });
    }
    private void initUI(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        binding.rvTransactionHistory.setLayoutManager(layoutManager);
        binding.rvTransactionHistory.addItemDecoration(itemDecoration);

        binding.exTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initExpenseViewModel(searchKey, lastSelected);
                incomeSelected = false;
            }
        });
        binding.inTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initIncomeViewModel(searchKey, lastSelected);
                incomeSelected = true;
            }
        });
        binding.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lastSelected = position;
                if (incomeSelected) initIncomeViewModel(searchKey, lastSelected);
                else initExpenseViewModel(searchKey, lastSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchKey = String.valueOf(binding.edtSearch.getText());
                if (incomeSelected) initIncomeViewModel(searchKey, lastSelected);
                else initExpenseViewModel(searchKey, lastSelected);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public List<Income> sortIncome(List<Income> lstIncome, int position){
        if (position==1){
            Collections.sort(lstIncome, new Comparator<Income>() {
                @Override
                public int compare(Income e1, Income e2) {
                    return Integer.compare(Integer.parseInt(e1.getMoney()),Integer.parseInt(e2.getMoney()));
                }
            });
        }
        if (position==2){
            Collections.sort(lstIncome, new Comparator<Income>() {
                @Override
                public int compare(Income e1, Income e2) {
                    return Integer.compare(Integer.parseInt(e2.getMoney()),Integer.parseInt(e1.getMoney()));
                }
            });
        }
        return lstIncome;
    }
    public List<Expense> sortExpense(List<Expense> lstExpense, int position){
        if (position==1){
            Collections.sort(lstExpense, new Comparator<Expense>() {
                @Override
                public int compare(Expense e1, Expense e2) {
                    return Integer.compare(Integer.parseInt(e1.getMoney()),Integer.parseInt(e2.getMoney()));
                }
            });
        }
        if (position==2){
            Collections.sort(lstExpense, new Comparator<Expense>() {
                @Override
                public int compare(Expense e1, Expense e2) {
                    return Integer.compare(Integer.parseInt(e2.getMoney()),Integer.parseInt(e1.getMoney()));
                }
            });
        }
        return lstExpense;
    }
    public List<Income> searchIncome(String text, List<Income> lstIncome){
        ArrayList<Income> search = new ArrayList<>();
        for(Income ts : lstIncome){
            if(ts.getCategory().toLowerCase().contains(text.toLowerCase())){
                search.add(ts);
            }
            else if(ts.getMoney().contains(text)){
                search.add(ts);
            }
            else if(ts.getAccount().toLowerCase().contains(text.toLowerCase())){
                search.add(ts);
            }
        }
       return search;
    }

    public List<Expense> searchExpense(String text, List<Expense> lstExpense){
        ArrayList<Expense> search = new ArrayList<>();
        for(Expense ts : lstExpense){
            if(ts.getCategory().toLowerCase().contains(text.toLowerCase())){
                search.add(ts);
            }
            else if(ts.getMoney().contains(text)){
                search.add(ts);
            }
            else if(ts.getAccount().toLowerCase().contains(text.toLowerCase())){
                search.add(ts);
            }
        }
        return search;
    }


}


