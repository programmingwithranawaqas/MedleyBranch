package com.example.lenovo.medleybranch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class View_Customer_List extends AppCompatActivity {

    RecyclerView customerRecyclerView;
    CustomerAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__customer__list);
        customerRecyclerView = findViewById(R.id.customerRecyclerView);

        customerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<CustomerClass> options =
                new FirebaseRecyclerOptions.Builder<CustomerClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Customers"), CustomerClass.class)
                        .build();
        myAdapter = new CustomerAdapter(options, this);

        customerRecyclerView.setAdapter(myAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
}


