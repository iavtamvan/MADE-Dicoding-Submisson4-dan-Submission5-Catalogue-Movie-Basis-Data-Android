package com.example.root.submission_4_basis_data.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.root.submission_4_basis_data.DetailActivity;
import com.example.root.submission_4_basis_data.R;
import com.example.root.submission_4_basis_data.helper.Config;
import com.example.root.submission_4_basis_data.model.FavoriteModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iav_root on 7/16/18.
 */
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<FavoriteModel> listPopuler;
    private List<FavoriteModel> searchResult;

    public FavoriteAdapter(Context context, ArrayList<FavoriteModel> listPopuler) {
        this.context = context;
        this.listPopuler = listPopuler;
    }

    @Override
    public FavoriteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FavoriteAdapter.MyViewHolder holder, final int position) {
        holder.tvJudulFilm.setText(listPopuler.get(position).getTitle());
        holder.tvoverview.setText(listPopuler.get(position).getOverview() + " ...");
        holder.tvrelease.setText(listPopuler.get(position).getReleaseDate());
        Glide.with(context)
                .load(listPopuler.get(position).getPosterPath())
                .into(holder.ivGambarFilm);

        holder.btn_item_list_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Config.BUNDLE_ID, listPopuler.get(position).getId());

                intent.putExtra(Config.BUNDLE_POSTER_IMAGE, listPopuler.get(position).getPosterPath());
                intent.putExtra(Config.BUNDLE_TITTLE, holder.tvJudulFilm.getText().toString().trim());
                intent.putExtra(Config.BUNDLE_OVERVIEW, listPopuler.get(position).getOverview());
                intent.putExtra(Config.BUNDLE_RELEASE_DATE, holder.tvrelease.getText().toString().trim());
                intent.putExtra(Config.BUNDLE_VOTE_COUNT, listPopuler.get(position).getVoteCount());
                intent.putExtra(Config.BUNDLE_VOTE_AVERAGE, listPopuler.get(position).getVoteAverage());
                intent.putExtra(Config.BUNDLE_POPULARITY, listPopuler.get(position).getPopularity());
                intent.putExtra(Config.BUNDLE_ORIGINAL_LANGUAGE, listPopuler.get(position).getOriginalLanguage());
                intent.putExtra(Config.BUNDLE_BACKDROPH_IMAGE, listPopuler.get(position).getBackdropPath());

                context.startActivity(intent);
            }
        });

        holder.btn_item_list_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = holder.tvJudulFilm.getText().toString().trim();
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.SUBJEK));
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.SHARE_VIA)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPopuler.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<FavoriteModel> FavoriteModels = new ArrayList<>();

                if (searchResult == null)
                    searchResult = listPopuler;
                if (constraint != null) {
                    if (listPopuler != null & searchResult.size() > 0) {
                        for (final FavoriteModel g : searchResult) {
                            if (g.getTitle().toLowerCase().contains(constraint.toString()))
                                FavoriteModels.add(g);
                        }
                    }
                    oReturn.values = FavoriteModels;
                }

                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listPopuler = (ArrayList<FavoriteModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGambarFilm;
        TextView tvJudulFilm;
        TextView tvoverview;
        TextView tvrelease;
        CardView cv_klick_detail;
        Button btn_item_list_share;
        Button btn_item_list_detail;

        MyViewHolder(View itemView) {
            super(itemView);
            ivGambarFilm = itemView.findViewById(R.id.iv_item_list_poster);
            tvJudulFilm = itemView.findViewById(R.id.tv_item_list_tittle);
            tvoverview = itemView.findViewById(R.id.tv_item_list_overview);
            tvrelease = itemView.findViewById(R.id.tv_item_list_date);
            cv_klick_detail = itemView.findViewById(R.id.cv_klik_detail);
            btn_item_list_share = itemView.findViewById(R.id.btn_item_list_share);
            btn_item_list_detail = itemView.findViewById(R.id.btn_item_list_detail);
        }
    }
}
