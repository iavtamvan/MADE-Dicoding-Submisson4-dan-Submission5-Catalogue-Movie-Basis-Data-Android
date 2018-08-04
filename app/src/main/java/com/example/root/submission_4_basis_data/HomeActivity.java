package com.example.root.submission_4_basis_data;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.root.submission_4_basis_data.fragment.FavoriteFragment;
import com.example.root.submission_4_basis_data.fragment.PopularMovieFragment;
import com.example.root.submission_4_basis_data.helper.database.FavoriteDataHelper;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class HomeActivity extends AppCompatActivity {
    FavoriteDataHelper dbcenter;
    private Button btn;
    private Button btnelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbcenter = new FavoriteDataHelper(this);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SQLiteDatabase sqLiteDatabase = dbcenter.getWritableDatabase();
//                String insert = "INSERT INTO favorite(" + Config.FIELD_ID + ", " + Config.FIELD_TITTLE + ", " + Config.FIELD_TGL + ", " + Config.FIELD_VOTE_AVERAGE + ", " +
//                        Config.FIELD_VOTE_COUNT + ", " + Config.FIELD_ORIGINAL_LANGUAGE + ", " + Config.FIELD_OVERVIEW + ", " + Config.FIELD_STATUS_FAVORITE + ") " +
//                        "VALUES ('4', 'MANUSIA', '28-08-2018', '7.1', '200', 'En', 'manusia ini setengah salmon', 'faforite'), ('5', 'MANUSIA', '28-08-2018', '7.1', '200', 'En', 'manusia ini setengah salmon', 'faforite');";
//
//                sqLiteDatabase.execSQL(insert);
//            }
//        });
//
//        btnelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SQLiteDatabase sqLiteDatabase  = dbcenter.getWritableDatabase();
//                String delete = "DELETE FROM favorite;";
//                sqLiteDatabase.execSQL(delete);
//            }
//        });

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.string_popular_movie, PopularMovieFragment.class)
                .add(R.string.string_favorite, FavoriteFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);


    }

}
