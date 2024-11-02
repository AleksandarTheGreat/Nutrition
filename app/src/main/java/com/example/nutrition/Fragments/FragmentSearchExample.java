package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anychart.core.annotations.Line;
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
        additionalThemeChanges();
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
        if (isNightModeOn){
            int textColor = ContextCompat.getColor(getContext(), R.color.white60Opacity);
            binding.textViewTitleDescriptionFragmentSearchExample.setTextColor(textColor);
        } else {
            int textColor = ContextCompat.getColor(getContext(), R.color.black60Opacity);
            binding.textViewTitleDescriptionFragmentSearchExample.setTextColor(textColor);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setUpLayoutItems(){
        List<String> list = seModel.getExamples();
        LinearLayout linearLayout = binding.linearLayoutExamplesFragmentSearchExample;
        linearLayout.removeAllViews();

        int color = 0;

//        if (isNightModeOn) color = ContextCompat.getColor(getContext(), R.color.white);
//        else color = ContextCompat.getColor(getContext(), R.color.black);

        for (int i=0;i<list.size();i++){

            LinearLayout.LayoutParams layoutParamsLinearLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamsLinearLayout.setMargins(0, 0, 0, 24);

            LinearLayout linearLayout1 = new LinearLayout(getContext());
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1.setGravity(Gravity.CENTER);
            linearLayout1.setLayoutParams(layoutParamsLinearLayout);
            linearLayout1.setPadding(4, 4, 4, 4);



            LinearLayout.LayoutParams layoutParamsImageView = new LinearLayout.LayoutParams(50, 50);
            layoutParamsImageView.setMargins(12, 0, 12, 0);

            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(layoutParamsImageView);
            imageView.setImageResource(seModel.getSmallIconResource());



            LinearLayout.LayoutParams layoutParamsTextView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamsTextView.setMargins(12, 0, 12, 0);

            TextView textView = new TextView(getContext());
            textView.setLayoutParams(layoutParamsTextView);
            textView.setTextSize(18);
            textView.setText(list.get(i));


            linearLayout1.addView(imageView);
            linearLayout1.addView(textView);
            linearLayout.addView(linearLayout1);
        }
    }
}













