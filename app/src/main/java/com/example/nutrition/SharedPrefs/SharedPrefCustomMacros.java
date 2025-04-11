package com.example.nutrition.SharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.nutrition.Model.CustomMacros;

public class SharedPrefCustomMacros {

    private static final String NAME = "macrosSharedPref";

    public static void writeToSharedPref(Context context, int proteins, int calories, int carbs, int sugars){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("proteins", proteins);
        editor.putInt("calories", calories);
        editor.putInt("carbs", carbs);
        editor.putInt("sugars", sugars);

        editor.apply();
        Log.d("Tag", "Written custom macros to shared preferences");
    }

    public static CustomMacros readFromSharedPref(Context context){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);

        int proteins = sharedPreferences.getInt("proteins", -1);
        int calories = sharedPreferences.getInt("calories", -1);
        int carbs = sharedPreferences.getInt("carbs", -1);
        int sugars = sharedPreferences.getInt("sugars", -1);

        return new CustomMacros(proteins, calories, carbs, sugars);
    }
}
