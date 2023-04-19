package com.example.moneymanagement.Views;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.moneymanagement.Model.Account;
import com.example.moneymanagement.R;
import com.example.moneymanagement.ViewModel.AccountViewModel;
import com.example.moneymanagement.firebaseHelper.FirebaseHelper;

import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private ImageView refresh;
    private TableLayout cate0, cate1, cate2, cate3;
    private TextView text0, text1, text2, text3;
    public static int resourceId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rvTransaction);
        refresh = view.findViewById(R.id.refreshImg);

        cate0 = (TableLayout) view.findViewById(R.id.income);
        cate1 = (TableLayout) view.findViewById(R.id.cate1);
        cate2 = (TableLayout) view.findViewById(R.id.cate2);
        cate3 = (TableLayout) view.findViewById(R.id.cate3);

        text0 = (TextView) view.findViewById(R.id.incomeEdt);
        text1 = (TextView) view.findViewById(R.id.cate1Edt);
        text2 = (TextView) view.findViewById(R.id.cate2Edt);
        text3 = (TextView) view.findViewById(R.id.cate3Edt);

        cate0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        return view;
    }
}