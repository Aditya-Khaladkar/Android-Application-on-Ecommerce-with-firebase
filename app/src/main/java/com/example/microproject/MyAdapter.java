package com.example.microproject;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class MyAdapter extends FirebaseRecyclerAdapter<Model, MyAdapter.MyViewHolder> {

    public MyAdapter(FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(MyAdapter.MyViewHolder holder, int position, Model model) {
        holder.pro_name.setText(model.productName);
        holder.pro_des.setText(model.productDes);
        holder.pro_price.setText(model.productPrice);
        Picasso.get().load(model.productImage).into(holder.pro_img);
        holder.relative.setOnClickListener(view -> {
            Intent intent = new Intent(holder.relative.getContext(),ProductInfo.class);
            intent.putExtra("productName",model.getProductName());
            intent.putExtra("productImage",model.getProductImage());
            intent.putExtra("productDes",model.getProductDes());
            intent.putExtra("productPrice",model.getProductPrice());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.relative.getContext().startActivity(intent);
        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list,
                parent,false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView pro_img;
        TextView pro_name,pro_des,pro_price;
        RelativeLayout relative;

        public MyViewHolder(View itemView) {
            super(itemView);

            pro_img = itemView.findViewById(R.id.pro_img);
            pro_name = itemView.findViewById(R.id.pro_name);
            pro_des = itemView.findViewById(R.id.pro_des);
            pro_price = itemView.findViewById(R.id.pro_price);
            relative = itemView.findViewById(R.id.relative);
        }
    }
}
