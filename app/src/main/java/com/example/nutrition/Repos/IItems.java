package com.example.nutrition.Repos;

import com.example.nutrition.Model.Item;
import com.example.nutrition.Model.Product;

import java.util.List;

public interface IItems {
    List<Item> listAll();
    List<Item> listAll(long d_id);
    void add(Item item, long d_id);
    Item get(long id);
    void delete(long id);
}
