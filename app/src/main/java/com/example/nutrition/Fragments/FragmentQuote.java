package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Model.QModel;
import com.example.nutrition.R;
import com.example.nutrition.databinding.FragmentQuoteBinding;

public class FragmentQuote extends Fragment implements IEssentials {

    private FragmentQuoteBinding binding;
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
        binding = FragmentQuoteBinding.bind(inflater.inflate(R.layout.fragment_quote, container, false));

        instantiateObjects();
        additionalThemeChanges();
        addEventListeners();

        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void instantiateObjects() {
        binding.textViewQuote.setText("'" + qModel.getQuote() + "'");
        binding.textViewAuthor.setText(" - " + qModel.getAuthor());
    }

    @Override
    public void addEventListeners() {

    }

    private void additionalThemeChanges(){
        int quoteColor;
        int authorColor;

        if (isNightModeOn) {
            quoteColor = ContextCompat.getColor(getContext(), R.color.white60Opacity);
            authorColor = ContextCompat.getColor(getContext(), R.color.white);
        }
        else {
            quoteColor = ContextCompat.getColor(getContext(), R.color.black60Opacity);
            authorColor = ContextCompat.getColor(getContext(), R.color.black);
        }

        binding.textViewQuote.setTextColor(authorColor);
        binding.textViewAuthor.setTextColor(quoteColor);
    }
}








