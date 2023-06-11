package com.example.h071211052_finalmobile.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    private DatabaseContract() {
    }

    public static class DatabaseEntry implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_POSTER_URL = "poster_url";
        public static final String COLUMN_BACKDROP_URL = "backdrop_url";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_GENRE_IDS = "genre_ids";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_TITLE + " TEXT," +
                        COLUMN_RELEASE_DATE + " TEXT," +
                        COLUMN_OVERVIEW + " TEXT," +
                        COLUMN_POSTER_URL + " TEXT," +
                        COLUMN_BACKDROP_URL + " TEXT," +
                        COLUMN_VOTE_AVERAGE + " REAL," +
                        COLUMN_GENRE_IDS + " TEXT)";

        public static final String SQL_DROP_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}

