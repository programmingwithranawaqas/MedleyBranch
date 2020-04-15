package com.example.lenovo.medleybranch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerAdapter extends FirebaseRecyclerAdapter<CustomerClass, CustomerAdapter.CustomerViewHolder> {

    Context context;
    public CustomerAdapter(@NonNull FirebaseRecyclerOptions<CustomerClass> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull CustomerViewHolder holder, int position, @NonNull CustomerClass model) {
        holder.tvCustomerName.setText(""+model.getCustomerName());
        holder.tvCustomerCNIC.setText("CNIC : "+model.getCnic());
      holder.tvCustomerMobile.setText("PH.# : "+model.getCustomerMobileNumber());
        holder.tvCarModel.setText(    "MODEL:"+model.getCarModel());
        String temp = model.getCustomerBalance()+"";
      holder.tvCustomerCredit.setText("CREDIT : "+temp+". PKR");
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_item_list, parent, false);
        return new CustomerViewHolder(view);
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder{

        TextView tvCustomerName;
        TextView tvCustomerCNIC;
        TextView tvCustomerMobile;
        TextView tvCarModel;
        TextView tvCustomerCredit;
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvCustomerCNIC = itemView.findViewById(R.id.tvCustomerCNIC);
            tvCustomerMobile = itemView.findViewById(R.id.tvCustomerMobile);
            tvCarModel = itemView.findViewById(R.id.tvCarModel);
            tvCustomerCredit = itemView.findViewById(R.id.tvCustomerCredit);
        }
    }
}
