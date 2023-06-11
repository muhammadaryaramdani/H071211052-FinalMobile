package com.example.h071211052_finalmobile.adapter;

import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211052_finalmobile.DetailsActivity;
import com.example.h071211052_finalmobile.R;
import com.example.h071211052_finalmobile.models.Favorite;

import java.util.List;
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private List<Favorite> favorites;

    public FavoriteAdapter(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Favorite favorite = favorites.get(position);
        holder.setData(favorite, context);
    }

    @Override
    public int getItemCount() {
        return favorites != null ? favorites.size() : 0;
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private ImageView poster;
        private TextView judul;
        private TextView tahun;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.iv_poster);
            judul = itemView.findViewById(R.id.tv_title);
            tahun = itemView.findViewById(R.id.tv_year_date);
        }

        public void setData(Favorite favorite, Context context) {
            String title = favorite.getTitle();
            String year = favorite.getReleaseDate();
            String gambar = favorite.getPosterPath();
            judul.setText(title);
            tahun.setText(year);
            Glide.with(context)
                    .load(gambar)
                    .into(poster);
            this.itemView.setOnClickListener(view ->  {
                Intent intent = new Intent(itemView.getContext(), DetailsActivity.class);
                intent.putExtra("favorite", favorite);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}




