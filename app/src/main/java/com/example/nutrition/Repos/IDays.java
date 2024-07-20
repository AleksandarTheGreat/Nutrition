package com.example.nutrition.Repos;

import com.example.nutrition.Model.Day;

import java.util.List;

public interface IDays {
    List<Day> listAll();
    void add(Day day);
    Day get(long id);
    void delete(long id);
}
