package com.example.nutrition.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentPredefinedMacrosBinding;


public class FragmentPredefinedMacros extends Fragment implements IEssentials {

    private AppCompatActivity appCompatActivity;
    private FragmentPredefinedMacrosBinding binding;
    private String gender;
    private Toaster toaster;

    public FragmentPredefinedMacros() {
        // Required empty public constructor
    }

    public FragmentPredefinedMacros(String gender, AppCompatActivity appCompatActivity) {
        this.gender = gender;
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPredefinedMacrosBinding.bind(inflater.inflate(R.layout.fragment_predefined_macros, container, false));

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

    }

    public void additionalThemeChanges(){
        if (ThemeUtils.isNightModeActive(appCompatActivity)){

        } else {

        }
    }
}