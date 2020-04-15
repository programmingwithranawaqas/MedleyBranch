package com.example.lenovo.medleybranch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Product_Main extends AppCompatActivity {

    Button btnAddProduct, btnViewProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__main);

        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnViewProduct = findViewById(R.id.btnViewProduct);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call add product activity
                Intent intent = new Intent(Product_Main.this, Add_Product.class);
                startActivity(intent);
            }
        });

        btnViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call view product activity
                Intent intent = new Intent(Product_Main.this, View_Product_List.class);
                startActivity(intent);
            }
        });

    }
}