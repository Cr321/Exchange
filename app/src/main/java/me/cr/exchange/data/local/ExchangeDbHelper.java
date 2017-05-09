package me.cr.exchange.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.cr.exchange.constants.Constant;

/**
 * Created by 10190178 on 2017/1/12.
 */

public class ExchangeDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Exchange.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_EXCHANGE_ENTRIES =
            "CREATE TABLE " + ExchangeContract.ExchangeEntry.TABLE_NAME + " (" +
                    ExchangeContract.ExchangeEntry.COLUMN_FROM + " TEXT PRIMARY KEY," +
                    ExchangeContract.ExchangeEntry.COLUMN_CNY + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_USD + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_JPY + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_EUR + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_GBP + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_HKD + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_AUD + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_CAD + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_MOP + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_SEK + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_THB + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_SGD + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_TWD + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_NZD + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_ISK + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_BRL + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_RUB + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_PHP + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_LAK + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_MXN + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_NOK + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_CLP + TEXT_TYPE + COMMA_SEP +
                    ExchangeContract.ExchangeEntry.COLUMN_INR + TEXT_TYPE + " )";

    private static final String SQL_CREATE_LOG_ENTRIES =
            "CREATE TABLE " + ExchangeContract.LogEntry.TABLE_NAME + " (" +
                    ExchangeContract.LogEntry._ID + " INT PRIMARY KEY," +
                    ExchangeContract.LogEntry.COLUMN_TIME + " NUMBER )";

    private static final String SQL_DELETE_EXCHANGE_ENTRIES =
            "DROP TABLE IF EXISTS " + ExchangeContract.ExchangeEntry.TABLE_NAME;

    private static final String SQL_DELETE_LOG_ENTRIES =
            "DROP TABLE IF EXISTS " + ExchangeContract.LogEntry.TABLE_NAME;

    public ExchangeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_EXCHANGE_ENTRIES);
        db.execSQL(SQL_CREATE_LOG_ENTRIES);

        for (int i = 0; i < Constant.ARRAY_CURRENCY.length; i++) {
            db.execSQL("INSERT INTO " + ExchangeContract.ExchangeEntry.TABLE_NAME +
                    " ( _FROM, " + Constant.ARRAY_CURRENCY[i] +
                    ") VALUES ( '" + Constant.ARRAY_CURRENCY[i] + "'" + COMMA_SEP + 1 + " )"
            );
        }
        db.execSQL("INSERT INTO " + ExchangeContract.LogEntry.TABLE_NAME +
                " ( _id ,time ) VALUES ( 1, " + 0 + " )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_EXCHANGE_ENTRIES);
        db.execSQL(SQL_DELETE_LOG_ENTRIES);
        onCreate(db);
    }
}
