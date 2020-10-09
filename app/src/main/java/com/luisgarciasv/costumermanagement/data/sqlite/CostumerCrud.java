package com.luisgarciasv.costumermanagement.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.MutableLiveData;

import com.luisgarciasv.costumermanagement.model.Costumer;

import java.util.ArrayList;
import java.util.List;

public class CostumerCrud {

    private CostumerDbHelper dbHelper;

    public CostumerCrud(Context context) {
        dbHelper = new CostumerDbHelper(context);
    }

    public void newCostumer(Costumer item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CostumerContract.CostumerEntry.COLUMN_NAME, item.getName());
        values.put(CostumerContract.CostumerEntry.COLUMN_LAST_NAME, item.getLastName());
        values.put(CostumerContract.CostumerEntry.COLUMN_DUI, item.getDui());
        values.put(CostumerContract.CostumerEntry.COLUMN_NIT, item.getNit());
        values.put(CostumerContract.CostumerEntry.COLUMN_ADDRESS, item.getAddress());
        values.put(CostumerContract.CostumerEntry.COLUMN_PHONE, item.getPhone());
        values.put(CostumerContract.CostumerEntry.COLUMN_EMAIL, item.getEmail());

        db.insert(CostumerContract.CostumerEntry.TABLE_NAME, null, values);

        db.close();
    }

    public MutableLiveData<List<Costumer>> getCostumers() {

        MutableLiveData<List<Costumer>> costumers = new MutableLiveData<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {
                CostumerContract.CostumerEntry.COLUMN_NAME,
                CostumerContract.CostumerEntry.COLUMN_LAST_NAME,
                CostumerContract.CostumerEntry.COLUMN_DUI,
                CostumerContract.CostumerEntry.COLUMN_NIT,
                CostumerContract.CostumerEntry.COLUMN_ADDRESS,
                CostumerContract.CostumerEntry.COLUMN_PHONE,
                CostumerContract.CostumerEntry.COLUMN_EMAIL,
        };

        String sortOrder =
                CostumerContract.CostumerEntry.COLUMN_NAME + " ASC ";

        Cursor cursor = db.query(
                CostumerContract.CostumerEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder,
                null
        );

        ArrayList<Costumer> items = new ArrayList<>();

        while (cursor.moveToNext()) {

            items.add(new Costumer(
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_LAST_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_DUI)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_NIT)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_ADDRESS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_PHONE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_EMAIL))
            ));
        }
        cursor.close();
        db.close();

        costumers.setValue(items);

        return costumers;
    }

    public MutableLiveData<Costumer> getCostumer(String dui) {
        MutableLiveData<Costumer> item = new MutableLiveData<>();
        Costumer costumer = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {
                CostumerContract.CostumerEntry.COLUMN_NAME,
                CostumerContract.CostumerEntry.COLUMN_LAST_NAME,
                CostumerContract.CostumerEntry.COLUMN_DUI,
                CostumerContract.CostumerEntry.COLUMN_NIT,
                CostumerContract.CostumerEntry.COLUMN_ADDRESS,
                CostumerContract.CostumerEntry.COLUMN_PHONE,
                CostumerContract.CostumerEntry.COLUMN_EMAIL,
        };

        String selection = CostumerContract.CostumerEntry.COLUMN_DUI + " = ?";
        String[] selectionArgs = {dui};

        Cursor cursor = db.query(
                CostumerContract.CostumerEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            costumer = new Costumer(
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_LAST_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_DUI)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_NIT)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_ADDRESS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_PHONE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CostumerContract.CostumerEntry.COLUMN_EMAIL))
            );
        }
        cursor.close();
        db.close();

        item.setValue(costumer);
        return item;
    }

    public void updateCostumer(Costumer item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CostumerContract.CostumerEntry.COLUMN_NAME, item.getName());
        values.put(CostumerContract.CostumerEntry.COLUMN_LAST_NAME, item.getLastName());
        values.put(CostumerContract.CostumerEntry.COLUMN_DUI, item.getDui());
        values.put(CostumerContract.CostumerEntry.COLUMN_NIT, item.getNit());
        values.put(CostumerContract.CostumerEntry.COLUMN_ADDRESS, item.getAddress());
        values.put(CostumerContract.CostumerEntry.COLUMN_PHONE, item.getPhone());
        values.put(CostumerContract.CostumerEntry.COLUMN_EMAIL, item.getEmail());

        String selection = CostumerContract.CostumerEntry.COLUMN_DUI + " = ?";
        String[] selectionArgs = {item.getDui()};

        db.update(
                CostumerContract.CostumerEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();
    }

    public void deleteCostumer(String dui) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = CostumerContract.CostumerEntry.COLUMN_DUI + " = ?";
        String[] selectionArgs = {dui};

        db.delete(
                CostumerContract.CostumerEntry.TABLE_NAME,
                selection,
                selectionArgs);

        db.close();
    }
}
