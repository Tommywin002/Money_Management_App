package com.example.moneymanagement.ui.home.category;

import android.app.Activity;
import android.content.Context;
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
        holder.binding.categoryName.setText(categories.get(position).getName());
        //holder.binding.cateImg.setImageResource(categories.get(position).getImgId());
        //Glide.with(context).load(categories.get(position).getImgId()).into(holder.binding.cateImg);
        holder.binding.cateLayout.setOnClickListener(view->{
            String category = holder.binding.categoryName.getText().toString();
            String img = holder.binding.cateImg.toString();
            String type = categories.get(position).getType();
            Toast.makeText(context, category + " - " + type, Toast.LENGTH_SHORT).show();
            /*Bundle bundle = new Bundle();
            bundle.putString("cateName", category);
            bundle.putString("cateImg", img);*/
            DataHolder.getInstance().setCategoryName(category);
            DataHolder.getInstance().setImgId(img);
            DataHolder.getInstance().setType(type);
            NavController navController = Navigation.findNavController((Activity) context, R.id.fragment);
            //navController.navigate(R.id.transactionFragment, bundle);
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
