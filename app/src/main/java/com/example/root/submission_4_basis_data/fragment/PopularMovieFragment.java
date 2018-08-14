package com.example.root.submission_4_basis_data.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.submission_4_basis_data.R;
import com.example.root.submission_4_basis_data.adapter.MovieAdapter;
import com.example.root.submission_4_basis_data.helper.Config;
import com.example.root.submission_4_basis_data.helper.database.FavoriteDataHelper;
import com.example.root.submission_4_basis_data.model.MovieModel;
import com.example.root.submission_4_basis_data.model.ResultsItem;
import com.example.root.submission_4_basis_data.rest.ApiService;
import com.example.root.submission_4_basis_data.rest.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMovieFragment extends Fragment {
    private EditText edtSearch;
    private RecyclerView rvNowPlayingMovie;
    private MovieAdapter movieAdapter ;
    private ArrayList<ResultsItem>  resultsItems;
    private SQLiteDatabase sqLiteDatabase;
    FavoriteDataHelper dbHelper;
    public PopularMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular_movie, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        resultsItems = new ArrayList<>();
        dbHelper = new FavoriteDataHelper(getActivity());


        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()){
                    Toast.makeText(getActivity(), "Masukan keyword", Toast.LENGTH_SHORT).show();
                }
                else {
                    movieAdapter.getFilter().filter(s);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if (savedInstanceState == null){
            getDataPopular();
        } else {

//            ArrayList<Parcelable> parcelableArrayList = savedInstanceState.getParcelableArrayList(Config.BUNDLE_EXT);
            for (int i = 0; i < resultsItems.size(); i++) {
                resultsItems.add(resultsItems.get(i));
            }
            Log.d(TAG, "onViewCreated: "+resultsItems.size());
            rvNowPlayingMovie.setAdapter(new MovieAdapter(getActivity(), resultsItems));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putParcelableArrayList(Config.BUNDLE_EXT, (ArrayList<? extends Parcelable>) resultsItems);
    }

    private void getDataPopular() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getMovieNowPlaying().enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful()){
                    resultsItems = response.body().getResults();
                    movieAdapter = new MovieAdapter(getActivity(), resultsItems);
                    rvNowPlayingMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvNowPlayingMovie.setAdapter(movieAdapter);
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Toast.makeText(getActivity(), "" + Config.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null) {
            rvNowPlayingMovie.setAdapter(new MovieAdapter(getActivity(), resultsItems));
        }
    }

    private void initView(View view) {
        edtSearch = view.findViewById(R.id.edt_search);
        rvNowPlayingMovie = view.findViewById(R.id.rv_now_playing_movie);
    }
}
