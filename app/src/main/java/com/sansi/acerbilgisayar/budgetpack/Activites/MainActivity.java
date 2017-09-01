package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sansi.acerbilgisayar.budgetpack.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    TextView welcomeText;
    EditText budgetText;
    Button findplanButton;
    Spinner currencySpinner;
    Spinner typeSpinner;
    Calendar calendar, calendar1, calendar2;
    ImageButton startDateButton;
    ImageButton endDateButton;
    TextView startDateText;
    TextView endDateText;

    private String[] currencies = {"USD", "TRY", "EUR"};
    private String[] types = {"Doesn't matter", "Nightlife", "Historical Landmarks", "Cuisine", "Leisure", "Religion"};
    private String str;
    private String curr;
    private String type;

    private int startYear, startMonth, startDay;
    private int endYear, endMonth, endDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeText = (TextView) findViewById(R.id.welcomeTxt);
        budgetText = (EditText) findViewById(R.id.budgetTxt);
        findplanButton = (Button) findViewById(R.id.findBtn);
        currencySpinner = (Spinner) findViewById(R.id.spinner);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        startDateText = (TextView) findViewById(R.id.startDateTxt);
        endDateText = (TextView) findViewById(R.id.endDateTxt);
        startDateButton = (ImageButton) findViewById(R.id.startDateButton);
        endDateButton = (ImageButton) findViewById(R.id.endDateButton);


        welcomeText.setText("Welcome to Budgetpack!");
        calendar = Calendar.getInstance();

        findplanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str = budgetText.getText().toString();


                /*Log.e("startdate:",""+calendar1.getTime());
                Log.e("enddate:",""+calendar2.getTime());*/
                //Log.e("diff:",""+diffInDays);
                /*Log.e("startday = ", " " + startDay);
                Log.e("startmonth = ", " " + startMonth);
                Log.e("startyear = ", " " + startYear);
                Log.e("endday = ", " " + endDay);
                Log.e("endmonth = ", " " + endMonth);
                Log.e("endyear = ", " " + endYear);*/

                if(inputCheck(str, startDay, endDay)) {

                    Intent intent = new Intent(MainActivity.this, FindPlan.class);
                    intent.putExtra("budget", str);
                    intent.putExtra("currency", curr);
                    intent.putExtra("type", type);
                    intent.putExtra("startDay", startDay);
                    intent.putExtra("startmonth", startMonth);
                    intent.putExtra("startyear", startYear);
                    intent.putExtra("endday", endDay);
                    intent.putExtra("endmonth", endMonth);
                    intent.putExtra("endyear", endYear);
                    long diffInDays = (calendar2.getTimeInMillis() - calendar1.getTimeInMillis() ) / (1000 * 60 * 60 * 24) ;
                    intent.putExtra("diff", diffInDays);
                    startActivity(intent);
                }
                /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("startday", startDay);*/

            }
        });
        
        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener  date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        calendar1= Calendar.getInstance();
                        calendar1.set(Calendar.YEAR, year);
                        calendar1.set(Calendar.MONTH, monthOfYear);
                        calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "dd/MM/yy";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        startDay = dayOfMonth;
                        startMonth = monthOfYear;
                        startYear = year;

                        startDateText.setText(sdf.format(calendar1.getTime()));

                    }

                };
                new DatePickerDialog(MainActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener  date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        calendar2 = Calendar.getInstance();
                        calendar2.set(Calendar.YEAR, year);
                        calendar2.set(Calendar.MONTH, monthOfYear);
                        calendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "dd/MM/yy";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        endDay = dayOfMonth;
                        endMonth = monthOfYear;
                        endYear = year;

                        endDateText.setText(sdf.format(calendar2.getTime()));

                    }

                };
                if(startDay != 0) {
                    new DatePickerDialog(MainActivity.this, date, calendar1
                            .get(Calendar.YEAR), calendar1.get(Calendar.MONTH),
                            calendar1.get(Calendar.DAY_OF_MONTH)).show();
                }else{
                    Toast msg = Toast.makeText(getBaseContext(),"Ending Date Input Error", Toast.LENGTH_SHORT);
                    msg.show();
                }

            }
        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        currencySpinner.setAdapter(dataAdapter);
        typeSpinner.setAdapter(dataAdapter2);

        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               curr = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public boolean inputCheck(String b, int startday, int endday){
        if(b.equals("")){
            Toast msg = Toast.makeText(getBaseContext(),"Budget Input Error", Toast.LENGTH_SHORT);
            msg.show();
            return false;
        }
        else if(startday == 0){
            Toast msg = Toast.makeText(getBaseContext(),"Starting Date Input Error", Toast.LENGTH_SHORT);
            msg.show();
            return false;
        }
        else if(endday == 0){
            Toast msg = Toast.makeText(getBaseContext(),"Ending Date Input Error", Toast.LENGTH_SHORT);
            msg.show();
            return false;
        }
        return true;
    }

}
