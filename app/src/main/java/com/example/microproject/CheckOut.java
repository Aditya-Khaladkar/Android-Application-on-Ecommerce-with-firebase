package com.example.microproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.util.HashMap;

public class CheckOut extends AppCompatActivity {
    FrameLayout pay1;
    LinearLayout option1;
    TextView total;
    Button order;
    FirebaseFirestore firebaseFirestore;
    EditText card, cvv, MM_YY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        String productPrice = getIntent().getStringExtra("total");
        String productName = getIntent().getStringExtra("name");
        String productDes = getIntent().getStringExtra("des");

        firebaseFirestore = FirebaseFirestore.getInstance();


        pay1 = findViewById(R.id.pay1);
        option1 = findViewById(R.id.option1);
        total = findViewById(R.id.total);
        order = findViewById(R.id.order);

        // edit text
        card = findViewById(R.id.card);
        cvv = findViewById(R.id.cvv);
        MM_YY = findViewById(R.id.MM_YY);

        total.setText(productPrice);

        pay1.setOnClickListener(view -> {
            option1.setVisibility(View.VISIBLE);
        });
        order.setOnClickListener(view -> {

            String cardNo = card.getText().toString();
            String cvvNo = cvv.getText().toString();
            String ExpireDate = MM_YY.getText().toString();

            if (TextUtils.isEmpty(cardNo)) {
                card.setError("enter card number");
            } else if (cardNo.length() != 16) {
                card.setError("enter valid card number");
            } else if (!cardNo.equals("1111222233334444")) {
                card.setError("incorrect card number");
            } else if (TextUtils.isEmpty(cvvNo)) {
                cvv.setError("enter cvv number");
            } else if (cvvNo.length() != 3) {
                cvv.setError("enter proper cvv number");
            } else if (!cvvNo.equals("123")) {
                cvv.setError("invalid cvv number");
            } else if (TextUtils.isEmpty(ExpireDate)) {
                MM_YY.setError("enter expire date of card");
            } else if (!ExpireDate.contains("/")) {
                MM_YY.setError("enter proper expire date");
            } else if (ExpireDate.length() != 5) {
                MM_YY.setError("enter proper expire date");
            } else {

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("ProductName",productName);
                hashMap.put("ProductDes",productDes);
                hashMap.put("ProductPrice",productPrice);

                firebaseFirestore.collection("MyOrders")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .set(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                startActivity(new Intent(getApplicationContext(),OrderSuccess.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(CheckOut.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}