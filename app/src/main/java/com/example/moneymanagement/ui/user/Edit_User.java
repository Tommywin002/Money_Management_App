package com.example.moneymanagement.ui.user;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.moneymanagement.R;
import com.example.moneymanagement.ui.home.transaction.TransactionFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Edit_User extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText eName, eGender, eBirth, ePhone;
    private Button btnSave, btnCancel;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_edit__user, container, false);
        eName = view.findViewById(R.id.editName);
        eBirth = view.findViewById(R.id.editBirth);
        eGender = view.findViewById(R.id.editGender);
        ePhone = view.findViewById(R.id.editPhone);
        btnSave = view.findViewById(R.id.BtnSave);
        btnCancel = view.findViewById(R.id.BtnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> update = new HashMap<>();
                update.put("name", eName.getText().toString());
                update.put("gender", eGender.getText().toString());
                update.put("birth", eBirth.getText().toString());
                update.put("phone", ePhone.getText().toString());

                //navigateToPreviousFragment();
                db.collection("user").document("name").set(update).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        navigateToPreviousFragment();
                    }
                });

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPreviousFragment();
            }
        });
        return view;
    }
    private void navigateToPreviousFragment(){
        NavController navController = Navigation.findNavController((Activity) getContext(), R.id.fragment);
        navController.popBackStack();
    }
}