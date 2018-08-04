package com.example.root.submission_4_basis_data.helper;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Config {
    public static final String AUTHORITY = "com.example.root.submission_4_basis_data";
    public static final String PATH_TASKS ="listfilm";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class MoviesEntry implements BaseColumns{
        public static Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_TASKS).build();

        public static final String DATABASE_TABLE = "favorite";
        public static final String CREATE_TABLE = "create table favorite(id integer primary key, tittle text null, tgl text null, vote_average text null, vote_count text null, original_language text null, overview text null, status_favorite text null);";
        public static final String FIELD_ID = "id";
        public static final String FIELD_ID_ = "_id";
        public static final String FIELD_TITTLE = "tittle";
        public static final String FIELD_TGL = "tgl";
        public static final String FIELD_VOTE_AVERAGE = "vote_average";
        public static final String FIELD_VOTE_COUNT = "vote_count";
        public static final String FIELD_ORIGINAL_LANGUAGE =  "original_language";
        public static final String FIELD_OVERVIEW = "overview";
        public static final String FIELD_STATUS_FAVORITE = "status_favorite";
        public static final String FIELD_POSTER_PATH = "poster";
        public static final String FIELD_RELEASE_DATE = "release_date";
        public static final String FIELD_POPULARITY = "popularity";
        public static final String FIELD_BACKDROPH_PATH = "backdroph_path";
    }

    public static final String ERROR_NETWORK = "Periksai koneksi anda";
    public static final String ERROR_LIST = "Response Skiped";

    public static final String BUNDLE_ID = "bundle_id";
    public static final String BUNDLE_POSTER_IMAGE = "bundle_image";
    public static final String BUNDLE_BACKDROPH_IMAGE = "bundle_image_backdroph";
    public static final String BUNDLE_TITTLE = "bundle_tittle";
    public static final String BUNDLE_OVERVIEW = "bundle_overview";
    public static final String BUNDLE_OVERVIEW_LANGUAGE = "bundle_overview_language";
    public static final String BUNDLE_RELEASE_DATE = "bundle_release_date";
    public static final String BUNDLE_VOTE_COUNT = "bundle_vote_count";
    public static final String BUNDLE_VOTE_AVERAGE = "bundle_vote_average";
    public static final String BUNDLE_POPULARITY = "bundle_popularity";
    public static final String BUNDLE_ORIGINAL_LANGUAGE = "bundle_language";


}
