package com.sansi.acerbilgisayar.budgetpack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    TextView welcomeText;
    EditText budgetText;
    Button findplanButton;
    Spinner currencySpinner;

    private String[] currencies = {"USD", "TRY", "EUR"};
    private String str;
    private String curr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeText = (TextView) findViewById(R.id.welcomeTxt);
        budgetText = (EditText) findViewById(R.id.budgetTxt);
        findplanButton = (Button) findViewById(R.id.findBtn);
        currencySpinner = (Spinner) findViewById(R.id.spinner);

        welcomeText.setText("Welcome to Budgetpack!");


        findplanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = budgetText.getText().toString();
                Toast msg = Toast.makeText(getBaseContext(),"Budget:"+str+" Currency:"+curr, Toast.LENGTH_SHORT);
                msg.show();
            }
        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(dataAdapter);

        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               curr = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
