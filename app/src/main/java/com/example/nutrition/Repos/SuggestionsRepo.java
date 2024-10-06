package com.example.nutrition.Repos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.nutrition.Model.Suggestion;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SuggestionsRepo extends ParentRepo implements ISuggestionsRepo{

    private Context context;

    public SuggestionsRepo(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public List<Suggestion> listAll() {
        List<Suggestion> suggestions = new ArrayList<>();
        // So we list the most recently added first
        String query = "SELECT * FROM suggestions ORDER BY id DESC";
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String text = cursor.getString(1);

            suggestions.add(new Suggestion(id, text));
        }

        cursor.close();
        database.close();

        Log.d("Tag", "Read '" + suggestions.size() + "' suggestions");
        return suggestions;
    }

    @Override
    public void add(String suggestion) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = getWritableDatabase();

        contentValues.put("suggestion", suggestion);

        database.insert("suggestions", null, contentValues);
        database.close();

        Log.d("Tag", "Successfully added new suggestion");
    }

    @Override
    public boolean contains(String suggestion) {
        String query = "SELECT * FROM suggestions WHERE suggestion = ?";
        SQLiteDatabase database = getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery(query, new String[]{suggestion});

        return cursor.moveToFirst();
    }

    @Override
    public void delete(int id) {
        String query = "id = ?";

        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();
        try {
            database.delete("suggestions", query, new String[]{String.valueOf(id)});
            database.setTransactionSuccessful();
            Log.d("Tag", "Successfully deleted suggestion with id '" + id + "'");
        } finally {
            database.endTransaction();
            database.close();
        }
    }
}
