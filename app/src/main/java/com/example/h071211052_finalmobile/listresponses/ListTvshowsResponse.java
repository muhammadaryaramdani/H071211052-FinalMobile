package com.example.h071211052_finalmobile.listresponses;

import com.example.h071211052_finalmobile.models.TvshowsResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListTvshowsResponse {

    @SerializedName("results")
    private List<TvshowsResponse> tvshowsData;

    public List<TvshowsResponse> getTvshowsData() {
        return tvshowsData;
    }
}
