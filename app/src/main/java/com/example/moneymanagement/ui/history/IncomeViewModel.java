package com.example.moneymanagement.ui.history;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymanagement.model.Income;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class IncomeViewModel extends ViewModel {

    private final MutableLiveData<List<Income>> lstIncomeLiveData = new MutableLiveData<>();
    private List<Income> lstIncome;

    public void getIncome() {
        lstIncome = new ArrayList<>();
        String uid = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("User").document(uid).collection("Income").get().addOnCompleteListener(task ->  {
            lstIncome.clear();
            if(task.isSuccessful()){
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                    String account = documentSnapshot.getString("account");
                    String category = documentSnapshot.getString("category");
                    String money = documentSnapshot.getString("money");
                    String date = documentSnapshot.getString("date");
                    String imgId = documentSnapshot.getString("imgId");
                    Income income = new Income(account, category, money, date, imgId);
                    income.setId(documentSnapshot.getId());
                    lstIncome.add(income);
                    System.out.println(documentSnapshot.getId());
                }
                lstIncomeLiveData.postValue(lstIncome);
            }
            else{
                Log.d("Error", "Load data (Expense) fail");
            }
        });
    }

    public LiveData<List<Income>> getIncomeLiveData(){
        getIncome();
        return lstIncomeLiveData;
    }

    public void deleteIncome(String id){
        String uid = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("User").document(uid).collection("Income").document(id).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }
                        else{
                            Log.d("VHDuc", "Error ", task.getException());
                        }
                        getIncome();
                    }
                });
    }


}
