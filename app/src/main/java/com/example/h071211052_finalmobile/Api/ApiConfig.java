package com.example.h071211052_finalmobile.Api;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    public static ApiService getApiService() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {

                    Request originalRequest = chain.request();
                    HttpUrl originalHttpUrl = originalRequest.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter("api_key",
                                    "a61543607c23d83cd6844d310cf8cbe9")
                            .build();

                    Request.Builder requestBuilder = originalRequest.newBuilder()
                            .url(url);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }
}
