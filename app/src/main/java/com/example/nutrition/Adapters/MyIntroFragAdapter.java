package com.example.nutrition.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.nutrition.Fragments.FragmentIntroduction;
import com.example.nutrition.Model.Macronutrient;
import com.example.nutrition.R;

import java.util.ArrayList;
import java.util.List;

public class MyIntroFragAdapter extends FragmentStateAdapter {

    private Context context;
    private List<Fragment> fragmentList;
    private AppCompatActivity appCompatActivity;

    public MyIntroFragAdapter(Context context, FragmentActivity fragmentActivity, AppCompatActivity appCompatActivity) {
        super(fragmentActivity);

        this.context = context;
        this.appCompatActivity = appCompatActivity;
        setUpFragmentList();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    private void setUpFragmentList(){
        this.fragmentList = new ArrayList<>();

        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Protein", "Proteins are large biomolecules and macromolecules that comprise one or more long chains of amino acid residues. In short terms they are important for muscle growth", R.drawable.ic_steak), appCompatActivity));
        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Carbohydrate", "Carbohydrates, or carbs, are sugar molecules. Along with proteins and fats, carbohydrates are one of three main nutrients found in foods and drinks. Your body breaks down carbohydrates into glucose. Glucose, or blood sugar, is the main source of energy for your body's cells, tissues, and organs.", R.drawable.ic_wheat), appCompatActivity));
        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Calorie", "A calorie is a unit of energy. In nutrition, calories refer to the energy people get from the food and drink they consume, and the energy they use in physical activity. Calories are listed in the nutritional information on all food packaging. Many weight loss programs center around reducing the intake of calories.", R.drawable.ic_olive_oil), appCompatActivity));
        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Sugar", "Sugars are the smallest unit of carbohydrates and include both naturally occurring sugars (like those found in fruits and milk) and added sugars (like table sugar or high-fructose corn syrup).", R.drawable.ic_sugar), appCompatActivity));
    }
}
