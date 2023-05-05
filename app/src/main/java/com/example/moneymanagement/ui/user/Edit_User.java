package com.example.moneymanagement.ui.user;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymanagement.R;
import com.example.moneymanagement.ui.home.transaction.TransactionFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Edit_User extends Fragment {

    private MutableLiveData<String> editUser = new MutableLiveData<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    private void updateAccount(String name, String gender, String birth, String phone){
        Map<String, String> newData = new HashMap<>();
        newData.put("Name", name);
        newData.put("Gender", gender);
        newData.put("Birth", birth);
        newData.put("Phone", phone);
        db.collection("user").document("name").set(newData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                NavController navController = Navigation.findNavController((Activity) TransactionFragment.context, R.id.edit_User2);
                navController.popBackStack();
            }
        });
    }
}