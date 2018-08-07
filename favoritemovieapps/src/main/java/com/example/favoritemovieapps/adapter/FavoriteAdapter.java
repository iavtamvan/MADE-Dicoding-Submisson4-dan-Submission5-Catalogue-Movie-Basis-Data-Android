package com.example.favoritemovieapps.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.favoritemovieapps.DetailActivity;
import com.example.favoritemovieapps.R;
import com.example.favoritemovieapps.helper.Config;
import com.example.favoritemovieapps.model.FavoriteModel;

/**
 * Created by iav_root on 7/16/18.
 */
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {
    private Cursor cursor;
    private Context context;

    public void setListMovie(Cursor listMovie) {
        this.cursor = listMovie;
    }

    public FavoriteAdapter(Activity context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_fav, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final FavoriteModel favoriteModel = getItem(position);
        Toast.makeText(context, "" + favoriteModel.getPosterPath(), Toast.LENGTH_SHORT).show();

        Glide.with(context).load(favoriteModel.getPosterPath()).error(R.drawable.ic_launcher_background).into(holder.ivItemListPoster);
        holder.tvItemListTittle.setText(favoriteModel.getTitle());
        holder.tvItemListOverview.setText(favoriteModel.getOverview());
        holder.tvItemListDate.setText(favoriteModel.getReleaseDate());

        holder.cvKlikDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Config.BUNDLE_ID, favoriteModel.getId());

                intent.putExtra(Config.BUNDLE_POSTER_IMAGE, favoriteModel.getPosterPath());
                intent.putExtra(Config.BUNDLE_TITTLE, favoriteModel.getTitle());
                intent.putExtra(Config.BUNDLE_OVERVIEW, favoriteModel.getOverview());
                intent.putExtra(Config.BUNDLE_RELEASE_DATE, favoriteModel.getReleaseDate());
                intent.putExtra(Config.BUNDLE_VOTE_COUNT, favoriteModel.getVoteCount());
                intent.putExtra(Config.BUNDLE_VOTE_AVERAGE, favoriteModel.getVoteAverage());
                intent.putExtra(Config.BUNDLE_POPULARITY, favoriteModel.getPopularity());
                intent.putExtra(Config.BUNDLE_ORIGINAL_LANGUAGE, favoriteModel.getOriginalLanguage());
                intent.putExtra(Config.BUNDLE_BACKDROPH_IMAGE, favoriteModel.getBackdropPath());

                context.startActivity(intent);
                
            }
        });

    }

    private FavoriteModel getItem(int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Postion Invalid");
        }
        return new FavoriteModel(cursor);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView cvKlikDetail;
        private ImageView ivItemListPoster;
        private TextView tvItemListTittle;
        private TextView tvItemListOverview;
        private TextView tvItemListDate;
        private Button btnItemListDetail;
        private Button btnItemListShare;
        public MyViewHolder(View itemView) {
            super(itemView);

            cvKlikDetail = itemView.findViewById(R.id.cv_klik_detail);
            ivItemListPoster = itemView.findViewById(R.id.iv_item_list_poster);
            tvItemListTittle = itemView.findViewById(R.id.tv_item_list_tittle);
            tvItemListOverview = itemView.findViewById(R.id.tv_item_list_overview);
            tvItemListDate = itemView.findViewById(R.id.tv_item_list_date);
            btnItemListDetail = itemView.findViewById(R.id.btn_item_list_detail);
            btnItemListShare = itemView.findViewById(R.id.btn_item_list_share);
        }
    }
}
