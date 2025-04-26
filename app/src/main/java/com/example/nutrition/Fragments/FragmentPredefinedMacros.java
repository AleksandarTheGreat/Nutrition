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
import com.example.nutrition.Model.CustomMacros;
import com.example.nutrition.Model.PredefinedPlan;
import com.example.nutrition.R;
import com.example.nutrition.Repos.ParentRepo;
import com.example.nutrition.SharedPrefs.SharedPrefCustomMacros;
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
            binding.imageViewLogoFragmentPredefinedMacros.setImageResource(R.drawable.ic_male);
            Log.d("Tag", "Male Plans");
            setUpMalePlans();
        } else {
            binding.imageViewLogoFragmentPredefinedMacros.setImageResource(R.drawable.ic_female);
            Log.d("Tag", "Female Plans ");
            setUpFemalePlans();
        }

        applyPlansToCards();
        readMacrosFromSharedPrefAndCheckTheCard();
    }

    @Override
    public void addEventListeners() {
        for (int i=0;i<materialCardViewsPlans.length;i++){
            MaterialCardView materialCardView = materialCardViewsPlans[i];
            int finalI = i;
            materialCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialCardView.setChecked(!materialCardView.isChecked());
                    if (materialCardView.isChecked()){
                        toaster.text("Plan saved");

                        PredefinedPlan predefinedPlan = predefinedPlans[finalI];
                        int proteins = predefinedPlan.getProteins();
                        int calories = predefinedPlan.getCalories();
                        int carbohydrates = predefinedPlan.getCarbohydrates();
                        int sugars = predefinedPlan.getSugars();

                        SharedPrefCustomMacros.writeToSharedPref(getContext(), proteins, calories, carbohydrates, sugars);
                        materialCardView.setScaleY(1.02f);
                        materialCardView.setScaleX(1.02f);
                    } else {
                        toaster.text("Plan revoked");
                        SharedPrefCustomMacros.writeToSharedPref(getContext(), -1, -1, -1 ,-1);
                        materialCardView.setScaleY(1.0f);
                        materialCardView.setScaleX(1.0f);
                    }

                    // This is to not have multiple cards selected
                    for (MaterialCardView materialCardView1: materialCardViewsPlans){
                        if (materialCardView != materialCardView1){
                            materialCardView1.setChecked(false);
                            materialCardView1.setScaleY(1.0f);
                            materialCardView1.setScaleX(1.0f);
                        }
                    }

                    // Maybe exit, go to the main fragment idk ?
                }
            });
        }
    }

    public void additionalThemeChanges(){
        if (ThemeUtils.isNightModeActive(appCompatActivity)){
            int colorWhite = ContextCompat.getColor(getContext(), R.color.white);

            binding.textViewPickAPlanFragmentPredefinedMacros.setTextColor(colorWhite);
            binding.textViewNotActive.setTextColor(colorWhite);
            binding.textViewLightlyActive.setTextColor(colorWhite);
            binding.textViewActive.setTextColor(colorWhite);
            binding.textViewAthlete.setTextColor(colorWhite);

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

            binding.textViewPickAPlanFragmentPredefinedMacros.setTextColor(colorBlack);
            binding.textViewNotActive.setTextColor(colorBlack);
            binding.textViewLightlyActive.setTextColor(colorBlack);
            binding.textViewActive.setTextColor(colorBlack);
            binding.textViewAthlete.setTextColor(colorBlack);

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

        predefinedPlans[9] = new PredefinedPlan("Lose Fat", "Athlete", 130, 2700, 240, 40);
        predefinedPlans[10] = new PredefinedPlan("Maintain", "Athlete", 150, 3200, 360, 45);
        predefinedPlans[11] = new PredefinedPlan("Gain Muscle", "Athlete", 170, 3600, 450, 50);
    }

    @SuppressLint("SetTextI18n")
    public void applyPlansToCards(){
        for (int i=0;i<materialCardViewsPlans.length;i++){
            MaterialCardView materialCardView = materialCardViewsPlans[i];
            LinearLayout linearLayout = (LinearLayout) materialCardView.getChildAt(0);
            PredefinedPlan predefinedPlan = predefinedPlans[i];

            TextView textViewTitle = (TextView) linearLayout.getChildAt(0);

            TextView textViewProteins = (TextView) linearLayout.getChildAt(2);
            TextView textViewCalories = (TextView) linearLayout.getChildAt(4);
            TextView textViewCarbohydrates = (TextView) linearLayout.getChildAt(6);
            TextView textViewSugars = (TextView) linearLayout.getChildAt(8);

            textViewTitle.setText(predefinedPlan.getTitle());

            textViewProteins.setText(String.valueOf(predefinedPlan.getProteins()));
            textViewCalories.setText(String.valueOf(predefinedPlan.getCalories()));
            textViewCarbohydrates.setText(String.valueOf(predefinedPlan.getCarbohydrates()));
            textViewSugars.setText(String.valueOf(predefinedPlan.getSugars()));

            textViewProteins.setTextColor(ContextCompat.getColor(getContext(), R.color.colorProtein));
            textViewCalories.setTextColor(ContextCompat.getColor(getContext(), R.color.colorCalorie));
            textViewCarbohydrates.setTextColor(ContextCompat.getColor(getContext(), R.color.colorCarbohydrate));
            textViewSugars.setTextColor(ContextCompat.getColor(getContext(), R.color.colorSugar));
        }
    }

    public void readMacrosFromSharedPrefAndCheckTheCard(){
        CustomMacros customMacros = SharedPrefCustomMacros.readFromSharedPref(getContext());

        for (int i=0;i<predefinedPlans.length;i++){
            PredefinedPlan predefinedPlan = predefinedPlans[i];

            if (predefinedPlan.getProteins() == customMacros.getProteins() && predefinedPlan.getCalories() == customMacros.getCalories()
            && predefinedPlan.getCarbohydrates() == customMacros.getCarbs() && predefinedPlan.getSugars() == customMacros.getSugars()){
                materialCardViewsPlans[i].setChecked(true);
                materialCardViewsPlans[i].setScaleX(1.02f);
                materialCardViewsPlans[i].setScaleY(1.02f);
            }
        }

    }
}






