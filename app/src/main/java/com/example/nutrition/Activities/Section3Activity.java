package com.example.nutrition.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nutrition.Fragments.FragmentADay;
import com.example.nutrition.Fragments.FragmentAllDays;
import com.example.nutrition.Fragments.MyFragmentManager;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.databinding.ActivitySection3Binding;

public class Section3Activity extends ParentActivity {

    private ActivitySection3Binding binding;
    private AppCompatActivity appCompatActivity;

    private FragmentADay fragmentADay;
    private FragmentAllDays fragmentAllDays;
    private DaysRepo daysRepo;

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

        daysRepo = new DaysRepo(Section3Activity.this);
        appCompatActivity = this;

        // If I come here via a day click, change to the corresponding day fragment,
        // else if I come here via simple layout click, everything to normal

        // Get the day id, via the intent
        // get the corresponding day
        // change the fragment with that day
        long id = getIntent().getLongExtra("dayId", 0);
        if (id != 0) {
            Day day = daysRepo.get(id);
            fragmentADay = new FragmentADay(day, appCompatActivity);
            MyFragmentManager.change(appCompatActivity, fragmentADay, true);
        } else {
            fragmentAllDays = new FragmentAllDays(appCompatActivity);
            MyFragmentManager.change(appCompatActivity, fragmentAllDays, false);
        }
    }

    @Override
    public void addEventListeners() {}

    @Override
    public void additionalThemeChanges() {}
}









