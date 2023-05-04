package com.example.moneymanagement.ui.setting;

import android.content.Intent;
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
import com.example.moneymanagement.databinding.FragmentAccountsBinding;
import com.example.moneymanagement.databinding.FragmentLanguageBinding;
import com.example.moneymanagement.ui.setting.LanguageManagement;


public class LanguageFragment extends Fragment {
    FragmentLanguageBinding binding;
    LanguageManagement languageManagement = new LanguageManagement();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLanguageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeLanguage();
    }
    public void changeLanguage(){
        binding.engbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageManagement.updateResource(getActivity(),"en");
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
                navController.navigate(R.id.settingFragment);
            }
        });
        binding.viebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageManagement.updateResource(getActivity(), "vi");
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
                navController.navigate(R.id.settingFragment);
            }
        });
    }
}