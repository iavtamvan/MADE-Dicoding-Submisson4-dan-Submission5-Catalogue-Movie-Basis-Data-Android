package com.example.root.submission_4_basis_data.scheduler;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.root.submission_4_basis_data.DetailActivity;
import com.example.root.submission_4_basis_data.R;
import com.example.root.submission_4_basis_data.helper.Config;
import com.example.root.submission_4_basis_data.model.FavoriteModel;
import com.example.root.submission_4_basis_data.model.MovieModel;
import com.example.root.submission_4_basis_data.model.ResultsItem;
import com.example.root.submission_4_basis_data.rest.ApiService;
import com.example.root.submission_4_basis_data.rest.Client;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchedulerService extends GcmTaskService {

    public final String TAG = "MoviePop";

    public ArrayList<ResultsItem> favoriteModels = new ArrayList<>();

    @Override
    public int onRunTask(TaskParams taskParams) {
        int result = 0;
        if (taskParams.getTag().equals(Config.TAG_TASK_MOVIE_LOG)) {
            getCurrentMovie();
            result = GcmNetworkManager.RESULT_SUCCESS;
        }
        return result;
    }

    private void getCurrentMovie() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getMovieNowPlaying().enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                final List<ResultsItem> favoriteModels1 = response.body().getResults();
                favoriteModels.addAll(favoriteModels1);
                showNotification(getApplicationContext(), favoriteModels);
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }

    private void showNotification(Context applicationContext, ArrayList<ResultsItem> listMovie) {
        NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alaramSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent i = new Intent(applicationContext, DetailActivity.class);

        i.putExtra(Config.BUNDLE_TITTLE, listMovie.get(0).getTitle());
        i.putExtra(Config.BUNDLE_RELEASE_DATE, listMovie.get(0).getReleaseDate());
        i.putExtra(Config.BUNDLE_OVERVIEW, listMovie.get(0).getOverview());
        i.putExtra(Config.BUNDLE_POSTER_IMAGE, listMovie.get(0).getPosterPath());
        i.putExtra(Config.BUNDLE_BACKDROPH_IMAGE, listMovie.get(0).getBackdropPath());
        i.putExtra(Config.BUNDLE_POPULARITY, listMovie.get(0).getPopularity());
        i.putExtra(Config.BUNDLE_ORIGINAL_LANGUAGE, listMovie.get(0).getOriginalLanguage());
        i.putExtra(Config.BUNDLE_VOTE_COUNT, listMovie.get(0).getVoteCount());
        i.putExtra(Config.BUNDLE_VOTE_AVERAGE, listMovie.get(0).getVoteAverage());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            int notifId = 102;
            PendingIntent pendingIntent = TaskStackBuilder.create(applicationContext)
                    .addNextIntent(i)
                    .getPendingIntent(notifId, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new android.support.v7.app.NotificationCompat.Builder(applicationContext)
                    .setContentTitle(listMovie.get(0).getTitle())
                    .setSmallIcon(R.drawable.ic_star_black_24dp)
                    .setContentText("Release pada : " + listMovie.get(0).getReleaseDate())
                    .setColor(ContextCompat.getColor(applicationContext, android.R.color.black))
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 100})
                    .setSound(alaramSound)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            if (notificationManager != null) {
                notificationManager.notify(notifId, builder.build());
            }
        } else {
            Toast.makeText(applicationContext, "Tidak Support", Toast.LENGTH_SHORT).show();
        }
    }
}
