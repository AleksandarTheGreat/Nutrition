package com.example.nutrition.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.stream.Stream;

public class QModel {
    private String quote;
    private String author;

    public QModel(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("'%s' - %s", quote, author);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (getClass() == null || obj.getClass() == null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        return quote.equals(((QModel) obj).quote)
                && author.equals(((QModel) obj).author);
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
