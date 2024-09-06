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
import com.example.nutrition.R;
import com.example.nutrition.databinding.ActivitySection3Binding;

public class Section3Activity extends ParentActivity {

    private ActivitySection3Binding binding;
    private AppCompatActivity appCompatActivity;
    private FragmentAllDays fragmentAllDays;

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

        appCompatActivity = this;
        fragmentAllDays = new FragmentAllDays(appCompatActivity);

        MyFragmentManager.change(this, fragmentAllDays);
    }

    @Override
    public void addEventListeners() {}

    @Override
    public void additionalThemeChanges() {}
}









