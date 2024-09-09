package com.example.nutrition.Repos;

import com.example.nutrition.Model.Suggestion;

import java.util.List;

public interface ISuggestionsRepo {
    List<Suggestion> listAll();
    void add(String suggestion);
    boolean contains(String suggestion);
    void delete(int id);
}
