package com.example.lesson38sqlitetransaction;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// класс для работы с БД
class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String LOG_TAG = "myLogs";
        Log.d(LOG_TAG, "--- onCreate database ---");

        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "val text"
                + ");");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
