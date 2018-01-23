package com.example.senamit.wordmeaning.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  com.example.senamit.wordmeaning.Data.WordContract.*;

/**
 * Created by senamit on 20/1/18.
 */

public class WordDbHelper extends SQLiteOpenHelper  {

    public static final String DATABASE_NAME = "WordTracker";
    public static final int DATABASE_VERSION=4;


    public WordDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_MOVIE_ENTRY = "CREATE TABLE " + WordListDiary.TABLE_NAME +
            "(" + WordListDiary._ID + " INTEGER PRIMARY KEY, " +
            WordListDiary.COLUMN_WORD_NAME + " TEXT NOT NULL, " +
            WordListDiary.COLUMN_WORD_DESCRIPTION+ " TEXT NOT NULL, "+
            WordListDiary.COLUMN_WORD_PRIORITY + " INTEGER NOT NULL)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + WordListDiary.TABLE_NAME;



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MOVIE_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
