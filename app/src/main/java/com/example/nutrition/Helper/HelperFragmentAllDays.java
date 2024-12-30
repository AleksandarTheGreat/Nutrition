package com.example.nutrition.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.nutrition.Adapters.AllDaysAdapter;
import com.example.nutrition.Fragments.FragmentADay;
import com.example.nutrition.Fragments.FragmentAllDays;
import com.example.nutrition.Fragments.MyFragmentManager;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.SharedPrefs.SharedPrefMacronutrients;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentAllDaysBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.time.LocalDate;
import java.util.List;

public class HelperFragmentAllDays {
    private Context context;
    private AppCompatActivity appCompatActivity;
    private boolean isNightModeOn;
    public HelperFragmentAllDays(Context context, AppCompatActivity appCompatActivity){
        this.context = context;
        this.appCompatActivity = appCompatActivity;
        this.isNightModeOn = ThemeUtils.isNightModeActive(appCompatActivity);
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
        Handler handler = new Handler(Looper.getMainLooper());
        binding.linearLayoutCustomGraph.removeAllViews();

        new Thread(() -> {
            int max = findMaxProgressOfACertainMacronutrient(macronutrient, allDaysAdapter.getDaysList());
            for (Day day: allDaysAdapter.getDaysList()){
                float progress = 0.0f;
                String dayShort = day.calculateShortDayNameOfDate();

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
                progressBar.setProgressDrawable(findProgressBarDrawable(macronutrient));
                progressBar.setMax(max);
                progressBar.setProgress((int) progress);


                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setGravity(Gravity.CENTER);

                linearLayout.setOnClickListener(view -> {
                    MyFragmentManager.change(appCompatActivity, new FragmentADay(day, appCompatActivity), false);
                    Log.d("Tag", day.calculateShortDayNameOfDate() + " - " + day.getDateIntoStringFormat());
                });

                linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
                        builder.setTitle("Alert")
                                .setIcon(R.drawable.ic_remove)
                                .setMessage("Are you sure you want to delete\n'" + day.calculateLongDayNameOfDate() + " - " + day.getDateIntoStringFormat() + "' ")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @SuppressLint("NotifyDataSetChanged")
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DaysRepo daysRepo = allDaysAdapter.getDaysRepo();

                                        daysRepo.delete(day.getId());
                                        allDaysAdapter.setDaysList(daysRepo.listAllSorted());
                                        allDaysAdapter.notifyDataSetChanged();

                                        FragmentAllDays.checkIfDaysAreEmpty(binding, allDaysAdapter);
                                        countAndSetTotalDays(context, binding, allDaysAdapter);

                                        String macro = SharedPrefMacronutrients.readMacronutrientFromSharedPref(context);
                                        createCustomChart(macro, binding, allDaysAdapter);
                                        checkAndSelectCorrectChip(macro, binding);
                                        calculateAverageMacronutrient(macro, binding, allDaysAdapter.getDaysList());
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setCancelable(true)
                                .show();

                        return true;
                    }
                });


                // Style importante
                setUpTodayYesterdayIndicators(day, textViewDay, textViewProgress);



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

                handler.post(() -> {
                    binding.linearLayoutCustomGraph.addView(linearLayout);
                });
            }
        }).start();
    }

    private Drawable findProgressBarDrawable(String macro){
        switch (macro){
            case "Proteins":
                return ContextCompat.getDrawable(context, R.drawable.progress_bar_protein);
            case "Carbohydrates":
                return ContextCompat.getDrawable(context, R.drawable.progress_bar_carbs);
            case "Calories":
                return ContextCompat.getDrawable(context, R.drawable.progress_bar_calorie);
            case "Sugars":
                return ContextCompat.getDrawable(context, R.drawable.progress_bar_sugar);
            default:
                return ContextCompat.getDrawable(context, R.drawable.progress_bar_light_green);
        }
    }

    private int findMaxProgressOfACertainMacronutrient(String macronutrient, List<Day> dayList){
        float max = 0.0f;
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

    private void setUpTodayYesterdayIndicators(Day day, TextView textViewProgress, TextView textViewDay){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (day.getCreatedAt().equals(LocalDate.now())){
                if (isNightModeOn){
                    int colorWhite = ContextCompat.getColor(context, R.color.white);
                    textViewProgress.setTextColor(colorWhite);
                    textViewDay.setTextColor(colorWhite);
                    textViewProgress.setTypeface(null, Typeface.BOLD);
                    textViewDay.setTypeface(null, Typeface.BOLD);
                } else {
                    int colorBlack = ContextCompat.getColor(context, R.color.black);
                    textViewProgress.setTextColor(colorBlack);
                    textViewDay.setTextColor(colorBlack);
                    textViewProgress.setTypeface(null, Typeface.BOLD);
                    textViewDay.setTypeface(null, Typeface.BOLD);
                }
            } else if (day.getCreatedAt().equals(LocalDate.now().minusDays(1))){
                if (isNightModeOn){
                    int colorWhite = ContextCompat.getColor(context, R.color.white);
                    textViewProgress.setTextColor(colorWhite);
                    textViewDay.setTextColor(colorWhite);
                } else {
                    int colorBlack = ContextCompat.getColor(context, R.color.black);
                    textViewProgress.setTextColor(colorBlack);
                    textViewDay.setTextColor(colorBlack);
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

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void calculateAverageMacronutrient(String macronutrient, FragmentAllDaysBinding binding, List<Day> dayList){
        int colorPrimary = MaterialColors.getColor(context, com.google.android.material.R.attr.colorPrimary, Color.BLUE);

        if (dayList.size() == 0){
            binding.textViewSub3.setText("Average " + macronutrient);
            binding.textViewSub3.setTextColor(colorPrimary);
            binding.textViewAverage.setText("0");
            binding.textViewAverage.setTextColor(colorPrimary);
            return;
        }

        float sum = 0.0f;
        for (Day day: dayList){
            switch (macronutrient){
                case "Proteins":{
                    sum += day.totalProteins();
                    break;
                } case "Calories":{
                    sum += day.totalCalories();
                    break;
                } case "Carbohydrates": {
                    sum += day.totalCarbohydrates();
                    break;
                } case "Sugars":{
                    sum += day.totalSugar();
                    break;
                }
            }
        }

        binding.textViewSub3.setText("Average " + macronutrient);
        binding.textViewSub3.setTextColor(colorPrimary);
        binding.textViewAverage.setText(String.format("%.0f", sum / dayList.size()));
        binding.textViewAverage.setTextColor(colorPrimary);
    }

    public void countAndSetTotalDays(Context context, FragmentAllDaysBinding binding, AllDaysAdapter allDaysAdapter){
        int colorPrimary = MaterialColors.getColor(context, com.google.android.material.R.attr.colorPrimary, Color.BLUE);

        binding.textViewTotalDays.setText(String.valueOf(allDaysAdapter.getItemCount()));
        binding.textViewTotalDays.setTextColor(colorPrimary);
        binding.textViewSub2.setTextColor(colorPrimary);
    }
}







