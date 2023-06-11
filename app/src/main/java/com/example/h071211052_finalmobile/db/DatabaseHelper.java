package com.example.h071211052_finalmobile.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "Content.db";
    private static final int DATABASE_VERSION = 2;
    private static final String SQL_CREATE_TABLE_NOTE =
            String.format(
                    "CREATE TABLE %s"
                            + " (%s INTEGER PRIMARY KEY,"
                            + " %s TEXT NOT NULL,"
                            + " %s FLOAT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s INTEGER NOT NULL)",
                    DatabaseContract.TABLE_NAME,
                    DatabaseContract.ContentColumns.ID,
                    DatabaseContract.ContentColumns.TITLE,
                    DatabaseContract.ContentColumns.VOTE_AVERAGE,
                    DatabaseContract.ContentColumns.OVERVIEW,
                    DatabaseContract.ContentColumns.RELEASE_YEAR,
                    DatabaseContract.ContentColumns.POSTER_PATH,
                    DatabaseContract.ContentColumns.BACKDROP_PATH,
                    DatabaseContract.ContentColumns.CONTENT_TYPE

            );

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
