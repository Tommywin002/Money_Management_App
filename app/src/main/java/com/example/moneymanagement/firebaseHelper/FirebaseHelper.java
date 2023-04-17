package com.example.moneymanagement.firebaseHelper;

import androidx.annotation.NonNull;

import com.example.moneymanagement.Model.Account;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private List<Account> accounts = new ArrayList<>();

    public FirebaseHelper(){
        String uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(uId).child("Account");
    }

    public interface DataStatus{
        void DataIsLoaded(List<Account> accounts, List<String> keys);
        void DataIsInsert();
        void DataIsUpdate();
        void DataIsDeleted();
    }

    public void readData(final DataStatus dataStatus){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                accounts.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Account account = keyNode.getValue(Account.class);
                    accounts.add(account);
                }
                dataStatus.DataIsLoaded(accounts, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addData(Account account, final DataStatus dataStatus){
        String key = databaseReference.push().getKey();
        databaseReference.child(key).setValue(account).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsInsert();
            }
        });
    }

    public void editData(String key, Account account, final DataStatus dataStatus){
        databaseReference.child(key).setValue(account).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsUpdate();
            }
        });
    }

    public void deleteData(String key, final  DataStatus dataStatus){
        databaseReference.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
