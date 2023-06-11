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
import com.example.h071211052_finalmobile.adapter.TvshowsAdapter;
import com.example.h071211052_finalmobile.Api.ApiConfig;
import com.example.h071211052_finalmobile.models.TvshowsResponse;
import com.example.h071211052_finalmobile.listresponses.ListTvshowsResponse;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvshowsFragment extends Fragment {

    TvshowsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {

            activity.getSupportActionBar().setTitle(getString(R.string.tv_shows));
        }

        Call<ListTvshowsResponse> call = ApiConfig.getApiService().
                getTvshows(MainActivity.language, 1);
        List<TvshowsResponse> tvshowsResponses = new ArrayList<>();


        MaterialTextView mtvNoInternet = view.findViewById(R.id.mtv_no_internet);
        RecyclerView recyclerView = view.findViewById(R.id.rv_tv_shows);
        Button btnRetry = view.findViewById(R.id.btn_retry);

        recyclerView.setVisibility(View.INVISIBLE);

        btnRetry.setOnClickListener(v -> getActivity().recreate());

        call.enqueue(new Callback<ListTvshowsResponse>() {
            @Override
            public void onResponse(@NonNull Call<ListTvshowsResponse> call,
                                   @NonNull Response<ListTvshowsResponse> response) {


                recyclerView.setVisibility(View.VISIBLE);

                if (response.isSuccessful() && response.body() != null) {

                    ListTvshowsResponse listTvshowsResponse = response.body();
                    tvshowsResponses.addAll(listTvshowsResponse.getTvshowsData());
                }

                adapter = new TvshowsAdapter(tvshowsResponses);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                        LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<ListTvshowsResponse> call, @NonNull Throwable t) {

                mtvNoInternet.setVisibility(View.VISIBLE);
                btnRetry.setVisibility(View.VISIBLE);
            }
        });

    }
}