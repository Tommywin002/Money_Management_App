package com.example.moneymanagement.ui.home.transaction;

import android.app.Activity;
import android.nfc.Tag;
import android.util.Log;

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionViewModel extends ViewModel {

    private MutableLiveData<List<String>> accName = new MutableLiveData<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private void loadData(){
        db.collection("Account").get().addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               List<String> dataList = new ArrayList<>();
               for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                   String name = documentSnapshot.getString("Name");
                   dataList.add(name);
               }
               accName.postValue(dataList);
           }
           else {
               Log.d("VHDuc", "Error getting documents", task.getException());
           }
        });
    }

    public MutableLiveData<List<String>> getData(){
        loadData();
        return accName;
    }

    public void addTransaction(String account, String category, String date, String imgId, String money, String type, String id, String newMoney){
        Map<String, String> item = new HashMap<>();
        item.put("account", account);
        item.put("category", category);
        item.put("date", date);
        item.put("imgId", imgId);
        item.put("money", money);
        db.collection(type).add(item).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                updateAccount(account, newMoney, id);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Error", "Can't conduct the transaction");
            }
        });

    }

    private void updateAccount(String name, String money, String id){
        Map<String, String> newData = new HashMap<>();
        newData.put("Name", name);
        newData.put("Money", money);
        db.collection("Account").document(id).set(newData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                NavController navController = Navigation.findNavController((Activity) TransactionFragment.context, R.id.fragment);
                navController.navigate(R.id.homeFragment);
            }
        });
    }

}
