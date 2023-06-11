package com.example.h071211052_finalmobile.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.h071211052_finalmobile.MainActivity;
import com.example.h071211052_finalmobile.R;
import com.example.h071211052_finalmobile.adapter.MoviesAdapter;
import com.example.h071211052_finalmobile.Api.ApiConfig;
import com.example.h071211052_finalmobile.models.MoviesResponse;
import com.example.h071211052_finalmobile.listresponses.ListMoviesResponse;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {

    private MoviesAdapter moviesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.getSupportActionBar().setTitle(getString(R.string.movies));
        }

        Call<ListMoviesResponse> call = ApiConfig.getApiService().
                getMovies(MainActivity.language, 1);
        List<MoviesResponse> moviesResponses = new ArrayList<>();


        MaterialTextView mtvNoInternet = view.findViewById(R.id.mtv_no_internet);
        RecyclerView recyclerView = view.findViewById(R.id.rv_movies);
        Button btnRetry = view.findViewById(R.id.btn_retry);
        recyclerView.setVisibility(View.INVISIBLE);

        btnRetry.setOnClickListener(v -> getActivity().recreate());

        call.enqueue(new Callback<ListMoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<ListMoviesResponse> call,
                                   @NonNull Response<ListMoviesResponse> response) {


                recyclerView.setVisibility(View.VISIBLE);

                if (response.isSuccessful() && response.body() != null) {

                    ListMoviesResponse listMoviesResponse = response.body();
                    moviesResponses.addAll(listMoviesResponse.getMoviesData());
                }

                moviesAdapter = new MoviesAdapter(moviesResponses);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                        LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(moviesAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<ListMoviesResponse> call, @NonNull Throwable t) {

                mtvNoInternet.setVisibility(View.VISIBLE);
                btnRetry.setVisibility(View.VISIBLE);
            }
        });
    }
}
