package com.example.moneymanagement.ui.history;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

import com.example.moneymanagement.data.model.Transaction;
import com.example.moneymanagement.R;
import com.example.moneymanagement.ViewModel.ExpendViewModel;
import com.example.moneymanagement.ViewModel.IncomeVIewModel;
import com.example.moneymanagement.adapter.IncomeAdapter;
import com.example.moneymanagement.adapter.TransactionAdapter;
import com.example.moneymanagement.data.services.FirebaseHelper_Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {
    private IncomeAdapter incomeAdapter = new IncomeAdapter();
    private TransactionAdapter transactionAdapter = new TransactionAdapter();
    private TextView incometxt, outtxt;

    private EditText eSearch;
    private ArrayList<Transaction> list;
    private RecyclerView recyclerView;
    public boolean checked = false;


    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    private Spinner spSapXep;
    private int lastSelected = -1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.rvTransactionHistory);
        eSearch = view.findViewById(R.id.edtSearch);
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
        eSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checked == false){
                    searchExpense(s.toString());
                }
                if(checked == true){
                    searchIncome(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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
                checked = true;
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
                checked = false;
            }
        });

        return view;
    }

    public void searchIncome(String text){
        ArrayList<Transaction> search = new ArrayList<>();
        ArrayList<String> searchKey = new ArrayList<>();
        for(String k : IncomeVIewModel.ks){
            searchKey.add(k);
        }
        for(Transaction ts : IncomeVIewModel.income){
            if(ts.getCategory().toLowerCase().contains(text.toLowerCase())){
                search.add(ts);
            }
            else if(ts.getMoney().contains(text.toString())){
                search.add(ts);
            }
            else if(ts.getAccount().toLowerCase().contains(text.toLowerCase())){
                search.add(ts);
            }
        }
        incomeAdapter.setDataList(search, searchKey);
        incomeAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(incomeAdapter);
    }

    public void searchExpense(String text){
        ArrayList<Transaction> search = new ArrayList<>();
        ArrayList<String> searchKey = new ArrayList<>();
        for(String k : ExpendViewModel.ks){
            searchKey.add(k);
        }
        for(Transaction ts : ExpendViewModel.expense){
            if(ts.getCategory().toLowerCase().contains(text.toLowerCase())){
                search.add(ts);
            }
            else if(ts.getMoney().contains(text.toString())){
                search.add(ts);
            }
            else if(ts.getAccount().toLowerCase().contains(text.toLowerCase())){
                search.add(ts);
            }
        }
        transactionAdapter.setDataList(search, searchKey);
        transactionAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(transactionAdapter);
    }

}

