package com.example.favoritemovieapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.favoritemovieapps.helper.Config;

public class DetailActivity extends AppCompatActivity {

    private String image, tittle, overview, overview_language, release, voteCount, voteAverage, popularity, language, backdrophImage;
    private int id;
    private AppBarLayout appBar;
    private CollapsingToolbarLayout toolbarLayout;
    private ImageView ivDetailFavorite;
    private Toolbar toolbar;
    private ImageView ivDetailMovieFavorite;
    private TextView tvDetailReleaseDateFavorite;
    private TextView tvDetailVoteAverageFavorite;
    private RatingBar rating;
    private TextView tvDetailVoteCountFavorite;
    private TextView tvDetailOriginalLanguageFavorite;
    private TextView tvDetailOverviewFavorite;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        image = intent.getStringExtra(Config.BUNDLE_POSTER_IMAGE);
        tittle = intent.getStringExtra(Config.BUNDLE_TITTLE);
        getSupportActionBar().setTitle(tittle);

        id = Integer.parseInt(intent.getStringExtra(Config.BUNDLE_ID));
        overview = intent.getStringExtra(Config.BUNDLE_OVERVIEW);
        overview_language = intent.getStringExtra(Config.BUNDLE_OVERVIEW_LANGUAGE);
        release = intent.getStringExtra(Config.BUNDLE_RELEASE_DATE);

        voteCount = intent.getStringExtra(Config.BUNDLE_VOTE_COUNT);
        voteAverage = intent.getStringExtra(Config.BUNDLE_VOTE_AVERAGE);
        popularity = intent.getStringExtra(Config.BUNDLE_POPULARITY);
        language = intent.getStringExtra(Config.BUNDLE_ORIGINAL_LANGUAGE);
        backdrophImage = intent.getStringExtra(Config.BUNDLE_BACKDROPH_IMAGE);

        Glide.with(this).load(backdrophImage).error(R.drawable.ic_launcher_background).into(ivDetailMovieFavorite);
        Glide.with(this).load(image).error(R.drawable.ic_launcher_background).into(ivDetailFavorite);

        tvDetailReleaseDateFavorite.setText(release);
        tvDetailVoteAverageFavorite.setText(voteAverage);
        tvDetailVoteCountFavorite.setText(voteCount);
        tvDetailOriginalLanguageFavorite.setText(language);
        tvDetailOverviewFavorite.setText(overview);
        rating.setRating(Float.parseFloat(voteAverage));

    }

    private void initView() {
        appBar = findViewById(R.id.app_bar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        ivDetailFavorite = findViewById(R.id.iv_detail_favorite);
        toolbar = findViewById(R.id.toolbar);
        ivDetailMovieFavorite = findViewById(R.id.iv_detail_movie_favorite);
        tvDetailReleaseDateFavorite = findViewById(R.id.tv_detail_release_date_favorite);
        tvDetailVoteAverageFavorite = findViewById(R.id.tv_detail_vote_average_favorite);
        rating = findViewById(R.id.rating);
        tvDetailVoteCountFavorite = findViewById(R.id.tv_detail_vote_count_favorite);
        tvDetailOriginalLanguageFavorite = findViewById(R.id.tv_detail_original_language_favorite);
        tvDetailOverviewFavorite = findViewById(R.id.tv_detail_overview_favorite);
        fab = findViewById(R.id.fab);
    }
}
