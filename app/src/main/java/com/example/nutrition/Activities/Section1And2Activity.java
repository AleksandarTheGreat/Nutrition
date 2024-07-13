package com.example.nutrition.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.nutrition.Adapters.ItemsAdapter;
import com.example.nutrition.Helper.ContentLoader;
import com.example.nutrition.Helper.HelperSection1And2Activity;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Product;
import com.example.nutrition.R;
import com.example.nutrition.databinding.ActivitySection1And2Binding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Section1And2Activity extends ParentActivity {


    private ActivitySection1And2Binding binding;
    private Toaster toaster;
    private ItemsAdapter itemsAdapter;
    private HelperSection1And2Activity helperSection1And2Activity;
    private List<Product> allProductsAlways;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instantiateObjects();
        additionalThemeChanges();

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addEventListeners();
    }

    @Override
    public void instantiateObjects() {
        binding = ActivitySection1And2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toaster = new Toaster(Section1And2Activity.this);
        helperSection1And2Activity = new HelperSection1And2Activity(Section1And2Activity.this);
        allProductsAlways = ContentLoader.createTestList();

        itemsAdapter = new ItemsAdapter(Section1And2Activity.this, ContentLoader.createTestList());

        binding.recyclerViewSection1And2.setLayoutManager(new LinearLayoutManager(Section1And2Activity.this));
        binding.recyclerViewSection1And2.setHasFixedSize(true);
        binding.recyclerViewSection1And2.setAdapter(itemsAdapter);
    }

    @Override
    public void addEventListeners() {
        binding.chipGroupSection1And2.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                HashSet<String> set = new HashSet<>();
                for (Integer id : checkedIds) {
                    Chip chip = group.findViewById(id);
                    String category = chip.getText().toString().trim();

                    set.add(category);
                }

                List<Product> filteredList;
                if (!checkedIds.isEmpty())
                    filteredList = allProductsAlways
                            .stream()
                            .filter(product -> set.contains(product.getCategory()))
                            .collect(Collectors.toList());
                else
                    filteredList = ContentLoader.createTestList();

                itemsAdapter.setProductList(filteredList);
                itemsAdapter.notifyDataSetChanged();
            }
        });

        binding.searchViewSection1And2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onQueryTextChange(String newText) {
                List<Product> filteredProducts = allProductsAlways
                        .stream()
                        .filter(product -> product.getName().trim().toLowerCase().contains(newText.toLowerCase())
                                || product.getCategory().trim().toLowerCase().contains(newText.toLowerCase()))
                        .collect(Collectors.toList());

                itemsAdapter.setProductList(filteredProducts);
                itemsAdapter.notifyDataSetChanged();

                return true;
            }
        });

        binding.imageViewIconHeaderLayoutSection1And2.setOnClickListener(view -> {
            helperSection1And2Activity.showStatistics(itemsAdapter);
        });
    }

    @Override
    public void additionalThemeChanges() {

    }

}