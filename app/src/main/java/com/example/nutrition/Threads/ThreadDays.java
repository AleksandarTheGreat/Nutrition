package com.example.nutrition.Threads;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.nutrition.Adapters.AllDaysAdapter;
import com.example.nutrition.Fragments.FragmentAllDays;
import com.example.nutrition.Repos.DaysRepo;
import com.example.nutrition.databinding.ActivitySection3Binding;
import com.example.nutrition.databinding.FragmentAllDaysBinding;

public class ThreadDays extends Thread{

    private Context context;
    private AppCompatActivity appCompatActivity;
    private FragmentAllDaysBinding fragmentAllDaysBinding;
    private AllDaysAdapter allDaysAdapter;
    private DaysRepo daysRepo;

    public ThreadDays(Context context,
                      AppCompatActivity appCompatActivity,
                      FragmentAllDaysBinding fragmentAllDaysBinding,
                      AllDaysAdapter allDaysAdapter,
                      DaysRepo daysRepo){

        this.context = context;
        this.appCompatActivity = appCompatActivity;
        this.fragmentAllDaysBinding = fragmentAllDaysBinding;
        this.allDaysAdapter = allDaysAdapter;
        this.daysRepo = daysRepo;
    }

    @Override
    public void run() {
        super.run();

        allDaysAdapter = new AllDaysAdapter(context, appCompatActivity, fragmentAllDaysBinding, daysRepo);

        fragmentAllDaysBinding.recyclerViewAllDaysFragment.setLayoutManager(new GridLayoutManager(context, 3));
        fragmentAllDaysBinding.recyclerViewAllDaysFragment.setHasFixedSize(true);
        fragmentAllDaysBinding.recyclerViewAllDaysFragment.setAdapter(allDaysAdapter);

        Log.d("Tag", "Loaded days from this thread '" + getName() + "'");
    }
}
