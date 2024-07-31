package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Adapters.ItemsAdapter;
import com.example.nutrition.Adapters.ProductsAdapter;
import com.example.nutrition.Helper.HelperFragmentADay;
import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Day;
import com.example.nutrition.R;
import com.example.nutrition.Repos.ItemsRepo;
import com.example.nutrition.databinding.FragmentADayBinding;


public class FragmentADay extends Fragment implements IEssentials {

    private Day day;
    private Toaster toaster;
    private FragmentADayBinding binding;

    private ItemsAdapter itemsAdapter;
    private ItemsRepo itemsRepo;
    private HelperFragmentADay helperFragmentADay;

    public FragmentADay() {
    }

    public FragmentADay(Day day) {
        // This day already contains the products list
        this.day = day;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentADayBinding.bind(inflater.inflate(R.layout.fragment_a_day, container, false));

        instantiateObjects();
        addEventListeners();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        toaster = new Toaster(getContext());

        itemsRepo = new ItemsRepo(getContext());
        itemsAdapter = new ItemsAdapter(getContext(), binding, itemsRepo, day);

        binding.recyclerViewFragmentADay.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewFragmentADay.setHasFixedSize(true);
        binding.recyclerViewFragmentADay.setAdapter(itemsAdapter);

        toaster.text("Listed '" + day.itemsCount() + "' items");

        helperFragmentADay = new HelperFragmentADay(getContext());
        HelperFragmentADay.checkIfItemsAreEmpty(binding, itemsAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void addEventListeners() {
        binding.buttonAddFragmentADay.setOnClickListener(view -> {

            // Make the request to the API and add the Item to repo
            // After that load the adapter with items from the itemsRepo
            // So that those items can have the id NECESSARY for deletion

            helperFragmentADay.addProduct(binding, itemsAdapter, itemsRepo, day.getId());
        });
    }

}





