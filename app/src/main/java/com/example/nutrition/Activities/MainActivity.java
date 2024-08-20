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

        binding.constraintLayoutDaysMainActivity.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, Section3Activity.class);
        });

        binding.introductionLayoutMainActivity.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, IntroductionActivity.class);
        });
    }

    @Override
    public void additionalThemeChanges() {
        if (ThemeUtils.isNightModeActive(this)){
            binding.imageViewLogoMainActivity.setImageResource(R.drawable.ic_logo_light);
            binding.imageViewGoRight.setImageResource(R.drawable.ic_right_white);
            binding.imageViewGoRightIntro.setImageResource(R.drawable.ic_right_white);
            binding.viewTrackingMask.setBackground(ContextCompat.getDrawable(this, R.drawable.dark_list));

            binding.imageViewIntroductionArrow.setImageResource(R.drawable.ic_intro_white);
            binding.imageViewIconSection1.setImageResource(R.drawable.ic_pyramid_white);
            binding.imageViewIconSection3.setImageResource(R.drawable.ic_calendar_white);

            int color = ContextCompat.getColor(MainActivity.this, R.color.colorText60Light);

            binding.textViewSub1.setTextColor(color);
            binding.textViewSub2.setTextColor(color);
            binding.textViewSub3.setTextColor(color);
        } else {
            binding.imageViewLogoMainActivity.setImageResource(R.drawable.ic_logo_dark);
            binding.imageViewGoRight.setImageResource(R.drawable.ic_right_black);
            binding.imageViewGoRightIntro.setImageResource(R.drawable.ic_right_black);
            binding.viewTrackingMask.setBackground(ContextCompat.getDrawable(this, R.drawable.light_list));

            binding.imageViewIntroductionArrow.setImageResource(R.drawable.ic_intro_black);
            binding.imageViewIconSection1.setImageResource(R.drawable.ic_pyramid_black);
            binding.imageViewIconSection3.setImageResource(R.drawable.ic_calendar_black);

            int color = ContextCompat.getColor(MainActivity.this, R.color.colorText60Dark);

            binding.textViewSub1.setTextColor(color);
            binding.textViewSub2.setTextColor(color);
            binding.textViewSub3.setTextColor(color);
        }

        helperMain.setUpUIMasksOnCards(this, materialCardViews);
    }
}





