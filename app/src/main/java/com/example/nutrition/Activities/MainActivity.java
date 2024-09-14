package com.example.nutrition.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

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
import com.example.nutrition.Helper.HelperMain;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.ActivityMainBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.color.MaterialColors;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

        binding.section3LayoutMainActivity.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, Section3Activity.class);
        });
    }

    @Override
    public void additionalThemeChanges() {
        if (ThemeUtils.isNightModeActive(this)){

            int color = ContextCompat.getColor(MainActivity.this, R.color.colorText60Light);
            int colorBright = ContextCompat.getColor(MainActivity.this, R.color.colorTextLight);
            int colorWhite = ContextCompat.getColor(MainActivity.this, R.color.white);

            binding.imageViewIconSection1.setImageResource(R.drawable.ic_pyramid_white);
            binding.imageViewIconSection3.setImageResource(R.drawable.ic_calendar_white);
            binding.imageViewIconSection0.setImageResource(R.drawable.ic_educate_light);
            binding.imageViewArrow.setImageResource(R.drawable.ic_right_white);

            binding.textViewSub2.setTextColor(color);
            binding.textViewSub3.setTextColor(color);
            binding.textView1.setTextColor(colorBright);
            binding.textView2.setTextColor(colorBright);
            binding.textView3.setTextColor(colorBright);
            binding.textView4.setTextColor(colorBright);
            binding.textView5.setTextColor(colorBright);

            binding.textViewTotalDays.setTextColor(colorWhite);
            binding.textViewSubTitleSection0.setTextColor(colorWhite);
            binding.textViewSubTitleSection1.setTextColor(colorWhite);
            binding.textViewSubTitleSection3.setTextColor(colorWhite);
        } else {

            int color = ContextCompat.getColor(MainActivity.this, R.color.colorText60Dark);
            int colorDark = ContextCompat.getColor(MainActivity.this, R.color.colorTextDark);
            int colorBlack = ContextCompat.getColor(MainActivity.this, R.color.black);

            binding.imageViewIconSection1.setImageResource(R.drawable.ic_pyramid_black);
            binding.imageViewIconSection3.setImageResource(R.drawable.ic_calendar_black);
            binding.imageViewIconSection0.setImageResource(R.drawable.ic_educate_dark);
            binding.imageViewArrow.setImageResource(R.drawable.ic_right_black);

            binding.textViewSub2.setTextColor(color);
            binding.textViewSub3.setTextColor(color);
            binding.textView1.setTextColor(colorDark);
            binding.textView2.setTextColor(colorDark);
            binding.textView3.setTextColor(colorDark);
            binding.textView4.setTextColor(colorDark);
            binding.textView5.setTextColor(colorDark);

            binding.textViewTotalDays.setTextColor(colorBlack);
            binding.textViewSubTitleSection0.setTextColor(colorBlack);
            binding.textViewSubTitleSection1.setTextColor(colorBlack);
            binding.textViewSubTitleSection3.setTextColor(colorBlack);
        }

//        int primaryContainer = MaterialColors.getColor(this, com.google.android.material.R.attr.colorPrimaryContainer, Color.BLACK);
//        int secondaryContainer = MaterialColors.getColor(this, com.google.android.material.R.attr.colorSecondaryContainer, Color.BLACK);
//        int tertiaryContainer = MaterialColors.getColor(this, com.google.android.material.R.attr.colorTertiaryContainer, Color.BLACK);
//
//        binding.matCardDefinitions.setCardBackgroundColor(primaryContainer);
//        binding.matCardMythFact.setCardBackgroundColor(secondaryContainer);
//        binding.matCardQuiz.setCardBackgroundColor(tertiaryContainer);

        helperMain.setUpUIMasksOnCards(this, materialCardViews);
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
                if (daysAdapter.isEmpty()) binding.textViewNoDaysMainActivity.setVisibility(View.VISIBLE);
                else binding.textViewNoDaysMainActivity.setVisibility(View.INVISIBLE);

                binding.textViewTotalDays.setText(String.valueOf(daysAdapter.getItemCount()));
            });
        }).start();
    }
}





