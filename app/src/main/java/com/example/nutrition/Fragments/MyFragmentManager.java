package com.example.nutrition.Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nutrition.R;

public class MyFragmentManager {

    public static void change(AppCompatActivity activity, Fragment fragment, boolean cameFromMainActivityAdapter) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        if (fragment instanceof FragmentADay || fragment instanceof FragmentCustomMacros)

            // I do not add this fragment to a stack because I want to go back to main when I exit
            if (cameFromMainActivityAdapter){
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_to_center, R.anim.center_to_left, R.anim.left_to_center, R.anim.center_to_right)
                        .replace(R.id.fragmentContainerViewSection3Activity, fragment)
                        .commit();
            } else {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_to_center, R.anim.center_to_left, R.anim.left_to_center, R.anim.center_to_right)
                        .replace(R.id.fragmentContainerViewSection3Activity, fragment)
                        .addToBackStack("days")
                        .commit();
            }
        else
            fragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.fragmentContainerViewSection3Activity, fragment)
                    .commit();
    }
}
