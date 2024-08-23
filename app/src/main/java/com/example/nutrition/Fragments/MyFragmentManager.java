package com.example.nutrition.Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nutrition.R;

public class MyFragmentManager {

    public static void change(AppCompatActivity activity, Fragment fragment) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        if (fragment instanceof FragmentADay)
            fragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .replace(R.id.fragmentContainerViewSection3Activity, fragment)
                    .addToBackStack("days")
                    .commit();
        else
            fragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.fragmentContainerViewSection3Activity, fragment)
                    .commit();
    }
}
