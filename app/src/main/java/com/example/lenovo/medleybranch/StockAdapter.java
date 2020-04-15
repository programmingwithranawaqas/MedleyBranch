package com.example.lenovo.medleybranch;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StockAdapter extends FirebaseRecyclerAdapter<StockClass, StockAdapter.StockViewHoler> {

    Context context;
    public StockAdapter(@NonNull FirebaseRecyclerOptions<StockClass> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final StockViewHoler holder, final int position, @NonNull final StockClass model) {


        holder.tvProductNumberInStockItemList.setText(model.getProductId()+"");

        // Fetch product name
        DatabaseReference refCat  = FirebaseDatabase.getInstance().getReference();
        Query query = refCat.child("Products");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot productSnapshot : dataSnapshot.getChildren())
                {
                    if(productSnapshot.child("productNumber").getValue().toString().trim().equals(model.getProductId()+""))
                    {
                        String pname = productSnapshot.child("productName").getValue().toString();
                        holder.tvProductNameInStockList.setText(pname);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        holder.tvTotalQuantity.setText(model.getProductQuantity()+"");
        holder.tvLeftQuantity.setText(model.getProductLeftQuantity()+"");

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference()
                        .child("Stocks")
                        .child(getRef(position).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                final DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50, 0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.update_product_dialog))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                View viewHolder = dialog.getHolderView();

                final TextInputEditText etEdit_ProductName = viewHolder.findViewById(R.id.etEdit_Product_Name);
                final TextInputEditText etEdit_ProductPurchasePrice = viewHolder.findViewById(R.id.etEdit_Purchase_Price);
                final TextInputEditText etEdit_ProductSalePrice = viewHolder.findViewById(R.id.etEdit_Sale_Price);
                final TextInputEditText etEdit_ProductDescription = viewHolder.findViewById(R.id.etEdit_Product_Description);
                final TextInputEditText etEdit_CompanyName = viewHolder.findViewById(R.id.etEdit_Company_Name);
                Button update = viewHolder.findViewById(R.id.btnUpdateProduct);

                etEdit_ProductName.setText(model.getProductName());
                etEdit_ProductPurchasePrice.setText(model.getPurchasePrice()+"");
                etEdit_ProductSalePrice.setText(model.getSalePrice()+"");
                etEdit_ProductDescription.setText(model.getProductDescription());
                etEdit_CompanyName.setText(model.getCompanyName());


                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("productName",etEdit_ProductName.getText().toString().trim());
                        map.put("purchasePrice",Integer.parseInt(etEdit_ProductPurchasePrice.getText().toString().trim()));
                        map.put("salePrice",Integer.parseInt(etEdit_ProductSalePrice.getText().toString().trim()));
                        map.put("productDescription",etEdit_ProductDescription.getText().toString().trim());
                        map.put("companyName",etEdit_CompanyName.getText().toString().trim());

                        FirebaseDatabase.getInstance().getReference()
                                .child("Products")
                                .child(getRef(position).getKey())
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
                */
                Toast.makeText(context, "Kam Baaki hai", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @NonNull
    @Override
    public StockViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_item_in_list, parent, false);

        return new StockViewHoler(view);
    }

    class StockViewHoler extends RecyclerView.ViewHolder{

        TextView tvProductNameInStockList;
        TextView tvProductNumberInStockItemList;
        TextView tvTotalQuantity;
        TextView tvLeftQuantity;
        ImageView edit;
        ImageView delete;
        public StockViewHoler(@NonNull View itemView) {
            super(itemView);
            tvProductNumberInStockItemList = itemView.findViewById(R.id.tvProductNumberInStockItemList);
            tvProductNameInStockList = itemView.findViewById(R.id.tvProductNameInStockList);
            tvTotalQuantity = itemView.findViewById(R.id.tvTotalQuantity);
            tvLeftQuantity = itemView.findViewById(R.id.tvLeftQuantity);
            edit = itemView.findViewById(R.id.ivEditStock);
            delete = itemView.findViewById(R.id.ivDeleteStock);

        }
    }
}
