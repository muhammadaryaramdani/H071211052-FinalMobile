package com.example.h071211052_finalmobile.Api;

import com.example.h071211052_finalmobile.MoviesDataResponse;
import com.example.h071211052_finalmobile.listresponses.ListMoviesResponse;
import com.example.h071211052_finalmobile.listresponses.ListTvshowsResponse;
import com.example.h071211052_finalmobile.TvShowsDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/now_playing")
    Call<ListMoviesResponse> getMovies(@Query("language") String language,
                                       @Query("page") int page);

    @GET("tv/top_rated")
    Call<ListTvshowsResponse> getTvshows(@Query("language") String language,
                                         @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<MoviesDataResponse> getMovieById(@Path("movie_id") int movieId,
                                          @Query("language") String language);

    @GET("tv/{tv_id}")
    Call<TvShowsDataResponse> getTvShowById(@Path("tv_id") int tvId,
                                            @Query("language") String language);
}
