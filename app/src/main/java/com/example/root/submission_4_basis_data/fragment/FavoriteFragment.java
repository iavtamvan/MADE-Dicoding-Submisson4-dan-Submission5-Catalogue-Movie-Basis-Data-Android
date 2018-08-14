package com.example.root.submission_4_basis_data.fragment;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.root.submission_4_basis_data.R;
import com.example.root.submission_4_basis_data.adapter.FavoriteAdapter;
import com.example.root.submission_4_basis_data.helper.Config;
import com.example.root.submission_4_basis_data.model.FavoriteModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private RecyclerView rv;
    private ArrayList<FavoriteModel> FavoriteModels;
    private FavoriteAdapter FavoriteAdapter ;
    private static final int ID_FILM_LOADER = 100;
    private String TAG = "Main";

    public FavoriteFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getActivity().getSupportLoaderManager().initLoader(ID_FILM_LOADER, null, this);
//        FavoriteModels = new ArrayList<>();
//        initAdapter(FavoriteModels);
//        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState == null){
            getActivity().getSupportLoaderManager().initLoader(ID_FILM_LOADER, null, this);
            FavoriteModels = new ArrayList<>();
            initAdapter(FavoriteModels);
            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null){
            getActivity().getSupportLoaderManager().initLoader(ID_FILM_LOADER, null, this);
            FavoriteModels = new ArrayList<>();
            initAdapter(FavoriteModels);
            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        initView(view);
        return view;

    }

    private void initAdapter(ArrayList<FavoriteModel> FavoriteModels) {
        FavoriteAdapter = new FavoriteAdapter(getActivity(), FavoriteModels);
        rv.setAdapter(FavoriteAdapter);
    }

    private void initView(View view) {
        rv = view.findViewById(R.id.rv);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case ID_FILM_LOADER:
                Uri filmUri = Config.MoviesEntry.CONTENT_URI;
                Log.d(TAG, "onCreateLoader: "+ filmUri.toString());
                return new CursorLoader(getActivity(), filmUri, null, null, null, null);
            default:
                throw new RuntimeException("Loader Not Implemented: " + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data.getCount()>0) {
            initAdapter(getMoviesFromCursor(data));
        } else {
            Toast.makeText(getActivity(), "Tidak Ada Favorite", Toast.LENGTH_LONG).show();
        }
    }

    private ArrayList<FavoriteModel> getMoviesFromCursor(Cursor cursor) {
        ArrayList<FavoriteModel> items = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()){
                do{
                    FavoriteModel FavoriteModel = new FavoriteModel(cursor);
                    FavoriteModel.setId(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_ID)));
                    FavoriteModel.setPosterPath(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_POSTER_PATH)));
                    FavoriteModel.setTitle(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_TITTLE)));
                    FavoriteModel.setOverview(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_OVERVIEW)));
                    FavoriteModel.setReleaseDate(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_RELEASE_DATE)));
                    FavoriteModel.setVoteCount(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_VOTE_COUNT)));
                    FavoriteModel.setVoteAverage(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_VOTE_AVERAGE)));
                    FavoriteModel.setPopularity(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_POPULARITY)));
                    FavoriteModel.setOriginalLanguage(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_ORIGINAL_LANGUAGE)));
                    FavoriteModel.setBackdropPath(cursor.getString(cursor.getColumnIndex(Config.MoviesEntry.FIELD_BACKDROPH_PATH)));
                    items.add(FavoriteModel);
                }while(cursor.moveToNext());
            }
        }
        return items;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
