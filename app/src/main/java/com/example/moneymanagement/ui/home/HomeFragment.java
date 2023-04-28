package com.example.moneymanagement.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymanagement.R;
import com.example.moneymanagement.adapter.IncomeAdapter;
import com.example.moneymanagement.databinding.FragmentHomeBinding;
import com.example.moneymanagement.model.Income;

import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private IncomeAdapter incomeAdapter;
    private IncomeViewModel incomeViewModel;
    public static boolean checkTr = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        initViewModel();
    }

    private void conductTransaction(){
        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        navController.navigate(R.id.transactionFragment);
    }

    private void initUI(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        binding.rvTransaction.setLayoutManager(layoutManager);
        binding.rvTransaction.addItemDecoration(itemDecoration);

        binding.income.setOnClickListener(view->{
           conductTransaction();
        });
    }

    private void initViewModel(){
        incomeViewModel = new ViewModelProvider(this).get(IncomeViewModel.class);
        incomeViewModel.getIncomeLiveData().observe(requireActivity(), new Observer<List<Income>>() {
            @Override
            public void onChanged(List<Income> incomes) {
                incomeAdapter = new IncomeAdapter(getContext(), incomes);
                binding.rvTransaction.setAdapter(incomeAdapter);
            }
        });
    }
}