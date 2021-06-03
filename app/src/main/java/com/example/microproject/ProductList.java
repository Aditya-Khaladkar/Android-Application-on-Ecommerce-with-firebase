package com.example.microproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.microproject.cart.MyCart;
import com.example.microproject.fav.MyFav;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ProductList extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter adapter;
    ImageView goToOrder,goToCart,goToFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        goToOrder = findViewById(R.id.goToOrder);
        goToOrder.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),OrderDetails.class));
        });

        goToCart = findViewById(R.id.goToCart);
        goToCart.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MyCart.class));
        });

        goToFav = findViewById(R.id.goToFav);
        goToFav.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MyFav.class));
        });

        recyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("products"),
                                Model.class)
                        .build();

        adapter = new MyAdapter(options);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}