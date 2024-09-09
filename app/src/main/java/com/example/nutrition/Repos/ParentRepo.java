package com.example.nutrition.Repos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ParentRepo extends SQLiteOpenHelper {

    private final Context context;
    private static final String NAME = "daysAndItems.db";
    private static final int VERSION = 1;

    public ParentRepo(@Nullable Context context) {
        super(context, NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDaysTable = "CREATE TABLE days" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "createdAt TEXT" +
                ");";

        String createItemsTable = "CREATE TABLE items" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ingredient TEXT," +
                "protein REAL," +
                "carbohydrates REAL," +
                "calories REAL," +
                "sugar REAL," +
                "d_id INTEGER," +
                "FOREIGN KEY(d_id) REFERENCES days(id)" +
                ");";

        String createSuggestionsTable;

        db.execSQL(createDaysTable);
        db.execSQL(createItemsTable);
        Log.d("Tag", "Tables 'days' and 'items' created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
