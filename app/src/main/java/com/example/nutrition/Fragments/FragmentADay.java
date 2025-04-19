package com.example.nutrition.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anychart.core.annotations.Line;
import com.anychart.scales.Linear;
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
import com.example.nutrition.Model.Suggestion;
import com.example.nutrition.R;
import com.example.nutrition.Repos.ItemsRepo;
import com.example.nutrition.Repos.SuggestionsRepo;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.FragmentADayBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.color.MaterialColors;

import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class FragmentADay extends Fragment implements IEssentials {

    private Day day;
    private Toaster toaster;
    private FragmentADayBinding binding;
    private AppCompatActivity appCompatActivity;
    private SuggestionsRepo suggestionsRepo;

    private ItemsAdapter itemsAdapter;
    private ItemsRepo itemsRepo;
    private HelperFragmentADay helperFragmentADay;
    private HelperMain helperMain;
    private boolean isNightModeOn;
    private List<Suggestion> allSuggestions;

    private TextView [] textViewsMacros;

    public FragmentADay() {
    }

    public FragmentADay(Day day, AppCompatActivity appCompatActivity) {
        // This day already contains the products list
        this.day = day;
        this.appCompatActivity = appCompatActivity;
        this.isNightModeOn = ThemeUtils.isNightModeActive(appCompatActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentADayBinding.bind(inflater.inflate(R.layout.fragment_a_day, container, false));

        instantiateObjects();
        additionalThemeSettings();

        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void instantiateObjects() {
        toaster = new Toaster(getContext());

        // Removes underline from searchView
        View searchPlate = binding.searchViewFragmentADay.findViewById(androidx.appcompat.R.id.search_plate);
        if (searchPlate != null) searchPlate.setBackgroundColor(Color.TRANSPARENT);

        itemsRepo = new ItemsRepo(getContext());
        suggestionsRepo = new SuggestionsRepo(getContext());

        helperFragmentADay = new HelperFragmentADay(getContext());
        helperMain = new HelperMain(getContext());

        // binding.textViewSub2.setText(day.calculateLongDayNameOfDate() + " - " + day.getDateIntoStringFormat());

        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(() -> {
            itemsAdapter = new ItemsAdapter(getContext(), appCompatActivity, binding, itemsRepo, day);
            allSuggestions = suggestionsRepo.listAll();

            handler.post(() -> {
                binding.recyclerViewFragmentADay.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.recyclerViewFragmentADay.setHasFixedSize(true);
                binding.recyclerViewFragmentADay.setAdapter(itemsAdapter);

                // Calculate total and change the ui in the material cards
                HelperFragmentADay.checkIfItemsAreEmpty(binding, itemsAdapter);
                HelperFragmentADay.calculateTotalNutrients(getContext(), binding, itemsAdapter);

                addEventListeners();
            });

        }).start();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void addEventListeners() {
        binding.searchViewFragmentADay.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                helperFragmentADay.addItem(binding, suggestionsRepo, itemsAdapter, itemsRepo, day.getId());
                binding.searchViewFragmentADay.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Suggestion> filtered = allSuggestions
                        .stream()
                        .filter(suggestion -> suggestion.getText().toLowerCase().contains(newText.toLowerCase()))
                        .collect(Collectors.toList());

                helperFragmentADay.setUpTextViewsInTheScrollView(binding, getContext(), isNightModeOn, filtered);
                return true;
            }
        });

        binding.searchViewFragmentADay.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    Log.d("Tag","Has focus");
                    helperFragmentADay.showSuggestions(binding);
                    helperFragmentADay.setUpTextViewsInTheScrollView(binding, getContext(), isNightModeOn, suggestionsRepo.listAll());
                } else {
                    Log.d("Tag","No focus");
                    helperFragmentADay.hideSuggestions(binding);
                    binding.searchViewFragmentADay.clearFocus();
                }
            }
        });

        binding.textViewHelp.setOnClickListener(view -> {
            helperMain.goToActivity(getContext(), IntroductionActivity.class, MyIntroFragAdapter.TYPE_4);
        });

    }

    public void additionalThemeSettings() {
        if (ThemeUtils.isNightModeActive(appCompatActivity)) {
            int colorWhite = ContextCompat.getColor(getContext(), R.color.white);

            binding.imageViewIconDay.setImageResource(R.drawable.ic_meal_light);

            binding.textViewSub1.setTextColor(colorWhite);
            binding.textViewSubTitleSection2.setTextColor(colorWhite);

            binding.textViewLimitProteins.setTextColor(colorWhite);
            binding.textViewLimitCalories.setTextColor(colorWhite);
            binding.textViewLimitCarbs.setTextColor(colorWhite);
            binding.textViewLimitSugars.setTextColor(colorWhite);

        } else {
            int colorBlack = ContextCompat.getColor(getContext(), R.color.black);

            binding.imageViewIconDay.setImageResource(R.drawable.ic_meal_dark);

            binding.textViewSub1.setTextColor(colorBlack);
            binding.textViewSubTitleSection2.setTextColor(colorBlack);

            binding.textViewLimitProteins.setTextColor(colorBlack);
            binding.textViewLimitCalories.setTextColor(colorBlack);
            binding.textViewLimitCarbs.setTextColor(colorBlack);
            binding.textViewLimitSugars.setTextColor(colorBlack);
        }

        int primaryColor = MaterialColors.getColor(getContext(), com.google.android.material.R.attr.colorPrimary, Color.BLACK);
        binding.textViewHelp.setTextColor(primaryColor);
    }


}










