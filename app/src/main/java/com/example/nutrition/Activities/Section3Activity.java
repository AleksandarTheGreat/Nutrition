package com.example.nutrition.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.nutrition.Fragments.FragmentADay;
import com.example.nutrition.Fragments.FragmentAllDays;
import com.example.nutrition.Fragments.FragmentSec3Adapter;
import com.example.nutrition.R;
import com.example.nutrition.databinding.ActivitySection3Binding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class Section3Activity extends ParentActivity {

    private ActivitySection3Binding binding;

    private FragmentAllDays fragmentAllDays;
    private FragmentADay fragmentADay;
    private FragmentSec3Adapter fragmentSec3Adapter;

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
        binding = ActivitySection3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentAllDays = new FragmentAllDays(binding);
        fragmentADay = new FragmentADay();

        fragmentSec3Adapter = new FragmentSec3Adapter(Section3Activity.this);

        binding.viewPager2Section3Activity.setAdapter(fragmentSec3Adapter);
    }

    @Override
    public void addEventListeners() {
        binding.tabLayoutSection3Activity.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager2Section3Activity.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        binding.viewPager2Section3Activity.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayoutSection3Activity.getTabAt(position).select();
            }
        });
    }

    @Override
    public void additionalThemeChanges() {

    }
}









