package com.vk_2.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;


/**
 * Created by User on 12.01.2018.
 */

public class SqlConnector extends SQLiteOpenHelper {

    private static final String NAME = "application.db";
    private static final int VERSION = 2;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Tables.ACCOUNT_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.e(TAG, "onUpgrade: " );
sqLiteDatabase.execSQL(Tables.ACCOUNT_INFO_TABLE_DROP);
        sqLiteDatabase.execSQL(Tables.ACCOUNT_INFO_TABLE);
    }

    public SqlConnector(Context context) {
        super(context, NAME, null, VERSION);

    }
}
