package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.PredefinedPlan;
import com.example.nutrition.R;
import com.example.nutrition.Repos.ParentRepo;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentPredefinedMacrosBinding;
import com.google.android.material.card.MaterialCardView;


public class FragmentPredefinedMacros extends Fragment implements IEssentials {

    private AppCompatActivity appCompatActivity;
    private FragmentPredefinedMacrosBinding binding;
    private MaterialCardView [] materialCardViewsPlans;
    private String gender;
    private Toaster toaster;

    private PredefinedPlan [] predefinedPlans;

    public FragmentPredefinedMacros() {
        // Required empty public constructor
    }

    public FragmentPredefinedMacros(String gender, AppCompatActivity appCompatActivity) {
        this.gender = gender;
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPredefinedMacrosBinding.bind(inflater.inflate(R.layout.fragment_predefined_macros, container, false));

        instantiateObjects();
        additionalThemeChanges();
        addEventListeners();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        toaster = new Toaster(getContext());

        materialCardViewsPlans = new MaterialCardView[]{binding.matCardPlan1, binding.matCardPlan2, binding.matCardPlan3, binding.matCardPlan4, binding.matCardPlan5,
        binding.matCardPlan6, binding.matCardPlan7, binding.matCardPlan8, binding.matCardPlan9, binding.matCardPlan10, binding.matCardPlan11, binding.matCardPlan12};

        predefinedPlans = new PredefinedPlan[12];

        if (gender.equals("male")){
            Log.d("Tag", "Male Plans");
            setUpMalePlans();
        } else {
            Log.d("Tag", "Female Plans ");
            setUpFemalePlans();
        }

        applyPlansToCards();
    }

    @Override
    public void addEventListeners() {
        for (MaterialCardView materialCardView: materialCardViewsPlans){
            materialCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialCardView.setChecked(!materialCardView.isChecked());
                    for (MaterialCardView materialCardView1: materialCardViewsPlans){
                        if (materialCardView != materialCardView1)
                            materialCardView1.setChecked(false);
                    }
                }
            });
        }
    }

    public void additionalThemeChanges(){
        if (ThemeUtils.isNightModeActive(appCompatActivity)){
            int colorWhite = ContextCompat.getColor(getContext(), R.color.white);

            binding.textViewTitlePlan1.setTextColor(colorWhite);
            binding.textViewTitlePlan2.setTextColor(colorWhite);
            binding.textViewTitlePlan3.setTextColor(colorWhite);
            binding.textViewTitlePlan4.setTextColor(colorWhite);
            binding.textViewTitlePlan5.setTextColor(colorWhite);
            binding.textViewTitlePlan6.setTextColor(colorWhite);
            binding.textViewTitlePlan7.setTextColor(colorWhite);
            binding.textViewTitlePlan8.setTextColor(colorWhite);
            binding.textViewTitlePlan9.setTextColor(colorWhite);
            binding.textViewTitlePlan10.setTextColor(colorWhite);
            binding.textViewTitlePlan11.setTextColor(colorWhite);
            binding.textViewTitlePlan12.setTextColor(colorWhite);
        } else {
            int colorBlack = ContextCompat.getColor(getContext(), R.color.black);

            binding.textViewTitlePlan1.setTextColor(colorBlack);
            binding.textViewTitlePlan2.setTextColor(colorBlack);
            binding.textViewTitlePlan3.setTextColor(colorBlack);
            binding.textViewTitlePlan4.setTextColor(colorBlack);
            binding.textViewTitlePlan5.setTextColor(colorBlack);
            binding.textViewTitlePlan6.setTextColor(colorBlack);
            binding.textViewTitlePlan7.setTextColor(colorBlack);
            binding.textViewTitlePlan8.setTextColor(colorBlack);
            binding.textViewTitlePlan9.setTextColor(colorBlack);
            binding.textViewTitlePlan10.setTextColor(colorBlack);
            binding.textViewTitlePlan11.setTextColor(colorBlack);
            binding.textViewTitlePlan12.setTextColor(colorBlack);
        }
    }

    public void setUpFemalePlans(){
        predefinedPlans[0] = new PredefinedPlan("Lose Fat", "Not Active", 60, 1400, 130, 25);
        predefinedPlans[1] = new PredefinedPlan("Maintain", "Not Active", 55, 1600, 180, 25);
        predefinedPlans[2] = new PredefinedPlan("Gain Muscle", "Not Active", 75, 1800, 200, 30);

        predefinedPlans[3] = new PredefinedPlan("Lose Fat", "Lightly Active", 70, 1600, 160, 25);
        predefinedPlans[4] = new PredefinedPlan("Maintain","Lightly Active", 75, 1800, 200, 30);
        predefinedPlans[5] = new PredefinedPlan("Gain Muscle", "Lightly Active", 85, 2000, 230, 35);

        predefinedPlans[6] = new PredefinedPlan("Lose Fat", "Active", 80, 1800, 180, 30);
        predefinedPlans[7] = new PredefinedPlan("Maintain", "Active", 90, 2000, 230, 35);
        predefinedPlans[8] = new PredefinedPlan("Gain Muscle", "Active", 100, 2200, 260, 40);

        predefinedPlans[9] = new PredefinedPlan("Lose Fat", "Athlete", 90, 2000, 200, 35);
        predefinedPlans[10] = new PredefinedPlan("Maintain", "Athlete", 100, 2300, 260, 40);
        predefinedPlans[11] = new PredefinedPlan("Gain Muscle", "Athlete", 120, 2500, 300, 45);
    }

    public void setUpMalePlans(){
        predefinedPlans[0] = new PredefinedPlan("Lose Fat", "Not Active", 70, 1800, 220, 30);
        predefinedPlans[1] = new PredefinedPlan("Maintain", "Not Active", 65, 2000, 180, 35);
        predefinedPlans[2] = new PredefinedPlan("Gain Muscle", "Not Active", 85, 2200, 240, 35);

        predefinedPlans[3] = new PredefinedPlan("Lose Fat", "Lightly Active", 80, 2000, 180, 30);
        predefinedPlans[4] = new PredefinedPlan("Maintain","Lightly Active", 80, 2300, 250, 35);
        predefinedPlans[5] = new PredefinedPlan("Gain Muscle", "Lightly Active", 100, 2500, 280, 40);

        predefinedPlans[6] = new PredefinedPlan("Lose Fat", "Active", 90, 2200, 200, 35);
        predefinedPlans[7] = new PredefinedPlan("Maintain", "Active", 100, 2600, 300, 40);
        predefinedPlans[8] = new PredefinedPlan("Gain Muscle", "Active", 120, 2800, 330, 45);

        predefinedPlans[9] = new PredefinedPlan("Lose Fat", "Athlete", 100, 2400, 220, 45);
        predefinedPlans[10] = new PredefinedPlan("Maintain", "Athlete", 130, 2900, 350, 45);
        predefinedPlans[11] = new PredefinedPlan("Gain Muscle", "Athlete", 150, 3300, 380, 50);
    }

    @SuppressLint("SetTextI18n")
    public void applyPlansToCards(){
        for (int i=0;i<materialCardViewsPlans.length;i++){
            MaterialCardView materialCardView = materialCardViewsPlans[i];
            LinearLayout linearLayout = (LinearLayout) materialCardView.getChildAt(0);
            PredefinedPlan predefinedPlan = predefinedPlans[i];

            TextView textViewTitle = (TextView) linearLayout.getChildAt(1);
            TextView textViewStatus = (TextView) linearLayout.getChildAt(2);

            TextView textViewProteins = (TextView) linearLayout.getChildAt(3);
            TextView textViewCalories = (TextView) linearLayout.getChildAt(4);
            TextView textViewCarbohydrates = (TextView) linearLayout.getChildAt(5);
            TextView textViewSugars = (TextView) linearLayout.getChildAt(6);


            textViewTitle.setText(predefinedPlan.getTitle());
            textViewStatus.setText(predefinedPlan.getStatus());

            textViewProteins.setText("Proteins - " + predefinedPlan.getProteins());
            textViewCalories.setText("Calories - " + predefinedPlan.getCalories());
            textViewCarbohydrates.setText("Carbohydrates - " + predefinedPlan.getCarbohydrates());
            textViewSugars.setText("Sugars - " + predefinedPlan.getSugars());

            textViewProteins.setTextColor(ContextCompat.getColor(getContext(), R.color.colorProtein));
            textViewCalories.setTextColor(ContextCompat.getColor(getContext(), R.color.colorCalorie));
            textViewCarbohydrates.setTextColor(ContextCompat.getColor(getContext(), R.color.colorCarbohydrate));
            textViewSugars.setTextColor(ContextCompat.getColor(getContext(), R.color.colorSugar));
        }
    }
}






