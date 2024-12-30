package com.example.nutrition.BroadcastReceivers;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.nutrition.Activities.MainActivity;
import com.example.nutrition.Model.Day;
import com.example.nutrition.Repos.DaysRepo;

import java.time.LocalDate;
import java.util.Calendar;

public class DayReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Tag", "Alarm received");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Day day = new Day("Day", LocalDate.now());

            DaysRepo daysRepo = new DaysRepo(context);
            daysRepo.add(day);

            Log.d("Tag", "Successfully added a new day");

            // schedule a new alarm
            // with the time parameters
            // retrieved from the intent
            // and a new calendar instance
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    public static void scheduleNewDayAlarm(Context context, Calendar calendar){
        Intent intent = new Intent(context, DayReceiver.class);
        intent.setAction("com.example.nutrition.dayAlarm");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,1, intent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Log.d("Tag", "Alarm scheduled");
    }
}
