package com.example.nutrition.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.nutrition.Adapters.AllDaysAdapter;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentAllDaysBinding;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.chip.Chip;
import com.google.android.material.color.MaterialColors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HelperFragmentAllDays {
    private Context context;
    private AppCompatActivity appCompatActivity;
    public HelperFragmentAllDays(Context context, AppCompatActivity appCompatActivity){
        this.context = context;
        this.appCompatActivity = appCompatActivity;
    }

    public boolean isANewDayValidToAdd(AllDaysAdapter allDaysAdapter, Toaster toaster) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            for (Day day : allDaysAdapter.getDaysList()) {
                if (day.getCreatedAt().equals(LocalDate.now())) {
                    toaster.text("Cannot create 2 days, today");
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressLint("DefaultLocale")
    public void createCustomChart(String macronutrient, FragmentAllDaysBinding binding, AllDaysAdapter allDaysAdapter){
        binding.linearLayoutCustomGraph.removeAllViews();

        int max = findMaxProgressOfACertainMacronutrient(macronutrient, allDaysAdapter.getDaysList());
        for (Day day: allDaysAdapter.getDaysList()){
            float progress = 0.0f;
            String dayShort = day.calculateDayNameOfDate();

            switch (macronutrient){
                case "Proteins":{
                    progress = day.totalProteins();
                    break;
                }
                case "Carbohydrates":{
                    progress = day.totalCarbohydrates();
                    break;
                }
                case "Calories":{
                    progress = day.totalCalories();
                    break;
                }
                case "Sugars":{
                    progress = day.totalSugar();
                    break;
                }
            }

            TextView textViewProgress = new TextView(context);
            textViewProgress.setText(String.format("%.0f", progress));
            textViewProgress.setId(View.generateViewId());
            textViewProgress.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            TextView textViewDay = new TextView(context);
            textViewDay.setText(dayShort);
            textViewDay.setId(View.generateViewId());
            textViewDay.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
            progressBar.setProgressDrawable(ContextCompat.getDrawable(context, R.drawable.list_progress_bar_vertical));
            progressBar.setMax(max);
            progressBar.setProgress((int) progress);

            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(Gravity.CENTER);




            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0);
            layoutParams1.setMargins(0,0,0,12);
            textViewProgress.setLayoutParams(layoutParams1);

            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 4);
            layoutParams2.setMargins(0,0,0,8);
            progressBar.setLayoutParams(layoutParams2);

            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            layoutParams3.setMargins(0,0,0,12);
            textViewDay.setLayoutParams(layoutParams3);

            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams4.setMargins(16, 8, 16, 8);
            linearLayout.setLayoutParams(layoutParams4);

            linearLayout.addView(textViewProgress);
            linearLayout.addView(progressBar);
            linearLayout.addView(textViewDay);
            binding.linearLayoutCustomGraph.addView(linearLayout);
        }
    }

    private int findMaxProgressOfACertainMacronutrient(String macronutrient, List<Day> dayList){
        float max = 0;
        float progress = 0.0f;
        for (Day day: dayList){
            switch (macronutrient){
                case "Proteins":{
                    progress = day.totalProteins();
                    if (progress > max) max = progress;
                    break;
                }
                case "Carbohydrates":{
                    progress = day.totalCarbohydrates();
                    if (progress > max) max = progress;
                    break;
                }
                case "Calories":{
                    progress = day.totalCalories();
                    if (progress > max) max = progress;
                    break;
                }
                case "Sugars":{
                    progress = day.totalSugar();
                    if (progress > max) max = progress;
                    break;
                }
            }
        }

        return (int) max;
    }

    public void checkAndSelectCorrectChip(String macronutrient, FragmentAllDaysBinding binding){
        for (int i=0;i<binding.chipGroupGraphFragmentAllDays.getChildCount();i++){
            Chip chip = (Chip) binding.chipGroupGraphFragmentAllDays.getChildAt(i);
            if (chip.getTag().toString().trim().equals(macronutrient)){
                chip.setChecked(true);
                break;
            }
        }
    }
}







