package com.e.lab3.ListCountries;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.e.lab3.ConsumoAPI.Countries;
import com.e.lab3.ConsumoAPI.EquipeActivity;
import com.e.lab3.ConsumoAPI.InterfaceCountries;
import com.e.lab3.DatabaseIn.Datas;
import com.e.lab3.MapsActivity;
import com.e.lab3.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayAdapter<Countries> adapter;
    private ArrayList<Countries> countryList;
    private ListView listView;
    Datas datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datas = new Datas(getBaseContext());
        countryList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_main);
        adapter = new Adapter(MainActivity.this, countryList);
        getDataRetro();
        listView.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setTitle("Lista de países");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_main);
        NavigationView navigationView = findViewById(R.id.nav_view_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void getDataRetro(){
        if(isConnected()){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(InterfaceCountries.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            InterfaceCountries service = retrofit.create(InterfaceCountries.class);
            service.getCountries().enqueue(new Callback<List<Countries>>() {
                @Override
                public void onResponse(Call<List<Countries>> call, Response<List<Countries>> response) {
                    if(response.isSuccessful()){
                        List<Countries> countriesBody = response.body();
                        countryList.clear();
                        datas.deleteAll();

                        for(Countries countries : countriesBody){
                            countryList.add(countries);
                            datas.inserT(countries);
                        }
                        adapter.notifyDataSetChanged();
                    } else{
                        System.out.println(response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<List<Countries>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Falha" + t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "Sem conexão", Toast.LENGTH_LONG).show();
            getDataSqlite();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_paises) {

        } else if (id == R.id.nav_southAmerican) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_equipe) {
            Intent intent = new Intent(MainActivity.this, EquipeActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null){
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
        return false;
    }

    private void getDataSqlite(){
        countryList.clear();
        countryList.addAll(datas.listCountries());
        adapter.notifyDataSetChanged();
    }
}
