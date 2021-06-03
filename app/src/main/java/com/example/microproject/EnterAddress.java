package com.example.microproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class EnterAddress extends AppCompatActivity {
    EditText apt, street, land, city, state, pinCode;
    Button btn_next;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_address);

        apt = findViewById(R.id.apt);
        street = findViewById(R.id.street);
        land = findViewById(R.id.land);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        pinCode = findViewById(R.id.pinCode);
        btn_next = findViewById(R.id.btn_next);
        progressBar = findViewById(R.id.progressBar);

        btn_next.setOnClickListener(view -> {
            String apartment = apt.getText().toString();
            String LocStreet = street.getText().toString();
            String LocLand = land.getText().toString();
            String LocCity = city.getText().toString();
            String LocState = state.getText().toString();
            String LocPinCode = pinCode.getText().toString();

            if (TextUtils.isEmpty(apartment)) {
                apt.setError("this field can't be empty");
            } else if (TextUtils.isEmpty(LocStreet)) {
                street.setError("this field can't be empty");
            } else if (TextUtils.isEmpty(LocCity)) {
                city.setError("this field can't be empty");
            } else if (TextUtils.isEmpty(LocState)) {
                state.setError("this field can't be empty");
            } else if (TextUtils.isEmpty(LocPinCode)) {
                pinCode.setError("this field can't be empty");
            } else {

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("Apartment",apartment);
                hashMap.put("Street",LocStreet);
                hashMap.put("LandMark",LocLand);
                hashMap.put("City",LocCity);
                hashMap.put("State",LocState);
                hashMap.put("PinCode",LocPinCode);

                FirebaseFirestore.getInstance().collection("MyAddress")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .set(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progressBar.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(getApplicationContext(),ProductList.class);
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(EnterAddress.this, "something went wrong",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}