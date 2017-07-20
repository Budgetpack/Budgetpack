package com.sansi.acerbilgisayar.budgetpack;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    TextView welcomeText;
    EditText budgetText;
    Button findplanButton;
    Spinner currencySpinner;
    DatePickerDialog datePickerDialog;
    Calendar calendar;
    ImageButton startDateButton;
    ImageButton endDateButton;
    TextView startDateText;
    TextView endDateText;

    static final int DATE_DIALOG_ID = 999;
    private String[] currencies = {"USD", "TRY", "EUR"};
    private String str;
    private String curr;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeText = (TextView) findViewById(R.id.welcomeTxt);
        budgetText = (EditText) findViewById(R.id.budgetTxt);
        findplanButton = (Button) findViewById(R.id.findBtn);
        currencySpinner = (Spinner) findViewById(R.id.spinner);
        startDateText = (TextView) findViewById(R.id.startDateTxt);
        endDateText = (TextView) findViewById(R.id.endDateTxt);
        startDateButton = (ImageButton) findViewById(R.id.startDateButton);
        endDateButton = (ImageButton) findViewById(R.id.endDateButton);


        welcomeText.setText("Welcome to Budgetpack!");



        findplanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = budgetText.getText().toString();
                Toast msg = Toast.makeText(getBaseContext(),"Budget:"+str+" Currency:"+curr, Toast.LENGTH_SHORT);
                msg.show();
            }
        });

        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(v);
            }
        });
        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(v);
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
    public void datePicker(View view){

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"date");
    }
    private void setStartDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        ((TextView) findViewById(R.id.startDateTxt))
                .setText(dateFormat.format(calendar.getTime()));

    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        Calendar cal = new GregorianCalendar(year, month, day);
        setStartDate(cal);
    }
}
