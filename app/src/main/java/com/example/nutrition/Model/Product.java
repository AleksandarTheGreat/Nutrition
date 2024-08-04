package com.example.nutrition.Model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Product {
    // id, day_id
    protected long id;
    protected String imageUri;
    protected String name;
    protected String category;
    protected double protein;
    protected double carbs;
    protected double calories;
    protected double sugar;
    protected long d_id;

    private Product(String name, String category, double protein, double carbs, double calories, double sugar) {
        this.name = name;
        this.category = category;
        this.protein = protein;
        this.carbs = carbs;
        this.calories = calories;
        this.sugar = sugar;
    }

    private Product(String name, String category, double protein, double carbs, double calories, double sugar, String imageUri) {
        this.name = name;
        this.category = category;
        this.protein = protein;
        this.carbs = carbs;
        this.calories = calories;
        this.sugar = sugar;
        this.imageUri = imageUri;
    }

    public Product(long id, String name, String category, double protein, double carbs, double calories, double sugar, long d_id) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.protein = protein;
        this.carbs = carbs;
        this.calories = calories;
        this.sugar = sugar;
        this.d_id = d_id;
    }

    public Product(){}

    public static Product createProduct(String name, String category, double protein, double carbs, double calories, double sugar){
        return new Product(name, category, protein, carbs, calories, sugar);
    }

    public static Product createProduct(String name, String category, double protein, double carbs, double calories, double sugar, String imageUri){
        return new Product(name, category, protein, carbs, calories, sugar, imageUri);
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

    public String getImageUri() {
        return imageUri;
    }
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
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








