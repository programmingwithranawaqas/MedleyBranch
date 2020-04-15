package com.example.lenovo.medleybranch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewStock extends AppCompatActivity {
    TextInputEditText etProduct_Number_In_Stock;
    TextInputEditText etProduct_Name_In_Stock;
    TextInputEditText etPurchase_Price_In_Stock;
    TextInputEditText etSale_Price_In_Stock;
    TextInputEditText etProduct_Quantity_In_Stock;
    TextInputEditText etEntry_Date_Of_Stock;
    TextView bill_product_price, bill_product_name, bill_product_number, total_stock_bill;
    Button fetchById, fetchByName, btnAddStock, btnUpdatePrice, btnGenerateStockBill;
    boolean flag;
    String key;

    String productIds;
    String productNames;
    String productPrices;
    int totalBill;
    String dateOfStockBill;

    // testing listView
    List<StockClass> stock_items;
    List<StockClass> newStockItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_stock);
        productIds="";
        productNames="";
        productPrices="";
        etProduct_Number_In_Stock = findViewById(R.id.etProduct_Number_In_Stock);
        etProduct_Name_In_Stock= findViewById(R.id.etProduct_Name_In_Stock);
        etPurchase_Price_In_Stock= findViewById(R.id.etPurchase_Price_In_Stock);
        etSale_Price_In_Stock= findViewById(R.id.etSale_Price_In_Stock);
        etProduct_Quantity_In_Stock= findViewById(R.id.etProduct_Quantity_In_Stock);
        etEntry_Date_Of_Stock= findViewById(R.id.etEntry_Date_Of_Stock);
        fetchById = findViewById(R.id.btnFetchById);
        fetchByName = findViewById(R.id.btnFetchByName);
        bill_product_name = findViewById(R.id.bill_product_name);
        bill_product_price = findViewById(R.id.bill_product_price);
        bill_product_number = findViewById(R.id.bill_product_number);
        total_stock_bill = findViewById(R.id.total_stock_bill);
        btnAddStock = findViewById(R.id.btnAddStock);
        btnGenerateStockBill = findViewById(R.id.btnGenerateStockBill);
        btnUpdatePrice = findViewById(R.id.btnUpdatePrice);
        btnUpdatePrice.setVisibility(View.GONE);
        final String[] catid = new String[1];
        newStockItems = new ArrayList<>();
        stock_items = new ArrayList<>();


        flag=false;

        // fetch code
        fetchById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                DatabaseReference refCat  = FirebaseDatabase.getInstance().getReference();
                Query query = refCat.child("Products");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Toast.makeText(AddNewStock.this, "Fetch Record", Toast.LENGTH_SHORT).show();

                        for (DataSnapshot productSnapshot : dataSnapshot.getChildren())
                        {
                            if(productSnapshot.child("productNumber").getValue().toString().trim().equals(etProduct_Number_In_Stock.getText().toString().trim()))
                            {
                                key = productSnapshot.getKey();
                                String pname = productSnapshot.child("productName").getValue().toString();
                                String pprice = productSnapshot.child("purchasePrice").getValue().toString();
                                String sprice = productSnapshot.child("salePrice").getValue().toString();
                                catid[0] = productSnapshot.child("categoryId").getValue().toString().trim();
                                etProduct_Name_In_Stock.setText(pname);
                                etPurchase_Price_In_Stock.setText(pprice);
                                etSale_Price_In_Stock.setText(sprice);
                                etEntry_Date_Of_Stock.setText(Add_Product.getCurrentDate());
                                btnUpdatePrice.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //Toast.makeText(AddNewStock.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        fetchByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                DatabaseReference refCat  = FirebaseDatabase.getInstance().getReference();
                Query query = refCat.child("Products");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Toast.makeText(AddNewStock.this, "Fetch Record", Toast.LENGTH_SHORT).show();

                        for (DataSnapshot productSnapshot : dataSnapshot.getChildren())
                        {
                            if(productSnapshot.child("productName").getValue().toString().trim().equals(etProduct_Name_In_Stock.getText().toString().trim()))
                            {
                                key = productSnapshot.getKey();
                                String pnumber = productSnapshot.child("productNumber").getValue().toString();
                                String pname = productSnapshot.child("productName").getValue().toString();
                                String pprice = productSnapshot.child("purchasePrice").getValue().toString();
                                String sprice = productSnapshot.child("salePrice").getValue().toString();
                                catid[0] = productSnapshot.child("categoryId").getValue().toString().trim();
                                etProduct_Number_In_Stock.setText(pnumber);
                                etProduct_Name_In_Stock.setText(pname);
                                etPurchase_Price_In_Stock.setText(pprice);
                                etSale_Price_In_Stock.setText(sprice);
                                etEntry_Date_Of_Stock.setText(Add_Product.getCurrentDate());
                                btnUpdatePrice.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        btnUpdatePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialog = DialogPlus.newDialog(v.getContext())
                        .setGravity(Gravity.CENTER)
                        .setMargin(50, 0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.edit_purchase_price_dialog))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                View viewHolder = dialog.getHolderView();

                final TextInputEditText etEdit_ProductPurchasePrice = viewHolder.findViewById(R.id.etEdit_Purchase_Price_through_Stock);
                final TextInputEditText etEdit_ProductSalePrice = viewHolder.findViewById(R.id.etEdit_Sale_Price_through_Stock);
                Button update = viewHolder.findViewById(R.id.btnUpdateProduct);

                etEdit_ProductPurchasePrice.setText(etPurchase_Price_In_Stock.getText()+"");
                etEdit_ProductSalePrice.setText(etSale_Price_In_Stock.getText()+"");

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("purchasePrice",Integer.parseInt(etEdit_ProductPurchasePrice.getText().toString().trim()));
                        map.put("salePrice",Integer.parseInt(etEdit_ProductSalePrice.getText().toString().trim()));

                        FirebaseDatabase.getInstance().getReference()
                                .child("Products")
                                .child(key)
                                .updateChildren(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialog.dismiss();
                                    }
                                });
                    }
                });

                dialog.show();
            }
        });

        btnAddStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag) {
                    Toast.makeText(AddNewStock.this, "Fetch Information before Adding Stock", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    int pid = Integer.parseInt(etProduct_Number_In_Stock.getText().toString().trim());
                    int quantity = Integer.parseInt(etProduct_Quantity_In_Stock.getText().toString().trim());
                    String date = etEntry_Date_Of_Stock.getText().toString().trim();
                    dateOfStockBill = date;
                    StockClass stockObject = new StockClass(pid,catid[0],quantity,date);

                    stock_items.add(stockObject);
                   String temp_name = bill_product_name.getText().toString().trim();
                   temp_name+="\n"+etProduct_Name_In_Stock.getText().toString().trim();

                   productNames += ","+etProduct_Name_In_Stock.getText().toString().trim();

                   String temp_price = bill_product_price.getText().toString().trim();


                   int total_price = Integer.parseInt(total_stock_bill.getText().toString().trim());
                   int pprice = Integer.parseInt(etPurchase_Price_In_Stock.getText().toString().trim())*Integer.parseInt(etProduct_Quantity_In_Stock.getText().toString().trim());
                   temp_price += "\n"+pprice;
                    productPrices += ","+pprice;
                   total_price+=pprice;
                   totalBill =total_price;
                   String temp_number = bill_product_number.getText().toString().trim();
                   temp_number += "\n"+etProduct_Number_In_Stock.getText().toString().trim();

                   productIds += ","+etProduct_Number_In_Stock.getText().toString().trim();

                   bill_product_name.setText(temp_name);
                   bill_product_price.setText(temp_price);
                   bill_product_number.setText(temp_number);
                   total_stock_bill.setText(""+total_price);

                   etProduct_Number_In_Stock.setText("");
                   etProduct_Name_In_Stock.setText("");
                   etPurchase_Price_In_Stock.setText("");
                   etSale_Price_In_Stock.setText("");
                   etProduct_Quantity_In_Stock.setText("");

                }
            }
        });

        btnGenerateStockBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference stockbillsDatabase = FirebaseDatabase.getInstance().getReference("Stock_Bills");
                String uniqueID = stockbillsDatabase.push().getKey();
                StockBillClass sbc = new StockBillClass(productIds,productNames,productPrices,totalBill,dateOfStockBill);
                stockbillsDatabase.child(uniqueID).setValue(sbc);

                final DatabaseReference productDatabase = FirebaseDatabase.getInstance().getReference();


                for (int i=0; i<stock_items.size(); i++)
                {

                    final Query q = productDatabase.child("Stocks");
                    final int finalI = i;
                    final boolean[] flag = {true};

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                            {
                               // Toast.makeText(AddNewStock.this, ""+stock_items.get(finalI).getProductId(), Toast.LENGTH_SHORT).show();
                                if(dataSnapshot1.child("productId").getValue().toString().trim().equals(stock_items.get(finalI).getProductId()+""))
                                {
                                    Map<String, Object> map = new HashMap<>();
                                    int updateleftq = stock_items.get(finalI).getProductLeftQuantity()+Integer.parseInt(dataSnapshot1.child("productLeftQuantity").getValue().toString());
                                    int updateq = stock_items.get(finalI).getProductQuantity()+Integer.parseInt(dataSnapshot1.child("productQuantity").getValue().toString());


                                    map.put("productLeftQuantity",updateleftq);
                                    map.put("productQuantity",updateq);
                                    Toast.makeText(AddNewStock.this, "Updated "+stock_items.get(finalI).getProductId(), Toast.LENGTH_SHORT).show();

                                    FirebaseDatabase.getInstance().getReference()
                                            .child("Stocks")
                                            .child(dataSnapshot1.getKey())
                                            .updateChildren(map)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    }
                                            });
                                    flag[0] = false;
                                    break;
                                }
                            }
                            if(flag[0])
                            {
                                newStockItems.add(stock_items.get(finalI));
                                DatabaseReference def = FirebaseDatabase.getInstance().getReference("Stocks");
                               // Toast.makeText(AddNewStock.this, "" + stock_items.size(), Toast.LENGTH_SHORT).show();

                                //for(int j=0; j<newStockItems.size(); j++) {
                                    String id = def.push().getKey();
                                    def.child(id).setValue(stock_items.get(finalI));
                                   // Toast.makeText(AddNewStock.this, "" + id, Toast.LENGTH_SHORT).show();
                               // }
                                //Toast.makeText(AddNewStock.this, "" + newStockItems.size(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

//                DatabaseReference def = FirebaseDatabase.getInstance().getReference("Stocks");
//                Toast.makeText(AddNewStock.this, "" + stock_items.size(), Toast.LENGTH_SHORT).show();
//
//                for(int j=0; j<newStockItems.size(); j++) {
//                    String id = def.push().getKey();
//                    def.child(id).setValue(newStockItems.get(j));
//                    Toast.makeText(AddNewStock.this, "" + id, Toast.LENGTH_SHORT).show();
//                }
                // Toast.makeText(AddNewStock.this, "Stock has been added successfully", Toast.LENGTH_SHORT).show();
                // startActivity(new Intent(AddNewStock.this, Purchase_Main.class));
                //finish();
            }
        });
    }
}
