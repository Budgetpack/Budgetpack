package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.sansi.acerbilgisayar.budgetpack.R;


public class ActivityPage extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    ImageView cityImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        Bundle b = getIntent().getExtras();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (b != null) {
            Log.e("City Name: ", "" + b.getString("city"));
            Log.e("Budget: ", "" + preferences.getString("budget", "N/A"));
            Log.e("Currency :", "" + b.getString("currency"));
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        cityImage = (ImageView) findViewById(R.id.city_id);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(b.getString("city"));

        dynamicToolbarColor();
        toolbarTextAppernce();
        cityPicturePicker();

    }
    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.rome);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {

            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimary)));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimaryDark)));
            }
        });
    }

    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }
    private void cityPicturePicker() {
        Bundle b = getIntent().getExtras();

        switch (b.getString("city")) {
            case "Barcelona":
                cityImage.setImageResource(R.drawable.walks_barcelona_1);
                break;
            case "Belgrade":
                cityImage.setImageResource(R.drawable.belgrade);
                break;
            case "Berlin":
                cityImage.setImageResource(R.drawable.berlin);
                break;
            case "Brussels":
                cityImage.setImageResource(R.drawable.brussels);
                break;
            case "Bucharest":
                cityImage.setImageResource(R.drawable.bucharest);
                break;
            case "Budapest":
                cityImage.setImageResource(R.drawable.budapest);
                break;
            case "Cophenagen":
                cityImage.setImageResource(R.drawable.copenhagen);
                break;
            case "Dublin":
                cityImage.setImageResource(R.drawable.dublin);
                break;
            case "Hamburg":
                cityImage.setImageResource(R.drawable.hamburg);
                break;
            case "Istanbul":
                cityImage.setImageResource(R.drawable.istanbul);
                break;
            case "Kiev":
                cityImage.setImageResource(R.drawable.kiev);
                break;
            case "London":
                cityImage.setImageResource(R.drawable.london);
                break;
            case "Madrid":
                cityImage.setImageResource(R.drawable.madrid);
                break;
            case "Milan":
                cityImage.setImageResource(R.drawable.milan);
                break;
            case "Moscow":
                cityImage.setImageResource(R.drawable.moscow);
                break;
            case "Munich":
                cityImage.setImageResource(R.drawable.munich);
                break;
            case "Paris":
                cityImage.setImageResource(R.drawable.paris);
                break;
            case "Prague":
                cityImage.setImageResource(R.drawable.prague);
                break;
            case "Rome":
                cityImage.setImageResource(R.drawable.rome);
                break;
            case "Saint Petersburg":
                cityImage.setImageResource(R.drawable.saintpetersburg);
                break;
            case "Sofia":
                cityImage.setImageResource(R.drawable.sofia);
                break;
            case "Stockholm":
                cityImage.setImageResource(R.drawable.stockholm);
                break;
            case "Vienna":
                cityImage.setImageResource(R.drawable.vienna);
                break;
            case "Warsaw":
                cityImage.setImageResource(R.drawable.warsaw);
                break;
            default:
                cityImage.setImageResource(R.drawable.walks_barcelona_1);
                break;
        }
    }

}

