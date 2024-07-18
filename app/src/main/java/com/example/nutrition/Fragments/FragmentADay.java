package com.example.nutrition.Fragments;

import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nutrition.Helper.ContentLoader;
import com.example.nutrition.Helper.IEssentials;
import com.example.nutrition.Model.Day;
import com.example.nutrition.Model.Product;
import com.example.nutrition.R;
import com.example.nutrition.databinding.FragmentADayBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class FragmentADay extends Fragment implements IEssentials {

    private FragmentADayBinding binding;
    private Day day;

    public FragmentADay() {}
    public FragmentADay(Day day){
        this.day = day;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentADayBinding.bind(inflater.inflate(R.layout.fragment_a_day, container, false));

        instantiateObjects();
        addEventListeners();

        return binding.getRoot();
    }

    @Override
    public void instantiateObjects() {
        List<String> subList = ContentLoader.createTestList()
                .stream()
                .map(product -> product.getName())
                .collect(Collectors.toList());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, subList);
        binding.autoCompleteTextViewFragmentADay.setAdapter(adapter);

        checkEmptyLayout();
    }

    @Override
    public void addEventListeners() {
        binding.buttonSearchFragmentADay.setOnClickListener(view -> {
            String text = binding.autoCompleteTextViewFragmentADay.getText().toString().trim();
            if (text.equals("")){
                binding.autoCompleteTextViewFragmentADay.setError("Type an item");
                return;
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(12,0,0,16);

            TextView textView = new TextView(getContext());
            textView.setLayoutParams(layoutParams);
            textView.setText(text);

            binding.linearLayoutFragmentADay.addView(textView);
            binding.autoCompleteTextViewFragmentADay.setText("");

            checkEmptyLayout();
        });
    }



    private void checkEmptyLayout(){
        if (binding.linearLayoutFragmentADay.getChildCount() != 0){
            binding.textViewNoItemsFragmentADay.setVisibility(View.INVISIBLE);
        } else {
            binding.textViewNoItemsFragmentADay.setVisibility(View.VISIBLE);
        }
    }
}





