package com.example.microproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class OrderDetails extends AppCompatActivity {
    TextView orderName, orderDes, orderPrice, shipping_address;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        orderName = findViewById(R.id.orderName);
        orderDes = findViewById(R.id.orderDes);
        orderPrice = findViewById(R.id.orderPrice);
        firebaseFirestore = FirebaseFirestore.getInstance();

        documentReference=firebaseFirestore.collection("MyOrders")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                orderDes.setText(value.getString("ProductDes"));
                orderName.setText(value.getString("ProductName"));
                orderPrice.setText(value.getString("ProductPrice"));
            }
        });

        getShippingAddress();

    }

    private void getShippingAddress() {
        shipping_address = findViewById(R.id.shipping_address);
        documentReference=firebaseFirestore.collection("MyAddress")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                shipping_address.setText(
                        "Apartment: "+value.getString("Apartment")+"\n"+
                                "Street: "+value.getString("Street")+"\n"+
                                "City: "+value.getString("City")+"\n"+
                                "State: "+value.getString("State")+"\n"+
                                "PinCode: "+value.getString("PinCode")
                );
            }
        });
    }
}