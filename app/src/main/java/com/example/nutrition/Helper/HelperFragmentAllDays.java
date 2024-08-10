package com.example.nutrition.Helper;

import android.content.Context;
import android.os.Build;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.nutrition.Adapters.AllDaysAdapter;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentAllDaysBinding;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.material.chip.Chip;

import java.time.LocalDate;
import java.util.ArrayList;

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
                    toaster.text("Cannot create 2 days");
                    return false;
                }
            }
        }
        return true;
    }

    public void  setUpAnyChart(String macronutrient, FragmentAllDaysBinding binding, AllDaysAdapter allDaysAdapter) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        switch (macronutrient) {
            case "Carbohydrates": {
                for (Day day : allDaysAdapter.getDaysList()) {
                    barEntries.add(new BarEntry(day.getId(), day.totalCarbohydrates()));
                }
                break;
            }
            case "Calories": {
                for (Day day : allDaysAdapter.getDaysList()) {
                    barEntries.add(new BarEntry(day.getId(), day.totalCalories()));
                }
                break;
            }
            case "Sugars": {
                for (Day day : allDaysAdapter.getDaysList()) {
                    barEntries.add(new BarEntry(day.getId(), day.totalSugar()));
                }
                break;
            }
            case "Proteins":
            default: {
                for (Day day : allDaysAdapter.getDaysList()) {
                    barEntries.add(new BarEntry(day.getId(), day.totalProteins()));
                }
                break;

            }
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Days");
        barDataSet.setValueTextSize(14f);

        // Change the colors for the axis - es
        // Left, Right, and Horizontal (X - axis)

        if (ThemeUtils.isNightModeActive(appCompatActivity)) {
            int color = ContextCompat.getColor(context, R.color.white60Opacity);

            barDataSet.setValueTextColor(color);
            barDataSet.setColor(color);
            binding.anyChartFragmentAllDays.getDescription().setTextColor(color);
            // X axis
            binding.anyChartFragmentAllDays.getXAxis().setGridColor(color);
            binding.anyChartFragmentAllDays.getXAxis().setTextColor(color);
            binding.anyChartFragmentAllDays.getXAxis().setAxisLineColor(color);

            // Left and Right axes
            binding.anyChartFragmentAllDays.getAxisLeft().setGridColor(color);
            binding.anyChartFragmentAllDays.getAxisLeft().setTextColor(color);
            binding.anyChartFragmentAllDays.getAxisLeft().setAxisLineColor(color);
            binding.anyChartFragmentAllDays.getAxisRight().setGridColor(color);
            binding.anyChartFragmentAllDays.getAxisRight().setTextColor(color);
            binding.anyChartFragmentAllDays.getAxisRight().setAxisLineColor(color);

        } else {
            int color = ContextCompat.getColor(context, R.color.black60Opacity);

            barDataSet.setValueTextColor(color);
            barDataSet.setColor(color);
            binding.anyChartFragmentAllDays.getDescription().setTextColor(color);
            // X axis
            binding.anyChartFragmentAllDays.getXAxis().setGridColor(color);
            binding.anyChartFragmentAllDays.getXAxis().setTextColor(color);
            binding.anyChartFragmentAllDays.getXAxis().setAxisLineColor(color);

            // Left and Right axes
            binding.anyChartFragmentAllDays.getAxisLeft().setGridColor(color);
            binding.anyChartFragmentAllDays.getAxisLeft().setTextColor(color);
            binding.anyChartFragmentAllDays.getAxisLeft().setAxisLineColor(color);
            binding.anyChartFragmentAllDays.getAxisRight().setGridColor(color);
            binding.anyChartFragmentAllDays.getAxisRight().setTextColor(color);
            binding.anyChartFragmentAllDays.getAxisRight().setAxisLineColor(color);
        }

        BarData barData = new BarData(barDataSet);

        binding.anyChartFragmentAllDays.setFitBars(true);
        binding.anyChartFragmentAllDays.setData(barData);
        binding.anyChartFragmentAllDays.getDescription().setText("Calories test");
        binding.anyChartFragmentAllDays.animateY(700);
    }

    private void changeChipsColors(FragmentAllDaysBinding binding){
        if (ThemeUtils.isNightModeActive(appCompatActivity)){
            for (int i=0;i<binding.chipGroupGraphFragmentAllDays.getChildCount();i++){
                Chip chip = (Chip) binding.chipGroupGraphFragmentAllDays.getChildAt(i);
                if (chip.isChecked()){

                }
            }
        }
    }
}







