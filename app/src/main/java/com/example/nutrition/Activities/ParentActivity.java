package com.example.nutrition.Activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.google.android.material.color.DynamicColors;

public abstract class ParentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int res = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (res == Configuration.UI_MODE_NIGHT_YES){
            Log.d("Tag", "Theme is night");
        } else {
            Log.d("Tag", "Theme is day");
        }
//        if (ThemeUtils.isNightModeActive(this)) {
//            setTheme(R.style.NightThemeNutrition);
//            Log.d("Tag", "Night mode on");
//        } else {
//            setTheme(R.style.DayThemeNutrition);
//            Log.d("Tag", "Day mode on");
//        }

    }

    public abstract void instantiateObjects();
    public abstract void addEventListeners();
    public abstract void additionalThemeChanges();
}
