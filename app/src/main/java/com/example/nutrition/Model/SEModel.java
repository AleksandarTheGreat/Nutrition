package com.example.nutrition.Model;

import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

public class SEModel {
    private String title;
    private String description;
    private List<String> examples;
    private int imageResource;

    public SEModel(String title, String description, String [] array, int imageResource){
        this.title = title;
        this.description = description;
        this.examples = Arrays.asList(array);
        this.imageResource = imageResource;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (getClass() == null || obj.getClass() == null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        return title.equals(((SEModel) obj).title)
                && description.equals(((SEModel) obj).description);
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }
}
