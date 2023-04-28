package com.example.moneymanagement.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.moneymanagement.R;

public class Account_transactionFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageView back;
    public static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_transaction, container, false);
        context = getActivity();
        recyclerView = view.findViewById(R.id.recycleViewAcc);
        back = view.findViewById(R.id.backImg);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
                navController.popBackStack();
            }
        });

        return view;

    }
}