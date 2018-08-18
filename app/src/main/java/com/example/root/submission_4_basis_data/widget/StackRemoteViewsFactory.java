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

import com.example.root.submission_4_basis_data.R;
import com.example.root.submission_4_basis_data.helper.Config;
import com.example.root.submission_4_basis_data.model.FavoriteModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private int mAppWidgetId;
    private Cursor cursor;
    FavoriteModel data;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

    }

    public void onCreate() {
        cursor = context.getContentResolver().query(Config.MoviesEntry.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onDataSetChanged() {
        final long token = Binder.clearCallingIdentity();
        cursor = context.getContentResolver().query(Config.MoviesEntry.CONTENT_URI, null, null, null, null);
        Binder.restoreCallingIdentity(token);
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        if (cursor.moveToPosition(position)) {
            data = new FavoriteModel(cursor);
            String alamatGambar = "http://image.tmdb.org/t/p/w185";
            Bitmap bp = null;
            String posterPath = data.getPosterPath();
            try {
                bp = Picasso.with(context).load(posterPath).get();
                rv.setImageViewBitmap(R.id.imageView,bp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String tittle = data.getTitle();
            rv.setTextViewText(R.id.tv_tittle_film, tittle);
//                bp = Picasso.with(context).load(alamatGambar + data.getPosterPath()).get();
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

