package com.example.root.submission_4_basis_data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieModel {

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private ArrayList<ResultsItem> results;
    @SerializedName("results")
    private ArrayList<FavoriteModel> results1;

    @SerializedName("total_results")
    private int totalResults;

    public ArrayList<ResultsItem> getResults() {
        return results;
    }

    public ArrayList<FavoriteModel> getResultsFavorite() {
        return results1;
    }
}