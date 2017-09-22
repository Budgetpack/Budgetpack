package com.sansi.acerbilgisayar.budgetpack.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sansi.acerbilgisayar.budgetpack.Activites.ActivityPage;
import com.sansi.acerbilgisayar.budgetpack.R;

/**
 * Created by Acer Bilgisayar on 5.9.2017.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView countryName;
    public ImageView countryPhoto;
    public String cityName;

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
        if(optionMode.equals("A")){
            Intent intent = new Intent(itemView.getContext(), ActivityPage.class);
            intent.putExtra("city",cityName);
            itemView.getContext().startActivity(intent);
        }

    }
}