package me.cr.exchange.data.local;

import android.provider.BaseColumns;

import me.cr.exchange.constants.Constant;

/**
 * Created by 10190178 on 2017/1/12.
 */

public final class ExchangeContract {

    private ExchangeContract() {
    }

    public static class ExchangeEntry {
        public static final String TABLE_NAME = "exchange_rate";
        public static final String COLUMN_FROM = "_FROM";
        public static final String COLUMN_CNY = Constant.CHINA_YUAN;
        public static final String COLUMN_USD = Constant.US_DOLLAR;
        public static final String COLUMN_JPY = Constant.JAPANESE_YEN;
        public static final String COLUMN_EUR = Constant.EUROPEAN_EURO;
        public static final String COLUMN_GBP = Constant.GREATBRITAIN_POUND;
        public static final String COLUMN_HKD = Constant.HONGKONG_DOLLAR;
        public static final String COLUMN_AUD = Constant.AUSTRALIA_DOLLAR;
        public static final String COLUMN_CAD = Constant.CANADA_DOLLAR;
        public static final String COLUMN_MOP = Constant.MACAU_PATACA;
        public static final String COLUMN_SEK = Constant.SWEDEN_KRONA;
        public static final String COLUMN_THB = Constant.THAILAND_BAHT;
        public static final String COLUMN_SGD = Constant.SINGAPORE_DOLLAR;
        public static final String COLUMN_TWD = Constant.TAIWAN_DOLLAR;
        public static final String COLUMN_NZD = Constant.NEW_ZEALAND_DOLLAR;
        public static final String COLUMN_ISK = Constant.ICELANDIC_KRONA;
        public static final String COLUMN_BRL = Constant.BRAZIL_REAL;
        public static final String COLUMN_RUB = Constant.RUSSIA_RUBLE;
        public static final String COLUMN_PHP = Constant.PHILIPPINE_PESO;
        public static final String COLUMN_LAK = Constant.LAOS_KIP;
        public static final String COLUMN_MXN = Constant.MEXICAN_PESO;
        public static final String COLUMN_NOK = Constant.NORWEGIAN_KRONE;
        public static final String COLUMN_CLP = Constant.CHILE_PESO;
        public static final String COLUMN_INR = Constant.INDIAN_RUPEE;

    }

    public static class LogEntry implements BaseColumns{
        public static final String TABLE_NAME = "log";
        public static final String COLUMN_TIME = "time";
    }

}
