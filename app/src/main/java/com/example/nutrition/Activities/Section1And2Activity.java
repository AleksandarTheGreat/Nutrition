package com.example.nutrition.Activities;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
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
import com.google.android.material.color.MaterialColors;

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
    }

    @Override
    public void instantiateObjects() {
        binding = ActivitySection1And2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appCompatActivity = this;

        // Removes underline on the searchView
        View searchPlate = binding.searchViewSection1And2.findViewById(androidx.appcompat.R.id.search_plate);
        if (searchPlate != null) searchPlate.setBackgroundColor(Color.TRANSPARENT);

        toaster = new Toaster(Section1And2Activity.this);
        helperSection1And2Activity = new HelperSection1And2Activity(Section1And2Activity.this);

        // Set up the adapter and and load the list on a new thread
        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(() -> {

            allProductsAlways = ContentLoader.loadPopularFoodsList();
            productsAdapter = new ProductsAdapter(Section1And2Activity.this, appCompatActivity, allProductsAlways);

            handler.post(() -> {
                binding.recyclerViewSection1And2.setLayoutManager(new LinearLayoutManager(Section1And2Activity.this));
                binding.recyclerViewSection1And2.setHasFixedSize(true);
                binding.recyclerViewSection1And2.setAdapter(productsAdapter);

                // Adding the event listeners here
                // because the allProductsAlways is not loaded on the main thread yet
                // and causes some null pointer exceptions
                addEventListeners();
                // The first shall be called if we come here via the List button
                helperSection1And2Activity.setUpByPyramidCategories(this, binding);
            });

        }).start();
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

                // Clear the second chip group, all selections
                binding.chipGroupFiltersSection1And2.clearCheck();

                // Get the one and only selected filter chip
                int id = checkedIds.get(0);
                Chip selectedChip = group.findViewById(id);
                String text = selectedChip.getText().toString().trim();

                helperSection1And2Activity.setUpBackgroundMask(text, binding);

                List<Product> filteredList = helperSection1And2Activity.filterByCategory(text, allProductsAlways);
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

                // Clear the first chip group, all selections
                binding.chipGroupSection1And2.clearCheck();

                Chip chip = group.findViewById(checkedIds.get(0));
                String text = chip.getText().toString().trim();

                List<Product> filtered = helperSection1And2Activity.findFilteredProducts(text, allProductsAlways);
                productsAdapter.setProductList(filtered);
                productsAdapter.notifyDataSetChanged();
            }
        });

        binding.searchViewSection1And2.setOnSearchClickListener(view -> {
            binding.chipGroupSection1And2.clearCheck();
            binding.chipGroupFiltersSection1And2.clearCheck();
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

        binding.searchViewSection1And2.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    toaster.text("Has focus");
                } else {
                    toaster.text("No focus");
                }
            }
        });
    }

    @Override
    public void additionalThemeChanges() {
        if (ThemeUtils.isNightModeActive(appCompatActivity)){
            int color = ContextCompat.getColor(Section1And2Activity.this, R.color.colorText60Light);
            int colorWhite = ContextCompat.getColor(Section1And2Activity.this, R.color.white);
            int colorPrimary = MaterialColors.getColor(this, android.R.attr.colorPrimary, Color.WHITE);

            binding.imageViewIconSection1And2.setImageResource(R.drawable.ic_stats_white);
            binding.textViewSubTitleSection1And2.setTextColor(colorWhite);
        } else {
            int color = ContextCompat.getColor(Section1And2Activity.this, R.color.colorText60Dark);
            int colorBlack = ContextCompat.getColor(Section1And2Activity.this, R.color.black);
            int colorPrimary = MaterialColors.getColor(this, android.R.attr.colorPrimary, Color.BLACK);

            binding.imageViewIconSection1And2.setImageResource(R.drawable.ic_stats_black);
            binding.textViewSubTitleSection1And2.setTextColor(colorBlack);
        }
    }
}