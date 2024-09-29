package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.nutrition.Adapters.AllDaysAdapter;
import com.example.nutrition.Helper.HelperFragmentAllDays;
import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Helper.SharedPrefMacronutrients;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Day;
import com.example.nutrition.Model.Product;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.Threads.ThreadDays;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.ActivitySection3Binding;
import com.example.nutrition.databinding.FragmentAllDaysBinding;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class FragmentAllDays extends Fragment implements IEssentials {

    private FragmentAllDaysBinding binding;
    private AllDaysAdapter allDaysAdapter;
    private AppCompatActivity appCompatActivity;
    private Toaster toaster;
    private DaysRepo daysRepo;
    private HelperFragmentAllDays helperFragmentAllDays;

    public FragmentAllDays() {}
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

        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(() -> {

            allDaysAdapter = new AllDaysAdapter(getContext(), appCompatActivity, binding, daysRepo);

            handler.post(() ->{
                binding.recyclerViewAllDaysFragment.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                binding.recyclerViewAllDaysFragment.setHasFixedSize(true);
                binding.recyclerViewAllDaysFragment.setAdapter(allDaysAdapter);

                // This is also here since we are using a binding
                // And binding is a main UI component

                // Set up the graph with data
                // Se the default selection the saved one

                String macro = SharedPrefMacronutrients.readMacronutrientFromSharedPref(getContext());
                helperFragmentAllDays.createCustomChart(macro, binding, allDaysAdapter);
                helperFragmentAllDays.checkAndSelectCorrectChip(macro, binding);
                helperFragmentAllDays.calculateAverageMacronutrient(macro, binding, allDaysAdapter.getDaysList());

                addEventListeners();
                checkIfDaysAreEmpty(binding, allDaysAdapter);
                updateTotalDays(binding, allDaysAdapter);
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

                if (!helperFragmentAllDays.isANewDayValidToAdd(allDaysAdapter, toaster)) return;

                Day day = new Day("Day", LocalDate.now());
                daysRepo.add(day);

                allDaysAdapter.setDaysList(daysRepo.listAllSorted());
                allDaysAdapter.notifyDataSetChanged();

                checkIfDaysAreEmpty(binding, allDaysAdapter);
                updateTotalDays(binding, allDaysAdapter);


                String macro = SharedPrefMacronutrients.readMacronutrientFromSharedPref(getContext());
                helperFragmentAllDays.createCustomChart(macro, binding, allDaysAdapter);
                helperFragmentAllDays.checkAndSelectCorrectChip(macro, binding);
                helperFragmentAllDays.calculateAverageMacronutrient(macro, binding, allDaysAdapter.getDaysList());
            }
        });

        binding.chipGroupGraphFragmentAllDays.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                if (checkedIds.isEmpty()){
                    return;
                }

                int id = checkedIds.get(0);
                Chip chip = group.findViewById(id);
                String text = chip.getTag().toString().trim();

                helperFragmentAllDays.createCustomChart(text, binding, allDaysAdapter);
                helperFragmentAllDays.calculateAverageMacronutrient(text, binding, allDaysAdapter.getDaysList());
                SharedPrefMacronutrients.writeMacronutrientToSharedPref(getContext(), text);
            }
        });

    }

    public void additionalThemeChanges() {
        if (ThemeUtils.isNightModeActive(appCompatActivity)) {
            int color = ContextCompat.getColor(getContext(), R.color.colorText60Light);
            int colorWhite = ContextCompat.getColor(getContext(), R.color.white);

            binding.imageViewLogoTrackedDays.setImageResource(R.drawable.ic_calendar_white);
            binding.imageViewLogoGraph.setImageResource(R.drawable.ic_stats_white);

            binding.textViewSub1.setTextColor(color);
            binding.textViewSub2.setTextColor(color);
            binding.textViewSub3.setTextColor(color);

            binding.textViewTrackedDays.setTextColor(colorWhite);
            binding.textViewStatistics.setTextColor(colorWhite);
        } else {
            int color = ContextCompat.getColor(getContext(), R.color.colorText60Dark);
            int colorBlack = ContextCompat.getColor(getContext(), R.color.black);

            binding.imageViewLogoTrackedDays.setImageResource(R.drawable.ic_calendar_black);
            binding.imageViewLogoGraph.setImageResource(R.drawable.ic_stats_black);

            binding.textViewSub1.setTextColor(color);
            binding.textViewSub2.setTextColor(color);
            binding.textViewSub3.setTextColor(color);

            binding.textViewTrackedDays.setTextColor(colorBlack);
            binding.textViewStatistics.setTextColor(colorBlack);
        }
    }

    // HELPER METHODS

    public static void checkIfDaysAreEmpty(FragmentAllDaysBinding binding, AllDaysAdapter allDaysAdapter) {
        if (allDaysAdapter.isListEmpty())
            binding.textViewNoDaysYetFragmentAllDays.setVisibility(View.VISIBLE);
        else
            binding.textViewNoDaysYetFragmentAllDays.setVisibility(View.INVISIBLE);
    }

    public static void updateTotalDays(FragmentAllDaysBinding binding, AllDaysAdapter allDaysAdapter){
        binding.textViewSub2.setText("Total days " + allDaysAdapter.getItemCount());
    }


}





