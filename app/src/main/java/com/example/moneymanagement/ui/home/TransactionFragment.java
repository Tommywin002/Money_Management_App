package com.example.moneymanagement.ui.home;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanagement.R;
import com.example.moneymanagement.ViewModel.ExpendViewModel;
import com.example.moneymanagement.ui.home.HomeFragment;

import java.util.Calendar;

public class TransactionFragment extends Fragment {

    private TextView accountName, categoryName, dateTime;
    private EditText moneyEdt, noteEdt;
    private ImageView back, conduct;
    private String bKey, bName, bMoney, cateName;
    public static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        accountName = view.findViewById(R.id.accountName);
        categoryName = view.findViewById(R.id.categoryName);
        dateTime = view.findViewById(R.id.dateTxt);
        moneyEdt = view.findViewById(R.id.edtMoneyInput);
        noteEdt = view.findViewById(R.id.noteEdt);
        back = view.findViewById(R.id.backImg);
        conduct = view.findViewById(R.id.conductImg);
        context = getContext();

        Bundle bundle = getArguments();
        bKey = bundle.getString("key");
        bName = bundle.getString("name");
        bMoney = bundle.getString("money");

        accountName.setText(bName);
        cateName = HomeFragment.cateName;
        categoryName.setText(cateName);

        getCurrentDate(dateTime);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
                navController.popBackStack();
            }
        });

        conduct.setOnClickListener(new View.OnClickListener() {
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
                            new ExpendViewModel().conductTheTransaction(bName, cateName, String.valueOf(moneyInput), dateTime.getText().toString(), String.valueOf(HomeFragment.resourceId), String.valueOf(remainedMoney), bKey);
                            NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
                            navController.navigate(R.id.homeFragment);
                        }
                    }
                }
            }
        });

        return view;
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