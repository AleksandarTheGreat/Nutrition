package com.example.nutrition.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.nutrition.Adapters.MyIntroFragAdapter;
import com.example.nutrition.Fragments.FragmentIntroduction;
import com.example.nutrition.Model.Macronutrient;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.ActivityIntroductionBinding;

import java.util.ArrayList;
import java.util.List;

public class IntroductionActivity extends ParentActivity {

    private ActivityIntroductionBinding binding;
    private MyIntroFragAdapter myIntroFragAdapter;
    private int pageCounter;
    private AppCompatActivity appCompatActivity;

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
    public void instantiateObjects() {
        binding = ActivityIntroductionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appCompatActivity = this;

        pageCounter = 0;
        myIntroFragAdapter = new MyIntroFragAdapter(this, this, appCompatActivity);
        binding.viewPagerIntroductionActivity.setAdapter(myIntroFragAdapter);

        updateImageArrowsVisibility();
    }

    @Override
    public void addEventListeners() {
        binding.imageViewArrowLeft.setOnClickListener(view -> {
            binding.viewPagerIntroductionActivity.setCurrentItem(--pageCounter);
            updateImageArrowsVisibility();
        });

        binding.imageViewArrowRight.setOnClickListener(view -> {
            binding.viewPagerIntroductionActivity.setCurrentItem(++pageCounter);
            updateImageArrowsVisibility();
        });

        binding.viewPagerIntroductionActivity.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                pageCounter = position;
                updateImageArrowsVisibility();
            }
        });
    }

    @Override
    public void additionalThemeChanges() {
        if (ThemeUtils.isNightModeActive(this)){
            binding.imageViewArrowLeft.setImageResource(R.drawable.ic_left_white);
            binding.imageViewArrowRight.setImageResource(R.drawable.ic_right_white);
        } else {
            binding.imageViewArrowLeft.setImageResource(R.drawable.ic_left_black);
            binding.imageViewArrowRight.setImageResource(R.drawable.ic_right_black);
        }
    }

    private void updateImageArrowsVisibility(){
        if (pageCounter <= 0)
            binding.imageViewArrowLeft.setVisibility(View.INVISIBLE);
        else binding.imageViewArrowLeft.setVisibility(View.VISIBLE);

        if (pageCounter >= myIntroFragAdapter.getItemCount() - 1)
            binding.imageViewArrowRight.setVisibility(View.INVISIBLE);
        else binding.imageViewArrowRight.setVisibility(View.VISIBLE);
    }

}