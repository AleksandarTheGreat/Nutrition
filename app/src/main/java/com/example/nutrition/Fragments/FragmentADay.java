package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Activities.IntroductionActivity;
import com.example.nutrition.Adapters.ItemsAdapter;
import com.example.nutrition.Adapters.MyIntroFragAdapter;
import com.example.nutrition.Adapters.ProductsAdapter;
import com.example.nutrition.Helper.HelperFragmentADay;
import com.example.nutrition.Helper.HelperMain;
import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Day;
import com.example.nutrition.Model.Item;
import com.example.nutrition.R;
import com.example.nutrition.Repos.ItemsRepo;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentADayBinding;
import com.google.android.material.color.MaterialColors;


public class FragmentADay extends Fragment implements IEssentials {

    private Day day;
    private Toaster toaster;
    private FragmentADayBinding binding;
    private AppCompatActivity appCompatActivity;

    private ItemsAdapter itemsAdapter;
    private ItemsRepo itemsRepo;
    private HelperFragmentADay helperFragmentADay;
    private HelperMain helperMain;

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
        helperFragmentADay = new HelperFragmentADay(getContext());
        helperMain = new HelperMain(getContext());

        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(() -> {
            itemsAdapter = new ItemsAdapter(getContext(), appCompatActivity, binding, itemsRepo, day);

            handler.post(() -> {
                binding.recyclerViewFragmentADay.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.recyclerViewFragmentADay.setHasFixedSize(true);
                binding.recyclerViewFragmentADay.setAdapter(itemsAdapter);

                HelperFragmentADay.checkIfItemsAreEmpty(binding, itemsAdapter);

                // Calculate total and change the ui in the material cards
                HelperFragmentADay.calculateTotalNutrients(binding, itemsAdapter);
            });

        }).start();
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

        binding.textViewHelp.setOnClickListener(view -> {
            helperMain.goToActivity(getContext(), IntroductionActivity.class, MyIntroFragAdapter.TYPE_4);
        });
    }

    public void additionalThemeSettings(){
        if (ThemeUtils.isNightModeActive(appCompatActivity)){
            int color = ContextCompat.getColor(getContext(), R.color.colorText60Light);
            binding.textViewSub1.setTextColor(color);
            binding.textViewSub2.setTextColor(color);
            binding.textViewSub3.setTextColor(color);
        } else {
            int color = ContextCompat.getColor(getContext(), R.color.colorText60Dark);
            binding.textViewSub1.setTextColor(color);
            binding.textViewSub2.setTextColor(color);
            binding.textViewSub3.setTextColor(color);
        }

        int primaryColor = MaterialColors.getColor(getContext(), com.google.android.material.R.attr.colorPrimary, Color.BLACK);
        binding.textViewHelp.setTextColor(primaryColor);
    }

}





