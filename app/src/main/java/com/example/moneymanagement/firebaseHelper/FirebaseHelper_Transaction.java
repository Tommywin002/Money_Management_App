package com.example.moneymanagement.firebaseHelper;
import androidx.annotation.NonNull;

import com.example.moneymanagement.Model.Transaction;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper_Transaction {

    private DatabaseReference databaseReference, databaseReference2;
    private FirebaseDatabase firebaseDatabase;
    private List<Transaction> transactions = new ArrayList<>();


    public FirebaseHelper_Transaction(){
        //String uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Transaction/out");
        databaseReference2 = firebaseDatabase.getReference("Transaction/Income");
    }

    public interface DataStatus{
        void DataIsLoaded(List<Transaction> transactions, List<String> keys);
        void DataIsInsert();
        void DataIsUpdate();
        void DataIsDeleted();
    }

    public void readData(final DataStatus dataStatus){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                transactions.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Transaction tst = keyNode.getValue(Transaction.class);
                    transactions.add(tst);
                }
                dataStatus.DataIsLoaded(transactions, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void readData2(final DataStatus dataStatus){
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                transactions.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Transaction tst = keyNode.getValue(Transaction.class);
                    transactions.add(tst);
                }
                dataStatus.DataIsLoaded(transactions, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addData(Transaction tst, final DataStatus dataStatus){
        String key = databaseReference.push().getKey();
        databaseReference.child(key).setValue(tst).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsInsert();
            }
        });
    }

    public void addData2(Transaction tst, final DataStatus dataStatus){
        String key = databaseReference2.push().getKey();
        databaseReference2.child(key).setValue(tst).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsInsert();
            }
        });
    }

    public void editData(String key, Transaction tst, final DataStatus dataStatus){
        databaseReference.child(key).setValue(tst).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    public void deleteData2(String key, final  DataStatus dataStatus){
        databaseReference2.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }

}