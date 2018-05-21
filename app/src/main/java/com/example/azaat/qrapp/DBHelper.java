package com.example.azaat.qrapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Azaat on 27.03.2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "mydatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table mytable (" +
                "id integer primary key autoincrement ," +
                "name text," +
                "title text," +
                "song_id integer," +
                "song_loc integer)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("song_id",142355);
        contentValues.put("name","Pravda");
        contentValues.put("title","pravda-1");
        contentValues.put("song_loc",R.raw.muz1);
        sqLiteDatabase.insert("mytable",null,contentValues);
        contentValues.put("song_id",142355);
        contentValues.put("name","Pravda");
        contentValues.put("title","pravda-2");
        contentValues.put("song_loc",R.raw.muz1);
        sqLiteDatabase.insert("mytable",null,contentValues);
        contentValues.put("song_id",142355);
        contentValues.put("name","Pravda");
        contentValues.put("title","pravda-3");
        contentValues.put("song_loc",R.raw.muz1);
        sqLiteDatabase.insert("mytable",null,contentValues);
        contentValues.put("song_id",142355);
        contentValues.put("name","Pravda");
        contentValues.put("title","pravda-4");
        contentValues.put("song_loc",R.raw.muz1);
        sqLiteDatabase.insert("mytable",null,contentValues);

        contentValues = new ContentValues();
        contentValues.put("name","Kazakhstan");
        contentValues.put("title","kaz-1");
        contentValues.put("song_id",142356);
        contentValues.put("song_loc",R.raw.muz2);
        sqLiteDatabase.insert("mytable",null,contentValues);
        contentValues.put("name","Kazakhstan");
        contentValues.put("title","kaz-2");
        contentValues.put("song_id",142356);
        contentValues.put("song_loc",R.raw.muz2);
        sqLiteDatabase.insert("mytable",null,contentValues);
        contentValues.put("name","Kazakhstan");
        contentValues.put("title","kaz-3");
        contentValues.put("song_id",142356);
        contentValues.put("song_loc",R.raw.muz2);
        sqLiteDatabase.insert("mytable",null,contentValues);
        contentValues.put("name","Kazakhstan");
        contentValues.put("title","kaz-4");
        contentValues.put("song_id",142356);
        contentValues.put("song_loc",R.raw.muz2);
        sqLiteDatabase.insert("mytable",null,contentValues);
        contentValues.put("name","Kazakhstan");
        contentValues.put("title","kaz-5");
        contentValues.put("song_id",142356);
        contentValues.put("song_loc",R.raw.muz2);
        sqLiteDatabase.insert("mytable",null,contentValues);
        contentValues.put("name","Kazakhstan");
        contentValues.put("title","kaz-6");
        contentValues.put("song_id",142356);
        contentValues.put("song_loc",R.raw.muz2);
        sqLiteDatabase.insert("mytable",null,contentValues);

        contentValues.put("song_id",142357);
        contentValues.put("name","Haha news");
        contentValues.put("title","Haha-1");
        contentValues.put("song_loc",R.raw.muz2);
        sqLiteDatabase.insert("mytable",null,contentValues);
        contentValues.put("name","Haha news");
        contentValues.put("title","Haha-2");
        contentValues.put("song_id",142357);
        contentValues.put("song_loc",R.raw.muz1);
        sqLiteDatabase.insert("mytable",null,contentValues);
        contentValues.put("name","Haha news");
        contentValues.put("title","Haha-3");
        contentValues.put("song_id",142357);
        contentValues.put("song_loc",R.raw.muz3);
        sqLiteDatabase.insert("mytable",null,contentValues);

        sqLiteDatabase.execSQL("create table best_table (" +
                "id integer primary key autoincrement ," +
                "title text," +
                "song_id integer," +
                "song_loc integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
