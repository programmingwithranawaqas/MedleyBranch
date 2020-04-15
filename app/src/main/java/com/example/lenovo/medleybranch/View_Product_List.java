package com.example.lenovo.medleybranch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class View_Product_List extends AppCompatActivity {
    RecyclerView product_recycler_view;
    private ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__product__list);

        product_recycler_view = findViewById(R.id.product_recyclerView);
        product_recycler_view.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ProductClass> options =
                new FirebaseRecyclerOptions.Builder<ProductClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products"), ProductClass.class)
                        .build();

        adapter = new ProductAdapter(options, this);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL);
//        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
//        product_recycler_view.addItemDecoration(dividerItemDecoration);
        product_recycler_view.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
