package com.example.moneymanagement.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moneymanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private EditText edtEmail, edtPassword;
    private Button loginBtn, registerBtn;
    private ProgressBar progressBar;
    private FirebaseAuth fireAuth;
    @Override
    public void onStart() {
        super.onStart();
        checkLoginStatus();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);
        progressBar = findViewById(R.id.progressBar);
        fireAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
    }
    public void login(){
        progressBar.setVisibility(View.VISIBLE);
        String email, password;
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(Login.this, "Enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        fireAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(Login.this, "Authentication success.", Toast.LENGTH_SHORT).show();
                    saveData(email);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    edtPassword.setText("");
                }
            }
        });
    }
    public void saveData(String email){
        SharedPreferences sharedPreferences = getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("loginCheck", true);
        editor.putString("loginEmail", email);
        editor.apply();
    }
    public void checkLoginStatus(){
        SharedPreferences sharedPreferences = getSharedPreferences("loginData", MODE_PRIVATE);
        Boolean getCheck = sharedPreferences.getBoolean("loginCheck", Boolean.valueOf(String.valueOf(MODE_PRIVATE)));
        String getEmail = sharedPreferences.getString("loginEmail", String.valueOf(MODE_PRIVATE));
        if(getCheck){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


}