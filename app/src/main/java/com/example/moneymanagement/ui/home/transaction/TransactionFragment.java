package com.example.moneymanagement.ui.home.transaction;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.FragmentTransactionBinding;
import com.example.moneymanagement.model.Account;
import com.example.moneymanagement.ui.accounts.AccountsViewModel;

import org.checkerframework.checker.units.qual.A;

import java.util.Calendar;
import java.util.List;

public class TransactionFragment extends Fragment {

    private FragmentTransactionBinding binding;
    private TransactionViewModel transactionViewModel;
    private AccountsViewModel accountsViewModel;
    private List<Account> lstAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTransactionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

        /*conduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moneyEdt.getText().toString().length() == 0){
                    Toast.makeText(getActivity(), "Please type in the amount of money", Toast.LENGTH_LONG).show();
                }
                else{
                    if(HomeFragment.checkTr == true){

                    }
                    else{
                        Long moneyInput = Long.parseLong(moneyEdt.getText().toString());
                        Long remainedMoney = Long.parseLong(bMoney) - moneyInput;
                        if(remainedMoney < 0){
                            Toast.makeText(getActivity(), "Not enough money to carry out the transaction", Toast.LENGTH_LONG).show();
                        }
                        else{
                            //new ExpendViewModel().conductTheTransaction(bName, cateName, String.valueOf(moneyInput), dateTime.getText().toString(), String.valueOf(HomeFragment.resourceId), String.valueOf(remainedMoney), bKey);
                            NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
                            navController.navigate(R.id.homeFragment);
                        }
                    }
                }
            }
        });*/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        initViewModel();
        initData();
    }

    private void initUI(){
        binding.backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
                navController.popBackStack();
            }
        });
    }


    private void initViewModel(){
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.getData().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, strings);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.spinnerAccount.setAdapter(adapter);
            }
        });
    }

    private void initData(){
        Bundle bundle = getArguments();
        binding.accountName.setText(bundle.getString("account"));
        binding.categoryName.setText(bundle.getString("money"));
    }

    private void getCurrentDate(TextView textView){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        String currentDate = String.format("%02d/%02d/%d", day, month, year);
        textView.setText(currentDate);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ngày hiện tại của hệ thống
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Lấy ngày được chọn và hiển thị nó trên TextView
                                String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                                textView.setText(selectedDate);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
                System.out.println(textView.getText().toString());
            }
        });
    }
}