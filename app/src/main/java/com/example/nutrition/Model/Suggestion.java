package com.example.nutrition.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Suggestion {
    private int id;
    private String text;

    public Suggestion(int id, String text) {
        this.id = id;
        this.text = text;
    }

    @NonNull
    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (getClass() == null || obj.getClass() == null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        return id == ((Suggestion) obj).id;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
