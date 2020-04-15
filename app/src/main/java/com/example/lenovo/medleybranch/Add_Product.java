package com.example.lenovo.medleybranch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Add_Product extends AppCompatActivity {
    public static final String DATE_FORMAT_2 = "dd-MMM-yyyy";
    TextInputEditText etProduct_Number;
    TextInputEditText etProduct_Name;
    TextInputEditText etPurchase_Price;
    TextInputEditText etSale_Price;
    TextInputEditText etProduct_Description;
    AutoCompleteTextView etProduct_Category;
    TextInputEditText etProduct_Company;
    TextInputEditText etEntry_Date;
    Button btnAddProduct;
    Button btnCancelProduct;
    List<String> categories;
    List<String> categoryIds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product);
        etProduct_Number = findViewById(R.id.etProduct_Number);
        etProduct_Name = findViewById(R.id.etProduct_Name);
        etPurchase_Price = findViewById(R.id.etPurchase_Price);
        etSale_Price = findViewById(R.id.etSale_Price);
        etProduct_Description = findViewById(R.id.etProduct_Description);
        etProduct_Category = findViewById(R.id.etProduct_Category);
        etEntry_Date = findViewById(R.id.etEntry_Date);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnCancelProduct = findViewById(R.id.btnCancelProduct);
        etProduct_Company = findViewById(R.id.etCompany_Name);

        etEntry_Date.setText(getCurrentDate()); // setting current date

        categories = new ArrayList<>();
        categoryIds = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,categories);
        etProduct_Category.setAdapter(adapter);

        btnCancelProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_Product.this, Product_Main.class);
                startActivity(intent);
                Add_Product.this.finish();
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // getting data from firebase categories table
        DatabaseReference refCat  = FirebaseDatabase.getInstance().getReference();
        Query query = refCat.child("Categories");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot categoriesSnapshot : dataSnapshot.getChildren())
                {

                    // Toast.makeText(Add_Product.this, categoriesSnapshot.child("name").getValue().toString(), Toast.LENGTH_SHORT).show();
                    categories.add(categoriesSnapshot.child("name").getValue().toString());
                    categoryIds.add(categoriesSnapshot.child("id").getValue().toString());
                    //Toast.makeText(Add_Product.this, categoriesSnapshot.child("id").getValue().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addProduct()
    {
        String categoryNumber;
        String productNumber;
        String productName;
        int productPurchasePrice;
        int productSalePrice;
        String productDescription;
        String companyName;
        String entryDate;
        String categoryName;

        productNumber = etProduct_Number.getText().toString().trim();
        productName = etProduct_Name.getText().toString().trim();
        productDescription = etProduct_Description.getText().toString().trim();
        companyName = etProduct_Company.getText().toString().trim();
        categoryName = etProduct_Category.getText().toString().trim();
        entryDate = etEntry_Date.getText().toString().trim();

        if(productNumber.isEmpty())
        {
            Toast.makeText(this, "Enter Product Number", Toast.LENGTH_SHORT).show();
        }
        else if (productName.isEmpty())
        {
            Toast.makeText(this, "Enter Product Name", Toast.LENGTH_SHORT).show();
        }
        else if(companyName.isEmpty())
        {
            Toast.makeText(this, "Enter Product Company", Toast.LENGTH_SHORT).show();
        }
        else if (etPurchase_Price.getText().toString().trim().isEmpty())
        {
                Toast.makeText(this, "Enter Purchase Price", Toast.LENGTH_SHORT).show();
        }
        else if(etSale_Price.getText().toString().trim().isEmpty())
        {
                Toast.makeText(this, "Enter Sale Price", Toast.LENGTH_SHORT).show();
        }
        else {
            productPurchasePrice = Integer.parseInt(etPurchase_Price.getText().toString().trim());
            productSalePrice = Integer.parseInt(etSale_Price.getText().toString().trim());
            categoryNumber = getCategoryNumberFromFirebase(categoryName);

            DatabaseReference productDatabase = FirebaseDatabase.getInstance().getReference("Products");
            String id = productDatabase.push().getKey();
            ProductClass productObject = new ProductClass(id, productNumber, categoryNumber, productName, productPurchasePrice, productSalePrice, productDescription, companyName, entryDate);
            productDatabase.child(id).setValue(productObject);
            Toast.makeText(this, "Product added successfully.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Add_Product.this, Product_Main.class);
            Add_Product.this.finish();
        }
    }

    private String getCategoryNumberFromFirebase(String categoryName) {
        for (int i=0; i<categoryIds.size(); i++)
        {
            if(categories.get(i).equals(categoryName))
                return categoryIds.get(i);
        }
        return null;
    }

    public static String getCurrentDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_2);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }
}