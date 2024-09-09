package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Model.QModel;
import com.example.nutrition.R;
import com.example.nutrition.databinding.FragmentFragmentQuoteBinding;
import com.example.nutrition.databinding.FragmentQuiz2OptionsBinding;
import com.example.nutrition.databinding.FragmentQuizBinding;

public class FragmentQuote extends Fragment implements IEssentials {

    private FragmentFragmentQuoteBinding binding;
    private QModel qModel;
    private boolean isNightModeOn;

    public FragmentQuote() {
        // Required empty public constructor
    }

    public FragmentQuote(boolean isNightModeOn, QModel qModel){
        this.isNightModeOn = isNightModeOn;
        this.qModel = qModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFragmentQuoteBinding.bind(inflater.inflate(R.layout.fragment_fragment_quote, container, false));

        instantiateObjects();
        addEventListeners();

        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void instantiateObjects() {
        binding.textViewQuote.setText("'" + qModel.getQuote() + "'");
        binding.textViewAuthor.setText(qModel.getAuthor());
    }

    @Override
    public void addEventListeners() {

    }

    private void additionalThemeChanges(){

    }
}