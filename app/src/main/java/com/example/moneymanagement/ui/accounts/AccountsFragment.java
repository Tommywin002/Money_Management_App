package com.example.moneymanagement.ui.accounts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.FragmentAccountsBinding;
import com.example.moneymanagement.model.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AccountsFragment extends Fragment {
    FirebaseFirestore db ;
    AccountAdapter accountAdapter;
    List<Account> accountList = new ArrayList<>();
    FragmentAccountsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseFirestore.getInstance();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        binding.revAcc.setLayoutManager(layoutManager);
        binding.revAcc.addItemDecoration(itemDecoration);

        accountAdapter = new AccountAdapter(getContext(), accountList);
        binding.revAcc.setAdapter(accountAdapter);
        initUI();
        accountAdapter.setDialog(new AccountAdapter.Dialog() {
            @Override
            public void onClick(int pos) {

            }
        });


        binding.addAccFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
                navController.navigate(R.id.addUserFragment);
            }
        });
    }

    private void initUI(){
        db.collection("Account").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                accountList.clear();
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        Account account = new Account(documentSnapshot.getString("Name"), documentSnapshot.getString("Money"));
                        account.setId(documentSnapshot.getId());
                        accountList.add(account);
                    }
                    accountAdapter.notifyDataSetChanged();
                }
                else {

                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        initUI();
    }
}