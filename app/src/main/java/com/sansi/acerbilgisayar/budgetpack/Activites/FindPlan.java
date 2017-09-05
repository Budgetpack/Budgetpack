package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import com.sansi.acerbilgisayar.budgetpack.Adapters.RecyclerViewAdapter;
import com.sansi.acerbilgisayar.budgetpack.Classes.City;
import com.sansi.acerbilgisayar.budgetpack.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.callback.Callback;

public class FindPlan extends AppCompatActivity {
    final List<City> cities =new ArrayList<City>();


    String budget, currency, type;
    int startDay, startMonth, startYear, endDay, endMonth, endYear;
    long diff;
    int dailyBudget;
    int deneme = 9;

    private LinearLayoutManager lLayout;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    ListView myList;
    RecyclerView rView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_find_plan);
        setContentView(R.layout.find_plan);

        myList = (ListView) findViewById(R.id.liste);
        lLayout = new LinearLayoutManager(this);

        rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        Bundle b = getIntent().getExtras();
        budget = b.getString("budget");
        currency = b.getString("currency");
        type = b.getString("type");

        startDay = b.getInt("startDay");
        startMonth = b.getInt("startmonth");
        startYear = b.getInt("startyear");

        endDay = b.getInt("endday");
        endMonth = b.getInt("endmonth");
        endYear = b.getInt("endyear");

        diff = b.getLong("diff");

        dailyBudget = (int) calculateDailyBudget(budget, diff);
        Log.e("daily budget :",""+dailyBudget);

        readFromDatabase();

       new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                for(int i=0;i<cities.size();i++) {
                    Log.e("delay log", "" + cities.get(i));
                    mHandler.obtainMessage(1).sendToTarget();
                }

            }
        }, 3000);
    }

    public double calculateDailyBudget(String b, long diff){
        int budget = Integer.parseInt(b);

        return (double) budget/diff;
    }

    public void readFromDatabase(){
        Query myQuery = myRef.child("Cities");
        myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if(Integer.valueOf(child.child("budget").getValue().toString()) < dailyBudget){
                        for(DataSnapshot child2 : child.child("cityChar").getChildren()) {
                            if(child2.getKey().equals(type.toLowerCase())) {
                                 Log.e("Cities: ", "" + child.getKey() + " Budget: " + child.child("budget").getValue());
                                 //Log.e("cityChar ", "" + child2.getKey());
                                cities.add(new City(child.getKey()));
                            }
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Handler mHandler = new Handler(){
        public void handleMessage(Message msg){

            RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(FindPlan.this, cities);
            rView.setAdapter(rcAdapter);

        }
    };


}
