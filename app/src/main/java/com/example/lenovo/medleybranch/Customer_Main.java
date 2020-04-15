package com.example.lenovo.medleybranch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Customer_Main extends AppCompatActivity {

    Button btnNewCustomer;
    Button btnViewCustomers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__main);
        btnNewCustomer = findViewById(R.id.btnNewCustomer);
        btnViewCustomers = findViewById(R.id.btnViewCustomers);

        btnNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open activity for new customer
                startActivity(new Intent(Customer_Main.this, AddCustomer.class));
            }
        });

        btnViewCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open activity to view all customers
                startActivity(new Intent(Customer_Main.this, View_Customer_List.class));
            }
        });
    }
}
