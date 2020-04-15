package com.example.lenovo.medleybranch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCustomer extends AppCompatActivity {
    TextInputEditText etCarRegistrationNumber;
    TextInputEditText etCustomerName;
    TextInputEditText etCustomerEmail;
    AutoCompleteTextView etCarBrand;
    TextInputEditText etCarModel;
    TextInputEditText etCarMileage;
    TextInputEditText etCarLocation;
    TextInputEditText etCustomerMobileNumber;
    TextInputEditText etCustomerBalance;
    TextInputEditText etCustomerCNIC;
    Button            btnAddCustomer;
    Button            btnCancelCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        etCarRegistrationNumber = findViewById(R.id.etCarRegistrationNumber);
        etCustomerName = findViewById(R.id.etCustomerName);
        etCustomerEmail = findViewById(R.id.etCustomerEmail);
        etCarBrand = findViewById(R.id.etCarBrand);
        etCarModel = findViewById(R.id.etCarModel);
        etCarMileage = findViewById(R.id.etCarMileage);
        etCarLocation = findViewById(R.id.etCarLocation);
        etCustomerCNIC = findViewById(R.id.etCustomerCNIC);
        etCustomerMobileNumber = findViewById(R.id.etCustomerMobileNumber);
        etCustomerBalance = findViewById(R.id.etCustomerBalance);
        btnAddCustomer = findViewById(R.id.btnAddCustomer);
        btnCancelCustomer = findViewById(R.id.btnCancelCustomer);

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String customerName = etCustomerName.getText().toString().trim();
                String customerEmail = etCustomerEmail.getText().toString().trim();
                String customerMobileNumber = etCustomerMobileNumber.getText().toString().trim();
                String customerCnic = etCustomerCNIC.getText().toString().trim();
                int customerBalance;
                if(etCustomerBalance.getText().toString().trim().isEmpty())
                    customerBalance=0;
                else
                    customerBalance = Integer.parseInt(etCustomerBalance.getText().toString().trim());
                String carRegistrationNumber = etCarRegistrationNumber.getText().toString().trim();
                String carBrand = etCarBrand.getText().toString().trim();
                String carModel = etCarModel.getText().toString().trim();

                String carLocation = etCarLocation.getText().toString().trim();
boolean flag = true;
                if(customerName.isEmpty())
                {
                    flag = false;
                    etCustomerName.setBackgroundColor(getResources().getColor(R.color.error));
                }
                if(customerMobileNumber.isEmpty())
                {
                    flag = false;
                    etCustomerMobileNumber.setBackgroundColor(getResources().getColor(R.color.error));
                }
                if(customerCnic.isEmpty())
                {
                    flag = false;
                    etCustomerCNIC.setBackgroundColor(getResources().getColor(R.color.error));
                }
                if(carRegistrationNumber.isEmpty())
                {
                    flag = false;
                    etCarRegistrationNumber.setBackgroundColor(getResources().getColor(R.color.error));
                }
                if(carBrand.isEmpty())
                {
                    flag = false;
                    etCarBrand.setBackgroundColor(getResources().getColor(R.color.error));
                }
                if(carModel.isEmpty())
                { flag = false;
                    etCarModel.setBackgroundColor(getResources().getColor(R.color.error));
                }
                if(etCarMileage.getText().toString().trim().isEmpty())
                {
                    flag = false;
                    etCarMileage.setBackgroundColor(getResources().getColor(R.color.error));
                }
                if(carLocation.isEmpty())
                {
                    flag = false;
                    etCarLocation.setBackgroundColor(getResources().getColor(R.color.error));
                }
                if(flag)
                {
                    float carMileage = Float.parseFloat(etCarMileage.getText().toString().trim());
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Customers");
                    CustomerClass customerClass = new CustomerClass(customerName, customerEmail, customerMobileNumber, customerBalance, carRegistrationNumber,carBrand, carModel, carMileage, carLocation, customerCnic);
                    String id = databaseReference.push().getKey();
                    databaseReference.child(id).setValue(customerClass);
                    Toast.makeText(AddCustomer.this, "Customer Added Successfully", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(AddCustomer.this,Customer_Main.class));
                    finish();
                }
                else
                {
                    Toast.makeText(AddCustomer.this, "Sorry. Your there is a error.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancelCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(AddCustomer.this,Customer_Main.class));
                finish();
            }
        });
    }
}
