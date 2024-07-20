package com.example.nutrition.Model;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day {
    // long id, List<Products> products
    private long id;
    private String title;
    private LocalDate createdAt;
    private List<Product> productList;

    public Day(String title, LocalDate createdAt){
        this.title = title;
        this.createdAt = createdAt;
        this.productList = new ArrayList<>();
    }

    public Day(long id, String title, LocalDate createdAt){
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.productList = new ArrayList<>();
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

    @Override
    public boolean equals(@Nullable Object obj) {
        if (getClass() == null || obj.getClass() == null)
            return false;
        else if (getClass() != obj.getClass())
            return false;

        return id == ((Day) obj).id && title.equals(((Day) obj).title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
    public long getId() {
        return id;
    }
    public List<Product> getProductList() {
        return productList;
    }
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
