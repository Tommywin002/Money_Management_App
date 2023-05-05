package com.example.moneymanagement.ui.accounts;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymanagement.model.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountsViewModel extends ViewModel {

    private MutableLiveData<List<Account>> listAccountsLiveData = new MutableLiveData<>();
    private List<Account> lstAccounts = new ArrayList<>();
    private ArrayAdapter<String> accNames;
    private String[] acc = null;

    public LiveData<List<Account>> getAccountLiveData(){
        lstAccounts = new ArrayList<>();
        String uid = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("User").document(uid).collection("Account").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                lstAccounts.clear();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        Account account = new Account(documentSnapshot.getString("Name"), documentSnapshot.getString("Money"));
                        account.setId(documentSnapshot.getId());
                        lstAccounts.add(account);
                    }
                    listAccountsLiveData.postValue(lstAccounts);
                }
            }
        });
        return listAccountsLiveData;
    }

    public ArrayAdapter<String> getAccNames(){
        return accNames;
    }
}
