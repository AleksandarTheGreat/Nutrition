package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Adapters.AllDaysAdapter;
import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Day;
import com.example.nutrition.Model.Product;
import com.example.nutrition.R;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.databinding.ActivitySection3Binding;
import com.example.nutrition.databinding.FragmentAllDaysBinding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class FragmentAllDays extends Fragment implements IEssentials {

    private FragmentAllDaysBinding binding;
    private AllDaysAdapter allDaysAdapter;
    private AppCompatActivity appCompatActivity;
    private ActivitySection3Binding activitySection3Binding;
    private Toaster toaster;
    private DaysRepo daysRepo;

    public FragmentAllDays() {}
    public FragmentAllDays(AppCompatActivity appCompatActivity, ActivitySection3Binding activitySection3Binding){
        this.appCompatActivity = appCompatActivity;
        this.activitySection3Binding = activitySection3Binding;
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
        // Load the days from SQLite
        daysRepo = new DaysRepo(getContext());

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        allDaysAdapter = new AllDaysAdapter(getContext(), appCompatActivity, activitySection3Binding, daysRepo);

        binding.recyclerViewAllDaysFragment.setLayoutManager(gridLayoutManager);
        binding.recyclerViewAllDaysFragment.setHasFixedSize(true);
        binding.recyclerViewAllDaysFragment.setAdapter(allDaysAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void addEventListeners() {
        // Add the day to database and to the adapter's list
        binding.buttonCreateANewDay.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                // We create a new day and add it to the database
                // Then we load all days from the database and set the dayList pointing to that list
                // Finally we updated the adapter

                Day day = new Day("Day", LocalDate.now());
                daysRepo.add(day);

                allDaysAdapter.setDaysList(daysRepo.listAll());
                allDaysAdapter.notifyDataSetChanged();
            }
        });
    }
}