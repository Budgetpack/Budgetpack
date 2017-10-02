package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.animation.ObjectAnimator;
import android.content.Context;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sansi.acerbilgisayar.budgetpack.Classes.City;
import com.sansi.acerbilgisayar.budgetpack.Classes.Event;
import com.sansi.acerbilgisayar.budgetpack.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ActivityPage extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private LinearLayout linearLayout;
    SharedPreferences preferences;
    List<Event> arraylist = new ArrayList<Event>();
    List<Event> tempList = new ArrayList<Event>();
    ImageView cityImage;
    TextView dayText,contentText;
    View childLayout;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    int totalPrice = 0;
    int MAX_EVENT = 12;
    int numOfEvents=0;
    int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_page);

        Bundle b = getIntent().getExtras();
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
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

        linearLayout = (LinearLayout) findViewById(R.id.linear);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        childLayout = inflater.inflate(R.layout.card_layout, (ViewGroup) findViewById(R.id.child_id),false);
        linearLayout.addView(childLayout);
        dayText = (TextView) childLayout.findViewById(R.id.daytag);
        contentText = (TextView) childLayout.findViewById(R.id.contentText);
        contentText.setText("");
        dayText.setText("Details\nBudget:"+preferences.getString("budget","N/A"));


        for(int i=0; i<=preferences.getLong("diff",0)-1; i++){
            childLayout = inflater.inflate(R.layout.card_layout, (ViewGroup) findViewById(R.id.child_id),false);
            linearLayout.addView(childLayout);
            dayText = (TextView) childLayout.findViewById(R.id.daytag);
            dayText.setId(i);
            int j = i+1;
            dayText.setText("Day "+j);

        }

        eventPicker();



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
                Log.e("day: ","blabla");
                break;
            default:
                Intent intent = new Intent(ActivityPage.this, ActivityDetails.class);
                intent.putExtra("day",(view.getId())+1 );
                startActivity(intent);
                Log.e("onClick", "switchcase"+view.getId());
                break;
        }


    }

    public void eventPicker(){
        Log.e("TEST","DUDUBOI");
        long test = 0;
        final Long p = Long.valueOf(preferences.getString("budget","0"))/preferences.getLong("diff",test);
        Log.e("DailyBudget ",""+p);

        Bundle b = getIntent().getExtras();
        String cityName = b.getString("city");
        final int weight= 100;


        Log.e("test","fireabase öncesi"+b.getString("city"));
        Query myQuery = myRef.child("Cities").child(cityName).child("activities").orderByChild("price");
        myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String characteristic = child.child("characteristic").getValue().toString();
                    String eventName = child.child("name").getValue().toString();
                    Log.e("child price",""+child.child("price").getValue());
                    Event event = new Event(eventName, characteristic, Long.valueOf(child.child("price").getValue().toString()), true);
                    //Log.e("test","firebase içi");


                    //TODO : main karakteristik eventleri eklendikten sonra bütçe izin veriyorsa diğer eventleri ekle
                    //TODO : her gün için loop

                    if(preferences.getString("char","NULL").equals(characteristic) && !arraylist.contains(event) )   {
                        arraylist.add(event);
                        numOfEvents++;
                        totalPrice += Long.valueOf(child.child("price").getValue().toString());
                        index++;
                        if(totalPrice > p || numOfEvents > 2){
                            arraylist.remove(index-1);
                            numOfEvents--;
                            break;
                        }
                    }
//                    if(numOfEvents < MAX_EVENT && totalPrice < p ){
//                        if(!preferences.getString("char","NULL").equals(characteristic) && !arraylist.contains(event)){
//                            arraylist.add(event);
//                            totalPrice += Long.valueOf(child.child("price").getValue().toString());
//                        }
//                    }
                    Log.e("test","eventname "+eventName);
                    Log.e("test","index "+index);
                    Log.e("test","numofevents "+numOfEvents);
                    Log.e("test","totalprice: "+totalPrice);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.e("test","firebase sonraso");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Log.e("test","arraylist size"+arraylist.size() );
                for(int i = 0; i<arraylist.size(); i++ ){
                    Log.e("ARRAY ITEMII",""+arraylist.get(i).getName() );
                    Log.e("ARRAY CHARACTERISTIC",""+arraylist.get(i).getCharacteristic() );

                }
            }
        }, 2000);


    }

}




