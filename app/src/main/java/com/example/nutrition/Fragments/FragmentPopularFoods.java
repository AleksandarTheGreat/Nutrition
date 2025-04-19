package com.example.nutrition.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Activities.MainActivity;
import com.example.nutrition.Helper.HelperMain;
import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentPopularFoodsBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.color.MaterialColors;


public class FragmentPopularFoods extends Fragment implements IEssentials {

    private FragmentPopularFoodsBinding binding;
    private AppCompatActivity appCompatActivity;
    private boolean isNightModeOn;
    private MaterialCardView[] materialCardViews;
    private HelperMain helperMain;

    public FragmentPopularFoods() {
        // Required empty public constructor
    }

    public FragmentPopularFoods(AppCompatActivity appCompatActivity){
        this.appCompatActivity = appCompatActivity;
        this.isNightModeOn = ThemeUtils.isNightModeActive(appCompatActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPopularFoodsBinding.bind(inflater.inflate(R.layout.fragment_popular_foods, container, false));

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
        materialCardViews = new MaterialCardView[]{binding.matCard1, binding.matCard2, binding.matCard3,
                binding.matCard4, binding.matCard5, binding.matCard6, binding.matCard7};
        helperMain.setUpCardEventListeners(materialCardViews);
    }

    public void additionalThemeChanges(){
        if (isNightModeOn){

            int colorWhite = ContextCompat.getColor(getContext(), R.color.white);
            int colorBackground = MaterialColors.getColor(getContext(), android.R.attr.colorBackground, Color.BLACK);

            binding.imageViewIconSection1.setImageResource(R.drawable.ic_pyramid_white);
            binding.textViewSubTitleSection1.setTextColor(colorWhite);

            binding.textViewGrains.setTextColor(colorWhite);
            binding.textViewVegetables.setTextColor(colorWhite);
            binding.textViewFruit.setTextColor(colorWhite);
            binding.textViewMeatAndProteins.setTextColor(colorWhite);
            binding.textViewDairy.setTextColor(colorWhite);
            binding.textViewSweets.setTextColor(colorWhite);
            binding.textViewFastFood.setTextColor(colorWhite);

            binding.textViewGrainsDescription.setTextColor(colorWhite);
            binding.textViewVegetablesDescription.setTextColor(colorWhite);
            binding.textViewFruitDescription.setTextColor(colorWhite);
            binding.textViewMeatAndProteinsDescription.setTextColor(colorWhite);
            binding.textViewDairyDescription.setTextColor(colorWhite);
            binding.textViewSweetsDescription.setTextColor(colorWhite);
            binding.textViewFastFoodDescription.setTextColor(colorWhite);


            binding.textViewGrains.setBackgroundColor(colorBackground);
            binding.textViewVegetables.setBackgroundColor(colorBackground);
            binding.textViewFruit.setBackgroundColor(colorBackground);
            binding.textViewMeatAndProteins.setBackgroundColor(colorBackground);
            binding.textViewDairy.setBackgroundColor(colorBackground);
            binding.textViewSweets.setBackgroundColor(colorBackground);
            binding.textViewFastFood.setBackgroundColor(colorBackground);

            binding.textViewGrainsDescription.setBackgroundColor(colorBackground);
            binding.textViewVegetablesDescription.setBackgroundColor(colorBackground);
            binding.textViewFruitDescription.setBackgroundColor(colorBackground);
            binding.textViewMeatAndProteinsDescription.setBackgroundColor(colorBackground);
            binding.textViewDairyDescription.setBackgroundColor(colorBackground);
            binding.textViewSweetsDescription.setBackgroundColor(colorBackground);
            binding.textViewFastFoodDescription.setBackgroundColor(colorBackground);

        } else {

            int colorBlack = ContextCompat.getColor(getContext(), R.color.black);
            int colorBackground = ContextCompat.getColor(getContext(), R.color.white);

            binding.imageViewIconSection1.setImageResource(R.drawable.ic_pyramid_black);
            binding.textViewSubTitleSection1.setTextColor(colorBlack);

            binding.textViewGrains.setTextColor(colorBlack);
            binding.textViewVegetables.setTextColor(colorBlack);
            binding.textViewFruit.setTextColor(colorBlack);
            binding.textViewMeatAndProteins.setTextColor(colorBlack);
            binding.textViewDairy.setTextColor(colorBlack);
            binding.textViewSweets.setTextColor(colorBlack);
            binding.textViewFastFood.setTextColor(colorBlack);

            binding.textViewGrainsDescription.setTextColor(colorBlack);
            binding.textViewVegetablesDescription.setTextColor(colorBlack);
            binding.textViewFruitDescription.setTextColor(colorBlack);
            binding.textViewMeatAndProteinsDescription.setTextColor(colorBlack);
            binding.textViewDairyDescription.setTextColor(colorBlack);
            binding.textViewSweetsDescription.setTextColor(colorBlack);
            binding.textViewFastFoodDescription.setTextColor(colorBlack);


            binding.textViewGrains.setBackgroundColor(colorBackground);
            binding.textViewVegetables.setBackgroundColor(colorBackground);
            binding.textViewFruit.setBackgroundColor(colorBackground);
            binding.textViewMeatAndProteins.setBackgroundColor(colorBackground);
            binding.textViewDairy.setBackgroundColor(colorBackground);
            binding.textViewSweets.setBackgroundColor(colorBackground);
            binding.textViewFastFood.setBackgroundColor(colorBackground);

            binding.textViewGrainsDescription.setBackgroundColor(colorBackground);
            binding.textViewVegetablesDescription.setBackgroundColor(colorBackground);
            binding.textViewFruitDescription.setBackgroundColor(colorBackground);
            binding.textViewMeatAndProteinsDescription.setBackgroundColor(colorBackground);
            binding.textViewDairyDescription.setBackgroundColor(colorBackground);
            binding.textViewSweetsDescription.setBackgroundColor(colorBackground);
            binding.textViewFastFoodDescription.setBackgroundColor(colorBackground);
        }
    }
}