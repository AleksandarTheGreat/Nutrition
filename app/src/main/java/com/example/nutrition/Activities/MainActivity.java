package com.example.nutrition.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nutrition.Helper.HelperMain;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.R;
import com.example.nutrition.databinding.ActivityMainBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class MainActivity extends ParentActivity {

    private ActivityMainBinding binding;
    private Toaster toaster;
    private MaterialCardView[] materialCardViews;
    private HelperMain helperMain;

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

        toaster = new Toaster(MainActivity.this);

        materialCardViews = new MaterialCardView[]{binding.matCard1, binding.matCard2, binding.matCard3, binding.matCard4, binding.matCard5};
        helperMain = new HelperMain(MainActivity.this);
    }

    @Override
    public void addEventListeners() {
        binding.chipGroupMainActivity.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                binding.buttonFilterMainActivity.setEnabled(!checkedIds.isEmpty());

                StringBuilder builder = new StringBuilder();
                for (Integer id : checkedIds) {
                    Chip chip = group.findViewById(id);
                    builder.append(chip.getText().toString()).append(", ");
                }

                toaster.text(builder.toString());
            }
        });

        helperMain.setUpCardEventListeners(materialCardViews, binding);

        binding.buttonListMainActivity.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, Section1And2Activity.class);
        });

        binding.buttonFilterMainActivity.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, Section1And2Activity.class);
        });

        binding.imageViewAddNewDay.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, Section3Activity.class);
        });

        binding.constraintLayoutDaysMainActivity.setOnClickListener(view -> {
            helperMain.goToActivity(MainActivity.this, Section3Activity.class);
        });
    }

    @Override
    public void additionalThemeChanges() {

    }
}





