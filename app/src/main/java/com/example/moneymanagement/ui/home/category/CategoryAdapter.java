package com.example.moneymanagement.ui.home.category;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moneymanagement.R;
import com.example.moneymanagement.databinding.CategoryItemBinding;
import com.example.moneymanagement.model.Category;
import com.example.moneymanagement.ui.accounts.AccountAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private Context context;
    private List<Category> categories;
    private AccountAdapter.Dialog dialog;


    public interface Dialog{
        void onClick(int pos);
    }

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryItemBinding viewBinding = CategoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryAdapter.CategoryViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        Category category = categories.get(position);

        holder.binding.categoryName.setText(category.getName());
        Glide.with(context).load(category.getImgId()).into(holder.binding.cateImg);
        //Picasso.get().load(imageUrl).into(holder.binding.cateImg);

        holder.binding.cateLayout.setOnClickListener(view->{
            String categoryName = holder.binding.categoryName.getText().toString();
            String img = category.getImgId();
            String type = category.getType();

            DataHolder.getInstance().setCategoryName(categoryName);
            DataHolder.getInstance().setImgId(img);
            DataHolder.getInstance().setType(type);
            NavController navController = Navigation.findNavController((Activity) context, R.id.fragment);
            navController.popBackStack();
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private CategoryItemBinding binding;

        public CategoryViewHolder(@NonNull CategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
