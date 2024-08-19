package com.example.nutrition.Activities;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.nutrition.Adapters.ProductsAdapter;
import com.example.nutrition.Helper.ContentLoader;
import com.example.nutrition.Helper.HelperSection1And2Activity;
import com.example.nutrition.Helper.Toaster;
import com.example.nutrition.Model.Product;
import com.example.nutrition.R;
import com.example.nutrition.Utils.ThemeUtils;
import com.example.nutrition.databinding.ActivitySection1And2Binding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Section1And2Activity extends ParentActivity {


    private ActivitySection1And2Binding binding;
    private Toaster toaster;
    private ProductsAdapter productsAdapter;
    private HelperSection1And2Activity helperSection1And2Activity;
    private List<Product> allProductsAlways;
    private AppCompatActivity appCompatActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instantiateObjects();
        additionalThemeChanges();

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayoutSection1And2Activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addEventListeners();

        // Either the first method will be called or the second one

        // The first shall be called if we come here via the List button
        helperSection1And2Activity.setUpByPyramidCategories(this, binding);

        // The second shall be called if we come here via the Filter button
        helperSection1And2Activity.setUpByFilters(this, productsAdapter);
    }

    @Override
    public void instantiateObjects() {
        binding = ActivitySection1And2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appCompatActivity = this;

        toaster = new Toaster(Section1And2Activity.this);
        helperSection1And2Activity = new HelperSection1And2Activity(Section1And2Activity.this);

        allProductsAlways = ContentLoader.createTestList(Section1And2Activity.this);

        productsAdapter = new ProductsAdapter(Section1And2Activity.this, allProductsAlways);
        binding.recyclerViewSection1And2.setLayoutManager(new LinearLayoutManager(Section1And2Activity.this));
        binding.recyclerViewSection1And2.setHasFixedSize(true);
        binding.recyclerViewSection1And2.setAdapter(productsAdapter);
    }

    @Override
    public void addEventListeners() {
        binding.chipGroupSection1And2.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                // We shall clear the second chip group selection

                if (checkedIds.isEmpty()){

                    helperSection1And2Activity.setUpBackgroundMask("", binding);

                    productsAdapter.setProductList(allProductsAlways);
                    productsAdapter.notifyDataSetChanged();
                    return;
                }

                // Get the one and only selected filter chip

                int id = checkedIds.get(0);
                Chip selectedChip = group.findViewById(id);
                String text = selectedChip.getText().toString().trim();

                helperSection1And2Activity.setUpBackgroundMask(text, binding);

                List<Product> filteredList;
                if (!checkedIds.isEmpty())
                    filteredList = allProductsAlways
                            .stream()
                            .filter(product -> product.getCategory().equals(text))
                            .collect(Collectors.toList());
                else
                    filteredList = allProductsAlways;

                productsAdapter.setProductList(filteredList);
                productsAdapter.notifyDataSetChanged();
            }
        });

        binding.chipGroupFiltersSection1And2.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                // We shall clear the first chip group selection

                if (checkedIds.isEmpty()){

                    productsAdapter.setProductList(allProductsAlways);
                    productsAdapter.notifyDataSetChanged();

                    return;
                }

                Chip chip = group.findViewById(checkedIds.get(0));
                String text = chip.getText().toString().trim();

                List<Product> filtered = helperSection1And2Activity.findFilteredProducts(text, allProductsAlways);
                productsAdapter.setProductList(filtered);
                productsAdapter.notifyDataSetChanged();
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

                productsAdapter.setProductList(filteredProducts);
                productsAdapter.notifyDataSetChanged();

                return true;
            }
        });

        binding.imageViewIconHeaderLayoutSection1And2.setOnClickListener(view -> {
            helperSection1And2Activity.showStatistics(productsAdapter);
        });
    }

    @Override
    public void additionalThemeChanges() {
        if (ThemeUtils.isNightModeActive(appCompatActivity)){
            binding.imageViewIconSection1And2.setImageResource(R.drawable.ic_stats_white);
            binding.imageViewIconHeaderLayoutSection1And2.setImageResource(R.drawable.ic_calculate_white);
        } else {
            binding.imageViewIconSection1And2.setImageResource(R.drawable.ic_stats_black);
            binding.imageViewIconHeaderLayoutSection1And2.setImageResource(R.drawable.ic_calculate_black);
        }
    }
}