package com.example.federicomarchesi.bottegadelcaffe;

/**
 * Created by federico.marchesi on 29/12/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DBCoffeeType.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(DBCoffeeType.SQL_CREATE_COFFEE_TYPE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL(DBCoffeeType.SQL_DELETE_COFFEE_TYPE_TABLE);
        onCreate(db);
    }

    public boolean insertCoffeeType(String coffeeType, String descr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBCoffeeType.COLUMN_NAME_COFFEE_TYPE, coffeeType);
        contentValues.put(DBCoffeeType.COLUMN_NAME_COFFEE_DESCRIPTION, descr);
        db.insert(DBCoffeeType.TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + DBCoffeeType.TABLE_NAME + " where " + DBCoffeeType._ID + "=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DBCoffeeType.TABLE_NAME);
        return numRows;
    }

    public boolean updateCoffeeType(Integer id, String coffeeType, String descr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBCoffeeType.COLUMN_NAME_COFFEE_TYPE, coffeeType);
        contentValues.put(DBCoffeeType.COLUMN_NAME_COFFEE_DESCRIPTION, descr);
        db.update(DBCoffeeType.TABLE_NAME, contentValues, DBCoffeeType._ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteCoffetType(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DBCoffeeType.TABLE_NAME,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<CoffeeType> getAllCoffeeTypes() {
        ArrayList<CoffeeType> DBCoffeeTypes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + DBCoffeeType.TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
//            DBCoffeeTypes.add( new DBCoffeeType(res.getString(res.getColumnIndex(DBCoffeeType.COLUMN_NAME_COFFEE_TYPE)),
//                    res.getString(res.getColumnIndex(DBCoffeeType.COLUMN_NAME_COFFEE_DESCRIPTION))));
            String colName = res.getString(res.getColumnIndex(DBCoffeeType.COLUMN_NAME_COFFEE_TYPE));
            String colDescr = res.getString(res.getColumnIndex(DBCoffeeType.COLUMN_NAME_COFFEE_DESCRIPTION));

            DBCoffeeTypes.add(new CoffeeType(colName, colDescr));
            res.moveToNext();
        }
        return DBCoffeeTypes;
    }

    private static class DBCoffeeType implements BaseColumns {
        public static final String DATABASE_NAME = "coffees.db";
        static final String TABLE_NAME = "coffee_type";
        static final String COLUMN_NAME_COFFEE_TYPE = "coffee_type";
        static final String COLUMN_NAME_COFFEE_DESCRIPTION = "coffee_descr";

        private static final String SQL_CREATE_COFFEE_TYPE_TABLE =
                "CREATE TABLE " + DBCoffeeType.TABLE_NAME + " (" +
                        DBCoffeeType._ID + " INTEGER PRIMARY KEY," +
                        DBCoffeeType.COLUMN_NAME_COFFEE_TYPE + " TEXT," +
                        DBCoffeeType.COLUMN_NAME_COFFEE_DESCRIPTION + " TEXT)";

        private static final String SQL_DELETE_COFFEE_TYPE_TABLE =
                "DROP TABLE IF EXISTS " + DBCoffeeType.TABLE_NAME;
    }

}