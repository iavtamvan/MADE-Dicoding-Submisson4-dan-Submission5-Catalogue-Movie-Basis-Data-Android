package com.example.root.submission_4_basis_data.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
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
    public static final String BUNDLE_FAVORITE = "1";
    public static final String BUNDLE_EXTRA_ITEM = "com.example.root.submission_4_basis_data.EXTRA_ITEM";
    public static final String BUNDLE_TOAST_ACTION = "com.example.root.submission_4_basis_data.TOAST_ACTION";

    public static final String NOTIF_EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String NOTIF_TYPE_MESSAGE = "TYPE_MESSAGE";
    public static final String NOTIF_TYPE_REMINDER = "TYPE_REMINDER";
    public static final int NOTIF_ID_REMINDER  = 101;

    public static final String TAG_TASK_MOVIE_LOG = "Movie_Pop";


    private final static String PREF_NAME = "reminderMoviePreferences";
    private final static String KEY_REMINDER_MOVIE_TIME = "reminderTime";
    private final static String KEY_REMINDER_MOVIE_MESSAGE = "reminderMessage";
    private final static String KEY_FIELD_UPCOMING_REMINDER = "checkedPopular";
    private final static String KEY_FIELD_DAILY_REMINDER = "checkedDaily";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public Config(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void setReminderTime(String time) {
        editor.putString(KEY_REMINDER_MOVIE_TIME, time);
        editor.commit();
    }

    public String getReminderTime() {
        return sharedPreferences.getString(KEY_REMINDER_MOVIE_TIME, "");
    }

    public void setReminderMessage(String message) {
        editor.putString(KEY_REMINDER_MOVIE_MESSAGE, message);
        editor.commit();
    }

    public String getReminderMessage() {
        return sharedPreferences.getString(KEY_REMINDER_MOVIE_MESSAGE, "");
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }

    public void setUpcomingStatus(Boolean status) {
        editor.putBoolean(KEY_FIELD_UPCOMING_REMINDER, status);
        editor.commit();
    }

    public void setDailytatus(Boolean status) {
        editor.putBoolean(KEY_FIELD_DAILY_REMINDER, status);
        editor.commit();
    }

    public Boolean getUpcomingStatus() {
        return sharedPreferences.getBoolean(KEY_FIELD_UPCOMING_REMINDER, false);
    }

    public Boolean getDailyStatus() {
        return sharedPreferences.getBoolean(KEY_FIELD_DAILY_REMINDER, false);
    }



    public static String getColomnString(Cursor cursor, String colomnName) {
        return cursor.getString(cursor.getColumnIndex(colomnName));
    }


    public static int getColomnInt(Cursor cursor, String colomnName) {
        return cursor.getInt(cursor.getColumnIndex(colomnName));
    }
}
