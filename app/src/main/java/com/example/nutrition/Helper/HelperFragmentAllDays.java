package com.example.nutrition.Helper;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

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

    public void setUpAnyChart(String macronutrient, FragmentAllDaysBinding binding, AllDaysAdapter allDaysAdapter) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        int index = 1;
        List<String> xValuesDays = new ArrayList<>();

        // Set the description of the graph
        switch (macronutrient) {
            case "Carbohydrates":
                binding.anyChartFragmentAllDays.getDescription().setText("Carbohydrates");
                break;
            case "Calories":
                binding.anyChartFragmentAllDays.getDescription().setText("Calories");
                break;
            case "Sugars":
                binding.anyChartFragmentAllDays.getDescription().setText("Sugars");
                break;
            case "Proteins":
            default:
                binding.anyChartFragmentAllDays.getDescription().setText("Proteins");
                break;
        }

        for (Day day : allDaysAdapter.getDaysList()) {
            switch (macronutrient) {
                case "Carbohydrates":
                    barEntries.add(new BarEntry(index, day.totalCarbohydrates()));
                    break;
                case "Calories":
                    barEntries.add(new BarEntry(index, day.totalCalories()));
                    break;
                case "Sugars":
                    barEntries.add(new BarEntry(index, day.totalSugar()));
                    break;
                case "Proteins":
                default:
                    barEntries.add(new BarEntry(index, day.totalProteins()));
                    break;
            }
            xValuesDays.add(day.getTitle() + " " + index);
            // This index is just a number incrementing each time
            // and this represents the position of the day in the graph
            index++;  // Increment index for each day
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Days");
        barDataSet.setValueTextSize(14f);

        XAxis xAxis = binding.anyChartFragmentAllDays.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10);

        // Change the colors for the axis - es
        // Left, Right, and Horizontal (X - axis)

        if (ThemeUtils.isNightModeActive(appCompatActivity)) {
            int color = ContextCompat.getColor(context, R.color.white);
            int primaryColor = MaterialColors.getColor(context, com.google.android.material.R.attr.colorPrimary, Color.BLACK);

            barDataSet.setValueTextColor(color);
            barDataSet.setColor(primaryColor);
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
            int color = ContextCompat.getColor(context, R.color.black);
            int primaryColor = MaterialColors.getColor(context, com.google.android.material.R.attr.colorPrimary, Color.BLACK);

            barDataSet.setValueTextColor(color);
            barDataSet.setColor(primaryColor);
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
        binding.anyChartFragmentAllDays.animateY(700);

        binding.anyChartFragmentAllDays.invalidate();
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







