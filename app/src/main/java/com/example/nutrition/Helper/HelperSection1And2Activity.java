package com.example.nutrition.Helper;

import android.content.Context;

import com.example.nutrition.Adapters.ItemsAdapter;
import com.example.nutrition.Model.Product;

import java.util.DoubleSummaryStatistics;

public class HelperSection1And2Activity {
    private Context context;
    private Toaster toaster;

    public HelperSection1And2Activity(Context context){
        this.context = context;
        this.toaster = new Toaster(context);
    }

    public void showStatistics(ItemsAdapter itemsAdapter){
        DoubleSummaryStatistics statisticsProtein = itemsAdapter.getProductList().stream().mapToDouble(Product::getProtein).summaryStatistics();
        DoubleSummaryStatistics statisticsCarbs = itemsAdapter.getProductList().stream().mapToDouble(Product::getCarbs).summaryStatistics();
        DoubleSummaryStatistics statisticsCalories = itemsAdapter.getProductList().stream().mapToDouble(Product::getCalories).summaryStatistics();
        DoubleSummaryStatistics statisticsSugar = itemsAdapter.getProductList().stream().mapToDouble(Product::getSugar).summaryStatistics();

        StringBuilder builder = new StringBuilder();
        builder.append("Max protein: ").append(statisticsProtein.getMax()).append("\n");
        builder.append("Min protein: ").append(statisticsProtein.getMin()).append("\n");
        builder.append("Average protein: ").append(statisticsProtein.getAverage()).append("\n\n");

        builder.append("Max carbs: ").append(statisticsCarbs.getMax()).append("\n");
        builder.append("Min protein: ").append(statisticsCarbs.getMin()).append("\n");
        builder.append("Average protein: ").append(statisticsCarbs.getAverage()).append("\n\n");

        builder.append("Max calories: ").append(statisticsCalories.getMax()).append("\n");
        builder.append("Min protein: ").append(statisticsCalories.getMin()).append("\n");
        builder.append("Average protein: ").append(statisticsCalories.getAverage()).append("\n\n");

        builder.append("Max sugar: ").append(statisticsSugar.getMax()).append("\n");
        builder.append("Min protein: ").append(statisticsSugar.getMin()).append("\n");
        builder.append("Average protein: ").append(statisticsSugar.getAverage()).append("\n\n");

        toaster.alert("Stats:", builder.toString());
    }
}
