package com.example.moneymanagement.ui.history;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymanagement.model.Expense;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ExpenseViewModel extends ViewModel {

    private final MutableLiveData<List<Expense>> lstExpenseLiveData = new MutableLiveData<>();
    private List<Expense> lstExpense;

    public void getExpense(){
        lstExpense = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getUid();
        db.collection("User").document(uid).collection("Expense").get().addOnCompleteListener(task ->  {
            lstExpense.clear();
            if(task.isSuccessful()){
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                    String account = documentSnapshot.getString("account");
                    String category = documentSnapshot.getString("category");
                    String money = documentSnapshot.getString("money");
                    String date = documentSnapshot.getString("date");
                    String imgId = documentSnapshot.getString("imgId");
                    Expense expense = new Expense(account, category, money, date, imgId);
                    expense.setId(documentSnapshot.getId());
                    lstExpense.add(expense);
                    System.out.println(documentSnapshot.getId());
                }
                lstExpenseLiveData.postValue(lstExpense);
            }
            else{
                Log.d("Error", "Load data (Income) fail");
            }
        });
    }

    public LiveData<List<Expense>> getExpenseLiveData(){
        getExpense();
        return lstExpenseLiveData;
    }

    public void deleteExpense(String id){
        String uid = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("User").document(uid).collection("Expense").document(id).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }
                        else{
                            Log.d("VHDuc", "Error ", task.getException());
                        }
                        getExpense();
                    }
                });
    }

}
