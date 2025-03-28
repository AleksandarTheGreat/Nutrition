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
import android.view.View;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.nutrition.Adapters.DaysAdapter;
import com.example.nutrition.Adapters.MyIntroFragAdapter;
import com.example.nutrition.BroadcastReceivers.DayReceiver;
import com.example.nutrition.Helper.HelperMain;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.SharedPrefs.DaySharedPreferences;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.ActivityMainBinding;
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
    private MaterialCardView[] materialCardViews;
    private HelperMain helperMain;
    private AppCompatActivity appCompatActivity;
    private DaysAdapter daysAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instantiateObjects();
        additionalThemeChanges();

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addEventListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpMainActivityDaysAdapter();
    }

    @Override
    public void instantiateObjects() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appCompatActivity = this;
        toaster = new Toaster(MainActivity.this);

        materialCardViews = new MaterialCardView[]{binding.matCard1, binding.matCard2, binding.matCard3,
                binding.matCard4, binding.matCard5, binding.matCard6, binding.matCard7};
        helperMain = new HelperMain(MainActivity.this);

//        if (!DaySharedPreferences.readFromSharedPref(getApplicationContext())){
//            Calendar calendar = Calendar.getInstance();
//
//            DayReceiver.scheduleNewDayAlarm(getApplicationContext(), calendar);
//            DaySharedPreferences.writeToSharedPref(getApplicationContext(), true);
//        }
    }

    @Override
    public void addEventListeners() {
        helperMain.setUpCardEventListeners(materialCardViews);

        binding.matCardDefinitions.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, IntroductionActivity.class, MyIntroFragAdapter.TYPE_1);
        });

        binding.matCardMythFact.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, IntroductionActivity.class, MyIntroFragAdapter.TYPE_2);
        });

        binding.matCardQuiz.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, IntroductionActivity.class, MyIntroFragAdapter.TYPE_3);
        });

        binding.matCardSearchExamples.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, IntroductionActivity.class, MyIntroFragAdapter.TYPE_4);
        });

        binding.matCardMotivationalQuotes.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, IntroductionActivity.class, MyIntroFragAdapter.TYPE_5);
        });

        binding.imageViewArrow.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, Section3Activity.class);
        });

        binding.buttonGetStarted.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, Section3Activity.class);
        });
    }

    @Override
    public void additionalThemeChanges() {
        if (ThemeUtils.isNightModeActive(this)){

            int colorBright = ContextCompat.getColor(MainActivity.this, R.color.colorTextLight);
            int colorWhite = ContextCompat.getColor(MainActivity.this, R.color.white);
            int colorBackground = MaterialColors.getColor(MainActivity.this, android.R.attr.colorBackground, Color.BLACK);

            binding.imageViewIconSection1.setImageResource(R.drawable.ic_pyramid_white);
            binding.imageViewIconSection3.setImageResource(R.drawable.ic_calendar_white);
            binding.imageViewIconSection0.setImageResource(R.drawable.ic_educate_light);
            binding.imageViewArrow.setImageResource(R.drawable.ic_right_white);

            binding.textView1.setTextColor(colorBright);
            binding.textView2.setTextColor(colorBright);
            binding.textView3.setTextColor(colorBright);
            binding.textView4.setTextColor(colorBright);
            binding.textView5.setTextColor(colorBright);

            binding.textViewTitleMainActivity.setTextColor(colorWhite);
            binding.textViewTotalDays.setTextColor(colorWhite);
            binding.textViewSubTitleSection0.setTextColor(colorWhite);
            binding.textViewSubTitleSection1.setTextColor(colorWhite);
            binding.textViewSubTitleSection3.setTextColor(colorWhite);

            binding.textViewGrains.setTextColor(colorWhite);
            binding.textViewVegetables.setTextColor(colorWhite);
            binding.textViewFruit.setTextColor(colorWhite);
            binding.textViewMeatAndProteins.setTextColor(colorWhite);
            binding.textViewDairy.setTextColor(colorWhite);
            binding.textViewSweets.setTextColor(colorWhite);
            binding.textViewFastFood.setTextColor(colorWhite);

            binding.textViewGrains.setBackgroundColor(colorBackground);
            binding.textViewVegetables.setBackgroundColor(colorBackground);
            binding.textViewFruit.setBackgroundColor(colorBackground);
            binding.textViewMeatAndProteins.setBackgroundColor(colorBackground);
            binding.textViewDairy.setBackgroundColor(colorBackground);
            binding.textViewSweets.setBackgroundColor(colorBackground);
            binding.textViewFastFood.setBackgroundColor(colorBackground);

        } else {

            int colorDark = ContextCompat.getColor(MainActivity.this, R.color.colorTextDark);
            int colorBlack = ContextCompat.getColor(MainActivity.this, R.color.black);
            int colorBackground = ContextCompat.getColor(MainActivity.this, R.color.white);

            binding.imageViewIconSection1.setImageResource(R.drawable.ic_pyramid_black);
            binding.imageViewIconSection3.setImageResource(R.drawable.ic_calendar_black);
            binding.imageViewIconSection0.setImageResource(R.drawable.ic_educate_dark);
            binding.imageViewArrow.setImageResource(R.drawable.ic_right_black);

            binding.textView1.setTextColor(colorDark);
            binding.textView2.setTextColor(colorDark);
            binding.textView3.setTextColor(colorDark);
            binding.textView4.setTextColor(colorDark);
            binding.textView5.setTextColor(colorDark);

            binding.textViewTitleMainActivity.setTextColor(colorBlack);
            binding.textViewTotalDays.setTextColor(colorBlack);
            binding.textViewSubTitleSection0.setTextColor(colorBlack);
            binding.textViewSubTitleSection1.setTextColor(colorBlack);
            binding.textViewSubTitleSection3.setTextColor(colorBlack);

            binding.textViewGrains.setTextColor(colorBlack);
            binding.textViewVegetables.setTextColor(colorBlack);
            binding.textViewFruit.setTextColor(colorBlack);
            binding.textViewMeatAndProteins.setTextColor(colorBlack);
            binding.textViewDairy.setTextColor(colorBlack);
            binding.textViewSweets.setTextColor(colorBlack);
            binding.textViewFastFood.setTextColor(colorBlack);

            binding.textViewGrains.setBackgroundColor(colorBackground);
            binding.textViewVegetables.setBackgroundColor(colorBackground);
            binding.textViewFruit.setBackgroundColor(colorBackground);
            binding.textViewMeatAndProteins.setBackgroundColor(colorBackground);
            binding.textViewDairy.setBackgroundColor(colorBackground);
            binding.textViewSweets.setBackgroundColor(colorBackground);
            binding.textViewFastFood.setBackgroundColor(colorBackground);
        }
    }

    private void setUpMainActivityDaysAdapter(){
        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(() -> {
            daysAdapter = new DaysAdapter(MainActivity.this, appCompatActivity);

            handler.post(() -> {
                binding.recyclerViewDaysMainActivity.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                binding.recyclerViewDaysMainActivity.setHasFixedSize(true);
                binding.recyclerViewDaysMainActivity.setAdapter(daysAdapter);

                // check if the adapter is empty
                // and since we only interact via click
                // and don't load or delete new days this is enough
                if (daysAdapter.isEmpty()){
                    binding.textViewNoDaysMainActivity.setVisibility(View.VISIBLE);
                    binding.imageViewRunning.setVisibility(View.VISIBLE);
                    binding.buttonGetStarted.setVisibility(View.VISIBLE);
                } else {
                    binding.textViewNoDaysMainActivity.setVisibility(View.GONE);
                    binding.imageViewRunning.setVisibility(View.GONE);
                    binding.buttonGetStarted.setVisibility(View.GONE);
                }

                binding.textViewTotalDays.setText(String.valueOf(daysAdapter.getItemCount()));
            });
        }).start();
    }
}





