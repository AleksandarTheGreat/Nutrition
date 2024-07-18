package com.example.nutrition.Fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentSec3Adapter extends FragmentStateAdapter {

    public FragmentSec3Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0)
            return new FragmentAllDays();
        return new FragmentADay();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
