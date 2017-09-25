package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.sansi.acerbilgisayar.budgetpack.R;

public class ActivityDetails extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle b = getIntent().getExtras();
        Log.e("Which day? ",""+b.get("day") );
        tv = (TextView) findViewById(R.id.textView);
        tv.setText(b.get("day").toString());
    }
}
