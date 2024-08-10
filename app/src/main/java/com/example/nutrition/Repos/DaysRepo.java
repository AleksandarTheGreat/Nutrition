package com.example.nutrition.Repos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.nutrition.Model.Day;
import com.example.nutrition.Model.Item;
import com.example.nutrition.Model.Product;

import java.security.Policy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DaysRepo extends ParentRepo implements IDays {

    private static final String tableName = "days";

    public DaysRepo(@Nullable Context context) {
        super(context);
    }

    @Override
    public List<Day> listAll() {
        String query = String.format("SELECT * FROM days d LEFT JOIN items i ON d.id = i.d_id");
        SQLiteDatabase database = getReadableDatabase();

        Map<Day, List<Item>> map = new HashMap<>();
        List<Day> list = new ArrayList<>();

        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                long id = cursor.getLong(0);
                String title = cursor.getString(1);
                String dateTime = cursor.getString(2);
                LocalDate dateCreated = LocalDate.parse(dateTime);

                long i_id = cursor.getLong(3);
                String ingredient = cursor.getString(4);
                float protein = cursor.getFloat(5);
                float carbohydrates = cursor.getFloat(6);
                float calories = cursor.getFloat(7);
                float sugar = cursor.getFloat(8);
                long d_id = cursor.getLong(9);

                Day day = new Day(id, title, dateCreated);
                Item item = new Item(i_id, ingredient, protein, carbohydrates, calories, sugar, d_id);

                if (!map.containsKey(day))
                    map.put(day, new ArrayList<>());

                if (i_id != 0)
                    map.get(day).add(item);
            }
        }

        cursor.close();
        database.close();

        map.forEach((day, value) -> {
            day.setItemList(value);
            list.add(day);
        });

        Log.d("Tag", "Listed '" + list.size() + "' days");
        return list;
    }

    public List<Day> listAllSorted(){
        return listAll().stream()
                .sorted(Comparator.comparing(Day::getId).reversed())
                .collect(Collectors.toList());
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
        String queryFromItems = "d_id = ?";

        SQLiteDatabase database = getWritableDatabase();

        database.beginTransaction();
        try {
            database.delete("items", queryFromItems, new String[]{String.valueOf(id)});
            database.delete("days", query, new String[]{String.valueOf(id)});

            database.setTransactionSuccessful();
            Log.d("Tag", "Successfully deleted day and items with id " + id);
        } finally {
            database.endTransaction();
            database.close();
        }
    }
}
