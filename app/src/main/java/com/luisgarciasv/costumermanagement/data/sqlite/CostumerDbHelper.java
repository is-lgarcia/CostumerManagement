package com.luisgarciasv.costumermanagement.data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CostumerDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "costumer.db";

    public CostumerDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CostumerContract.CostumerEntry.TABLE_NAME + " (" +
                    CostumerContract.CostumerEntry._ID + " INTEGER PRIMARY KEY," +
                    CostumerContract.CostumerEntry.COLUMN_NAME + " TEXT," +
                    CostumerContract.CostumerEntry.COLUMN_LAST_NAME + " TEXT," +
                    CostumerContract.CostumerEntry.COLUMN_DUI + " TEXT," +
                    CostumerContract.CostumerEntry.COLUMN_NIT + " TEXT," +
                    CostumerContract.CostumerEntry.COLUMN_ADDRESS + " TEXT," +
                    CostumerContract.CostumerEntry.COLUMN_PHONE + " TEXT," +
                    CostumerContract.CostumerEntry.COLUMN_EMAIL + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CostumerContract.CostumerEntry.TABLE_NAME;
}
