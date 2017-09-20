package com.sansi.acerbilgisayar.budgetpack.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sansi.acerbilgisayar.budgetpack.Activites.SelectCity;
import com.sansi.acerbilgisayar.budgetpack.Adapters.ListViewAdapter;
import com.sansi.acerbilgisayar.budgetpack.Adapters.RecyclerViewAdapter;
import com.sansi.acerbilgisayar.budgetpack.Classes.City;
import com.sansi.acerbilgisayar.budgetpack.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CitySelectFragment extends Fragment implements SearchView.OnQueryTextListener {
    private Button button2;
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] cityNameList;
    ArrayList<City> arraylist = new ArrayList<City>();
    private ProgressBar spinner;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_city_select, container, false);
        button2 = (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionbInputFragment fragment2 = new OptionbInputFragment();
                FragmentManager manager= getFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        list = (ListView) view.findViewById(R.id.listview);
        spinner = (ProgressBar) view.findViewById(R.id.progressBar);
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
                Log.e("ITEM COUNT",""+arraylist.size());
            }
        }, 3000);

        editsearch = (SearchView) view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
        return view;

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
            adapter = new ListViewAdapter(getContext(), arraylist);
            list.setAdapter(adapter);
        }
    };
    public Handler sHandler = new Handler(){
        public void handleMessage(Message msg){
            spinner.setVisibility(View.GONE);
        }
    };
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }



}
