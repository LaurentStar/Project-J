package com.vinnstar.myfirstgame;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.*;
/**
 * Created by Laurent on 3/24/2017.
 */
public class ReadFile {

    Context context;
    GlobalFunctions globalFunctions;
    String level;
    int levelNum;
    ReadFile(Context context,   GlobalFunctions globalFunctions)
    {
        this.context = context;
        this.globalFunctions = globalFunctions;
        readLevelNum();
    }

    public void readLevelNum() {

        /*Scanner s = new Scanner(context.getResources().openRawResource(R.raw.level_management));

        try {
            while (s.hasNext()) {
                level = s.next();
                levelNum = Integer.parseInt(level);
                globalFunctions.setlevelNum(levelNum);
                // ....
            }
        } finally {
            s.close();
        }*/


        SharedPreferences sp = context.getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);

        //WRITE
        //SharedPreferences.Editor editor = sp.edit();
       // editor.putInt("your_int_key", 2);
        //editor.commit();

       // editor.putInt("level_selected", 1);
        //editor.commit();

        //READ
        //levelNum = sp.getInt("your_int_key", -1);
        levelNum = sp.getInt("level_selected", -1);
        globalFunctions.setlevelNum(levelNum);


    }

    public void saveLevelProgress(int progress) {
        SharedPreferences sp = context.getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("your_int_key", progress);
        editor.commit();
    }

    public int returnProgress()
    {
        SharedPreferences sp = context.getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        return sp.getInt("your_int_key", -1);
    }

}
