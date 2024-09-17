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
import com.google.android.material.color.MaterialColors;

import java.time.format.TextStyle;
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

        itemsRepo = new ItemsRepo(getContext());
        suggestionsRepo = new SuggestionsRepo(getContext());

        helperFragmentADay = new HelperFragmentADay(getContext());
        helperMain = new HelperMain(getContext());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String dayName = day.getCreatedAt().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            binding.textViewSub2.setText(dayName + " - " + day.getDateIntoStringFormat());
        }

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
                HelperFragmentADay.calculateTotalNutrients(binding, itemsAdapter);

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
                helperFragmentADay.addProduct(binding, suggestionsRepo, itemsAdapter, itemsRepo, day.getId());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Suggestion> filtered = allSuggestions.stream()
                        .filter(suggestion -> suggestion.getText().toLowerCase().contains(newText.toLowerCase()))
                        .collect(Collectors.toList());

                setUpTextViewsInTheScrollView(filtered);
                return true;
            }
        });

        binding.searchViewFragmentADay.setOnSearchClickListener(view -> {
            helperFragmentADay.showSuggestions(binding);
            setUpTextViewsInTheScrollView(suggestionsRepo.listAll());
        });

        binding.searchViewFragmentADay.setOnCloseListener(() -> {
            helperFragmentADay.hideSuggestions(binding);
            return true;
        });

        binding.textViewHelp.setOnClickListener(view -> {
            helperMain.goToActivity(getContext(), IntroductionActivity.class, MyIntroFragAdapter.TYPE_4);
        });
    }

    public void additionalThemeSettings(){
        if (ThemeUtils.isNightModeActive(appCompatActivity)){
            int color = ContextCompat.getColor(getContext(), R.color.colorText60Light);
            int colorWhite = ContextCompat.getColor(getContext(), R.color.white);

            binding.imageViewIconTotal.setImageResource(R.drawable.ic_energy_light);
            binding.imageViewIconDay.setImageResource(R.drawable.ic_meal_light);

            binding.textViewSub1.setTextColor(color);
            binding.textViewSub2.setTextColor(color);
            binding.textViewSub3.setTextColor(color);

            binding.textViewSubTitleSection1.setTextColor(colorWhite);
            binding.textViewSubTitleSection2.setTextColor(colorWhite);

        } else {
            int color = ContextCompat.getColor(getContext(), R.color.colorText60Dark);
            int colorBlack = ContextCompat.getColor(getContext(), R.color.black);

            binding.imageViewIconTotal.setImageResource(R.drawable.ic_energy_dark);
            binding.imageViewIconDay.setImageResource(R.drawable.ic_meal_dark);

            binding.textViewSub1.setTextColor(color);
            binding.textViewSub2.setTextColor(color);
            binding.textViewSub3.setTextColor(color);

            binding.textViewSubTitleSection1.setTextColor(colorBlack);
            binding.textViewSubTitleSection2.setTextColor(colorBlack);
        }

        int primaryColor = MaterialColors.getColor(getContext(), com.google.android.material.R.attr.colorPrimary, Color.BLACK);
        binding.textViewHelp.setTextColor(primaryColor);
    }

    @SuppressLint("SetTextI18n")
    private void setUpTextViewsInTheScrollView(List<Suggestion> suggestionList){
        binding.linearLayoutSuggestionsFragmentADay.removeAllViews();

        // These layout creations take time, that is why they shall be created on a new thread
        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(() -> {
            for (int i=0;i<suggestionList.size();i++){
                Suggestion suggestion = suggestionList.get(i);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0,0,0,12);

                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setPadding(4,24,4,24);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setGravity(Gravity.CENTER_VERTICAL);
                linearLayout.setBackgroundResource(R.drawable.back_for_suggestion);




                layoutParams = new LinearLayout.LayoutParams(50, 50);
                layoutParams.setMargins(24,12,12,12);

                ImageView imageView = new ImageView(getContext());
                imageView.setLayoutParams(layoutParams);
                if (isNightModeOn) imageView.setImageResource(R.drawable.ic_recent_light);
                else imageView.setImageResource(R.drawable.ic_recent_dark);




                layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(12, 12, 12, 12);

                TextView textView = new TextView(getContext());
                textView.setText(suggestion.getText());
                textView.setTextSize(16);
                textView.setLayoutParams(layoutParams);

                linearLayout.addView(imageView);
                linearLayout.addView(textView);

                handler.post(() -> {
                    binding.linearLayoutSuggestionsFragmentADay.addView(linearLayout);
                    linearLayout.setOnClickListener(view -> {
                        String text = textView.getText().toString().trim();
                        binding.searchViewFragmentADay.setQuery(text, false);
                    });
                });
            }

        }).start();
    }

}










