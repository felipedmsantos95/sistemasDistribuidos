package com.e.lab3.ConsumoAPI;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Countries implements Serializable {

    @SerializedName("name")
    public String name;

    @SerializedName("capital")
    public String capital;

    @SerializedName("subregion")
    public String subregion;

    @SerializedName("population")
    public String population;

    @SerializedName("latlng")
    public List<String> latlng;


    public Countries(String name, String capital, String subregion, String population, List<String> latlng){
        this.name = name;
        this.capital = capital;
        this.subregion = subregion;
        this.population = population;
        this.latlng = latlng;
    }

    @Override
    public String toString(){
        return "Countries{" +
                "name='" + name + '\'' +
                "capital='" + capital + '\'' +
                "region='" + subregion + '\'' +
                "population='" + population + '\'' +
                "latlng='" + latlng + '\'' +
                '}';
    }

    public String getName(){
        return name;
    }

    public String getCapital(){
        return capital;
    }

    public String getSubregion(){
        return subregion;
    }

    public String getPopulation(){
        return population;
    }

    public String  Latitude(){
        String par = latlng.toString();
        String[] listPar = par.split(",");
        String latitude = listPar[0];
        latitude = latitude.replace("[","");
        return latitude;
    }

    public String Longitude(){
        String par = latlng.toString();
        String[] listPar = par.split(",");
        String longitude = listPar[0];
        longitude = longitude.replace("[","");
        return longitude;
    }
}
