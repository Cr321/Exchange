package me.cr.exchange.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 10190178 on 2017/1/12.
 */

public class DatabaseWrapper {

    private static final String TAG = "DatabaseWrapper";
    private ExchangeDbHelper mDbHelper;
    public DatabaseWrapper(Context context) {
        mDbHelper = new ExchangeDbHelper(context);
    }

    public void insertExchange(String from, String to, String rate) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ExchangeContract.ExchangeEntry.COLUMN_FROM, from);
        values.put(to, rate);
        db.insert(ExchangeContract.ExchangeEntry.TABLE_NAME, null, values);
    }

    public void insertLog(long time) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ExchangeContract.LogEntry.COLUMN_TIME, time);
        db.insert(ExchangeContract.LogEntry.TABLE_NAME, null, values);
    }

    public float getExchangeRate(String from, String to) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] projection = {
                ExchangeContract.ExchangeEntry.COLUMN_FROM,
                to
        };

        String selection = ExchangeContract.ExchangeEntry.COLUMN_FROM + " =? ";
        String[] selectionArgs = {from};

        String sortOrder = ExchangeContract.ExchangeEntry.COLUMN_FROM + " DESC";

        Cursor c = db.query(ExchangeContract.ExchangeEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        if (c != null) {
            c.moveToFirst();
            String str_rate = c.getString(c.getColumnIndexOrThrow(to));
            if (str_rate != null) {
                return Float.valueOf(str_rate);
            }
        }

        return 0;
    }

    public float getLogTime() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] projection = {
                ExchangeContract.LogEntry._ID,
                ExchangeContract.LogEntry.COLUMN_TIME
        };

        String selection = ExchangeContract.LogEntry._ID + " =? ";
        String[] selectionArgs = { "1" };

        String sortOrder = ExchangeContract.LogEntry._ID + " DESC";

        Cursor c = db.query(ExchangeContract.LogEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        if (c != null) {
            try {
                c.moveToFirst();
                return c.getLong(c.getColumnIndexOrThrow(ExchangeContract.LogEntry.COLUMN_TIME));
            } finally {
                c.close();
                c = null;
            }

        }

        return 0;
    }

    public void updateExchange(String from, String column, String rate) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, rate);

        String selection = ExchangeContract.ExchangeEntry.COLUMN_FROM + " LIKE ?";
        String[] selectionArgs = {from};

        db.update(ExchangeContract.ExchangeEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    public void updateLogTime(long time) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ExchangeContract.LogEntry.COLUMN_TIME, time);

        String selection = ExchangeContract.LogEntry._ID + " LIKE ?";
        String[] selectionArgs = {"1"};

        db.update(ExchangeContract.LogEntry.TABLE_NAME, values, selection, selectionArgs);
    }
}
