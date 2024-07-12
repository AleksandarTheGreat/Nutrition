package com.example.nutrition.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;

public abstract class ParentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ThemeUtils.isNightModeActive(this)){
            setTheme(R.style.NightThemeNutrition);
            Log.d("Tag", "Night mode on");
        }
        else {
            setTheme(R.style.DayThemeNutrition);
            Log.d("Tag", "Day mode on");
        }
    }

    public abstract void instantiateObjects();
    public abstract void addEventListeners();
    public abstract void additionalThemeChanges();
}
