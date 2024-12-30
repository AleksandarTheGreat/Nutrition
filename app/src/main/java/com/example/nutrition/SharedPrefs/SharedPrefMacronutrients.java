package com.example.nutrition.SharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPrefMacronutrients {

    private static final String NAME = "macroSharedPref";
    public static void writeMacronutrientToSharedPref(Context context, String macronutrient){
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("macro", macronutrient);
        editor.apply();

        Log.d("Tag", "Saved '" + macronutrient + "' to shared preferences");
    }

    public static String readMacronutrientFromSharedPref(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        String macronutrient = sharedPreferences.getString("macro", "Proteins");

        Log.d("Tag", "Read '" + macronutrient + "' from shared preferences");
        return macronutrient;
    }
}
