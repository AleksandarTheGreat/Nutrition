package com.example.nutrition.Utils;

import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatActivity;

public class ThemeUtils {

    public static boolean isNightModeActive(AppCompatActivity appCompatActivity){
        int currentNightMode = appCompatActivity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }
}
