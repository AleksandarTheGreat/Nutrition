package com.example.nutrition.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Activities.IntroductionActivity;
import com.example.nutrition.Activities.MainActivity;
import com.example.nutrition.Adapters.MyIntroFragAdapter;
import com.example.nutrition.Helper.HelperMain;
import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentEducationalBinding;

public class FragmentEducational extends Fragment implements IEssentials {

    private AppCompatActivity appCompatActivity;
    private FragmentEducationalBinding binding;
    private boolean isNightModeOn;
    private HelperMain helperMain;

    public FragmentEducational() {
        // Required empty public constructor
    }

    public FragmentEducational(AppCompatActivity appCompatActivity){
        this.appCompatActivity = appCompatActivity;
        this.isNightModeOn = ThemeUtils.isNightModeActive(appCompatActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEducationalBinding.bind(inflater.inflate(R.layout.fragment_educational, container, false));

        instantiateObjects();
        additionalThemeChanges();
        addEventListeners();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        helperMain = new HelperMain(getContext());
    }

    @Override
    public void addEventListeners() {
        binding.matCardDefinitions.setOnClickListener(view -> {
            helperMain.goToActivity(getContext(), IntroductionActivity.class, MyIntroFragAdapter.TYPE_1);
        });

        binding.matCardMythFact.setOnClickListener(view -> {
            helperMain.goToActivity(getContext(), IntroductionActivity.class, MyIntroFragAdapter.TYPE_2);
        });

        binding.matCardQuiz.setOnClickListener(view -> {
            helperMain.goToActivity(getContext(), IntroductionActivity.class, MyIntroFragAdapter.TYPE_3);
        });

        binding.matCardSearchExamples.setOnClickListener(view -> {
            helperMain.goToActivity(getContext(), IntroductionActivity.class, MyIntroFragAdapter.TYPE_4);
        });

        binding.matCardMotivationalQuotes.setOnClickListener(view -> {
            helperMain.goToActivity(getContext(), IntroductionActivity.class, MyIntroFragAdapter.TYPE_5);
        });
    }

    public void additionalThemeChanges(){
        if (isNightModeOn){
            int colorBright = ContextCompat.getColor(getContext(), R.color.white);

            binding.imageViewIconSection0.setImageResource(R.drawable.ic_educate_light);

            binding.textView1.setTextColor(colorBright);
            binding.textView2.setTextColor(colorBright);
            binding.textView3.setTextColor(colorBright);
            binding.textView4.setTextColor(colorBright);
            binding.textView5.setTextColor(colorBright);
            binding.textViewSubTitleSection0.setTextColor(colorBright);

        } else {
            int colorDark = ContextCompat.getColor(getContext(), R.color.black);

            binding.imageViewIconSection0.setImageResource(R.drawable.ic_educate_dark);

            binding.textView1.setTextColor(colorDark);
            binding.textView2.setTextColor(colorDark);
            binding.textView3.setTextColor(colorDark);
            binding.textView4.setTextColor(colorDark);
            binding.textView5.setTextColor(colorDark);
            binding.textViewSubTitleSection0.setTextColor(colorDark);
        }
    }
}