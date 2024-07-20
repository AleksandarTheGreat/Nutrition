package com.example.nutrition.Repos;

import com.example.nutrition.Model.Product;

import java.util.List;

public interface IProducts {
    List<Product> listAll();
    List<Product> listAll(long d_id);
    void add(Product product, long d_id);
    Product get(long id);
    void delete(long id);
}
