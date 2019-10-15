package com.example.lesson38sqlitetransaction;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    DBHelper dbh;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "--- onCreate Activity ---");
        dbh = new DBHelper(this);
        myActions();
    }

    private void myActions() {
//        try {
//            db = dbh.getWritableDatabase();
//            delete(db);
//            db.beginTransaction();
//            insert(db, "val1");
//
//            Log.d(LOG_TAG, "create DBHelper");
//            DBHelper dbh2 = new DBHelper(this);
//            Log.d(LOG_TAG, "get db");
//            SQLiteDatabase db2 = dbh2.getWritableDatabase();
//            read(db2);
//            dbh2.close();
//
//            db.setTransactionSuccessful();
//            db.endTransaction();
//            read(db);
//            dbh.close();
//        } catch (Exception e) {
//            Log.d(LOG_TAG, e.getClass() + " error: " + e.getMessage());
//        }
        db = dbh.getWritableDatabase();
        SQLiteDatabase db2 = dbh.getWritableDatabase();
        Log.d(LOG_TAG, "db = db2 - " + db.equals(db2));
        Log.d(LOG_TAG, "db open - " + db.isOpen() + ", db2 open - " + db2.isOpen());
        db2.close();
        Log.d(LOG_TAG, "db open - " + db.isOpen() + ", db2 open - " + db2.isOpen());

        // рекомендуемая форма для использования транзакций
        db.beginTransaction();
        try {
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private void read(SQLiteDatabase db) {
        Log.d(LOG_TAG, "Read table " + "mytable");
        Cursor cursor = db.query("mytable", null, null, null, null, null, null);
        if (cursor != null) {
            Log.d(LOG_TAG, "Records count = " + cursor.getCount());
            if (cursor.moveToFirst()) {
                do {
                    Log.d(LOG_TAG, cursor.getString(cursor.getColumnIndex("val")));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    private void delete(SQLiteDatabase db) {
        Log.d(LOG_TAG, "Delete all from table " + "mytable");
        db.delete("mytable", null, null);
    }

    private void insert(SQLiteDatabase db, String value) {
        Log.d(LOG_TAG, "Insert in table " + "mytable" + " value = " + value);
        ContentValues cv = new ContentValues();
        cv.put("val", value);
        db.insert("mytable", null, cv);
    }
}
