package com.example.nutrition.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Model.MRModel;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentMythRealityBinding;

public class FragmentMythReality extends Fragment implements IEssentials {

    private FragmentMythRealityBinding binding;
    private AppCompatActivity appCompatActivity;
    private MRModel mrModel;

    public FragmentMythReality() {
        // Required empty public constructor
    }

    public FragmentMythReality(AppCompatActivity appCompatActivity, MRModel mrModel){
        this.appCompatActivity = appCompatActivity;
        this.mrModel = mrModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMythRealityBinding.bind(inflater.inflate(R.layout.fragment_myth_reality, container, false));

        instantiateObjects();
        additionalThemeChanges();

        addEventListeners();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        binding.textViewActualMyth.setText(mrModel.getMyth());
        binding.textViewActualFact.setText(mrModel.getReality());
    }

    @Override
    public void addEventListeners() {

    }

    public void additionalThemeChanges(){
        if (ThemeUtils.isNightModeActive(appCompatActivity)){
            int color = ContextCompat.getColor(getContext(), R.color.colorText60Light);

            binding.textViewActualMyth.setTextColor(color);
            binding.textViewActualFact.setTextColor(color);
        } else {
            int color = ContextCompat.getColor(getContext(), R.color.colorText60Dark);

            binding.textViewActualMyth.setTextColor(color);
            binding.textViewActualFact.setTextColor(color);
        }
    }
}





