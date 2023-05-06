package com.example.moneymanagement.ui.accounts;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.FragmentAddAccountBinding;

import java.util.HashMap;
import java.util.Map;

public class AddAccountFragment extends Fragment {

    private FragmentAddAccountBinding binding;
    private AccountsViewModel accountsViewModel;
    public static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        context = getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    private void initUI() {
        accountsViewModel = new AccountsViewModel();
        binding.confirmImg.setOnClickListener(view->{
            if(binding.accNameEdt.getText().toString().trim().length() == 0 || binding.accNumberEdt.getText().toString().trim().length() == 0){
                Toast.makeText(requireActivity(), "You must input all fields!", Toast.LENGTH_LONG).show();
            }
            else{
                Map<String, String> newAccount = new HashMap<>();
                newAccount.put("Name", binding.accNameEdt.getText().toString().trim());
                newAccount.put("Money", binding.accNumberEdt.getText().toString().trim());
                accountsViewModel.addData(newAccount);

            }

        });

        binding.cancelImg.setOnClickListener(view->{
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment);
            navController.popBackStack();
        });
    }

}