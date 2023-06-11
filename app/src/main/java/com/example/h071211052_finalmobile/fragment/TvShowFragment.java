package com.example.h071211052_finalmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071211052_finalmobile.CustomLayoutManager;
import com.example.h071211052_finalmobile.R;
import com.example.h071211052_finalmobile.Tv;
import com.example.h071211052_finalmobile.TvShowService;
import com.example.h071211052_finalmobile.adapter.TvShowAdapter;
import com.example.h071211052_finalmobile.models.TvResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvShowFragment extends Fragment {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "dad1cd55d3f6d09536f1c6bde1fe8d07";
    private RecyclerView recyclerView;
    private TvShowAdapter tvAdapter;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_shows, container, false);

        recyclerView = view.findViewById(R.id.rv_tv_shows);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TvShowService tvShowService = retrofit.create(TvShowService.class);

        Call<TvResponse> call = tvShowService.getAiringTodayTV(API_KEY);

        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                if (response.isSuccessful()) {
                    TvResponse tvResponse = response.body();
                    List<Tv> tvShow = tvResponse.getTvShows();
                    tvAdapter = new TvShowAdapter(tvShow);
                    recyclerView.setAdapter(tvAdapter);

                    CustomLayoutManager layoutManager = new CustomLayoutManager(getContext(), 2);
                    recyclerView.setLayoutManager(layoutManager);

                } else {
                    Toast.makeText(getActivity(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

