package com.example.microproject.fav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.microproject.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MyFav extends AppCompatActivity {
    RecyclerView recycler_fav;
    FavAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fav);

        recycler_fav = findViewById(R.id.recycler_fav);
        recycler_fav.setLayoutManager(new LinearLayoutManager(this));
        recycler_fav.setHasFixedSize(true);

        Query query = FirebaseDatabase.getInstance().getReference()
                .child("MyFav")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        FirebaseRecyclerOptions<FavModel> options =
                new FirebaseRecyclerOptions.Builder<FavModel>()
                .setQuery(query, FavModel.class).build();

        adapter = new FavAdapter(options);
        recycler_fav.setAdapter(adapter);
        adapter.startListening();
    }
}