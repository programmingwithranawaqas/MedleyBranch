package com.example.lenovo.medleybranch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddNewBill extends AppCompatActivity {
    TextInputEditText etCurrentDateForBill;
    TextInputEditText etCarRegistrationNumber;
    TextInputEditText etProductNumberForBill;
    TextInputEditText etProductDescriptionForBill;
    TextInputEditText etDiscountInRupees;
    TextInputEditText etDiscountInPercentage;
    Button btnAddProductToBill;
    TextView product_number_for_total_bill,product_name_for_total_bill,total_customer_bill;
    TextView total_discounted_amount;
    TextView product_price_for_total_bill;
    Button btnProceedBill, btnApplyDiscount;
    Button validateCustomer;
    boolean flagValidate;
    String pname;
    float pprice;
    int totalprice;
    //Button btnAddCustomerFirst;
    final int ADDNEWBILL = 2;

    ArrayList<String> productsIDsToUpdateStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bill);
        etDiscountInRupees = findViewById(R.id.etDiscountInRupees);
        etDiscountInPercentage = findViewById(R.id.etDiscountInPercentage);
        total_discounted_amount = findViewById(R.id.total_discounted_amount);
        productsIDsToUpdateStock = new ArrayList<>();
        etCurrentDateForBill = findViewById(R.id.etCurrentDateForBill);
        etCarRegistrationNumber = findViewById(R.id.etCarRegistrationNumber);
        etProductNumberForBill = findViewById(R.id.etProductNumberForBill);
        etProductDescriptionForBill = findViewById(R.id.etProductDescriptionForBill);
        btnAddProductToBill = findViewById(R.id.btnAddProductToBill);
        product_number_for_total_bill = findViewById(R.id.product_number_for_total_bill);
        product_name_for_total_bill = findViewById(R.id.product_name_for_total_bill);
        total_customer_bill = findViewById(R.id.total_customer_bill);
        btnProceedBill = findViewById(R.id.btnProceedBill);
        btnApplyDiscount = findViewById(R.id.btnApplyDiscount);
        validateCustomer = findViewById(R.id.validateCustomer);
        product_price_for_total_bill = findViewById(R.id.product_price_for_total_bill);
        // btnAddCustomerFirst = findViewById(R.id.btnAddCustomerFirst);

        btnApplyDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dis = etDiscountInPercentage.getText().toString().trim();
                String price = etDiscountInRupees.getText().toString().trim();
                if(dis.isEmpty() && price.isEmpty())
                {

                }
                else
                {
                    if(!dis.isEmpty())
                    {
                        float temp = Float.parseFloat(dis);
                        float totaldisbill = totalprice*temp/100;
                        totaldisbill = totalprice-totaldisbill;
                        total_discounted_amount.setText(totaldisbill+"");
                    }
                    if(!price.isEmpty())
                    {
                        float temp = Float.parseFloat(price);
                        float totaldisbill = totalprice-temp;
                        total_discounted_amount.setText(totaldisbill+"");
                    }
                }
            }
        });

        validateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String carRegistrationID = etCarRegistrationNumber.getText().toString().trim();
                if(carRegistrationID.isEmpty())
                {
                    Toast.makeText(AddNewBill.this, "Enter Car Registration Number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
                    Query q = dbRef.child("Customers");
                    q.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot data: dataSnapshot.getChildren())
                            {
                                if(data.child("carRegistrationNumber").getValue().toString().equals(carRegistrationID))
                                {
                                    validateCustomer.setText("VALIDATED");
                                    etCurrentDateForBill.setText(Add_Product.getCurrentDate());
                                    flagValidate = false;
                                    Toast.makeText(AddNewBill.this, data.child("carRegistrationNumber").getValue()+"", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            if(flagValidate)
                            {
                                startActivityForResult(new Intent(AddNewBill.this, AddCustomer.class),ADDNEWBILL);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });


        btnAddProductToBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flagValidate)
                {
                    Toast.makeText(AddNewBill.this, "Validate Customer First.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final String productNumber = etProductNumberForBill.getText().toString().trim();
                    if(productNumber.isEmpty())
                    {
                        etProductNumberForBill.setBackgroundColor(getResources().getColor(R.color.error));
                    }
                    else
                    {
                        DatabaseReference dbRefForStock = FirebaseDatabase.getInstance().getReference();
                        Query query = dbRefForStock.child("Stocks");
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                boolean productFoundInStock = false;
                                final boolean[] informationFetchedFromProducts = {false};
                                for(DataSnapshot stockData: dataSnapshot.getChildren())
                                {
                                    if(stockData.child("productId").getValue().toString().trim().equals(productNumber))
                                    {
                                        productFoundInStock=true;
                                          int quantity = Integer.parseInt(stockData.child("productLeftQuantity").getValue().toString().trim());
                                       // Toast.makeText(AddNewBill.this, "Quantity : "+quantity, Toast.LENGTH_SHORT).show();

                                          if(quantity>0)
                                          {
                                              // fetching information of product price

                                              final DatabaseReference[] dbRef = {FirebaseDatabase.getInstance().getReference()};
                                                  Query q = dbRef[0].child("Products");
                                                  q.addListenerForSingleValueEvent(new ValueEventListener() {
                                                      @Override
                                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                          for(DataSnapshot data: dataSnapshot.getChildren())
                                                          {
                                                             // Toast.makeText(AddNewBill.this, ""+data.child("productId").getValue().toString(), Toast.LENGTH_SHORT).show();
                                                              if(data.child("productNumber").getValue().toString().trim().equals(productNumber))
                                                              {
                                                                  productsIDsToUpdateStock.add(productNumber);
                                                                  pname = data.child("productName").getValue().toString().trim();
                                                                  if(etProductDescriptionForBill.getText().toString().isEmpty())
                                                                  {
                                                                      pprice = Float.parseFloat(data.child("salePrice").getValue().toString().trim());
                                                                  }
                                                                  else
                                                                  {
                                                                      String getDescription = etProductDescriptionForBill.getText().toString().trim();
                                                                      getDescription = getDescription.replaceAll("[^\\d.]", "");
                                                                      float litre = Float.parseFloat(getDescription);
                                                                      pprice = Float.parseFloat(data.child("salePrice").getValue().toString().trim());
                                                                      String getFirebaseDescription = data.child("productDescription").getValue().toString().trim();
                                                                      getFirebaseDescription = getFirebaseDescription.replaceAll("[^\\d.]", "");
                                                                      float firebaselitre = Float.parseFloat(getFirebaseDescription);
                                                                      pprice = (pprice/firebaselitre)*litre;
                                                                  }
                                                                  totalprice+=pprice;
                                                                  String temp =product_name_for_total_bill.getText().toString().trim()+"\n"+pname;
                                                                  product_name_for_total_bill.setText(temp);
                                                                  temp = product_price_for_total_bill.getText().toString().trim()+"\n"+pprice;
                                                                  product_price_for_total_bill.setText(temp);
                                                                  total_customer_bill.setText(totalprice+"");
                                                                  total_discounted_amount.setText(totalprice+"");
                                                                  informationFetchedFromProducts[0] =true;
                                                                  etProductNumberForBill.setText("");
                                                                  etProductDescriptionForBill.setText("");

                                                                  break;
                                                              }
                                                          }
                                                      }

                                                      @Override
                                                      public void onCancelled(@NonNull DatabaseError databaseError) {

                                                      }
                                                  });
                                          }
                                          else
                                          {
                                              Toast.makeText(AddNewBill.this, "Sorry the product is out of stock", Toast.LENGTH_SHORT).show();
                                          }
                                    }

                                }


                                if(!productFoundInStock)
                                {
                                    Toast.makeText(AddNewBill.this, "Sorry This Product Not Found", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }
        });

        btnProceedBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference updateStocks = FirebaseDatabase.getInstance().getReference();
                Query q = updateStocks.child("Stocks");
                for(int i=0; i<productsIDsToUpdateStock.size(); i++)
                {
                    DatabaseReference getStockRef = FirebaseDatabase.getInstance().getReference("Stocks");
                    final int finalI = i;
                    getStockRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot data:dataSnapshot.getChildren())
                            {
                                if(data.child("productId").getValue().toString().equals(productsIDsToUpdateStock.get(finalI)))
                                {
                                    String key = data.getKey();
                                    Map<String, Object> map = new HashMap<>();
                                    int quantity = Integer.parseInt(data.child("productLeftQuantity").getValue().toString());
                                    quantity--;
                                    map.put("productLeftQuantity", quantity);

                                    updateStocks.child("Stocks").
                                            child(key).
                                            updateChildren(map)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                generateCustomerBill();
            }
        });
    }

    public void generateCustomerBill()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Customer_bill");
        String uniqueBillId = ref.push().getKey();
        String ID = uniqueBillId;
        String CarID = etCarRegistrationNumber.getText().toString().trim().toUpperCase();
        String billDate = etCurrentDateForBill.getText().toString().trim();
        String nextDate = etCurrentDateForBill.getText().toString().trim();
        String meterReading = "";
        Customer_Bill customer_bill = new Customer_Bill(ID, CarID, billDate, nextDate, meterReading);
        ref.child(ID).setValue(customer_bill);
        DatabaseReference addProductsToCustomerProducts = FirebaseDatabase.getInstance().getReference("Customer_Products");
        for(int i=0; i<productsIDsToUpdateStock.size(); i++)
        {
            CustomerProductsList customerProductsList = new CustomerProductsList(ID,productsIDsToUpdateStock.get(i));
            addProductsToCustomerProducts.child(ID).setValue(customerProductsList);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        flagValidate = true;
        totalprice = 0;
        pprice=0;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        flagValidate=true;

    }
}
