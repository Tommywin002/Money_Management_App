package com.example.moneymanagement.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.FragmentAccountsBinding;
import com.example.moneymanagement.databinding.FragmentLoginBinding;
import com.example.moneymanagement.ui.Login;
import com.example.moneymanagement.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    private FirebaseAuth fireAuth = FirebaseAuth.getInstance();
    FragmentLoginBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        checkLoginStatus();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login();
    }
    public void login(){

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = binding.edtEmail.getText().toString();
                password = binding.edtPassword.getText().toString();
                if(TextUtils.isEmpty(email)){
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    return;
                }
                fireAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        binding.progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            saveData(email);
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);

                        }
                        else {
                            // If sign in fails, display a message to the user.
                            binding.edtPassword.setText("");
                        }
                    }
                });

            }
        });
    }
    public void saveData(String email){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("loginData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("loginCheck", true);
        editor.putString("loginEmail", email);
        editor.apply();
    }
    public void checkLoginStatus(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("loginData", Context.MODE_PRIVATE);
        Boolean getCheck = sharedPreferences.getBoolean("loginCheck", Boolean.valueOf(String.valueOf(Context.MODE_PRIVATE)));
        String getEmail = sharedPreferences.getString("loginEmail", String.valueOf(Context.MODE_PRIVATE));
        if(getCheck){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);

        }
    }
}