package com.vk_2.DB;

/**
 * Created by User on 12.01.2018.
 */

public class Tables {
    public static final String ACCOUNT_INFO_TABLE
            = "CREATE TABLE IF NOT EXISTS " +
            AccountInfoDb.TABLE + "(" +
            AccountInfoDb.ID + " VARCHAR (500)," +
            AccountInfoDb.FIRST_NAME + " VARCHAR (500)," +
            AccountInfoDb.LAST_NAME + " VARCHAR (500)," +
            AccountInfoDb.MAIDEN_NAME+ " VARCHAR (500)," +
            AccountInfoDb.SCREEN_NAME + " VARCHAR (500)," +
            AccountInfoDb.SEX + " VARCHAR (500)," +
            AccountInfoDb.RELATION + " VARCHAR (500)," +
            AccountInfoDb.BDATE + " VARCHAR (500)," +
            AccountInfoDb.HOME_TOWN + " VARCHAR (500)," +
            AccountInfoDb.STATUS + " VARCHAR (500)," +
            AccountInfoDb.PHONE + " VARCHAR (500)," +
            AccountInfoDb.URL + " VARCHAR (500)" +
            " )";

    public static final String ACCOUNT_INFO_TABLE_DROP=
            "DROP TABLE "+AccountInfoDb.TABLE;
}
