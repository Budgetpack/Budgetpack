package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sansi.acerbilgisayar.budgetpack.R;

import org.w3c.dom.Text;

public class FindPlan extends AppCompatActivity {
    TextView budgetText;
    TextView startDateText;
    TextView endDateText;
    TextView typeText, diffText, firebaseText;

    String budget, currency, type;
    int startDay, startMonth, startYear, endDay, endMonth, endYear;
    long diff;
    int dailyBudget;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_plan);

        budgetText = (TextView) findViewById(R.id.budgetTxt);
        startDateText = (TextView) findViewById(R.id.startDateTxt);
        endDateText = (TextView) findViewById(R.id.endDateTxt);
        typeText = (TextView) findViewById(R.id.typeTxt);
        diffText = (TextView) findViewById(R.id.diffTxt);
        firebaseText = (TextView) findViewById(R.id.firebaseTxt);

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
        /*Log.e("startday = ", " " + startDay);
        Log.e("startmonth = ", " " + startMonth);
        Log.e("startyear = ", " " + startYear);
        Log.e("endday = ", " " + endDay);
        Log.e("endmonth = ", " " + endMonth);
        Log.e("endyear = ", " " + endYear);*/

        dailyBudget = (int) calculateDailyBudget(budget, diff);
        //Log.e("daily budget :",""+dailyBudget);

        budgetText.setText("Your budget is "+budget+" "+currency);
        startDateText.setText("Starting date of your trip: "+startDay+"/"+startMonth+"/"+startYear);
        endDateText.setText("Ending date of your trip: "+endDay+"/"+endMonth+"/"+endYear);
        typeText.setText("Preferred type of your trip: "+type+"");
        diffText.setText("Days: "+diff+"");

        Query myQuery = myRef.child("Cities");
        myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if(Integer.valueOf(child.child("budget").getValue().toString()) < dailyBudget){
                        Log.e("Cities: ", ""+child.getKey()+" Budget: "+child.child("budget").getValue());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        /*myRef.child("Cities").child("Barcelona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long budget = (Long) dataSnapshot.child("budget").getValue();
                firebaseText.setText("Budget for Barcelona: "+budget.toString()+"");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("firebase error log", "");
            }
        });*/


    }
    public double calculateDailyBudget(String b, long diff){
        int budget = Integer.parseInt(b);

        return (double) budget/diff;
    }
}
