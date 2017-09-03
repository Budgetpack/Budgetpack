package com.sansi.acerbilgisayar.budgetpack.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.sansi.acerbilgisayar.budgetpack.Classes.City;
import com.sansi.acerbilgisayar.budgetpack.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer Bilgisayar on 2.9.2017.
 */

public class CityArrayAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<City>     mKisiListesi;

    public CityArrayAdapter(Activity activity, List<City> kisiler) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mKisiListesi = kisiler;
    }

    @Override
    public int getCount() {
        return mKisiListesi.size();
    }

    @Override
    public City getItem(int position) {
        return mKisiListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.city_adapter_layout, null);
        TextView textView =
                (TextView) satirView.findViewById(R.id.isimsoyisim);
        ImageView imageView =
                (ImageView) satirView.findViewById(R.id.simge);

        City kisi = mKisiListesi.get(position);

        textView.setText(kisi.getName());

       imageView.setImageResource(R.drawable.date);
        return satirView;
    }
}

