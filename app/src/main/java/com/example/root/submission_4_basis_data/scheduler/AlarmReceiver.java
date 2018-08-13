package com.example.root.submission_4_basis_data.scheduler;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.root.submission_4_basis_data.HomeActivity;
import com.example.root.submission_4_basis_data.R;
import com.example.root.submission_4_basis_data.helper.Config;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {



    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String message = intent.getStringExtra(Config.NOTIF_EXTRA_MESSAGE);

        String title = "Daily Reminder";

        showAlarmNotification(context, title, message, Config.NOTIF_ID_REMINDER);
    }


    private void showAlarmNotification(Context context, String title, String message, int notifId) {
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // TODO ragu
        Intent intent = new Intent(context, HomeActivity.class);
        // TODO ---
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            pendingIntent = TaskStackBuilder.create(context).addNextIntent(intent)
                    .getPendingIntent(Config.NOTIF_ID_REMINDER, PendingIntent.FLAG_UPDATE_CURRENT);


            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setSound(alarmSound);
            notificationManagerCompat.notify(notifId, builder.build());
//            if (notificationManagerCompat != null) {
//                notificationManagerCompat.notify(notifId, builder.build());
//            }
        } else {
            Toast.makeText(context, "Tidak Support", Toast.LENGTH_SHORT).show();
        }
    }


    public void setReminder(Context context, String type, String time, String message) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(Config.NOTIF_EXTRA_MESSAGE, message);
        intent.putExtra(Config.NOTIF_TYPE_MESSAGE, type);

        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Config.NOTIF_ID_REMINDER, intent, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Config.NOTIF_ID_REMINDER, intent, 0);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
        Toast.makeText(context, "Reminder dihapus", Toast.LENGTH_SHORT).show();
    }
}
