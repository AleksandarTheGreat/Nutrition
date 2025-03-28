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
    private boolean isNightModeActive;
    private MRModel mrModel;

    public FragmentMythReality() {
        // Required empty public constructor
    }

    public FragmentMythReality(boolean isNightModeActive, MRModel mrModel){
        this.isNightModeActive = isNightModeActive;
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
        if (isNightModeActive){
            int colorWhite = ContextCompat.getColor(getContext(), R.color.white);

            binding.textViewActualMyth.setTextColor(colorWhite);
            binding.textViewActualFact.setTextColor(colorWhite);
            binding.textViewMythTitle.setTextColor(colorWhite);
            binding.textViewFactTitle.setTextColor(colorWhite);
        } else {
            int colorBlack = ContextCompat.getColor(getContext(), R.color.black);

            binding.textViewActualMyth.setTextColor(colorBlack);
            binding.textViewActualFact.setTextColor(colorBlack);
            binding.textViewMythTitle.setTextColor(colorBlack);
            binding.textViewFactTitle.setTextColor(colorBlack);
        }
    }
}





