package com.example.h071211052_finalmobile;

import com.example.h071211052_finalmobile.models.TvshowsResponse;
import com.google.gson.annotations.SerializedName;

public class TvShowsDataResponse {

    @SerializedName("results")
    private TvshowsResponse tvshowsData;

    public TvshowsResponse getTvshowsData() {
        return tvshowsData;
    }
}
