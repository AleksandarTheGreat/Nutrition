package com.example.nutrition.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.nutrition.Fragments.FragmentIntroduction;
import com.example.nutrition.Model.Macronutrient;
import com.example.nutrition.R;

import java.util.ArrayList;
import java.util.List;

public class MyIntroFragAdapter extends FragmentStateAdapter {

    private Context context;
    private List<Fragment> fragmentList;

    public MyIntroFragAdapter(Context context, FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        this.context = context;
        setUpFragmentList();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    private void setUpFragmentList(){
        this.fragmentList = new ArrayList<>();

        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Protein", "Lorem ipsu Lorem ipsu Lorem ipsu Lorem ipsu Lorem ipsu", R.drawable.ic_steak)));
        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Carbohydrate", "Lorem ipsu Lorem ipsu Lorem ipsu", R.drawable.ic_wheat)));
        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Calorie", "Lorem ipsu Lorem ipsu Lorem ipsu Lorem ipsu Lorem ipsu Lorem ipsu", R.drawable.ic_olive_oil)));
        fragmentList.add(new FragmentIntroduction(
                new Macronutrient("Sugar", "Lorem ipsu Lorem ipsu", R.drawable.ic_sugar)));
    }
}
