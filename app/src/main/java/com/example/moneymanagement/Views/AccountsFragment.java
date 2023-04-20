package com.example.moneymanagement.Views;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moneymanagement.Model.Account;
import com.example.moneymanagement.R;
import com.example.moneymanagement.ViewModel.AccountViewModel;
import com.example.moneymanagement.firebaseHelper.FirebaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AccountsFragment extends Fragment {

    private RecyclerView recycleracc;
    private FloatingActionButton addAccBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);
        recycleracc = view.findViewById(R.id.revAcc);
        addAccBtn = view.findViewById(R.id.addAccFBtn);

        new FirebaseHelper().readData(new FirebaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Account> accounts, List<String> keys) {
                new AccountViewModel().setConfig(recycleracc, getActivity(), accounts, keys);
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

        addAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
                navController.navigate(R.id.addUserFragment);
            }
        });

        return view;
    }

}