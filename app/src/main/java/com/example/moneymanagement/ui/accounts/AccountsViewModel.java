package com.example.moneymanagement.ui.accounts;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymanagement.model.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountsViewModel extends ViewModel {

    private MutableLiveData<List<Account>> listAccountsLiveData = new MutableLiveData<>();
    private List<Account> lstAccounts;

    public LiveData<List<Account>> getAccountLiveData(){
        lstAccounts = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Account").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                lstAccounts.clear();
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        Account account = new Account(documentSnapshot.getString("Name"), documentSnapshot.getString("Money"));
                        account.setId(documentSnapshot.getId());
                        lstAccounts.add(account);
                    }
                    listAccountsLiveData.postValue(lstAccounts);
                }
                else {

                }
            }
        });
        return listAccountsLiveData;
    }

}
