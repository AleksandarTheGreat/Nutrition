package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Question;
import com.example.nutrition.R;
import com.example.nutrition.databinding.FragmentQuizBinding;

public class FragmentQuiz extends Fragment implements IEssentials {

    private FragmentQuizBinding binding;
    private boolean isNightModeActive;
    private Toaster toaster;
    private Question question;

    public FragmentQuiz() {
        // Required empty public constructor
    }

    public FragmentQuiz(boolean isNightModeActive, Question question){
        this.question = question;
        this.isNightModeActive = isNightModeActive;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQuizBinding.bind(inflater.inflate(R.layout.fragment_quiz, container, false));

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
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                String tag = radioButton.getTag().toString().trim();

                if (tag.equals(question.getCorrectOption())){
                    binding.textViewStatusFragmentQuiz.setText("Correct!");
                    binding.textViewStatusFragmentQuiz.setVisibility(View.VISIBLE);
                    binding.imageViewStatusFragmentQuiz.setImageResource(R.drawable.ic_correct);
                } else {
                    binding.textViewStatusFragmentQuiz.setText("Incorrect!");
                    binding.textViewStatusFragmentQuiz.setVisibility(View.VISIBLE);
                    binding.imageViewStatusFragmentQuiz.setImageResource(R.drawable.ic_wrong);
                }
            }
        });
    }

    public void additionalThemeChanges(){
        if (isNightModeActive){
            binding.imageViewQuestionFragmentQuiz.setImageResource(R.drawable.ic_question_light);
        } else {
            binding.imageViewQuestionFragmentQuiz.setImageResource(R.drawable.ic_question_dark);
        }
    }
}