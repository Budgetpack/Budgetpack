package com.sansi.acerbilgisayar.budgetpack.Activites;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.sansi.acerbilgisayar.budgetpack.R;


/**
 * Created by Acer Bilgisayar on 20.7.2017.
 */


public class Intro extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("title 1","descrp 1", R.drawable.backpack , Color.parseColor("#000000")));
        addSlide(AppIntroFragment.newInstance("title 2", "descrpt 2", R.drawable.backpack, Color.parseColor(("#000000"))));


    }
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
