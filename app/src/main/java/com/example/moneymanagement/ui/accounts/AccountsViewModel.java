package com.example.moneymanagement.ui.accounts;

import android.widget.ArrayAdapter;

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
    private List<Account> lstAccounts = new ArrayList<>();
    private ArrayAdapter<String> accNames;
    private String[] acc = null;

    public LiveData<List<Account>> getAccountLiveData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Account").get().addOnCompleteListener(task -> {
            lstAccounts.clear();
            if(task.isSuccessful()){
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                    Account account = new Account(documentSnapshot.getString("Name"), documentSnapshot.getString("Money"));
                    account.setId(documentSnapshot.getId());
                    lstAccounts.add(account);
                }
                listAccountsLiveData.postValue(lstAccounts);

                /*for(int i = 0; i < lstAccounts.size(); i++){
                    acc[i] = lstAccounts.get(i).getName();
                }
                System.out.println(acc[0] + " " + acc[1]);
                accNames = new ArrayAdapter<String>(accNames.getContext(), android.R.layout.simple_spinner_item, acc);*/
            }
            else {

            }
        });
        return listAccountsLiveData;
    }

    public ArrayAdapter<String> getAccNames(){
        return accNames;
    }
}
