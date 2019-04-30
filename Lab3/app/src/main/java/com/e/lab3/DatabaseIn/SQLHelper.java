package com.e.lab3.DatabaseIn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLHelper extends SQLiteOpenHelper {

    private static final String NAME_BANK = "DatasCountries";
    private static final int VERSION_BANK = 1;
    public static final String TABLE_COUNTRIES = "countries_table";
    public static final String COLUN_NAME = "name";
    public static final String COLUN_CAPITAL = "capital";
    public static final String COLUN_REGION = "region";
    public static final String COLUN_POPULATION = "population";

    public SQLHelper(Context context){
        super(context, NAME_BANK, null, VERSION_BANK);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_COUNTRIES + " ( " +
                        COLUN_NAME + " TEXT, " +
                        COLUN_CAPITAL + " TEXT, " +
                        COLUN_REGION + " TEXT, " +
                        COLUN_POPULATION + " TEXT)"
        );
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVerion, int newVerion){

    }

}
