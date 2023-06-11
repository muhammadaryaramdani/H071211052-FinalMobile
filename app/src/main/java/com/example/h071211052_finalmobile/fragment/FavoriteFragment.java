package com.example.h071211052_finalmobile.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071211052_finalmobile.CustomLayoutManager;
import com.example.h071211052_finalmobile.R;
import com.example.h071211052_finalmobile.adapter.FavoriteAdapter;
import com.example.h071211052_finalmobile.db.DatabaseContract;
import com.example.h071211052_finalmobile.db.DatabaseHelper;
import com.example.h071211052_finalmobile.models.Favorite;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Favorite> favoriteList = getAllMoviesFromDatabase();
        recyclerView = view.findViewById(R.id.rv_favorite);
        CustomLayoutManager layoutManager = new CustomLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(favoriteList);
        recyclerView.setAdapter(favoriteAdapter);
    }

    private List<Favorite> getAllMoviesFromDatabase() {
        List<Favorite> favoriteList = new ArrayList<>();
        DatabaseHelper movieHelper = new DatabaseHelper(getActivity());
        Cursor cursor = movieHelper.getAllFavorites();

        if (cursor != null && cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry._ID);
            int titleColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_TITLE);
            int releaseDateColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_RELEASE_DATE);
            int overviewColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_OVERVIEW);
            int posterUrlColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_POSTER_URL);
            int backdropUrlColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_BACKDROP_URL);
            int voteAverageColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_VOTE_AVERAGE);

            do {
                int id = cursor.getInt(idColumnIndex);
                String title = cursor.getString(titleColumnIndex);
                String releaseDate = cursor.getString(releaseDateColumnIndex);
                String overview = cursor.getString(overviewColumnIndex);
                String posterUrl = cursor.getString(posterUrlColumnIndex);
                String backdropUrl = cursor.getString(backdropUrlColumnIndex);
                double voteAverage = cursor.getDouble(voteAverageColumnIndex);

                Favorite favorite = new Favorite(id, overview, posterUrl, releaseDate, title, voteAverage, backdropUrl);
                favoriteList.add(favorite);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return favoriteList;
    }

}
