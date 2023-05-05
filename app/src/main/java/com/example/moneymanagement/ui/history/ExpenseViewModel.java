package com.example.moneymanagement.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymanagement.model.Expense;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ExpenseViewModel extends ViewModel {
    private final MutableLiveData<List<Expense>> lstExpenseLiveData = new MutableLiveData<>();
    private List<Expense> lstExpense;

    public LiveData<List<Expense>> getExpenseLiveData(){
        lstExpense = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Expense").get().addOnCompleteListener(task ->  {
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

            }
        });
        return lstExpenseLiveData;
    }


}
