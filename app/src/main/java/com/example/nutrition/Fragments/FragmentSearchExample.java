package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Model.SEModel;
import com.example.nutrition.R;
import com.example.nutrition.databinding.FragmentSearchExampleBinding;

import java.util.List;

public class FragmentSearchExample extends Fragment implements IEssentials {

    private FragmentSearchExampleBinding binding;
    private SEModel seModel;
    private boolean isNightModeOn;

    public FragmentSearchExample() {
        // Required empty public constructor
    }

    public FragmentSearchExample(boolean isNightModeOn, SEModel seModel){
        this.isNightModeOn = isNightModeOn;
        this.seModel = seModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchExampleBinding.bind(inflater.inflate(R.layout.fragment_search_example, container, false));

        instantiateObjects();
        addEventListeners();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        binding.textViewTitleFragmentSearchExample.setText(seModel.getTitle());
        binding.textViewTitleDescriptionFragmentSearchExample.setText(seModel.getDescription());
        binding.imageViewFragmentSearchExample.setImageResource(seModel.getImageResource());
        setUpLayoutItems();
    }

    @Override
    public void addEventListeners() {

    }

    private void additionalThemeChanges(){

    }

    @SuppressLint("SetTextI18n")
    private void setUpLayoutItems(){
        List<String> list = seModel.getExamples();
        LinearLayout linearLayout = binding.linearLayoutExamplesFragmentSearchExample;
        linearLayout.removeAllViews();

        for (int i=0;i<list.size();i++){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 24);

            TextView textView = new TextView(getContext());
            textView.setLayoutParams(layoutParams);
            textView.setText(" -> " + list.get(i));

            linearLayout.addView(textView);
        }
    }
}













