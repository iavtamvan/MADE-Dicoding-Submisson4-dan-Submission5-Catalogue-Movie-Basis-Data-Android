package com.example.root.submission_4_basis_data.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.root.submission_4_basis_data.R;
import com.example.root.submission_4_basis_data.helper.Config;
import com.example.root.submission_4_basis_data.model.FavoriteModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.example.root.submission_4_basis_data.helper.Config.MoviesEntry.CONTENT_URI;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private int mAppWidgetId;
    private Cursor list;
    FavoriteModel data;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

    }

    public void onCreate() {
        list = mContext.getContentResolver().query(Config.MoviesEntry.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onDataSetChanged() {
        final long token = Binder.clearCallingIdentity();
        list = mContext.getContentResolver().query(Config.MoviesEntry.CONTENT_URI, null, null, null, null);
        Binder.restoreCallingIdentity(token);
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return list.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        if (list.moveToPosition(position)) {
            data = new FavoriteModel(list);
            String alamatGambar = "http://image.tmdb.org/t/p/w185";
            Bitmap bp = null;
            String posterPath = data.getPosterPath();
            try {
                bp = Picasso.with(mContext).load(posterPath).get();
                rv.setImageViewBitmap(R.id.imageView,bp);
            } catch (IOException e) {
                e.printStackTrace();
            }
//                bp = Picasso.with(mContext).load(alamatGambar + data.getPosterPath()).get();
        }

        Bundle extras = new Bundle();
        extras.putInt(Config.BUNDLE_EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

