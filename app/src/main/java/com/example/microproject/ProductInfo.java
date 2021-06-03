package com.example.microproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.microproject.fav.MyFav;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class ProductInfo extends AppCompatActivity {
    ImageView product_img;
    TextView product_name,product_des,product_price;
    Button btn_buy,btn_cart;
    FloatingActionButton addToFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        product_img = findViewById(R.id.product_img);
        product_name = findViewById(R.id.product_name);
        product_des = findViewById(R.id.product_des);
        product_price = findViewById(R.id.product_price);
        btn_buy = findViewById(R.id.btn_buy);
        btn_cart = findViewById(R.id.btn_cart);
        addToFav = findViewById(R.id.addToFav);

        String productName = getIntent().getStringExtra("productName");
        product_name.setText(productName);

        String productDes = getIntent().getStringExtra("productDes");
        product_des.setText(productDes);

        String productPrice = getIntent().getStringExtra("productPrice");
        product_price.setText(productPrice);

        String productImage = getIntent().getStringExtra("productImage");
        Picasso.get().load(productImage).into(product_img);

        addToFav.setOnClickListener(view -> {

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("FavName",productName);
            hashMap.put("FavDes",productDes);
            hashMap.put("FavImg",productImage);

            FirebaseDatabase.getInstance().getReference("MyFav")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .push().setValue(hashMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), MyFav.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(ProductInfo.this, "" +
                                        "Item Added to collections", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

        btn_buy.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),CheckOut.class);
            intent.putExtra("total",productPrice);
            intent.putExtra("name",productName);
            intent.putExtra("des",productDes);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        btn_cart.setOnClickListener(view -> {

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ProductName",productName);
            hashMap.put("ProductDes",productDes);
            hashMap.put("ProductPrice",productPrice);
            hashMap.put("ProductImage",productImage);

            FirebaseDatabase.getInstance().getReference("MyCart")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .push()
                    .setValue(hashMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(),ProductList.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(ProductInfo.this, "added to cart",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ProductInfo.this, "something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        });
    }
}