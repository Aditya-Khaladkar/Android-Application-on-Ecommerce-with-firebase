package com.example.microproject.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.microproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class CartAdapter extends FirebaseRecyclerAdapter<CartModel,CartAdapter.CartViewHolder> {

    public CartAdapter(FirebaseRecyclerOptions<CartModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(CartAdapter.CartViewHolder holder, int position, CartModel model) {
        holder.cart_des.setText(model.ProductDes);
        holder.cart_name.setText(model.ProductName);
        holder.cart_price.setText(model.ProductPrice);
        Picasso.get().load(model.ProductImage).into(holder.cart_img);
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list,
                parent,false);
        return new CartAdapter.CartViewHolder(view);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView cart_img;
        TextView cart_des, cart_name, cart_price;

        public CartViewHolder(View itemView) {
            super(itemView);

            cart_img = itemView.findViewById(R.id.cart_img);
            cart_des = itemView.findViewById(R.id.cart_des);
            cart_name = itemView.findViewById(R.id.cart_name);
            cart_price = itemView.findViewById(R.id.cart_price);
        }
    }
}
