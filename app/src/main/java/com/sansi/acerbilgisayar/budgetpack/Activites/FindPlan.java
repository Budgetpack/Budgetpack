package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.sansi.acerbilgisayar.budgetpack.R;

import org.w3c.dom.Text;

public class FindPlan extends AppCompatActivity {
    TextView budgetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_plan);

        budgetText = (TextView) findViewById(R.id.budgetTxt);

        Bundle b = getIntent().getExtras();

        String budget = b.getString("budget");
        String currency = b.getString("currency");

        budgetText.setText("Your budget is "+budget+" "+currency);
    }
}
