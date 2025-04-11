package com.example.nutrition.Model;

import androidx.annotation.NonNull;

public class CustomMacros {

    protected int proteins;
    protected int calories;
    protected int carbs;
    protected int sugars;

    public CustomMacros(int proteins, int calories, int carbs, int sugars) {
        this.proteins = proteins;
        this.calories = calories;
        this.carbs = carbs;
        this.sugars = sugars;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Proteins: %d\nCalories: %d\nCarbs: %d\nSugars: %d\n", proteins, calories, carbs, sugars);
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getSugars() {
        return sugars;
    }

    public void setSugars(int sugars) {
        this.sugars = sugars;
    }
}
