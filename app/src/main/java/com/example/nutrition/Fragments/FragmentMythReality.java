package com.example.nutrition.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Model.MRModel;
import com.example.nutrition.R;
import com.example.nutrition.databinding.FragmentMythRealityBinding;

public class FragmentMythReality extends Fragment implements IEssentials {

    private FragmentMythRealityBinding binding;
    private MRModel mrModel;

    public FragmentMythReality() {
        // Required empty public constructor
    }

    public FragmentMythReality(MRModel mrModel){
        this.mrModel = mrModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMythRealityBinding.bind(inflater.inflate(R.layout.fragment_myth_reality, container, false));

        instantiateObjects();
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
}