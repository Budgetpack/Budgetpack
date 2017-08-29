package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.sansi.acerbilgisayar.budgetpack.R;

import org.w3c.dom.Text;

public class FindPlan extends AppCompatActivity {
    TextView budgetText;
    TextView startDateText;
    TextView endDateText;
    TextView typeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_plan);

        budgetText = (TextView) findViewById(R.id.budgetTxt);
        startDateText = (TextView) findViewById(R.id.startDateTxt);
        endDateText = (TextView) findViewById(R.id.endDateTxt);
        typeText = (TextView) findViewById(R.id.typeTxt);

        Bundle b = getIntent().getExtras();

        String budget = b.getString("budget");
        String currency = b.getString("currency");
        String type = b.getString("type");

        int startDay = b.getInt("startDay");
        int startMonth = b.getInt("startmonth");
        int startYear = b.getInt("startyear");

        int endDay = b.getInt("endday");
        int endMonth = b.getInt("endmonth");
        int endYear = b.getInt("endyear");

        Log.e("startday = ", " " + startDay);
        Log.e("startmonth = ", " " + startMonth);
        Log.e("startyear = ", " " + startYear);
        Log.e("endday = ", " " + endDay);
        Log.e("endmonth = ", " " + endMonth);
        Log.e("endyear = ", " " + endYear);

        budgetText.setText("Your budget is "+budget+" "+currency);
        startDateText.setText("Starting date of your trip: "+startDay+"/"+startMonth+"/"+startYear);
        endDateText.setText("Ending date of your trip: "+endDay+"/"+endMonth+"/"+endYear);
        typeText.setText("Preferred type of your trip: "+type);

    }
}
