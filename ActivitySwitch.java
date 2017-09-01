package com.vinnstar.myfirstgame;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

import java.util.TimerTask;

/**
 * Created by Laurent on 3/4/2017.
 */
public class ActivitySwitch {

    private final Context context;


    public ActivitySwitch(Context context)
    {
        this.context = context;
    }



   public void switchActivity()
    {
        Intent intent = new Intent(context, Menu_Activity.class);
        context.startActivity(intent);
    }

}
