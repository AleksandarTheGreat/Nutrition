package com.example.nutrition.Repos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.nutrition.Model.Item;
import com.example.nutrition.Model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemsRepo extends ParentRepo implements IItems {

    private static final String tableName = "items";

    public ItemsRepo(@Nullable Context context) {
        super(context);
    }

    @Override
    public List<Item> listAll() {
        return Collections.emptyList();
    }

    @Override
    public List<Item> listAll(long d_id) {
        String query = "SELECT * FROM items WHERE d_id = " + d_id;
        SQLiteDatabase database = getReadableDatabase();

        List<Item> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()){

            long i_id = cursor.getLong(0);
            String ingredient = cursor.getString(1);
            float protein = cursor.getFloat(2);
            float carbs = cursor.getFloat(3);
            float calories = cursor.getFloat(4);
            float sugar = cursor.getFloat(5);
            long day_id = cursor.getLong(6);

            Item item = new Item(i_id, ingredient, protein, carbs, calories, sugar, day_id);
            list.add(item);
        }

        cursor.close();
        database.close();

        Log.d("Tag", "Read '" + list.size() + "' items for day '" + d_id + "'");

        return list;
    }

    @Override
    public void add(Item item, long d_id) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ingredient", item.getIngredient());
        contentValues.put("protein", item.getProtein());
        contentValues.put("carbohydrates", item.getCarbohydrates());
        contentValues.put("calories", item.getCalories());
        contentValues.put("sugar", item.getSugar());
        contentValues.put("d_id", d_id);

        database.insert(tableName, null, contentValues);
        database.close();

        Log.d("Tag", "Successfully added item");
    }

    @Override
    public Item get(long id) {
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
            Log.d("Tag", "Successfully deleted item with id " + id);
        } finally {
            database.endTransaction();
            database.close();
        }
    }
}
