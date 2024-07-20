package com.example.nutrition.Repos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.nutrition.Model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsRepo extends ParentRepo implements IProducts{

    private static final String tableName = "products";

    public ProductsRepo(@Nullable Context context) {
        super(context);
    }

    @Override
    public List<Product> listAll() {
        return Collections.emptyList();
    }

    @Override
    public List<Product> listAll(long d_id) {
        String query = "SELECT * FROM products WHERE d_id = " + d_id;
        SQLiteDatabase database = getReadableDatabase();

        List<Product> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()){
            long p_id = cursor.getLong(0);
            String name = cursor.getString(1);
            String category = cursor.getString(2);
            double protein = cursor.getDouble(3);
            double carbs = cursor.getDouble(4);
            double calories = cursor.getDouble(5);
            double sugar = cursor.getDouble(6);
            long day_id = cursor.getLong(7);

            Product product = new Product(p_id, name, category, protein, carbs, calories, sugar, day_id);
            list.add(product);
        }

        cursor.close();
        database.close();

        Log.d("Tag", "Read '" + list.size() + "' products for day '" + d_id + "'");

        return list;
    }

    @Override
    public void add(Product product, long d_id) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", product.getName());
        contentValues.put("category", product.getCategory());
        contentValues.put("protein", product.getProtein());
        contentValues.put("carbs", product.getCarbs());
        contentValues.put("calories", product.getCalories());
        contentValues.put("sugar", product.getSugar());
        contentValues.put("d_id", d_id);

        database.insert(tableName, null, contentValues);
        database.close();

        Log.d("Tag", "Successfully added product");
    }

    @Override
    public Product get(long id) {
        return null;
    }

    @Override
    public void delete(long id) {
        String query = "id = ?";

        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();
        try {
            database.delete(tableName, query, new String[]{String.valueOf(id)});
            database.setTransactionSuccessful();
            Log.d("Tag", "Successfully deleted product with id " + id);
        } finally {
            database.endTransaction();
            database.close();
        }

    }
}
