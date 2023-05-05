package com.example.moneymanagement.ui.history;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.FragmentDetailAccountBinding;
import com.example.moneymanagement.databinding.FragmentDetailTransactionBinding;
import com.example.moneymanagement.databinding.FragmentTransactionBinding;
import com.example.moneymanagement.ui.home.category.DataHolder;

import java.util.Calendar;
import java.util.List;

public class DetailTransactionFragment extends Fragment {

    private FragmentDetailTransactionBinding binding;
    public String account, category, money, img, date, type, id;
    public static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailTransactionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        context = getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    private void initUI(){

        initData();

        binding.backImg.setOnClickListener(view-> {
            NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
            navController.popBackStack();
        });

        binding.categoryName.setOnClickListener(view->{
            NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
            navController.navigate(R.id.categoryFragment);
        });




    }
    private void initData(){
        category = DataHolder.getInstance().getCategoryName();
        img = DataHolder.getInstance().getImgId();
        type = DataHolder.getInstance().getType();
        binding.categoryName.setText(category);
        binding.textView10.setText(type);
        Glide.with(getContext()).load(img).into(binding.imgcheck);

        Bundle bundle = getArguments();
        account = bundle.getString("account");
        money = bundle.getString("money");
        id = bundle.getString("id");
        binding.accountName.setText(account);
        binding.edtMoneyInput.setText(money);
    }






}