package com.example.nutrition.Activities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.nutrition.Adapters.DaysAdapter;
import com.example.nutrition.Adapters.MyIntroFragAdapter;
import com.example.nutrition.BroadcastReceivers.DayReceiver;
import com.example.nutrition.Fragments.FragmentEducational;
import com.example.nutrition.Fragments.FragmentMainDays;
import com.example.nutrition.Fragments.FragmentPopularFoods;
import com.example.nutrition.Fragments.MyFragmentManager;
import com.example.nutrition.Helper.HelperMain;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.SharedPrefs.DaySharedPreferences;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.color.MaterialColors;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends ParentActivity {

    private ActivityMainBinding binding;
    private Toaster toaster;
    private HelperMain helperMain;
    private AppCompatActivity appCompatActivity;

    private FragmentEducational fragmentEducational;
    private FragmentMainDays fragmentMainDays;
    private FragmentPopularFoods fragmentPopularFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instantiateObjects();
        additionalThemeChanges();

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        addEventListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void instantiateObjects() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appCompatActivity = this;
        toaster = new Toaster(MainActivity.this);
        helperMain = new HelperMain(MainActivity.this);

        fragmentMainDays = new FragmentMainDays(appCompatActivity, binding);
        fragmentEducational = new FragmentEducational(appCompatActivity);
        fragmentPopularFoods = new FragmentPopularFoods(appCompatActivity);


        changeFragment(fragmentMainDays);
        binding.bottomNavigationViewMainActivity.getMenu().getItem(1).setChecked(true);

//        if (!DaySharedPreferences.readFromSharedPref(getApplicationContext())){
//            Calendar calendar = Calendar.getInstance();
//
//            DayReceiver.scheduleNewDayAlarm(getApplicationContext(), calendar);
//            DaySharedPreferences.writeToSharedPref(getApplicationContext(), true);
//        }
    }

    @Override
    public void addEventListeners() {
        binding.bottomNavigationViewMainActivity.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuItemEducational){
                    changeFragment(fragmentEducational);
                    toaster.text("Educational clicked");
                    return true;
                } else if (item.getItemId() == R.id.menuItemDays){
                    changeFragment(fragmentMainDays);
                    toaster.text("Days clicked");
                    return true;
                } else if (item.getItemId() == R.id.menuItemPopularFoods){
                    changeFragment(fragmentPopularFoods);
                    toaster.text("Popular foods clicked");
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void additionalThemeChanges() {
        if (ThemeUtils.isNightModeActive(this)){

            int colorBright = ContextCompat.getColor(MainActivity.this, R.color.colorTextLight);
            int colorWhite = ContextCompat.getColor(MainActivity.this, R.color.white);
            int colorBackground = MaterialColors.getColor(MainActivity.this, android.R.attr.colorBackground, Color.BLACK);

            binding.textViewTitleMainActivity.setTextColor(colorWhite);
            binding.textViewTotalDays.setTextColor(colorWhite);


        } else {

            int colorDark = ContextCompat.getColor(MainActivity.this, R.color.colorTextDark);
            int colorBlack = ContextCompat.getColor(MainActivity.this, R.color.black);
            int colorBackground = ContextCompat.getColor(MainActivity.this, R.color.white);

            binding.textViewTitleMainActivity.setTextColor(colorBlack);
            binding.textViewTotalDays.setTextColor(colorBlack);

        }
    }

    public void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(binding.fragmentContainerViewMainActivity.getId(), fragment)
                .commit();
    }
}





