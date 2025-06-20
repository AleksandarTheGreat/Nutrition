package com.example.nutrition.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Macronutrient {

    private String title;
    private String explanation;
    private int imageResource;
    private int colorResource;

    public Macronutrient(String title, String explanation, int imageResource, int colorResource) {
        this.title = title;
        this.explanation = explanation;
        this.imageResource = imageResource;
        this.colorResource = colorResource;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (getClass() == null || obj.getClass() == null)
            return false;
        else if (getClass() != obj.getClass())
            return false;

        return title.equals(((Macronutrient) obj).title) && explanation.equals(((Macronutrient) obj).explanation)
                && imageResource == ((Macronutrient) obj).imageResource && colorResource == ((Macronutrient) obj).colorResource;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " " + explanation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getColorResource() {
        return colorResource;
    }

    public void setColorResource(int colorResource) {
        this.colorResource = colorResource;
    }
}
