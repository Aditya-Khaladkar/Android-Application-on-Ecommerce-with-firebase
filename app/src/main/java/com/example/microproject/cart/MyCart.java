package com.example.microproject.cart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.microproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MyCart extends AppCompatActivity {
    RecyclerView recycler_cart;
    CartAdapter adapter;
    TextView txt_pinCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        fetchPinCode();

        recycler_cart = findViewById(R.id.recycler_cart);
        recycler_cart.setLayoutManager(new LinearLayoutManager(this));
        recycler_cart.setHasFixedSize(true);

        Query query = FirebaseDatabase.getInstance()
                .getReference().child("MyCart")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        FirebaseRecyclerOptions<CartModel> options =
                new FirebaseRecyclerOptions.Builder<CartModel>()
                        .setQuery(query, CartModel.class)
                        .build();

        adapter = new CartAdapter(options);
        recycler_cart.setAdapter(adapter);
        adapter.startListening();
    }

    private void fetchPinCode() {
        txt_pinCode = findViewById(R.id.txt_pinCode);
        DocumentReference documentReference= FirebaseFirestore.getInstance().
                collection("MyAddress")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                txt_pinCode.setText(value.getString("PinCode"));
            }
        });
    }
}