package com.example.moneymanagement.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.rvTransactionHistory);
        incometxt = view.findViewById(R.id.inTxt);
        outtxt = view.findViewById(R.id.exTxt);
        new FirebaseHelper_Transaction().readData(new FirebaseHelper_Transaction.DataStatus() {
            @Override
            public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {
                new ExpendViewModel().setConfig(recyclerView, getActivity(), transactions, keys);
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
                        new IncomeVIewModel().setConfig(recyclerView, getActivity(), transactions, keys);
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
                        new ExpendViewModel().setConfig(recyclerView, getActivity(), transactions, keys);
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