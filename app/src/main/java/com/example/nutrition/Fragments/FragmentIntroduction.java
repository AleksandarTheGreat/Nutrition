package com.example.nutrition.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Model.Macronutrient;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentIntroductionBinding;

public class FragmentIntroduction extends Fragment implements IEssentials {

    private FragmentIntroductionBinding binding;
    private Macronutrient macronutrient;
    private boolean isNightModeActive;

    public FragmentIntroduction() {}
    public FragmentIntroduction(Macronutrient macronutrient, boolean isNightModeActive){
        this.macronutrient = macronutrient;
        this.isNightModeActive = isNightModeActive;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentIntroductionBinding.bind(inflater.inflate(R.layout.fragment_introduction, container, false));

        instantiateObjects();
        addEventListeners();
        additionalThemeChanges();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        binding.textViewTitleIntroductionFragment.setText(macronutrient.getTitle());
        binding.textViewExplanationIntroductionFragment.setText(macronutrient.getExplanation());
        binding.textViewTitleIntroductionFragment.setTextColor(ContextCompat.getColor(getContext(), macronutrient.getColorResource()));
        binding.imageViewIconIntroductionFragment.setImageResource(macronutrient.getImageResource());
    }

    @Override
    public void addEventListeners() {

    }

    public void additionalThemeChanges(){
        if (isNightModeActive){
            binding.textViewExplanationIntroductionFragment.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        } else {
            binding.textViewExplanationIntroductionFragment.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        }
    }
}