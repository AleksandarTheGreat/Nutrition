package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Day;
import com.example.nutrition.Model.Product;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.Threads.ThreadDays;
import com.example.nutrition.databinding.ActivitySection3Binding;
import com.example.nutrition.databinding.FragmentAllDaysBinding;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class FragmentAllDays extends Fragment implements IEssentials {

    private FragmentAllDaysBinding binding;
    private AllDaysAdapter allDaysAdapter;
    private AppCompatActivity appCompatActivity;
    private Toaster toaster;
    private DaysRepo daysRepo;

    public FragmentAllDays() {
    }

    public FragmentAllDays(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAllDaysBinding.bind(inflater.inflate(R.layout.fragment_all_days, container, false));

        instantiateObjects();
        addEventListeners();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        daysRepo = new DaysRepo(getContext());

        toaster = new Toaster(getContext());
        allDaysAdapter = new AllDaysAdapter(getContext(), appCompatActivity, binding, daysRepo);
        checkIfDaysAreEmpty(binding, allDaysAdapter);

        // binding.recyclerViewAllDaysFragment.setLayoutManager(new GridLayoutManager(getContext(), 1, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewAllDaysFragment.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewAllDaysFragment.setHasFixedSize(true);
        binding.recyclerViewAllDaysFragment.setAdapter(allDaysAdapter);

        // Set up the graph with data
        setUpAnyChart();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void addEventListeners() {
        // Add the day to database and to the adapter's list
        binding.buttonCreateANewDay.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // We create a new day and add it to the database
                // Then we load all days from the database and set the dayList pointing to that list
                // Finally we update the adapter

                // if (!isANewDayValidToAdd()) return;

                Day day = new Day("Day", LocalDate.now());
                daysRepo.add(day);

                allDaysAdapter.setDaysList(daysRepo.listAll());
                allDaysAdapter.notifyDataSetChanged();

                checkIfDaysAreEmpty(binding, allDaysAdapter);
            }
        });
    }


    // HELPER METHODS

    public void setUpAnyChart() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (Day day: allDaysAdapter.getDaysList()){
            barEntries.add(new BarEntry(day.getId(), day.totalCalories()));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Days");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextSize(14f);
        barDataSet.setValueTextColor(Color.BLACK);

        BarData barData = new BarData(barDataSet);

        binding.anyChartFragmentAllDays.setFitBars(true);
        binding.anyChartFragmentAllDays.setData(barData);
        binding.anyChartFragmentAllDays.getDescription().setText("Calories test");
        binding.anyChartFragmentAllDays.animateY(1000);
    }

    private boolean isANewDayValidToAdd() {
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

    public static void checkIfDaysAreEmpty(FragmentAllDaysBinding binding, AllDaysAdapter allDaysAdapter) {
        if (allDaysAdapter.isListEmpty())
            binding.textViewNoDaysYetFragmentAllDays.setVisibility(View.VISIBLE);
        else
            binding.textViewNoDaysYetFragmentAllDays.setVisibility(View.INVISIBLE);
    }
}





