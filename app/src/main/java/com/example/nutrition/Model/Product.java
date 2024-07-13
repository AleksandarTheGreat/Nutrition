package com.example.nutrition.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Product {
    protected String name;
    protected double protein;
    protected double carbs;
    protected double calories;
    protected double sugar;

    private Product(String name, double protein, double carbs, double calories, double sugar) {
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.calories = calories;
        this.sugar = sugar;
    }

    public Product(){}

    public static Product createProduct(String name, double protein, double carbs, double calories, double sugar){
        return new Product(name, protein, carbs, calories, sugar);
    }

    @NonNull
    @Override
    public String toString() {
        return name + " " + protein + " " + carbs + " " + calories + " " + sugar;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (getClass() == null || obj.getClass() == null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        return name.equals(((Product) obj).name);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getProtein() {
        return protein;
    }
    public void setProtein(double protein) {
        this.protein = protein;
    }
    public double getCarbs() {
        return carbs;
    }
    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }
    public double getCalories() {
        return calories;
    }
    public void setCalories(double calories) {
        this.calories = calories;
    }
    public double getSugar() {
        return sugar;
    }
    public void setSugar(double sugar) {
        this.sugar = sugar;
    }
}
