package com.example.moneymanagement.ui.accounts;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moneymanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class AddAccountFragment extends Fragment {
    private AccountsViewModel accountsViewModel;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView cancel, confirm;
    private EditText accMoney, accName;
    public static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        cancel = view.findViewById(R.id.cancelImg);
        confirm = view.findViewById(R.id.confirmImg);
        accMoney = view.findViewById(R.id.accNumberEdt);
        accName = view.findViewById(R.id.accNameEdt);
        context = getContext();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> item = new HashMap<>();
                item.put("Name", accName.getText().toString().trim());
                item.put("Money", accMoney.getText().toString().trim());
                db.collection("Account").add(item).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        System.out.println("yep");
                        navigateToPreviousFragment();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
                navController.navigate(R.id.accountsFragment);
            }
        });

        return view;
    }

    private void navigateToPreviousFragment(){
        NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
        navController.popBackStack();
    }

}