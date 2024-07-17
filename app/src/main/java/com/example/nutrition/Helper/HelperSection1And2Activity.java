package com.example.nutrition.Helper;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrition.Adapters.ProductsAdapter;
import com.example.nutrition.Model.Product;
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

    public HelperSection1And2Activity(Context context){
        this.context = context;
        this.toaster = new Toaster(context);
    }

    public void showStatistics(ProductsAdapter productsAdapter){
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

    public void setUpByPyramidCategories(AppCompatActivity activity, ActivitySection1And2Binding binding){
        ArrayList<String> categories = activity.getIntent().getStringArrayListExtra("categories");
        if (categories == null)
            return;

        // Here we only update the UI since the event listeners are already set in the addEventListeners method
        for (int i=0;i<binding.chipGroupSection1And2.getChildCount();i++){
            Chip chip = (Chip) binding.chipGroupSection1And2.getChildAt(i);
            String text = chip.getText().toString().trim();

            if (categories.contains(text))
                chip.setChecked(true);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUpByFilters(AppCompatActivity activity, ActivitySection1And2Binding binding, ProductsAdapter productsAdapter){
        String text = activity.getIntent().getStringExtra("filter");
        if (text == null)
            return;

        List<Product> sortedList = new ArrayList<>();

        switch (text){
            case "Most Protein":{
                sortedList = productsAdapter.getProductList()
                        .stream()
                        .sorted(Comparator.comparing(Product::getProtein).reversed())
                        .limit(10)
                        .collect(Collectors.toList());
                break;
            } case "Most Calories":{
                sortedList = productsAdapter.getProductList()
                        .stream()
                        .sorted(Comparator.comparing(Product::getCalories).reversed())
                        .limit(10)
                        .collect(Collectors.toList());
                break;
            } case "Least Calories":{
                sortedList = productsAdapter.getProductList()
                        .stream()
                        .sorted(Comparator.comparing(Product::getCalories))
                        .limit(10)
                        .collect(Collectors.toList());
                break;
            } case "Least Sugar":{
                sortedList = productsAdapter.getProductList()
                        .stream()
                        .sorted(Comparator.comparing(Product::getSugar))
                        .limit(10)
                        .collect(Collectors.toList());
                break;
            } case "Most Carbs":{
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
}










