package com.example.nutrition.SharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class DaySharedPreferences {

    private static final String NAME = "daySharedPref";
    private static final String key = "isAnAlarmScheduled";

    public static void writeToSharedPref(Context context, boolean isAnAlarmScheduled){
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(key, isAnAlarmScheduled);
        editor.apply();

        Log.d("Tag", "Written '" + isAnAlarmScheduled + "' to day shared preferences");
    }

    public static boolean readFromSharedPref(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        boolean isAnAlarmScheduled = sharedPreferences.getBoolean(key, false);

        Log.d("Tag", "Read '" + isAnAlarmScheduled + "' from day shared preferences");
        return isAnAlarmScheduled;
    }

}
