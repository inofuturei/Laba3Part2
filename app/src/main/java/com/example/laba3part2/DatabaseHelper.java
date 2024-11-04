package com.example.laba3part2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_GROUPMATES = "groupmates";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_MIDDLE_NAME = "middle_name";
    private static final String COLUMN_TIMESTAMP = "timestamp";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_GROUPMATES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LAST_NAME + " TEXT, " +
                COLUMN_FIRST_NAME + " TEXT, " +
                COLUMN_MIDDLE_NAME + " TEXT, " +
                COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(CREATE_TABLE);
        insertInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPMATES);
        onCreate(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_GROUPMATES);

        for (int i = 1; i <= 5; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_LAST_NAME, "Фамилия" + i);
            values.put(COLUMN_FIRST_NAME, "Имя" + i);
            values.put(COLUMN_MIDDLE_NAME, "Отчество" + i);
            db.insert(TABLE_GROUPMATES, null, values);
        }
    }

    public Cursor getAllGroupmates() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_GROUPMATES, null);
    }

    public void addGroupmate(String lastName, String firstName, String middleName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LAST_NAME, lastName);
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_MIDDLE_NAME, middleName);
        db.insert(TABLE_GROUPMATES, null, values);
        db.close();
    }


    public void updateLastGroupmate(String lastName, String firstName, String middleName) {
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ID + " FROM " + TABLE_GROUPMATES + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1", null);

        if (cursor.moveToFirst()) {
            int lastId = cursor.getInt(0);

            ContentValues values = new ContentValues();
            values.put(COLUMN_LAST_NAME, lastName);
            values.put(COLUMN_FIRST_NAME, firstName);
            values.put(COLUMN_MIDDLE_NAME, middleName);

            db.update(TABLE_GROUPMATES, values, COLUMN_ID + "=?", new String[]{String.valueOf(lastId)});
        }

        cursor.close();
        db.close();
    }
}
