package com.example.microproject.fav;

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

public class FavAdapter extends FirebaseRecyclerAdapter<FavModel, FavAdapter.FavViewHolder> {

    public FavAdapter(FirebaseRecyclerOptions<FavModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(FavAdapter.FavViewHolder holder, int position, FavModel model) {
        holder.fav_des.setText(model.FavDes);
        holder.fav_name.setText(model.FavName);
        Picasso.get().load(model.FavImg).into(holder.fav_img);
    }

    @Override
    public FavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_list,
                parent,false);
        return new FavAdapter.FavViewHolder(view);
    }

    public class FavViewHolder extends RecyclerView.ViewHolder {
        ImageView fav_img;
        TextView fav_des, fav_name;


        public FavViewHolder(View itemView) {
            super(itemView);

            fav_img = itemView.findViewById(R.id.fav_img);
            fav_des = itemView.findViewById(R.id.fav_des);
            fav_name = itemView.findViewById(R.id.fav_name);
        }
    }
}
