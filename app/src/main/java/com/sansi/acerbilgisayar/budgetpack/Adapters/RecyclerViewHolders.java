package com.sansi.acerbilgisayar.budgetpack.Adapters;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sansi.acerbilgisayar.budgetpack.Activites.SelectCity;
import com.sansi.acerbilgisayar.budgetpack.R;

/**
 * Created by Acer Bilgisayar on 5.9.2017.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView countryName;
    public ImageView countryPhoto;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        countryName = (TextView)itemView.findViewById(R.id.country_name);
        countryPhoto = (ImageView)itemView.findViewById(R.id.country_photo);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String optionMode = preferences.getString("OPTION", "N/A");
        Toast.makeText(view.getContext(), "OPTION " +optionMode, Toast.LENGTH_SHORT).show();
    }
}