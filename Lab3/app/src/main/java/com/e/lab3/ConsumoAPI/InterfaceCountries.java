package com.e.lab3.ConsumoAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceCountries {
    public static final String BASE_URL = "https://restcountries.eu/rest/v1/";

    @GET("all")
    Call<List<Countries>> getCountries();
}
