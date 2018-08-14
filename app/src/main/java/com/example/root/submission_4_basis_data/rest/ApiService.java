package com.example.root.submission_4_basis_data.rest;

import com.example.root.submission_4_basis_data.BuildConfig;
import com.example.root.submission_4_basis_data.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET(BuildConfig.POPULAR_MOVIE)
    Call<MovieModel> getMovieNowPlaying();
}
