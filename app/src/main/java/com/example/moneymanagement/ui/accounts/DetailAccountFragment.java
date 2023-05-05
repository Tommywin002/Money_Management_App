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

import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.FragmentDetailAccountBinding;

public class DetailAccountFragment extends Fragment {

    private FragmentDetailAccountBinding binding;
    private AccountsViewModel accountsViewModel = new AccountsViewModel();
    private static String id;
    public static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding =  FragmentDetailAccountBinding.inflate(inflater, container, false);
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
        Bundle bundle = getArguments();
        binding.accNameEdt.setText(bundle.getString("account"));
        binding.accNumberEdt.setText(bundle.getString("money"));
        id = bundle.getString("id");

        binding.confirmImg.setOnClickListener(view->{
            initViewModel();
        });

        binding.cancelImg.setOnClickListener(view->{
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment);
            navController.popBackStack();
        });
    }

    private void initViewModel(){
        String account = binding.accNameEdt.getText().toString().trim();
        String money = binding.accNumberEdt.getText().toString().trim();
        accountsViewModel.editData(account, money, id);
    }
}