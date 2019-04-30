package com.e.lab3.DatabaseIn;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.e.lab3.ConsumoAPI.Countries;

import java.util.ArrayList;
import java.util.List;

import static com.e.lab3.DatabaseIn.SQLHelper.COLUN_CAPITAL;
import static com.e.lab3.DatabaseIn.SQLHelper.COLUN_POPULATION;
import static com.e.lab3.DatabaseIn.SQLHelper.COLUN_REGION;

public class Datas{

    private SQLHelper helper;
    private SQLiteDatabase database;

    public Datas(Context context){
        helper = new SQLHelper(context);
    }

    public String inserT(Countries countries){
        database = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLHelper.COLUN_NAME, countries.name);
        contentValues.put(SQLHelper.COLUN_CAPITAL, countries.capital);
        contentValues.put(SQLHelper.COLUN_REGION, countries.subregion);
        contentValues.put(SQLHelper.COLUN_POPULATION, countries.population);

        long id = database.insert(SQLHelper.TABLE_COUNTRIES, null, contentValues);
        database.close();

        if(id == -1){
            return "Erro ao inserir registro";
        } else{
            return "Registro inserido com sucesso";
        }
    }

    public void deleteAll(){
        database = helper.getWritableDatabase();
        database.delete(SQLHelper.TABLE_COUNTRIES, null, null);
        database.close();
    }

    public List<Countries> listCountries() {
        String sql = "SELECT * FROM " + SQLHelper.TABLE_COUNTRIES;
        database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        List<Countries> list = new ArrayList<>();

        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUN_NAME));
            String capital = cursor.getString(cursor.getColumnIndex(COLUN_CAPITAL));
            String region = cursor.getString(cursor.getColumnIndex(COLUN_REGION));
            String population = cursor.getString(cursor.getColumnIndex(COLUN_POPULATION));

            Countries countries = new Countries(name,capital,region,population, null);
            list.add(countries);
        }
        cursor.close();
        return list;
    }
}