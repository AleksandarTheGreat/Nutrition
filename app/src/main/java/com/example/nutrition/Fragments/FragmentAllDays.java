package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Adapters.AllDaysAdapter;
import com.example.nutrition.Helper.HelperFragmentAllDays;
import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Model.CustomMacros;
import com.example.nutrition.SharedPrefs.SharedPrefCustomMacros;
import com.example.nutrition.SharedPrefs.SharedPrefMacronutrients;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentAllDaysBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.time.LocalDate;
import java.util.List;


public class FragmentAllDays extends Fragment implements IEssentials {

    private FragmentAllDaysBinding binding;
    // private AllDaysAdapter allDaysAdapter;
    private AppCompatActivity appCompatActivity;
    private Toaster toaster;
    private DaysRepo daysRepo;
    private HelperFragmentAllDays helperFragmentAllDays;
    private List<Day> dayList;
    private CustomMacros customMacros;

    public FragmentAllDays() {
    }

    public FragmentAllDays(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAllDaysBinding.bind(inflater.inflate(R.layout.fragment_all_days, container, false));

        instantiateObjects();
        additionalThemeChanges();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        daysRepo = new DaysRepo(getContext());

        toaster = new Toaster(getContext());
        helperFragmentAllDays = new HelperFragmentAllDays(getContext(), appCompatActivity);

        customMacros = SharedPrefCustomMacros.readFromSharedPref(getContext());

        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(() -> {

            dayList = daysRepo.listAllSorted();
            handler.post(() -> {

                // This is also here since we are using a binding
                // And binding is a main UI component

                // Set up the graph with data
                // Se the default selection the saved one

                String macro = SharedPrefMacronutrients.readMacronutrientFromSharedPref(getContext());
                helperFragmentAllDays.createCustomChart(macro, binding, daysRepo, dayList);
                helperFragmentAllDays.checkAndSelectCorrectChip(macro, binding);
                helperFragmentAllDays.calculateAverageMacronutrient(macro, binding, dayList);

                addEventListeners();
            });

        }).start();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void addEventListeners() {
        // Add the day to database and to the adapter's list
        binding.floatingActionButtonAddDay.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // We create a new day and add it to the database
                // Then we load all days from the database and set the dayList pointing to that list
                // Finally we update the adapter

                if (!helperFragmentAllDays.isANewDayValidToAdd(dayList, toaster)) return;

                Day day = new Day("Day", LocalDate.now());
                daysRepo.add(day);
                dayList = daysRepo.listAllSorted();


                String macro = SharedPrefMacronutrients.readMacronutrientFromSharedPref(getContext());
                helperFragmentAllDays.createCustomChart(macro, binding, daysRepo, dayList);
                helperFragmentAllDays.checkAndSelectCorrectChip(macro, binding);
                helperFragmentAllDays.calculateAverageMacronutrient(macro, binding, dayList);
            }
        });

        binding.chipGroupGraphFragmentAllDays.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                if (checkedIds.isEmpty()) {
                    return;
                }

                dayList = daysRepo.listAllSorted();

                int id = checkedIds.get(0);
                Chip chip = group.findViewById(id);
                String text = chip.getTag().toString().trim();

                helperFragmentAllDays.createCustomChart(text, binding, daysRepo, dayList);
                helperFragmentAllDays.calculateAverageMacronutrient(text, binding, dayList);
                SharedPrefMacronutrients.writeMacronutrientToSharedPref(getContext(), text);
            }
        });

        binding.buttonSetManually.setOnClickListener(view -> {
            MyFragmentManager.change2(appCompatActivity, new FragmentCustomMacros(appCompatActivity), true);
            toaster.text("Going there now");
        });

        binding.buttonPickPredefinedMacros.setOnClickListener(view -> {
            MyFragmentManager.change2(appCompatActivity, new FragmentAutomaticMacros(appCompatActivity), true);
            toaster.text("Starting to be ready, babe ;)");
        });

    }

    public void additionalThemeChanges() {
        if (ThemeUtils.isNightModeActive(appCompatActivity)) {
            int color = ContextCompat.getColor(getContext(), R.color.colorText60Light);
            int colorWhite = ContextCompat.getColor(getContext(), R.color.white);


            binding.textViewAverageLabel.setTextColor(colorWhite);
            binding.textViewTitleFragmentAllDays.setTextColor(colorWhite);
        } else {
            int color = ContextCompat.getColor(getContext(), R.color.colorText60Dark);
            int colorBlack = ContextCompat.getColor(getContext(), R.color.black);

            binding.textViewAverageLabel.setTextColor(colorBlack);
            binding.textViewTitleFragmentAllDays.setTextColor(colorBlack);
        }
    }

    public void setUpCustomMacroLimits(){

    }

    // HELPER METHODS

}





