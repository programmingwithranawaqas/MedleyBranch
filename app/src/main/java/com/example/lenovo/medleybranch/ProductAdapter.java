package com.example.lenovo.medleybranch;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends FirebaseRecyclerAdapter<ProductClass, ProductAdapter.ProductViewHoler> {

    Context context;
    public ProductAdapter(@NonNull FirebaseRecyclerOptions<ProductClass> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductViewHoler holder, final int position, @NonNull final ProductClass model) {


        holder.tvProductNumber.setText(model.getProductNumber());
        holder.tvProductName.setText( model.getProductName());
        holder.tvProductPurchasePrice.setText(model.getPurchasePrice()+".PKR");
        holder.tvProductSalePrice.setText(model.getSalePrice()+".PKR");
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference()
                        .child("Products")
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
            }
        });
    }

    @NonNull
    @Override
    public ProductViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item_in_list, parent, false);

        return new ProductViewHoler(view);
    }

    static class ProductViewHoler extends RecyclerView.ViewHolder{

        TextView tvProductName;
        TextView tvProductNumber;
        TextView tvProductPurchasePrice;
        TextView tvProductSalePrice;
        ImageView edit;
        ImageView delete;
        public ProductViewHoler(@NonNull View itemView) {
            super(itemView);
            tvProductNumber = itemView.findViewById(R.id.tvProductNumber);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPurchasePrice = itemView.findViewById(R.id.tvPurchasePrice);
            tvProductSalePrice = itemView.findViewById(R.id.tvSalePrice);
            edit = itemView.findViewById(R.id.ivEditProduct);
            delete = itemView.findViewById(R.id.ivDeleteProduct);

        }
    }
}
