package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sansi.acerbilgisayar.budgetpack.Classes.Event;
import com.sansi.acerbilgisayar.budgetpack.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ActivityDetails extends AppCompatActivity {
    private LinearLayout linearLayout;
    TextView dayText,contentText;
    View childLayout;
    List<Event> arraylist = new ArrayList<Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_details);

        Bundle b = getIntent().getExtras();
        Log.e("Which day? ",""+b.get("day") );

        linearLayout = (LinearLayout) findViewById(R.id.linear);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        childLayout = inflater.inflate(R.layout.card_layout, (ViewGroup) findViewById(R.id.child_id),false);
        linearLayout.addView(childLayout);
        dayText = (TextView) childLayout.findViewById(R.id.daytag);
        contentText = (TextView) childLayout.findViewById(R.id.contentText);
        contentText.setText("");
        dayText.setText("Day "+b.get("day").toString());
        Log.e("test","outside");
//        Intent intent = getIntent();
//        if(intent != null && intent.hasExtra("array")){
//            Log.e("test","inside");
//            arraylist = intent.getParcelableExtra("array");
//            Log.e("test","array size "+arraylist.size());
//        }else{
//            Log.e("test","else");
//        }

        String eventsList = getIntent().getStringExtra("arraylist");

        Gson gson = new Gson();
        Type type = new TypeToken<List<Event>>(){}.getType();
        List<Event> eventList = gson.fromJson(eventsList, type);
//        for (Event event : eventList){
//            Log.e("test","event name "+ event.getName());
//        }

        //for(int i=0; i< eventList.size(); i++){
        int i = 0;
        for (Event event : eventList){
            childLayout = inflater.inflate(R.layout.card_layout_details, (ViewGroup) findViewById(R.id.child_id),false);
            linearLayout.addView(childLayout);
            dayText = (TextView) childLayout.findViewById(R.id.daytag);
            contentText = (TextView) childLayout.findViewById(R.id.contentText);
            dayText.setId(i);
            int j = i+1;
            dayText.setText(""+event.getName());
            contentText.setText("Price: "+event.getPrice().toString());
            i++;

        }

    }
    public void handleOnClick(View view) {
        switch(view.getId()){
            case R.id.daytag:
                Log.e("day: ","blabla");
                break;
            default:
                Log.e("onClick", "switchcase"+view.getId());
                break;
        }


    }
    private void duduboi(){
        //
        //
    }
}
