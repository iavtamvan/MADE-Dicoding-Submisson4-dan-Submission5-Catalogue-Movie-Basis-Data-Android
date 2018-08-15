package com.example.root.submission_4_basis_data;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.root.submission_4_basis_data.helper.Config;
import com.example.root.submission_4_basis_data.scheduler.AlarmReceiver;
import com.example.root.submission_4_basis_data.scheduler.SchedulerTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SettingActivity extends AppCompatActivity {

    private Switch switchDailyReminder;
    private Switch switchPopMovieReminder;
    private LinearLayout divTime;
    private TextView tvTime;


    private SchedulerTask schedulerTask;
    private AlarmReceiver alarmReceiver;
    private Calendar calendar;
    private Config config;

    boolean checkPopular;
    boolean checkDaily;
    private LinearLayout divClickTime;
    private Button btnKlikAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        schedulerTask = new SchedulerTask(SettingActivity.this);
        alarmReceiver = new AlarmReceiver();
        config = new Config(SettingActivity.this);
        calendar = Calendar.getInstance();

        checkPopular = config.getUpcomingStatus();
        checkDaily = config.getDailyStatus();

        switchPopMovieReminder.setChecked(checkPopular);
        switchDailyReminder.setChecked(checkDaily);

        switchDailyReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchDailyReminder.isChecked()) {
                    config.setDailytatus(true);
                    divTime.setVisibility(View.VISIBLE);
                    dailyReminderON();
                } else {
                    divTime.setVisibility(View.GONE);
                    config.setDailytatus(false);
                    dailyReminderOFF();
                }
            }
        });
        switchPopMovieReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchPopMovieReminder.isChecked()) {
                    config.setUpcomingStatus(true);
                    schedulerTask.createPreiodicTask();
                    Toast.makeText(SettingActivity.this, "Reminder Created", Toast.LENGTH_SHORT).show();
                } else {
                    config.setUpcomingStatus(false);
                    schedulerTask.cancelPeriodicTask();
                }
            }
        });

        btnKlikAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void dailyReminderOFF() {
        alarmReceiver.cancelAlarm(SettingActivity.this);
    }

    private void dailyReminderON() {
        divClickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                new TimePickerDialog(SettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        tvTime.setText(simpleDateFormat.format(calendar.getTime()));

                    }
                }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true).show();
                String reminder = simpleDateFormat.format(calendar.getTime());
                String messsage = "Please back soon";
                config.setReminderTime(reminder);
                config.setReminderMessage(messsage);
                alarmReceiver.setReminder(SettingActivity.this, Config.NOTIF_TYPE_REMINDER, reminder, messsage);
            }
        });
    }

    private void initView() {
        switchDailyReminder = findViewById(R.id.switch_daily_reminder);
        switchPopMovieReminder = findViewById(R.id.switch_pop_movie_reminder);
        divTime = findViewById(R.id.div_time);
        tvTime = findViewById(R.id.tv_time);
        divClickTime = findViewById(R.id.div_click_time);
        btnKlikAlarm = findViewById(R.id.btn_klik_alarm);
    }
}
