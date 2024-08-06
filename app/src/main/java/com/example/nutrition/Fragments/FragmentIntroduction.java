package com.example.nutrition.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Model.Macronutrient;
import com.example.nutrition.R;
import com.example.nutrition.databinding.FragmentIntroductionBinding;

public class FragmentIntroduction extends Fragment implements IEssentials {

    private FragmentIntroductionBinding binding;
    private Macronutrient macronutrient;

    public FragmentIntroduction() {}
    public FragmentIntroduction(Macronutrient macronutrient){
        this.macronutrient = macronutrient;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentIntroductionBinding.bind(inflater.inflate(R.layout.fragment_introduction, container, false));

        instantiateObjects();
        addEventListeners();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        binding.textViewTitleIntroductionFragment.setText(macronutrient.getTitle());
        binding.textViewExplanationIntroductionFragment.setText(macronutrient.getExplanation());
        binding.imageViewIconIntroductionFragment.setImageResource(macronutrient.getImageResource());
    }

    @Override
    public void addEventListeners() {

    }
}