package com.example.moneymanagement.ui.user;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.FragmentEditUserBinding;
import com.example.moneymanagement.ui.home.transaction.TransactionFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Edit_User extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FragmentEditUserBinding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = FragmentEditUserBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> update = new HashMap<>();
                update.put("name", binding.editName.getText().toString());
                update.put("gender", binding.editGender.getText().toString());
                update.put("birth", binding.editBirth.getText().toString());
                update.put("phone", binding.editPhone.getText().toString());
                String uid = FirebaseAuth.getInstance().getUid();
                //navigateToPreviousFragment();
                db.collection("User").document(uid).set(update).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        System.out.println("ok");
                        navigateToPreviousFragment();
                    }
                });

            }
        });
        binding.BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPreviousFragment();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void navigateToPreviousFragment(){
        NavController navController = Navigation.findNavController((Activity) getContext(), R.id.fragment);
        navController.popBackStack();
    }

    private void initData(){
        Bundle bundle = getArguments();
        binding.editName.setText(bundle.getString("name"));
        binding.editPhone.setText(bundle.getString("phone"));
        binding.editBirth.setText(bundle.getString("birth"));
        binding.editGender.setText(bundle.getString("gender"));
    }
}