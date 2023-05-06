package com.example.moneymanagement.ui.home.category;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymanagement.model.Category;
import com.example.moneymanagement.model.Income;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewModel extends ViewModel {

    private MutableLiveData<List<Category>> lstCategoryLiveData = new MutableLiveData<>();
    private List<Category> lstCategory;

    public LiveData<List<Category>> getCategoryLiveData() {
        lstCategory = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Category").get().addOnCompleteListener(task ->  {
            lstCategory.clear();
            if(task.isSuccessful()){
                for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                    String name = documentSnapshot.getString("name");
                    String imgId = documentSnapshot.getString("imgId");
                    String type = documentSnapshot.getString("type");
                    Category category = new Category(name, imgId, type);
                    category.setId(documentSnapshot.getId());
                    lstCategory.add(category);
                }
                lstCategoryLiveData.postValue(lstCategory);
            }
            else{
                Log.d("Error", "Load data failed");
            }
        });
        return lstCategoryLiveData;
    }

}
