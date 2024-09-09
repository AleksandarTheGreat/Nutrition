package com.example.nutrition.Helper;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.nutrition.Adapters.ProductsAdapter;
import com.example.nutrition.Model.Product;
import com.example.nutrition.R;
import com.example.nutrition.databinding.ActivitySection1And2Binding;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class HelperSection1And2Activity {
    private Context context;
    private Toaster toaster;

    public HelperSection1And2Activity(Context context) {
        this.context = context;
        this.toaster = new Toaster(context);
    }

    public void showStatistics(ProductsAdapter productsAdapter) {
        DoubleSummaryStatistics statisticsProtein = productsAdapter.getProductList().stream().mapToDouble(Product::getProtein).summaryStatistics();
        DoubleSummaryStatistics statisticsCarbs = productsAdapter.getProductList().stream().mapToDouble(Product::getCarbs).summaryStatistics();
        DoubleSummaryStatistics statisticsCalories = productsAdapter.getProductList().stream().mapToDouble(Product::getCalories).summaryStatistics();
        DoubleSummaryStatistics statisticsSugar = productsAdapter.getProductList().stream().mapToDouble(Product::getSugar).summaryStatistics();

        StringBuilder builder = new StringBuilder();
        builder.append("Max protein: ").append(statisticsProtein.getMax()).append("\n");
        builder.append("Min protein: ").append(statisticsProtein.getMin()).append("\n");
        builder.append("Average protein: ").append(statisticsProtein.getAverage()).append("\n\n");

        builder.append("Max carbs: ").append(statisticsCarbs.getMax()).append("\n");
        builder.append("Min carbs: ").append(statisticsCarbs.getMin()).append("\n");
        builder.append("Average carbs: ").append(statisticsCarbs.getAverage()).append("\n\n");

        builder.append("Max calories: ").append(statisticsCalories.getMax()).append("\n");
        builder.append("Min calories: ").append(statisticsCalories.getMin()).append("\n");
        builder.append("Average calories: ").append(statisticsCalories.getAverage()).append("\n\n");

        builder.append("Max sugar: ").append(statisticsSugar.getMax()).append("\n");
        builder.append("Min sugar: ").append(statisticsSugar.getMin()).append("\n");
        builder.append("Average sugar: ").append(statisticsSugar.getAverage()).append("\n\n");

        toaster.alert("Stats:", builder.toString());
    }

    public void setUpByPyramidCategories(AppCompatActivity activity, ActivitySection1And2Binding binding) {
        String category = activity.getIntent().getStringExtra("category");
        if (category == null) {
            setUpBackgroundMask("", binding);
            return;
        }

        // Here we only update the UI since the event listeners are already set in the addEventListeners method
        for (int i = 0; i < binding.chipGroupSection1And2.getChildCount(); i++) {
            Chip chip = (Chip) binding.chipGroupSection1And2.getChildAt(i);
            String text = chip.getText().toString().trim();

            setUpBackgroundMask(text, binding);

            if (category.equals(text)) {
                chip.setChecked(true);
                break;
            }
        }
    }

    public void setUpBackgroundMask(String category, ActivitySection1And2Binding binding) {
        switch (category) {
            case "Grains": {
                binding.mainLayoutSection1And2Activity.setBackground(ContextCompat.getDrawable(context, R.drawable.mask_grains));
                break;
            }
            case "Vegetables": {
                binding.mainLayoutSection1And2Activity.setBackground(ContextCompat.getDrawable(context, R.drawable.mask_vegetables));
                break;
            }
            case "Fruits": {
                binding.mainLayoutSection1And2Activity.setBackground(ContextCompat.getDrawable(context, R.drawable.mask_fruit));
                break;
            }
            case "Meat and Proteins": {
                binding.mainLayoutSection1And2Activity.setBackground(ContextCompat.getDrawable(context, R.drawable.mask_meat));
                break;
            }
            case "Dairy": {
                binding.mainLayoutSection1And2Activity.setBackground(ContextCompat.getDrawable(context, R.drawable.mask_dairy));
                break;
            }
            case "Fats, Oils, and Sweets": {
                binding.mainLayoutSection1And2Activity.setBackground(ContextCompat.getDrawable(context, R.drawable.mask_fats));
                break;
            }
            case "Fast Food": {
                binding.mainLayoutSection1And2Activity.setBackground(ContextCompat.getDrawable(context, R.drawable.mask_fast_food));
                break;
            }
            default: {
                binding.mainLayoutSection1And2Activity.setBackground(ContextCompat.getDrawable(context, R.drawable.mask_default));
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUpByFilters(AppCompatActivity activity, ProductsAdapter productsAdapter) {
        String text = activity.getIntent().getStringExtra("filter");
        if (text == null)
            return;

        List<Product> sortedList = new ArrayList<>();

        switch (text) {
            case "Most Protein": {
                sortedList = productsAdapter.getProductList()
                        .stream()
                        .sorted(Comparator.comparing(Product::getProtein).reversed())
                        .limit(10)
                        .collect(Collectors.toList());
                break;
            }
            case "Most Calories": {
                sortedList = productsAdapter.getProductList()
                        .stream()
                        .sorted(Comparator.comparing(Product::getCalories).reversed())
                        .limit(10)
                        .collect(Collectors.toList());
                break;
            }
            case "Least Calories": {
                sortedList = productsAdapter.getProductList()
                        .stream()
                        .sorted(Comparator.comparing(Product::getCalories))
                        .limit(10)
                        .collect(Collectors.toList());
                break;
            }
            case "Least Sugar": {
                sortedList = productsAdapter.getProductList()
                        .stream()
                        .sorted(Comparator.comparing(Product::getSugar))
                        .limit(10)
                        .collect(Collectors.toList());
                break;
            }
            case "Most Carbs": {
                sortedList = productsAdapter.getProductList()
                        .stream()
                        .sorted(Comparator.comparing(Product::getCarbs).reversed())
                        .limit(10)
                        .collect(Collectors.toList());
                break;
            }
        }

        productsAdapter.setProductList(sortedList);
        productsAdapter.notifyDataSetChanged();
    }

    public List<Product> findFilteredProducts(String text, List<Product> allProductsAlways) {
        List<Product> products = new ArrayList<>();
        switch (text) {
            case "Most Protein": {
                products = allProductsAlways.stream()
                        .sorted(Comparator.comparing(Product::getProtein).reversed())
                        .limit(20)
                        .collect(Collectors.toList());
                break;
            }
            case "Least Protein": {
                products = allProductsAlways.stream()
                        .sorted(Comparator.comparing(Product::getProtein))
                        .limit(20)
                        .collect(Collectors.toList());
                break;
            }
            case "Most Calories": {
                products = allProductsAlways.stream()
                        .sorted(Comparator.comparing(Product::getCalories).reversed())
                        .limit(20)
                        .collect(Collectors.toList());
                break;
            }
            case "Least Calories": {
                products = allProductsAlways.stream()
                        .sorted(Comparator.comparing(Product::getCalories))
                        .limit(20)
                        .collect(Collectors.toList());
                break;
            }
            case "Most Carbs": {
                products = allProductsAlways.stream()
                        .sorted(Comparator.comparing(Product::getCarbs).reversed())
                        .limit(20)
                        .collect(Collectors.toList());
                break;
            }
            case "Least Carbs": {
                products = allProductsAlways.stream()
                        .sorted(Comparator.comparing(Product::getCarbs))
                        .limit(20)
                        .collect(Collectors.toList());
                break;
            }
            case "Most Sugar": {
                products = allProductsAlways.stream()
                        .sorted(Comparator.comparing(Product::getSugar).reversed())
                        .limit(20)
                        .collect(Collectors.toList());
                break;
            }
            case "Least Sugar": {
                products = allProductsAlways.stream()
                        .sorted(Comparator.comparing(Product::getSugar))
                        .limit(20)
                        .collect(Collectors.toList());
                break;
            }
            default: {
                products = allProductsAlways;
                break;
            }
        }
        return products;
    }

    public List<Product> filterByCategory(String text, List<Product> allProductsAlways) {
        List<Product> filteredList;
        filteredList = allProductsAlways
                .stream()
                .filter(product -> product.getCategory().equals(text))
                .collect(Collectors.toList());

        return filteredList;
    }

}










