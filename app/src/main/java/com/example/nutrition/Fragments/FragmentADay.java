package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import com.example.nutrition.Model.Item;
import com.example.nutrition.R;
import com.example.nutrition.Repos.ItemsRepo;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentADayBinding;


public class FragmentADay extends Fragment implements IEssentials {

    private Day day;
    private Toaster toaster;
    private FragmentADayBinding binding;
    private AppCompatActivity appCompatActivity;

    private ItemsAdapter itemsAdapter;
    private ItemsRepo itemsRepo;
    private HelperFragmentADay helperFragmentADay;

    public FragmentADay() {
    }

    public FragmentADay(Day day, AppCompatActivity appCompatActivity) {
        // This day already contains the products list
        this.day = day;
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentADayBinding.bind(inflater.inflate(R.layout.fragment_a_day, container, false));

        instantiateObjects();
        addEventListeners();
        additionalThemeSettings();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        toaster = new Toaster(getContext());

        itemsRepo = new ItemsRepo(getContext());
        itemsAdapter = new ItemsAdapter(getContext(), appCompatActivity, binding, itemsRepo, day);

        binding.recyclerViewFragmentADay.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewFragmentADay.setHasFixedSize(true);
        binding.recyclerViewFragmentADay.setAdapter(itemsAdapter);

        toaster.text("Listed '" + day.itemsCount() + "' items");

        helperFragmentADay = new HelperFragmentADay(getContext());
        HelperFragmentADay.checkIfItemsAreEmpty(binding, itemsAdapter);

        // Calculate total and change the ui in the material cards
        HelperFragmentADay.calculateTotalNutrients(binding, itemsAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void addEventListeners() {
        binding.searchViewFragmentADay.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                helperFragmentADay.addProduct(binding, itemsAdapter, itemsRepo, day.getId());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void additionalThemeSettings(){
        if (ThemeUtils.isNightModeActive(appCompatActivity)){
            binding.imageViewIconTotal.setImageResource(R.drawable.ic_apple_white);
            binding.imageViewIconDay.setImageResource(R.drawable.ic_eating_white);
        } else {
            binding.imageViewIconTotal.setImageResource(R.drawable.ic_apple_black);
            binding.imageViewIconDay.setImageResource(R.drawable.ic_eating_black);
        }
    }

}





