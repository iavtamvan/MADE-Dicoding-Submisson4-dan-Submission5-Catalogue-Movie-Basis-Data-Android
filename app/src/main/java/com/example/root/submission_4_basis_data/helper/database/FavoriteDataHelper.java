package com.example.root.submission_4_basis_data.helper.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.root.submission_4_basis_data.helper.Config;

public class FavoriteDataHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "movie_faforite.db";
    private static final int DATABASE_VERSION = 24;
    public FavoriteDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table favorite(_id INTEGER primary key AUTOINCREMENT, id INTEGER not null, tittle text, tgl text null, vote_average text null, vote_count text null, original_language text null, overview text null, status_favorite text null);";
        Log.d("Data", "onCreate: " + CREATE_TABLE);
        String CREATE_TABLE_FAVORITE = "CREATE TABLE favorite (" +
                Config.MoviesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Config.MoviesEntry.FIELD_ID + " INTEGER NOT NULL, " +
                Config.MoviesEntry.FIELD_TITTLE + " TEXT NOT NULL, " +
                Config.MoviesEntry.FIELD_TGL + " TEXT NOT NULL, " +
                Config.MoviesEntry.FIELD_VOTE_AVERAGE + " TEXT NOT NULL, " +
                Config.MoviesEntry.FIELD_VOTE_COUNT + " TEXT NOT NULL, "+
                Config.MoviesEntry.FIELD_ORIGINAL_LANGUAGE + " TEXT NOT NULL, " +
                Config.MoviesEntry.FIELD_OVERVIEW + " TEXT NOT NULL, " +
                Config.MoviesEntry.FIELD_STATUS_FAVORITE + " TEXT NOT NULL, " +
                Config.MoviesEntry.FIELD_POSTER_PATH + " TEXT NOT NULL, " +
                Config.MoviesEntry.FIELD_RELEASE_DATE + " TEXT NOT NULL, " +
                Config.MoviesEntry.FIELD_POPULARITY + " TEXT NOT NULL, " +
                Config.MoviesEntry.FIELD_BACKDROPH_PATH + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }
}
