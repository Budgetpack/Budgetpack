package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sansi.acerbilgisayar.budgetpack.Adapters.RecyclerViewAdapter;
import com.sansi.acerbilgisayar.budgetpack.Classes.City;
import com.sansi.acerbilgisayar.budgetpack.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SelectCity extends AppCompatActivity {
    final List<City> cities =new ArrayList<City>();
    private LinearLayoutManager lLayout;
    private ProgressBar spinner;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    RecyclerView rView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_city);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("OPTION", "B");
        editor.apply();

        lLayout = new LinearLayoutManager(this);

        rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        readFromDatabase();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                for(int i=0;i<cities.size();i++) {
                    //Log.e("delay log", "" + cities.get(i));
                    mHandler.obtainMessage(1).sendToTarget();
                    sHandler.obtainMessage(1).sendToTarget();
                }
                Log.e("ITEM COUNT",""+cities.size());
            }
        }, 3000);

    }
    public void readFromDatabase(){
        Query myQuery = myRef.child("Cities");
        myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    cities.add(new City(child.getKey()));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(SelectCity.this, cities);
            rView.setAdapter(rcAdapter);
        }
    };
    public Handler sHandler = new Handler(){
        public void handleMessage(Message msg){
            spinner.setVisibility(View.GONE);
        }
    };
}
