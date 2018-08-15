package com.example.root.submission_4_basis_data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
    private LinearLayout divSettings;
    private SmartTabLayout viewpagertab;
    private ViewPager viewpager;
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        dbcenter = new FavoriteDataHelper(this);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.string_popular_movie, PopularMovieFragment.class)
                .add(R.string.string_favorite, FavoriteFragment.class)
                .create());

        viewpager.setAdapter(adapter);
        viewpagertab.setViewPager(viewpager);

        divSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
            }
        });


    }

    private void initView() {
        divSettings = findViewById(R.id.div_settings);
        viewpagertab = findViewById(R.id.viewpagertab);
        viewpager = findViewById(R.id.viewpager);
    }
}
