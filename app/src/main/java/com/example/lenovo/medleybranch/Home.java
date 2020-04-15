package com.example.lenovo.medleybranch;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    ImageView ivProfile, ivProduct, ivCategory, ivStock, ivCustomer, ivReports, ivBilling;
    TextView tvBranchNo, tvBranchAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ivProfile= findViewById(R.id.ivProfile);
        ivProduct= findViewById(R.id.ivProduct);
        ivCategory= findViewById(R.id.ivCategory);
        ivStock= findViewById(R.id.ivPurchase);
        ivCustomer= findViewById(R.id.ivCustomer);
        ivReports= findViewById(R.id.ivReports);
        ivBilling= findViewById(R.id.ivBilling);
        tvBranchNo= findViewById(R.id.tvBranchNo);
        tvBranchAddress= findViewById(R.id.tvBranchAddress);

        ivProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Product_Main.class);
                startActivity(intent);
            }
        });
        ivCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "category", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Home.this, Category.class));
            }
        });
        ivStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Purchase_Main.class));
            }
        });
        ivCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Customer_Main.class));
            }
        });
        ivBilling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Bill_Main.class));
            }
        });
        ivReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "reports", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
