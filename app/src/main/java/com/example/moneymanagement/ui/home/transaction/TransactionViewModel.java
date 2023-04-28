package com.example.moneymanagement.ui.home.transaction;

import android.nfc.Tag;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymanagement.model.Account;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class TransactionViewModel extends ViewModel {

    private MutableLiveData<List<String>> accName = new MutableLiveData<>();

    private void loadData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
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

}
