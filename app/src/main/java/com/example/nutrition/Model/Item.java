package com.example.nutrition.Model;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Item {
    private long id;
    private String ingredient;
    private float protein;
    private float carbohydrates;
    private float calories;
    private float sugar;
    private long d_id;

    public Item(long id, String ingredient, float protein, float carbohydrates, float calories, float sugar, long d_id) {
        this.id = id;
        this.ingredient = ingredient;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.sugar = sugar;
        this.d_id = d_id;
    }

    public Item(String ingredient, float protein, float carbohydrates, float calories, float sugar, long d_id) {
        this.ingredient = ingredient;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.sugar = sugar;
        this.d_id = d_id;
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public String toString() {
        return String.format("Ingredient: %s\n\nProtein: %.2f\nCarbohydrates: %.2f\nCalories: %.2f\nSugar: %.2f\n",
                ingredient, protein, carbohydrates, calories, sugar);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (getClass() == null || obj.getClass() == null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        return id == ((Item) obj).id;
    }

    public long getId() {
        return id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getSugar() {
        return sugar;
    }

    public void setSugar(float sugar) {
        this.sugar = sugar;
    }

    public long getD_id() {
        return d_id;
    }

    public void setD_id(long d_id) {
        this.d_id = d_id;
    }
}
