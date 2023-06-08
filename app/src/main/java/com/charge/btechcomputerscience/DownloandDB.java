package com.charge.btechcomputerscience;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DownloandDB extends SQLiteOpenHelper {
    public DownloandDB(@Nullable Context context) {
        super(context, "NotesStore.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create Table DownloadNotes(id TEXT primary key, task TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists DownloadNotes");
    }

    public Boolean insertData(String id, String sem,String subject,String unit,String name,String path){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("sem",sem);
        contentValues.put("subject",subject);
        contentValues.put("unit",unit);
        contentValues.put("name",name);
        contentValues.put("path",path);
        long result = db.insert("DownloadNotes", null, contentValues);
        if(result == -1){
            Log.d("check","not ok");
            return false;
        } else {
            Log.d("check","ok");
            return true;
        }
    }

    public Boolean updateData(String id,String sem, String subject,String unit,String name, String path){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sem", sem);
        contentValues.put("subject", subject);
        contentValues.put("unit", unit);
        contentValues.put("name", name);
        contentValues.put("path", path);
        Cursor cursor = db.rawQuery("Select * from DownloadNotes where id=?", new String[]{id});
        if (cursor.getCount() > 0) {


            long result = db.update("DownloadNotes", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from DownloadNotes where id=?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = db.delete("DownloadNotes", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }

    }
    public Cursor getNotes() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from DownloadNotes", null);
        return cursor;
    }


}
