package com.example.lenovo.medleybranch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class ViewStockList extends AppCompatActivity {

    RecyclerView stock_recycler_view;
    private StockAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stock_list);

        stock_recycler_view = findViewById(R.id.stock_recyclerView);
        stock_recycler_view.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<StockClass> options =
                new FirebaseRecyclerOptions.Builder<StockClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Stocks"), StockClass.class)
                        .build();

        adapter = new StockAdapter(options, this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        stock_recycler_view.addItemDecoration(dividerItemDecoration);
        stock_recycler_view.setAdapter(adapter);
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
