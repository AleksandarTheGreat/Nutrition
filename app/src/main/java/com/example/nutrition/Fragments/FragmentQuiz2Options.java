package com.example.nutrition.Fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Model.Question2;
import com.example.nutrition.R;
import com.example.nutrition.databinding.FragmentQuiz2OptionsBinding;
import com.google.android.material.color.MaterialColors;

public class FragmentQuiz2Options extends Fragment implements IEssentials {

    private FragmentQuiz2OptionsBinding binding;
    private Question2 question2;
    private boolean isNightModeActive;

    public FragmentQuiz2Options() {
        // Required empty public constructor
    }

    public FragmentQuiz2Options(boolean isNightModeActive, Question2 question2){
        this.isNightModeActive = isNightModeActive;
        this.question2 = question2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQuiz2OptionsBinding.bind(inflater.inflate(R.layout.fragment_quiz2_options, container, false));

        instantiateObjects();
        additionalThemeChanges();
        addEventListeners();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        binding.textViewQuestion.setText(question2.getQuestion());
        binding.radioA.setText(question2.getOptionA());
        binding.radioB.setText(question2.getOptionB());
    }

    @Override
    public void addEventListeners() {
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                String tag = radioButton.getTag().toString().trim();
                int textColor;

                if (tag.equals(question2.getCorrectOption())){
                    if (isNightModeActive) textColor = MaterialColors.getColor(getContext(), android.R.attr.textColorPrimary, Color.WHITE);
                    else textColor = MaterialColors.getColor(getContext(), android.R.attr.textColorPrimary, Color.BLACK);
                    radioButton.setTextColor(textColor);

                    binding.textViewStatusFragmentQuiz.setText("Correct!");
                    binding.textViewStatusFragmentQuiz.setVisibility(View.VISIBLE);
                    binding.imageViewStatusFragmentQuiz.setImageResource(R.drawable.ic_correct);
                    binding.imageViewEmoji.setImageResource(R.drawable.ic_smiling);
                } else {
                    if (isNightModeActive) textColor = ContextCompat.getColor(getContext(), R.color.white60Opacity);
                    else textColor = ContextCompat.getColor(getContext(), R.color.black60Opacity);
                    radioButton.setTextColor(textColor);

                    binding.textViewStatusFragmentQuiz.setText("Incorrect!");
                    binding.textViewStatusFragmentQuiz.setVisibility(View.VISIBLE);
                    binding.imageViewStatusFragmentQuiz.setImageResource(R.drawable.ic_wrong);
                    binding.imageViewEmoji.setImageResource(R.drawable.ic_sad);
                }
            }
        });
    }

    private void additionalThemeChanges(){
        setRadioButtonColors();
    }

    private void setRadioButtonColors(){
        if (isNightModeActive){
            int color = ContextCompat.getColor(getContext(), R.color.white60Opacity);
            for (int i=0;i<binding.radioGroup.getChildCount();i++){
                RadioButton radioButton = (RadioButton) binding.radioGroup.getChildAt(i);
                radioButton.setTextColor(color);
            }
        } else {
            int color = ContextCompat.getColor(getContext(), R.color.black60Opacity);
            for (int i=0;i<binding.radioGroup.getChildCount();i++){
                RadioButton radioButton = (RadioButton) binding.radioGroup.getChildAt(i);
                radioButton.setTextColor(color);
            }
        }
    }
}