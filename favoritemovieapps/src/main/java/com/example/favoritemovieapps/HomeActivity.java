package com.example.favoritemovieapps;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.favoritemovieapps.adapter.FavoriteAdapter;
import com.example.favoritemovieapps.helper.Config;
import com.example.favoritemovieapps.model.FavoriteModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private RecyclerView rv;
    private ArrayList<FavoriteModel> favoriteModels;
    private com.example.favoritemovieapps.adapter.FavoriteAdapter favoriteAdapter;
    private static final int ID_FILM_LOADER = 100;
    private String TAG = "Main";
    private Cursor  cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        favoriteModels = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        favoriteAdapter  = new FavoriteAdapter(HomeActivity.this);
        favoriteAdapter.setListMovie(cursor);
        rv.setAdapter(favoriteAdapter);

        if (savedInstanceState == null) {
            getSupportLoaderManager().restartLoader(ID_FILM_LOADER, null, this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getSupportLoaderManager().restartLoader(ID_FILM_LOADER, null, null);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        switch (id){
//            case ID_FILM_LOADER:
//                Uri filmUri = Config.MoviesEntry.CONTENT_URI;
//                Log.d(TAG, "onCreateLoader: "+ filmUri.toString());
//                return new CursorLoader(HomeActivity.this, filmUri, null, null, null, null);
//            default:
//                throw new RuntimeException("Loader Not Implemented: " + id);
//        }

        return new CursorLoader(this, Config.MoviesEntry.CONTENT_URI, null, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        Toast.makeText(this, "Loader Data " + data, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "Loader Data " + data, Toast.LENGTH_SHORT).show();
//        if(data.getCount()>0) {
//            initAdapter(getMoviesFromCursor(data));
//        } else {
//            Toast.makeText(HomeActivity.this, "Tidak Ada Favorite", Toast.LENGTH_LONG).show();
//        }

        cursor = data;
        favoriteAdapter.setListMovie(cursor);
        favoriteAdapter.notifyDataSetChanged();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Favorite Kosong", Toast.LENGTH_SHORT).show();
        }

    }


//    private ArrayList<FavoriteModel> getMoviesFromCursor(Cursor cursor) {
//        ArrayList<FavoriteModel> items = new ArrayList<>();
//        Toast.makeText(this, "Model faforite : " + items, Toast.LENGTH_SHORT).show();
//        if (cursor != null) {
//            if (cursor.moveToFirst()){
//                do{
//                    FavoriteModel FavoriteModel = new FavoriteModel();
//                    FavoriteModel.setId(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_ID)));
//                    FavoriteModel.setPosterPath(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_POSTER_PATH)));
//                    FavoriteModel.setTitle(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_TITTLE)));
//                    FavoriteModel.setOverview(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_OVERVIEW)));
//                    FavoriteModel.setReleaseDate(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_RELEASE_DATE)));
//                    FavoriteModel.setVoteCount(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_VOTE_COUNT)));
//                    FavoriteModel.setVoteAverage(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_VOTE_AVERAGE)));
//                    FavoriteModel.setPopularity(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_POPULARITY)));
//                    FavoriteModel.setOriginalLanguage(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_ORIGINAL_LANGUAGE)));
//                    FavoriteModel.setBackdropPath(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_BACKDROPH_PATH)));
//                    items.add(FavoriteModel);
//                }while(cursor.moveToNext());
//            }
//        }
//        return items;
//    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        favoriteAdapter.setListMovie(null);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void initView() {
        rv = findViewById(R.id.rv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(ID_FILM_LOADER);
    }
}
