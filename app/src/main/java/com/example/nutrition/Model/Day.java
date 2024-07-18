package com.example.nutrition.Model;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Day {
    private String title;
    private LocalDate createdAt;

    private Day(String title, LocalDate createdAt){
        this.title = title;
        this.createdAt = createdAt;
    }

    public static Day createANewDay(String title, LocalDate createdAt){
        return new Day(title, createdAt);
    }

    public String getDateIntoStringFormat(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return createdAt.format(dtf);
        }
        return createdAt.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
