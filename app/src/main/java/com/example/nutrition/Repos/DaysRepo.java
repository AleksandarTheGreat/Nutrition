package com.example.nutrition.Repos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.nutrition.Model.Day;
import com.example.nutrition.Model.Product;

import java.security.Policy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class DaysRepo extends ParentRepo implements IDays {

    public DaysRepo(@Nullable Context context) {
        super(context);
    }

    @Override
    public List<Day> listAll() {
        String query = String.format("SELECT * FROM days d LEFT JOIN products p ON d.id = p.d_id");
        SQLiteDatabase database = getReadableDatabase();

        Map<Day, List<Product>> map = new HashMap<>();
        List<Day> list = new ArrayList<>();

        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                long id = cursor.getLong(0);
                String title = cursor.getString(1);
                String dateTime = cursor.getString(2);
                LocalDate dateCreated = LocalDate.parse(dateTime);

                long p_id = cursor.getLong(3);
                String name = cursor.getString(4);
                String category = cursor.getString(5);
                double protein = cursor.getDouble(6);
                double carbs = cursor.getDouble(7);
                double calories = cursor.getDouble(8);
                double sugar = cursor.getDouble(9);
                long d_id = cursor.getLong(10);

                Day day = new Day(id, title, dateCreated);
                Product product = new Product(p_id, name, category, protein, carbs, calories, sugar, d_id);

                if (!map.containsKey(day))
                    map.put(day, new ArrayList<>());

                if (p_id != 0)
                    map.get(day).add(product);
            }
        }

        cursor.close();
        database.close();

        map.forEach((day, value) -> {
            day.setProductList(value);
            list.add(day);
        });

        Log.d("Tag", "Listed '" + list.size() + "' days");
        return list;
    }

    @Override
    public void add(Day day) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", day.getTitle());
        contentValues.put("createdAt", day.getCreatedAt().toString());

        database.insert("days", null, contentValues);
        database.close();

        Log.d("Tag", "Added a new day successfully");
    }

    @Override
    public Day get(long id) {

        return null;
    }

    @Override
    public void delete(long id) {
        String query = "id = ?";
        String queryFromProducts = "d_id = ?";

        SQLiteDatabase database = getWritableDatabase();

        database.beginTransaction();
        try {
            database.delete("products", queryFromProducts, new String[]{String.valueOf(id)});
            database.delete("days", query, new String[]{String.valueOf(id)});

            database.setTransactionSuccessful();
            Log.d("Tag", "Successfully deleted day and products with id " + id);
        } finally {
            database.endTransaction();
            database.close();
        }
    }
}
