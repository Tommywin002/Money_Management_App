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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.FragmentTransactionBinding;
import com.example.moneymanagement.ui.home.category.DataHolder;

import java.util.Calendar;
import java.util.List;

public class TransactionFragment extends Fragment {

    private FragmentTransactionBinding binding;
    private TransactionViewModel transactionViewModel;
    public String account, category, money, img, date, type, id;
    public static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTransactionBinding.inflate(inflater, container, false);
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

    private void initUI(){

        initData();

        binding.backImg.setOnClickListener(view-> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
            navController.popBackStack();
        });

        binding.categoryName.setOnClickListener(view->{
            NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
            navController.navigate(R.id.categoryFragment);
        });

        binding.conductImg.setOnClickListener(view->{
            conductTransaction();
        });

    }

    private void initViewModel(){
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        /*transactionViewModel.getData().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, strings);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.spinnerAccount.setAdapter(adapter);
            }
        });*/
    }

    private void initData(){

        category = DataHolder.getInstance().getCategoryName();
        img = DataHolder.getInstance().getImgId();
        type = DataHolder.getInstance().getType();
        binding.categoryName.setText(category);
        binding.textView10.setText(type);
        Glide.with(getContext()).load(img).into(binding.imgcheck);

        Bundle bundle = getArguments();
        account = bundle.getString("account");
        money = bundle.getString("money");
        id = bundle.getString("id");
        binding.accountName.setText(account);

        getCurrentDate(binding.dateTxt);
    }

    private void getCurrentDate(TextView textView){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        String currentDate = String.format("%02d/%02d/%d", day, month, year);
        textView.setText(currentDate);

        /*textView.setOnClickListener(new View.OnClickListener() {
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
        });*/
    }

    private void conductTransaction(){
        int tMoney = Integer.parseInt(binding.edtMoneyInput.getText().toString());
        int newMoney = type.contains("Income") ? Integer.parseInt(money) + tMoney : Integer.parseInt(money) - tMoney;
        String date = String.valueOf(binding.dateTxt.getText());
        transactionViewModel.addTransaction(account, category, date, img, String.valueOf(tMoney), type, id, String.valueOf(newMoney));
    }
}