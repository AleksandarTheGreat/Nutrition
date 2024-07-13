package com.example.nutrition.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nutrition.R;
import com.example.nutrition.databinding.ActivitySection1And2Binding;

public class Section1And2Activity extends ParentActivity {

    private ActivitySection1And2Binding binding;

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
        binding = ActivitySection1And2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void addEventListeners() {

    }

    @Override
    public void additionalThemeChanges() {

    }
}