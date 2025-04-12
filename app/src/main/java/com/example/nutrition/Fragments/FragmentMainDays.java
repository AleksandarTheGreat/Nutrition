package com.example.nutrition.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutrition.Activities.MainActivity;
import com.example.nutrition.Activities.Section3Activity;
import com.example.nutrition.Adapters.DaysAdapter;
import com.example.nutrition.Helper.HelperMain;
import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.ActivityMainBinding;
import com.example.nutrition.databinding.FragmentMainDaysBinding;


public class FragmentMainDays extends Fragment implements IEssentials {

    private AppCompatActivity appCompatActivity;
    private boolean isNightModeOn;
    private FragmentMainDaysBinding binding;
    private ActivityMainBinding activityMainBinding;
    private DaysAdapter daysAdapter;
    private HelperMain helperMain;

    public FragmentMainDays() {
        // Required empty public constructor
    }

    public FragmentMainDays(AppCompatActivity appCompatActivity, ActivityMainBinding activityMainBinding){
        this.appCompatActivity = appCompatActivity;
        this.activityMainBinding = activityMainBinding;
        this.isNightModeOn = ThemeUtils.isNightModeActive(appCompatActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainDaysBinding.bind(inflater.inflate(R.layout.fragment_main_days, container, false));

        instantiateObjects();
        additionalThemeChanges();
        addEventListeners();

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        setUpMainActivityDaysAdapter();
    }

    @Override
    public void instantiateObjects() {
        helperMain = new HelperMain(getContext());
    }

    @Override
    public void addEventListeners() {

        binding.imageViewArrow.setOnClickListener(view -> {
            helperMain.goToActivity(getContext(), Section3Activity.class);
        });

        binding.buttonGetStarted.setOnClickListener(view -> {
            helperMain.goToActivity(getContext(), Section3Activity.class);
        });
    }

    public void additionalThemeChanges(){
        if (isNightModeOn){

            int colorWhite = ContextCompat.getColor(getContext(), R.color.white);

            binding.textViewSubTitleSection3.setTextColor(colorWhite);

            binding.imageViewIconSection3.setImageResource(R.drawable.ic_calendar_white);
            binding.imageViewArrow.setImageResource(R.drawable.ic_right_white);

        } else {

            int colorBlack = ContextCompat.getColor(getContext(), R.color.black);

            binding.textViewSubTitleSection3.setTextColor(colorBlack);

            binding.imageViewIconSection3.setImageResource(R.drawable.ic_calendar_black);
            binding.imageViewArrow.setImageResource(R.drawable.ic_right_black);

        }
    }

    private void setUpMainActivityDaysAdapter(){
        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(() -> {
            daysAdapter = new DaysAdapter(getContext(), appCompatActivity);

            handler.post(() -> {
                binding.recyclerViewDaysMainActivity.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.recyclerViewDaysMainActivity.setHasFixedSize(true);
                binding.recyclerViewDaysMainActivity.setAdapter(daysAdapter);

                // check if the adapter is empty
                // and since we only interact via click
                // and don't load or delete new days this is enough
                if (daysAdapter.isEmpty()){
                    binding.textViewNoDaysMainActivity.setVisibility(View.VISIBLE);
                    binding.imageViewRunning.setVisibility(View.VISIBLE);
                    binding.buttonGetStarted.setVisibility(View.VISIBLE);
                } else {
                    binding.textViewNoDaysMainActivity.setVisibility(View.GONE);
                    binding.imageViewRunning.setVisibility(View.GONE);
                    binding.buttonGetStarted.setVisibility(View.GONE);
                }

                activityMainBinding.textViewTotalDays.setText(String.valueOf(daysAdapter.getItemCount()));
            });
        }).start();
    }
}