package com.charge.btechcomputerscience;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class databasemenu extends SQLiteOpenHelper {
    public databasemenu(@Nullable Context context) {
        super(context, "userdatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,sem TEXT,subject TEXT,unit TEXT,name TEXT,path TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Userdetails");
    }

    public void insertuserdata(String sem, String subject,String unit,String name,String path) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sem", sem);
        contentValues.put("subject", subject);
        contentValues.put("unit", unit);
        contentValues.put("name", name);
        contentValues.put("path", path);
        db.insert("Userdetails", null, contentValues);

    }

    public void updateuserdata(String id, String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task", task);
        Cursor cursor = db.rawQuery("Select * from Userdetails where id=?", new String[]{id});
        if (cursor.getCount() > 0) {
            db.update("Userdetails", contentValues, "id=?", new String[]{id});
        }
    }

    public void deleteuserdata(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from Userdetails where id=?", new String[]{String.valueOf(id)});
        if (cursor.getCount() > 0) {
            db.delete("Userdetails", "id=?", new String[]{String.valueOf(id)});
        }

    }
    public Cursor getdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}