package com.example.lenovo.medleybranch;
/*
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdaperForCategory extends RecyclerView.Adapter<RecyclerViewAdaperForCategory.myViewHolder> {

    Context myContext;
    List<CategoryClass> categoryClasses;


    public RecyclerViewAdaperForCategory(Context myContext, List<CategoryClass> categoryClasses) {
        this.myContext = myContext;
        this.categoryClasses = categoryClasses;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(myContext).inflate(R.layout.activity_item_category,parent,false);
        myViewHolder vHolder = new myViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.tvCounter.setText(categoryClasses.get(position).getId()+"");
        holder.tvCategoryName.setText(categoryClasses.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categoryClasses.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCounter;
        private TextView tvCategoryName;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCounter = itemView.findViewById(R.id.tv_counter);
            tvCategoryName = itemView.findViewById(R.id.tv_category_name);
        }
    }

}
*/

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdaperForCategory extends FirebaseRecyclerAdapter<CategoryClass, RecyclerViewAdaperForCategory.CategoryViewHolder> {
    private int i;
    public RecyclerViewAdaperForCategory(@NonNull FirebaseRecyclerOptions<CategoryClass> options) {
        super(options);
        i=1;
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position, @NonNull CategoryClass model) {
        holder.tvCounter.setText(i+"");
        holder.tvCategoryName.setText(model.getName());
        i++;
        holder.ivDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference()
                        .child("Categories")
                        .child(getRef(position).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
            }
        });
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_category, parent, false);

        return new CategoryViewHolder(view);
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView tvCounter;
        private TextView tvCategoryName;
        private ImageView ivDeleteCategory;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCounter = itemView.findViewById(R.id.tv_counter);
            tvCategoryName = itemView.findViewById(R.id.tv_category_name);
            ivDeleteCategory = itemView.findViewById(R.id.ivDeleteCategory);
        }
    }
}