package com.example.root.submission_4_basis_data;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.root.submission_4_basis_data.helper.Config;
import com.example.root.submission_4_basis_data.helper.database.FavoriteDataHelper;
import com.sackcentury.shinebuttonlib.ShineButton;

public class DetailActivity extends AppCompatActivity {

    private String image, tittle, overview, overview_language, release, voteCount, voteAverage, popularity, language, backdrophImage;
    private int id;

    private AppBarLayout appBar;
    private CollapsingToolbarLayout toolbarLayout;
    private Toolbar toolbar;
    private ImageView ivDetailMovie;
    private TextView tvDetailReleaseDate;
    private TextView tvDetailVoteAverage;
    private RatingBar rating;
    private TextView tvDetailVoteCount;
    private TextView tvDetailOriginalLanguage;
    private TextView tvDetailOverview;
    private ImageView ivDetailBackdrophImage;
    private FloatingActionButton fab;

    private FavoriteDataHelper favoriteDataHelper;
    private ShineButton btnFavorit;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        favoriteDataHelper = new FavoriteDataHelper(this);
        SQLiteDatabase sqLiteDatabase  = favoriteDataHelper.getWritableDatabase();
        sqLiteDatabase.isOpen();

        Intent intent = getIntent();
        image = intent.getStringExtra(Config.BUNDLE_POSTER_IMAGE);
        tittle = intent.getStringExtra(Config.BUNDLE_TITTLE);
        getSupportActionBar().setTitle(tittle);

        id = Integer.parseInt(intent.getStringExtra(Config.BUNDLE_ID));
        Toast.makeText(this, "Id Ku ? " + id , Toast.LENGTH_SHORT).show();
        overview = intent.getStringExtra(Config.BUNDLE_OVERVIEW);
        overview_language = intent.getStringExtra(Config.BUNDLE_OVERVIEW_LANGUAGE);
        release = intent.getStringExtra(Config.BUNDLE_RELEASE_DATE);

        voteCount = intent.getStringExtra(Config.BUNDLE_VOTE_COUNT);
        voteAverage = intent.getStringExtra(Config.BUNDLE_VOTE_AVERAGE);
        popularity = intent.getStringExtra(Config.BUNDLE_POPULARITY);
        language = intent.getStringExtra(Config.BUNDLE_ORIGINAL_LANGUAGE);
        backdrophImage = intent.getStringExtra(Config.BUNDLE_BACKDROPH_IMAGE);

        Glide.with(this).load(backdrophImage).error(R.drawable.ic_launcher_background).into(ivDetailBackdrophImage);
        Glide.with(this).load(image).error(R.drawable.ic_launcher_background).into(ivDetailMovie);

        tvDetailReleaseDate.setText(release);
        tvDetailVoteAverage.setText(voteAverage);
        tvDetailVoteCount.setText(voteCount);
        tvDetailOriginalLanguage.setText(language);
        tvDetailOverview.setText(overview);
        rating.setRating(Float.parseFloat(voteAverage));


        sharedPreferences = getApplicationContext().getSharedPreferences("SETTING", 0);
        Boolean favorit = sharedPreferences.getBoolean("FAVORITE"+ tittle, false);
        if (favorit){
            btnFavorit.setChecked(true);
        }
        btnFavorit.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                SharedPreferences.Editor  edit = sharedPreferences.edit();
                if (checked){
                    edit.putBoolean("FAVORITE"+tittle,true);
                    edit.commit();
                    saveDataFavorite();
                } else {
                    edit.putBoolean("FAVORITE"+tittle,false);
                    edit.commit();
//                    btnFavorit.setEnabled(false);
//                    hapusDataFavorite();
                }
            }
        });
//        favorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SQLiteDatabase sqLiteDatabase  =  favoriteDataHelper.getWritableDatabase();
//                String insert_favorite = "INSERT INTO favorite(" + Config.FIELD_TITTLE + ", " + Config.FIELD_TGL + ", " + Config.FIELD_VOTE_AVERAGE + ", " +
//                        Config.FIELD_VOTE_COUNT + ", " + Config.FIELD_ORIGINAL_LANGUAGE + ", " + Config.FIELD_OVERVIEW + ", " + Config.FIELD_STATUS_FAVORITE + ") " +
//                        "VALUES ('" + tittle + "', '" + release + "', '" + voteAverage + "', '" + voteCount + "', '" + language + "', '" + overview + "', 'favorite');";
//                sqLiteDatabase.execSQL(insert_favorite);
//                Toast.makeText(DetailActivity.this, "Sukses Favorite", Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    private void hapusDataFavorite() {
        getContentResolver().delete(Config.MoviesEntry.CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build(),
                null, null);
    }

    private void saveDataFavorite() {
        ContentValues contentValues  = new ContentValues();
        contentValues.put(Config.MoviesEntry.FIELD_ID, String.valueOf(id));
        contentValues.put(Config.MoviesEntry.FIELD_TITTLE, tittle);
        contentValues.put(Config.MoviesEntry.FIELD_TGL, release);
        contentValues.put(Config.MoviesEntry.FIELD_VOTE_AVERAGE, voteAverage);
        contentValues.put(Config.MoviesEntry.FIELD_VOTE_COUNT, voteCount);
        contentValues.put(Config.MoviesEntry.FIELD_ORIGINAL_LANGUAGE, language);
        contentValues.put(Config.MoviesEntry.FIELD_OVERVIEW, overview);
        contentValues.put(Config.MoviesEntry.FIELD_STATUS_FAVORITE, "favorite");
        contentValues.put(Config.MoviesEntry.FIELD_POSTER_PATH , image);
        contentValues.put(Config.MoviesEntry.FIELD_RELEASE_DATE , release);
        contentValues.put(Config.MoviesEntry.FIELD_POPULARITY , popularity);
        contentValues.put(Config.MoviesEntry.FIELD_BACKDROPH_PATH , backdrophImage);
        Uri uri = getContentResolver().insert(Config.MoviesEntry.CONTENT_URI,contentValues);
        Toast.makeText(this, "Uri " + uri, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Uri " + uri, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Uri " + uri, Toast.LENGTH_SHORT).show();
        Log.d("uri", "saveDataFavorite: "+ uri);
    }

    private void initView() {
        appBar = findViewById(R.id.app_bar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        toolbar = findViewById(R.id.toolbar);
        ivDetailMovie = findViewById(R.id.iv_detail_movie);
        tvDetailReleaseDate = findViewById(R.id.tv_detail_release_date);
        tvDetailVoteAverage = findViewById(R.id.tv_detail_vote_average);
        rating = findViewById(R.id.rating);
        tvDetailVoteCount = findViewById(R.id.tv_detail_vote_count);
        tvDetailOriginalLanguage = findViewById(R.id.tv_detail_original_language);
        tvDetailOverview = findViewById(R.id.tv_detail_overview);
        fab = findViewById(R.id.fab);
        ivDetailBackdrophImage = findViewById(R.id.iv_detail_backdroph_image);
        fab = findViewById(R.id.fab);
        btnFavorit = findViewById(R.id.btnFavorit);
    }
}
