package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sansi.acerbilgisayar.budgetpack.Adapters.ListViewAdapter;
import com.sansi.acerbilgisayar.budgetpack.Classes.City;
import com.sansi.acerbilgisayar.budgetpack.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity implements SearchView.OnQueryTextListener/*implements CitySelectFragment.DataCommunication*/{

   /* private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            CitySelectFragment firstFragment = new CitySelectFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

    @Override
    public String getCityName(){
        return name;
    }
    @Override
    public void setCityName(String x){
        this.name=x;
    }*/
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    ArrayList<City> arraylist = new ArrayList<City>();
    private ProgressBar spinner;
    CharSequence query;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main2);
       editsearch = (SearchView) findViewById(R.id.search);
       list = (ListView) findViewById(R.id.listview);
       spinner = (ProgressBar)findViewById(R.id.progressBar);
       spinner.setVisibility(View.VISIBLE);

       readFromDatabase();

       new Timer().schedule(new TimerTask() {
           @Override
           public void run() {
               for(int i=0;i<arraylist.size();i++) {
                   //Log.e("delay log", "" + cities.get(i));
                   mHandler.obtainMessage(1).sendToTarget();
                   sHandler.obtainMessage(1).sendToTarget();
               }
               // Log.e("ITEM COUNT",""+arraylist.size());
           }
       }, 3000);

       editsearch.setOnQueryTextListener(this);

       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String s = arraylist.get(position).toString();
               Log.e("onClick:",""+s);
               arraylist = new ArrayList<City>();
               Intent intent = new Intent(Main2Activity.this, MainActivity.class);
               startActivity(intent);


           }
       });
   }
    public void readFromDatabase(){
        Query myQuery = myRef.child("Cities");
        myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    arraylist.add(new City(child.getKey()));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            adapter = new ListViewAdapter(Main2Activity.this , arraylist);
            list.setAdapter(adapter);
        }
    };

    public Handler sHandler = new Handler(){
        public void handleMessage(Message msg){
            spinner.setVisibility(View.GONE);
        }
    };

    @Override
    public boolean onQueryTextSubmit(String queryy) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }

}

