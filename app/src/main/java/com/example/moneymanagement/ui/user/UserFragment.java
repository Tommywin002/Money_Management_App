package com.example.moneymanagement.ui.user;

import static android.content.ContentValues.TAG;

import static androidx.databinding.DataBindingUtil.findBinding;
import static androidx.databinding.DataBindingUtil.setContentView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.FragmentUserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.auth.User;

public class UserFragment extends Fragment {
    private String name, Gender, Birth, Phone;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FragmentUserBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater,container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
    }

    private void initUI() {
        db.collection("user").document("name")
                        .get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if(task.isSuccessful()){
                                            DocumentSnapshot documentSnapshot = task.getResult();
                                            name = documentSnapshot.getString("name");
                                            Birth = documentSnapshot.getString("birth");
                                            Gender = documentSnapshot.getString("gender");
                                            Phone = documentSnapshot.getString("phone");

                                            binding.txtUsername.setText(name);
                                            binding.txtBirth.setText(Birth);
                                            binding.txtGender.setText(Gender);
                                            binding.txtPhone.setText(Phone);
                                        }
                                    }
                                });
    }
}