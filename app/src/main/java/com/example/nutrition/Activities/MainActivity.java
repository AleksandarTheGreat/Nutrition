package com.example.nutrition.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.nutrition.Helper.HelperMain;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.ActivityMainBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

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
        helperMain.setUpCardEventListeners(materialCardViews, binding);

        binding.matCardDefinitions.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, IntroductionActivity.class, "definitions");
        });

        binding.matCardMythFact.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, IntroductionActivity.class, "mythFact");
        });

        binding.matCardQuiz.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, IntroductionActivity.class, "quiz");
        });

        binding.constraintLayoutDaysMainActivity.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, Section3Activity.class);
        });

    }

    @Override
    public void additionalThemeChanges() {
        if (ThemeUtils.isNightModeActive(this)){
            binding.imageViewLogoMainActivity.setImageResource(R.drawable.ic_logo_light);
            binding.imageViewGoRight.setImageResource(R.drawable.ic_right_white);
            binding.viewTrackingMask.setBackground(ContextCompat.getDrawable(this, R.drawable.dark_list));

            int color = ContextCompat.getColor(MainActivity.this, R.color.colorText60Light);
            int colorBright = ContextCompat.getColor(MainActivity.this, R.color.colorTextLight);

            binding.textViewSub2.setTextColor(color);
            binding.textViewSub3.setTextColor(color);
            binding.textView1.setTextColor(colorBright);
            binding.textView2.setTextColor(colorBright);
            binding.textView3.setTextColor(colorBright);
        } else {
            binding.imageViewLogoMainActivity.setImageResource(R.drawable.ic_logo_dark);
            binding.imageViewGoRight.setImageResource(R.drawable.ic_right_black);
            binding.viewTrackingMask.setBackground(ContextCompat.getDrawable(this, R.drawable.light_list));

            int color = ContextCompat.getColor(MainActivity.this, R.color.colorText60Dark);
            int colorDark = ContextCompat.getColor(MainActivity.this, R.color.colorTextDark);

            binding.textViewSub2.setTextColor(color);
            binding.textViewSub3.setTextColor(color);
            binding.textView1.setTextColor(colorDark);
            binding.textView2.setTextColor(colorDark);
            binding.textView3.setTextColor(colorDark);
        }

        helperMain.setUpUIMasksOnCards(this, materialCardViews);
    }
}





