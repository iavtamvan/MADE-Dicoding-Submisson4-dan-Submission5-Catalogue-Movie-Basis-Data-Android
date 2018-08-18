package com.example.root.submission_4_basis_data.scheduler;

import android.content.Context;

import com.example.root.submission_4_basis_data.helper.Config;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;

public class SchedulerTask {
    private GcmNetworkManager mGcmNetworkManager;

    public SchedulerTask(Context context) {
        this.mGcmNetworkManager = GcmNetworkManager.getInstance(context);
    }

    public void createPreiodicTask(){
        Task periodikTask = new PeriodicTask.Builder()
                .setService(SchedulerService.class)
                .setPeriod(30)
                .setFlex(10)
                .setTag(Config.TAG_TASK_MOVIE_LOG)
                .setPersisted(true)
                .build();
        mGcmNetworkManager.schedule(periodikTask);
    }

    public void cancelPeriodicTask(){
        if (mGcmNetworkManager!=null){
            mGcmNetworkManager.cancelTask(Config.TAG_TASK_MOVIE_LOG, SchedulerService.class);
        }
    }
}
