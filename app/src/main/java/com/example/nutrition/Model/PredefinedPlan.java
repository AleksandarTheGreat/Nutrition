package com.example.nutrition.Model;

public class PredefinedPlan {
    private String title;
    private String status;

    private int proteins;
    private int calories;
    private int carbohydrates;
    private int sugars;

    public PredefinedPlan(String title, String status, int proteins, int calories, int carbohydrates, int sugars) {
        this.title = title;
        this.status = status;
        this.proteins = proteins;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.sugars = sugars;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public int getSugars() {
        return sugars;
    }

    public void setSugars(int sugars) {
        this.sugars = sugars;
    }
}
