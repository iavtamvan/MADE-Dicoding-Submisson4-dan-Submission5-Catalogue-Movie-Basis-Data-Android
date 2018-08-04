package com.example.root.submission_4_basis_data.helper.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.root.submission_4_basis_data.helper.Config;

/**
 * Created by iav_root on 19/08/17.
 */

public class FilmContentProvider extends ContentProvider {
    // TODO 2 membuar Uri Matcher

    public static final int ALL_FILM = 100;
    public static final int FILM_WITH_ID = 101;

    private static final UriMatcher URI_MATCHER = buildurimatcher();

    private static UriMatcher buildurimatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Config.AUTHORITY, Config.PATH_TASKS,ALL_FILM);
        uriMatcher.addURI(Config.AUTHORITY, Config.PATH_TASKS + "/#", FILM_WITH_ID);
        return uriMatcher;
    }


    //1
    FavoriteDataHelper movieDBHelper;
    @Override
    public boolean onCreate() {
        Context context = getContext();
        movieDBHelper = new FavoriteDataHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        int match = URI_MATCHER.match(uri);
        Cursor retCursor;
        switch (match) {
            case ALL_FILM:
                retCursor =  db.query(Config.MoviesEntry.DATABASE_TABLE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = movieDBHelper.getWritableDatabase();

        int match = URI_MATCHER.match(uri);
        Uri returnUri; // URI to be returned

        switch (match) {
            case ALL_FILM:
                long id = db.insert(Config.MoviesEntry.DATABASE_TABLE, null, contentValues);
                if ( id > 0 ) {
                    returnUri = ContentUris.withAppendedId(Config.MoviesEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int numRowsDeleted;

        if (null == selection) selection = "1";

        switch (URI_MATCHER.match(uri)) {

            case ALL_FILM:
                numRowsDeleted = movieDBHelper.getWritableDatabase().delete(
                        Config.MoviesEntry.DATABASE_TABLE,
                        selection,
                        selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (numRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
