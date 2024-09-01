package com.example.nutrition.Fragments;

import android.os.Bundle;

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
                RadioButton button = group.findViewById(checkedId);
                String tag = button.getTag().toString().trim();

                if (tag.equals(question2.getCorrectOption())){
                    binding.textViewStatusFragmentQuiz.setText("Correct!");
                    binding.textViewStatusFragmentQuiz.setVisibility(View.VISIBLE);
                    binding.imageViewStatusFragmentQuiz.setImageResource(R.drawable.ic_correct);
                    binding.imageViewEmoji.setImageResource(R.drawable.ic_smiling);
                } else {
                    binding.textViewStatusFragmentQuiz.setText("Incorrect!");
                    binding.textViewStatusFragmentQuiz.setVisibility(View.VISIBLE);
                    binding.imageViewStatusFragmentQuiz.setImageResource(R.drawable.ic_wrong);
                    binding.imageViewEmoji.setImageResource(R.drawable.ic_sad);
                }
            }
        });
    }

    private void additionalThemeChanges(){

    }
}