package com.example.moneymanagement.ui.accounts;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.moneymanagement.R;
import com.example.moneymanagement.model.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsViewModel extends ViewModel {

    private final MutableLiveData<List<Account>> listAccountsLiveData = new MutableLiveData<>();
    private List<Account> lstAccounts = new ArrayList<>();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public LiveData<List<Account>> getAccountLiveData() {
        String uid = FirebaseAuth.getInstance().getUid();
        db.collection("User").document(uid).collection("Account").get().addOnCompleteListener(task -> {
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
                Log.d("Error", "Load data (Account) fail");
            }
        });
        return listAccountsLiveData;
    }

    public void addData(Map newAccount) {
        db.collection("Account").add(newAccount).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@org.checkerframework.checker.nullness.qual.NonNull Task<DocumentReference> task) {
                NavController navController = Navigation.findNavController((Activity) AddAccountFragment.context, R.id.fragment);
                navController.popBackStack();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void editData(String account, String money, String id) {
        String uid = FirebaseAuth.getInstance().getUid();
        Map<String, String> newData = new HashMap<>();
        newData.put("Name", account);
        newData.put("Money", money);
        db.collection("User").document(uid).collection("Account").document(id).set(newData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                NavController navController = Navigation.findNavController((Activity) DetailAccountFragment.context, R.id.fragment);
                navController.popBackStack();
            }
        });
    }

    public void deleteData(String account, String money, String id) {
        Map<String, String> newData = new HashMap<>();
        newData.put("Name", account);
        newData.put("Money", money);
        String uid = FirebaseAuth.getInstance().getUid();
        db.collection("User").document(uid).collection("Account").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                return;
            }
        });
    }

}
