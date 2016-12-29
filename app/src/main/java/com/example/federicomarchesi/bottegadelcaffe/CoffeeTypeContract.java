package com.example.federicomarchesi.bottegadelcaffe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by federico.marchesi on 29/12/2016.
 */


public final class CoffeeTypeContract {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "coffees.db";

    private static final String SQL_CREATE_COFFEE_TYPE_TABLE =
            "CREATE TABLE " + CoffeeType.TABLE_NAME + " (" +
                    CoffeeType._ID + " INTEGER PRIMARY KEY," +
                    CoffeeType.COLUMN_NAME_COFFEE_TYPE + " TEXT," +
                    CoffeeType.COLUMN_NAME_COFFEE_DESCRIPTION + " TEXT)";

    private static final String SQL_DELETE_COFFEE_TYPE_TABLE =
            "DROP TABLE IF EXISTS " + CoffeeType.TABLE_NAME;

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private CoffeeTypeContract() {
    }

    /* Inner class that defines the table contents */
    private static class CoffeeType implements BaseColumns {
        static final String TABLE_NAME = "coffee_type";
        static final String COLUMN_NAME_COFFEE_TYPE = "coffee_type";
        static final String COLUMN_NAME_COFFEE_DESCRIPTION = "coffee_descr";
    }

    public static class CoffeeTypeDbHelper extends SQLiteOpenHelper {
        public CoffeeTypeDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_COFFEE_TYPE_TABLE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_COFFEE_TYPE_TABLE);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}
