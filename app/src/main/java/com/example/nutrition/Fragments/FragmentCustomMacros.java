package com.example.nutrition.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.CustomMacros;
import com.example.nutrition.R;
import com.example.nutrition.SharedPrefs.SharedPrefCustomMacros;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentCustomMacrosBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class FragmentCustomMacros extends Fragment implements IEssentials {

    private FragmentCustomMacrosBinding binding;
    private Toaster toaster;
    private CustomMacros customMacros;
    private AppCompatActivity appCompatActivity;

    public FragmentCustomMacros() {
        // Required empty public constructor
    }

    public FragmentCustomMacros(AppCompatActivity appCompatActivity){
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCustomMacrosBinding.bind(inflater.inflate(R.layout.fragment_custom_macros, container, false));

        instantiateObjects();
        addEventListeners();
        additionalThemeChanges();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        toaster = new Toaster(getContext());
        customMacros = SharedPrefCustomMacros.readFromSharedPref(getContext());

        if (customMacros.getProteins() >= 0)
            binding.textInputProteins.setText(String.valueOf(customMacros.getProteins()));

        if (customMacros.getCalories() >= 0)
            binding.textInputCalories.setText(String.valueOf(customMacros.getCalories()));

        if (customMacros.getCarbs() >= 0)
            binding.textInputCarbs.setText(String.valueOf(customMacros.getCarbs()));

        if (customMacros.getSugars() >= 0)
            binding.textInputSugars.setText(String.valueOf(customMacros.getSugars()));
    }

    @Override
    public void addEventListeners() {
        binding.buttonSaveCustomMacros.setOnClickListener(view -> {
            if (binding.textInputProteins.getText().toString().isEmpty()) {
                binding.textLayoutProteins.setError("Enter proteins");
                return;
            }

            binding.textLayoutProteins.setError("");
            if (binding.textInputCalories.getText().toString().isEmpty()) {
                binding.textLayoutCalories.setError("Enter calories");
                return;
            }

            binding.textLayoutCalories.setError("");
            if (binding.textInputCarbs.getText().toString().isEmpty()) {
                binding.textLayoutCarbs.setError("Enter carbs");
                return;
            }

            binding.textLayoutCarbs.setError("");
            if (binding.textInputSugars.getText().toString().isEmpty()) {
                binding.textLayoutSugars.setError("Enter sugars");
                return;
            }

            binding.textLayoutSugars.setError("");

            int proteins = Integer.parseInt(binding.textInputProteins.getText().toString().trim());
            int calories = Integer.parseInt(binding.textInputCalories.getText().toString().trim());
            int carbs = Integer.parseInt(binding.textInputCarbs.getText().toString().trim());
            int sugars = Integer.parseInt(binding.textInputSugars.getText().toString().trim());

            SharedPrefCustomMacros.writeToSharedPref(getContext(),
                    proteins,
                    calories,
                    carbs,
                    sugars);

            toaster.text("Successfully saved custom macros");

            binding.textInputProteins.setText("");
            binding.textInputCalories.setText("");
            binding.textInputCarbs.setText("");
            binding.textInputSugars.setText("");

            appCompatActivity.getSupportFragmentManager().popBackStack();

        });
    }

    public void additionalThemeChanges(){
        if (ThemeUtils.isNightModeActive(appCompatActivity)) {
            int colorWhite = ContextCompat.getColor(getContext(), R.color.white);

            binding.textViewCustomMacros.setTextColor(colorWhite);
        } else {
            int colorBlack = ContextCompat.getColor(getContext(), R.color.black);

            binding.textViewCustomMacros.setTextColor(colorBlack);
        }
    }
}