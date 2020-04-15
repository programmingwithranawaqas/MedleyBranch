package com.example.lenovo.medleybranch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Purchase_Main extends AppCompatActivity {

    Button btnNewStock;
    Button btnViewStock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase__main);
        btnNewStock = findViewById(R.id.btnNewStock);
        btnViewStock = findViewById(R.id.btnViewStock);

        btnNewStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Purchase_Main.this, AddNewStock.class));
            }
        });
        btnViewStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call view product activity
                Intent intent = new Intent(Purchase_Main.this, ViewStockList.class);
                startActivity(intent);
            }
        });



    }
}
