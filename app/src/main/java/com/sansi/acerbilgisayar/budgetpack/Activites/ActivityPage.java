package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.animation.ObjectAnimator;
import android.content.Intent;
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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sansi.acerbilgisayar.budgetpack.R;


public class ActivityPage extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    ImageView cityImage;
    TextView dayText,contentText;
    View childLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        actionBar.setDisplayHomeAsUpEnabled(false);

        cityImage = (ImageView) findViewById(R.id.city_id);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(b.getString("city"));

        dynamicToolbarColor();
        toolbarTextAppernce();
        cityPicturePicker();

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        childLayout = inflater.inflate(R.layout.card_layout, (ViewGroup) findViewById(R.id.child_id),false);
        linearLayout.addView(childLayout);
        dayText = (TextView) childLayout.findViewById(R.id.daytag);
        dayText.setText("Details\nBudget:"+preferences.getString("budget","N/A"));


        for(int i=1; i<=preferences.getLong("diff",0); i++){
            childLayout = inflater.inflate(R.layout.card_layout, (ViewGroup) findViewById(R.id.child_id),false);
            linearLayout.addView(childLayout);
            dayText = (TextView) childLayout.findViewById(R.id.daytag);
            dayText.setText("Day "+i);
        }
        dayText = (TextView) childLayout.findViewById(R.id.daytag);
        contentText = (TextView) childLayout.findViewById(R.id.contentText);



    }
    private void dynamicToolbarColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.walks_barcelona_1);

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

    public void handleOnClick(View view) {
        switch(view.getId()){
            case R.id.daytag:
            if (contentText.getVisibility() == View.GONE) {
                // it's collapsed - expand it
                contentText.setVisibility(View.VISIBLE);

            } else {
                // it's expanded - collapse it
                contentText.setVisibility(View.GONE);

            }

            ObjectAnimator animation = ObjectAnimator.ofInt(contentText, "maxLines", contentText.getMaxLines());
            animation.setDuration(200).start();
                break;
            default:
                Log.e("onClick", "switchcase");
                break;
        }


    }
    }




