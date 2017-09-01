package com.vinnstar.myfirstgame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Scanner;

public class Menu_Activity extends AppCompatActivity {

    String level;
    int levelNum;
    private static final String TAG = "Menu_Activity";

    ImageButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
    //Button b2


    Menu_Activity()
    {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_);

        b1 = (ImageButton) findViewById(R.id.button1);
        b2 = (ImageButton) findViewById(R.id.button2);
        b3 = (ImageButton) findViewById(R.id.button3);
        b4 = (ImageButton) findViewById(R.id.button4);
        b5 = (ImageButton) findViewById(R.id.button5);
        b6 = (ImageButton) findViewById(R.id.button6);
        b7 = (ImageButton) findViewById(R.id.button7);
        b8 = (ImageButton) findViewById(R.id.button8);
        b9 = (ImageButton) findViewById(R.id.button9);
        b10 = (ImageButton) findViewById(R.id.button10);

        setLevelsValues();
        setGraphic();
        setButtonHandling();
    }

    private void setLevelsValues()
    {
        //Read level progression.
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        levelNum = sp.getInt("your_int_key", -1);


    }

    private void setGraphic()
    {
        switch (levelNum)
        {
            case 1:     b2.setImageResource(R.drawable.button2locked);
                        b3.setImageResource(R.drawable.button3locked);
                        b4.setImageResource(R.drawable.button4locked);
                        b5.setImageResource(R.drawable.button5locked);
                        b6.setImageResource(R.drawable.button6locked);
                        b7.setImageResource(R.drawable.button7locked);
                        b8.setImageResource(R.drawable.button8locked);
                        b9.setImageResource(R.drawable.button9locked);
                        b10.setImageResource(R.drawable.button10locked);
                break;
            case 2:     b3.setImageResource(R.drawable.button3locked);
                        b4.setImageResource(R.drawable.button4locked);
                        b5.setImageResource(R.drawable.button5locked);
                        b6.setImageResource(R.drawable.button6locked);
                        b7.setImageResource(R.drawable.button7locked);
                        b8.setImageResource(R.drawable.button8locked);
                        b9.setImageResource(R.drawable.button9locked);
                        b10.setImageResource(R.drawable.button10locked);
                break;
            case 3:     b4.setImageResource(R.drawable.button4locked);
                        b5.setImageResource(R.drawable.button5locked);
                        b6.setImageResource(R.drawable.button6locked);
                        b7.setImageResource(R.drawable.button7locked);
                        b8.setImageResource(R.drawable.button8locked);
                        b9.setImageResource(R.drawable.button9locked);
                        b10.setImageResource(R.drawable.button10locked);
                break;
            case 4:     b5.setImageResource(R.drawable.button5locked);
                        b6.setImageResource(R.drawable.button6locked);
                        b7.setImageResource(R.drawable.button7locked);
                        b8.setImageResource(R.drawable.button8locked);
                        b9.setImageResource(R.drawable.button9locked);
                        b10.setImageResource(R.drawable.button10locked);
                break;
            case 5:     b6.setImageResource(R.drawable.button6locked);
                        b7.setImageResource(R.drawable.button7locked);
                        b8.setImageResource(R.drawable.button8locked);
                        b9.setImageResource(R.drawable.button9locked);
                        b10.setImageResource(R.drawable.button10locked);
                break;
            case 6:     b7.setImageResource(R.drawable.button7locked);
                        b8.setImageResource(R.drawable.button8locked);
                        b9.setImageResource(R.drawable.button9locked);
                        b10.setImageResource(R.drawable.button10locked);
                break;
            case 7:     b8.setImageResource(R.drawable.button8locked);
                        b9.setImageResource(R.drawable.button9locked);
                        b10.setImageResource(R.drawable.button10locked);
                break;
            case 8:     b9.setImageResource(R.drawable.button9locked);
                        b10.setImageResource(R.drawable.button10locked);
                break;
            case 9:     b10.setImageResource(R.drawable.button10locked);
                break;
            case 10:
                break;
            default:    b2.setImageResource(R.drawable.button2locked);
                        b3.setImageResource(R.drawable.button3locked);
                        b4.setImageResource(R.drawable.button4locked);
                        b5.setImageResource(R.drawable.button5locked);
                        b6.setImageResource(R.drawable.button6locked);
                        b7.setImageResource(R.drawable.button7locked);
                        b8.setImageResource(R.drawable.button8locked);
                        b9.setImageResource(R.drawable.button9locked);
                        b10.setImageResource(R.drawable.button10locked);
                break;
        }
    }

    private void setButtonHandling()
    {
        switch (levelNum)
        {
            case 1:     b1.setOnClickListener(myhandler1);
                break;
            case 2:     b1.setOnClickListener(myhandler1);
                        b2.setOnClickListener(myhandler2);
                break;
            case 3:     b1.setOnClickListener(myhandler1);
                        b2.setOnClickListener(myhandler2);
                        b3.setOnClickListener(myhandler3);
                break;
            case 4:     b1.setOnClickListener(myhandler1);
                        b2.setOnClickListener(myhandler2);
                        b3.setOnClickListener(myhandler3);
                        b4.setOnClickListener(myhandler4);
                break;
            case 5:     b1.setOnClickListener(myhandler1);
                        b2.setOnClickListener(myhandler2);
                        b3.setOnClickListener(myhandler3);
                        b4.setOnClickListener(myhandler4);
                        b5.setOnClickListener(myhandler5);
                break;
            case 6:     b1.setOnClickListener(myhandler1);
                        b2.setOnClickListener(myhandler2);
                        b3.setOnClickListener(myhandler3);
                        b4.setOnClickListener(myhandler4);
                        b5.setOnClickListener(myhandler5);
                        b6.setOnClickListener(myhandler6);
                break;
            case 7:     b1.setOnClickListener(myhandler1);
                        b2.setOnClickListener(myhandler2);
                        b3.setOnClickListener(myhandler3);
                        b4.setOnClickListener(myhandler4);
                        b5.setOnClickListener(myhandler5);
                        b6.setOnClickListener(myhandler6);
                        b7.setOnClickListener(myhandler7);
                break;
            case 8:     b1.setOnClickListener(myhandler1);
                        b2.setOnClickListener(myhandler2);
                        b3.setOnClickListener(myhandler3);
                        b4.setOnClickListener(myhandler4);
                        b5.setOnClickListener(myhandler5);
                        b6.setOnClickListener(myhandler6);
                        b7.setOnClickListener(myhandler7);
                        b8.setOnClickListener(myhandler8);
                break;
            case 9:     b1.setOnClickListener(myhandler1);
                        b2.setOnClickListener(myhandler2);
                        b3.setOnClickListener(myhandler3);
                        b4.setOnClickListener(myhandler4);
                        b5.setOnClickListener(myhandler5);
                        b6.setOnClickListener(myhandler6);
                        b7.setOnClickListener(myhandler7);
                        b8.setOnClickListener(myhandler8);
                        b9.setOnClickListener(myhandler9);
                break;
            case 10:    b1.setOnClickListener(myhandler1);
                        b2.setOnClickListener(myhandler2);
                        b3.setOnClickListener(myhandler3);
                        b4.setOnClickListener(myhandler4);
                        b5.setOnClickListener(myhandler5);
                        b6.setOnClickListener(myhandler6);
                        b7.setOnClickListener(myhandler7);
                        b8.setOnClickListener(myhandler8);
                        b9.setOnClickListener(myhandler9);
                        b10.setOnClickListener(myhandler10);
                break;
            default:    b1.setOnClickListener(myhandler1);
                break;
        }
    }

    View.OnClickListener myhandler1 = new View.OnClickListener() {

        public void onClick(View v) {
            // it was the 1st button
            Log.d(TAG,"button 1");

            SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("level_selected", 1);
            editor.commit();

            switchActivity();

        }
    };

    View.OnClickListener myhandler2 = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 2nd button
            Log.d(TAG,"button 2");

            SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("level_selected", 2);
            editor.commit();

            switchActivity();
        }
    };

    View.OnClickListener myhandler3 = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d(TAG,"button 3");

            SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("level_selected", 3);
            editor.commit();

            switchActivity();
        }
    };

    View.OnClickListener myhandler4 = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 2nd button
        }
    };

    View.OnClickListener myhandler5 = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 2nd button
        }
    };

    View.OnClickListener myhandler6 = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 2nd button
        }
    };

    View.OnClickListener myhandler7 = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 2nd button
        }
    };

    View.OnClickListener myhandler8 = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 2nd button
        }
    };

    View.OnClickListener myhandler9 = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 2nd button
        }
    };

    View.OnClickListener myhandler10 = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 2nd button
        }
    };


    private void switchActivity()
     {
                Intent intent = new Intent(this, Game.class);
                startActivity(intent);
     }
}
