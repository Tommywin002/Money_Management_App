package com.example.moneymanagement.Views;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.moneymanagement.R;
import com.example.moneymanagement.ViewModel.AccountViewModel;

public class AddUserFragment extends Fragment {

    private ImageView cancel, confirm;
    private EditText accMoney, accName;
    public static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        cancel = view.findViewById(R.id.cancelImg);
        confirm = view.findViewById(R.id.confirmImg);
        accMoney = view.findViewById(R.id.accNumberEdt);
        accName = view.findViewById(R.id.accNameEdt);
        context = getContext();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AccountViewModel().addNewAccount(accName.getText().toString(), accMoney.getText().toString());
                navigateToPreviousFragment();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
                navController.navigate(R.id.accountsFragment);
            }
        });

        return view;
    }

    private void navigateToPreviousFragment(){
        NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
        navController.popBackStack();
    }
}