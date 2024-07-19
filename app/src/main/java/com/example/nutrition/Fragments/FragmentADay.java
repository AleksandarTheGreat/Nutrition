package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nutrition.Adapters.AllDaysAdapter;
import com.example.nutrition.Adapters.ProductsAdapter;
import com.example.nutrition.Helper.ContentLoader;
import com.example.nutrition.Helper.HelperFragmentADay;
import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Day;
import com.example.nutrition.Model.Product;
import com.example.nutrition.R;
import com.example.nutrition.databinding.FragmentADayBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


public class FragmentADay extends Fragment implements IEssentials {

    private Day day;
    private Toaster toaster;
    private FragmentADayBinding binding;
    private ProductsAdapter productsAdapter;
    private HelperFragmentADay helperFragmentADay;

    public FragmentADay() {
    }

    public FragmentADay(Day day) {
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
        helperFragmentADay = new HelperFragmentADay(getContext());
        toaster = new Toaster(getContext());

        // Load the auto complete text view with all the products data but as a string
        helperFragmentADay.loadProductsToAutoComplete(binding);
        helperFragmentADay.checkEmptyLayout(binding);

        productsAdapter = new ProductsAdapter(getContext(), new ArrayList<>());

        binding.recyclerViewFragmentADay.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewFragmentADay.setHasFixedSize(true);
        binding.recyclerViewFragmentADay.setAdapter(productsAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void addEventListeners() {
        binding.buttonAddFragmentADay.setOnClickListener(view -> {
            helperFragmentADay.addProduct(binding, productsAdapter);
            helperFragmentADay.checkEmptyLayout(binding);
        });
    }

}





