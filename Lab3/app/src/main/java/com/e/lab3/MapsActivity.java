package com.e.lab3;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.e.lab3.ConsumoAPI.Countries;
import com.e.lab3.ConsumoAPI.EquipeActivity;
import com.e.lab3.ConsumoAPI.InterfaceCountries;
import com.e.lab3.DatabaseIn.Datas;
import com.e.lab3.ListCountries.MainActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    Datas datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        datas = new Datas(getBaseContext());
        SouthAmerican();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.toolbar_map);
        setTitle("América do Sul");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_map);
        NavigationView navigationView = findViewById(R.id.nav_view_map);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_map);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_paises) {
            Intent intent = new Intent(MapsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_southAmerican) {

        } else if (id == R.id.nav_equipe) {
            Intent intent = new Intent(MapsActivity.this, EquipeActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_map);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void SouthAmerican(){
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
                        mMap.clear();

                        for(Countries countries : countriesBody){
                            //Lógica para implementar o países da américa do sul
                            /*
                            if(countries.getSubregion().equals("SouthAmerica")){
                                String par = countries.getLatlng().toString();
                                String[] listPar = par.split(",");
                                String latitude = listPar[0];
                                latitude = latitude.replace("[","");
                                String longitude = listPar[1];
                                longitude = longitude.replace("]", "");

                                Toast.makeText(MapsActivity.this, countries.getSubregion(), Toast.LENGTH_LONG).show();

                                LatLng pais = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
                                mMap.addMarker(new MarkerOptions()
                                        .position(pais)
                                        .title(countries.name)
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                            }
                            */
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Countries>> call, Throwable t) {
                    Toast.makeText(MapsActivity.this, "Falha" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        } else{
            Toast.makeText(MapsActivity.this, "sem conexão", Toast.LENGTH_SHORT).show();
        }

    }

    public Boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null){
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
        return false;
    }


}
