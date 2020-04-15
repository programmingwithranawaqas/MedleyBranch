package com.example.lenovo.medleybranch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bill_Main extends AppCompatActivity {
    Button btnNewBill;
    Button btnPendingBill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill__main);
        btnNewBill = findViewById(R.id.btnNewBill);
        btnPendingBill = findViewById(R.id.btnPendingBill);

        btnNewBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Bill_Main.this, AddNewBill.class));
            }
        });

        btnPendingBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
