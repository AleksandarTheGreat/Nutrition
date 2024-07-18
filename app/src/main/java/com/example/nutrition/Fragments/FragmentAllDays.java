package com.example.nutrition.Fragments;

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
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
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

    public FragmentAllDays() {}
    public FragmentAllDays(ActivitySection3Binding activitySection3Binding){
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
        List<Day> list = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            list.add(Day.createANewDay("Day 1", LocalDate.of(2024, 3, 10)));
            list.add(Day.createANewDay("Day 2", LocalDate.of(2024, 4, 11)));
            list.add(Day.createANewDay("Day 3", LocalDate.of(2024, 4, 12)));
            list.add(Day.createANewDay("Day 4", LocalDate.of(2024, 4, 13)));
            list.add(Day.createANewDay("Day 5", LocalDate.of(2024, 4, 14)));
            list.add(Day.createANewDay("Day 6", LocalDate.of(2024, 4, 15)));
            list.add(Day.createANewDay("Day 7", LocalDate.of(2024, 4, 11)));
            list.add(Day.createANewDay("Day 8", LocalDate.of(2024, 5, 1)));
            list.add(Day.createANewDay("Day 9", LocalDate.of(2024, 5, 2)));
            list.add(Day.createANewDay("Day 10", LocalDate.of(2024, 5, 3)));
            list.add(Day.createANewDay("Day 11", LocalDate.of(2024, 5, 4)));
            list.add(Day.createANewDay("Day 12", LocalDate.of(2024, 6, 8)));
            list.add(Day.createANewDay("Day 13", LocalDate.of(2024, 6, 9)));
            list.add(Day.createANewDay("Day 14", LocalDate.of(2024, 6, 10)));
            list.add(Day.createANewDay("Day 15", LocalDate.of(2024, 6, 11)));
            list.add(Day.createANewDay("Day 16", LocalDate.of(2024, 6, 12)));
            list.add(Day.createANewDay("Day 17", LocalDate.of(2024, 6, 13)));
            list.add(Day.createANewDay("Day 18", LocalDate.of(2024, 6, 14)));
            list.add(Day.createANewDay("Day 19", LocalDate.of(2024, 7, 17)));
            list.add(Day.createANewDay("Day 20", LocalDate.of(2024, 7, 18)));
            list.add(Day.createANewDay("Day 21", LocalDate.of(2024, 7, 19)));
            list.add(Day.createANewDay("Day 22", LocalDate.of(2024, 7, 20)));
        }

        allDaysAdapter = new AllDaysAdapter(getContext(), activitySection3Binding, list);

        binding.recyclerViewAllDaysFragment.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.recyclerViewAllDaysFragment.setHasFixedSize(true);
        binding.recyclerViewAllDaysFragment.setAdapter(allDaysAdapter);
    }

    @Override
    public void addEventListeners() {

    }
}