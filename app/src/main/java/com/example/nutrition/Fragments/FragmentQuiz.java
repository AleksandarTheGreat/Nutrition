package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
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
    private Question question;
    private MediaPlayer mediaPlayer;

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
        binding.textViewQuestion.setText(question.getQuestion());
        binding.radioA.setText(question.getOptionA());
        binding.radioB.setText(question.getOptionB());
        binding.radioC.setText(question.getOptionC());
        binding.radioD.setText(question.getOptionD());
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
                    binding.imageViewEmoji.setImageResource(R.drawable.ic_smiling);
                    playWinSounds();
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

    private void playWinSounds(){
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.win_bell);
        mediaPlayer.start();
    }
}