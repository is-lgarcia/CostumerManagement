package com.luisgarciasv.costumermanagement.data.sqlite;

import android.provider.BaseColumns;

public final class CostumerContract {

    private CostumerContract() {}

    public static class CostumerEntry implements BaseColumns {
        public static final String TABLE_NAME = "costumer";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_DUI = "dui";
        public static final String COLUMN_NIT = "nit";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";
    }
}

