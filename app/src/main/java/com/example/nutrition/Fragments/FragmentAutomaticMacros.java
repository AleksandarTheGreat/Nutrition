package com.example.nutrition.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentAutomaticMacrosBinding;


public class FragmentAutomaticMacros extends Fragment implements IEssentials {

    private AppCompatActivity appCompatActivity;
    private FragmentAutomaticMacrosBinding binding;
    private Toaster toaster;

    public FragmentAutomaticMacros() {
        // Required empty public constructor
    }

    public FragmentAutomaticMacros(AppCompatActivity appCompatActivity){
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAutomaticMacrosBinding.bind(inflater.inflate(R.layout.fragment_automatic_macros, container, false));

        instantiateObjects();
        additionalThemeChanges();
        addEventListeners();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        toaster = new Toaster(getContext());
    }

    @Override
    public void addEventListeners() {
        binding.linearLayoutMale.setOnClickListener(view -> {
            MyFragmentManager.change2(appCompatActivity, new FragmentPredefinedMacros("male", appCompatActivity), true);
        });

        binding.linearLayoutFemale.setOnClickListener(view -> {
            MyFragmentManager.change2(appCompatActivity, new FragmentPredefinedMacros("female", appCompatActivity), true);
        });
    }

    public void additionalThemeChanges(){

    }
}