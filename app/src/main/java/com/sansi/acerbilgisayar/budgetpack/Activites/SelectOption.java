package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.sansi.acerbilgisayar.budgetpack.R;

public class SelectOption extends AppCompatActivity {

    Button btnTop, btnDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_option);

        btnTop=(Button) findViewById(R.id.buttonTop);
        btnDown=(Button) findViewById(R.id.buttonDown);

        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectOption.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectOption.this, SelectCity.class);
                startActivity(intent);
            }
        });

    }
}
