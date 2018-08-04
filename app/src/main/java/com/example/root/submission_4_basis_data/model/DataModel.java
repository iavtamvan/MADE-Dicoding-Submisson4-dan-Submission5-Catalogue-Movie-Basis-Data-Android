package com.example.root.submission_4_basis_data.model;

public class DataModel {

    private String rowid;
    private String tittle;
    private String tgl;
    private String vote_average;
    private String vote_count;
    private String original_language;
    private String overview;
    private String status_favorite;

//    public DataModel(String rowid, String tittle, String tgl, String vote_average, String vote_count, String original_language, String overview, String status_favorite) {
//        this.rowid = rowid;
//        this.tittle = tittle;
//        this.tgl = tgl;
//        this.vote_average = vote_average;
//        this.vote_count = vote_count;
//        this.original_language = original_language;
//        this.overview = overview;
//        this.status_favorite = status_favorite;
//    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getStatus_favorite() {
        return status_favorite;
    }

    public void setStatus_favorite(String status_favorite) {
        this.status_favorite = status_favorite;
    }
}
