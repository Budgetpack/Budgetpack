package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sansi.acerbilgisayar.budgetpack.R;

public class ActivityPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        Bundle b = getIntent().getExtras();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if(b != null){
            Log.e("City Name: ",""+b.getString("city"));
            Log.e("Budget: ",""+preferences.getString("budget","N/A"));
            Log.e("Currency :",""+b.getString("currency"));
        }
    }
}
