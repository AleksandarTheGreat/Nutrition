package com.example.nutrition.Repos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ParentRepo extends SQLiteOpenHelper {

    /**
     * In version 1 we had the 2 tables
     * In version 2 we created the suggestions table
     * So basically we increment a version number to call the onUpgrade() method
     */

    private final Context context;
    private static final String NAME = "daysAndItems.db";
    private static final int VERSION = 2;

    public ParentRepo(@Nullable Context context) {
        super(context, NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDaysTable(db);
        createItemsTable(db);
        createSuggestionsTable(db);

        Log.d("Tag", "Tables 'days', 'items' and 'suggestions' created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createSuggestionsTable(SQLiteDatabase db){
        String createSuggestionsTable = "CREATE TABLE suggestions" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "suggestion TEXT" +
                ");";

        db.execSQL(createSuggestionsTable);
        Log.d("Tag", "Created table 'suggestions'");
    }

    private void createDaysTable(SQLiteDatabase db){
        String createDaysTable = "CREATE TABLE days" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "createdAt TEXT" +
                ");";

        db.execSQL(createDaysTable);
        Log.d("Tag","Created table 'days'");
    }

    private void createItemsTable(SQLiteDatabase db){
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

        db.execSQL(createItemsTable);
        Log.d("Tag","Created table 'items'");
    }
}
