package com.example.moneymanagement.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moneymanagement.Model.Transaction;
import com.example.moneymanagement.R;
import com.example.moneymanagement.ViewModel.AccountViewModel;
import com.example.moneymanagement.ViewModel.ExpendViewModel;
import com.example.moneymanagement.ViewModel.IncomeVIewModel;
import com.example.moneymanagement.firebaseHelper.FirebaseHelper_Transaction;
import com.example.moneymanagement.itemView.IncomeItemView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView incometxt, outtxt;
    private Spinner spSapXep;
    private int lastSelected = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.rvTransactionHistory);
        incometxt = view.findViewById(R.id.inTxt);
        outtxt = view.findViewById(R.id.exTxt);
        spSapXep = view.findViewById(R.id.spinner2);
        String[] options = {"Tăng dần", "Giảm dần"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSapXep.setAdapter(adapter);

        spSapXep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lastSelected = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        new FirebaseHelper_Transaction().readData(new FirebaseHelper_Transaction.DataStatus() {
            @Override
            public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {
                new ExpendViewModel().setConfig(recyclerView, getActivity(), transactions, keys, lastSelected);
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

        incometxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseHelper_Transaction().readData2(new FirebaseHelper_Transaction.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {
                        new IncomeVIewModel().setConfig(recyclerView, getActivity(), transactions, keys,lastSelected);
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
        });
        outtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseHelper_Transaction().readData(new FirebaseHelper_Transaction.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {
                        new ExpendViewModel().setConfig(recyclerView, getActivity(), transactions, keys,lastSelected);
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
        });

        return view;
    }
}
