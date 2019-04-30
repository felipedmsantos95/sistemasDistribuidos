package com.e.lab3.ListCountries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.e.lab3.ConsumoAPI.Countries;
import com.e.lab3.R;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Countries> {
    private Context context;
    private ArrayList<Countries> country;

    public Adapter(Context c, ArrayList<Countries> objects){
        super(c,0, objects);
        this.context = c;
        this.country = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = null;

        if(country != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.list_countries, parent, false);

            TextView name = (TextView) view.findViewById(R.id.list_titulo);
            TextView capital = (TextView) view.findViewById(R.id.list_subtitulo);

            Countries countries = country.get(position);
            name.setText(countries.getName());
            capital.setText(countries.getCapital());
        }
        return view;
    }
}
