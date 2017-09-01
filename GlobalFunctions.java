package com.vinnstar.myfirstgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;


/**
 * Created by Laurent on 1/13/2017.
 */
public class GlobalFunctions {

    protected int screen_touch;
    private static final String TAG = "GamePanel";
    protected String level;
    protected int levelNum;

    public GlobalFunctions()
    {
        screen_touch = MotionEvent.ACTION_UP;
    }

    void setScreenTouch(int setter){
        screen_touch = setter;
        Log.d(TAG,"Setter was set");

    }
    void setlevelNum(int levelNum) {
        this.levelNum = levelNum;

    }

    int twoDSquareObjectDetection(int Vx, int Vy, int Vw, int Vh, int Sx, int Sy, int Sw, int Sh ) {


        //The sides of the rectangles
        int S_left, S_right, S_top, S_bottom;
        int V_left, V_right, V_top, V_bottom;

        //Calculate the sides of rect S
        S_left = Sx;
        S_right = Sx + Sw;
        S_top = Sy;
        S_bottom = Sy + Sh;

        //Calculate the sides of rect V
        V_left = Vx;
        V_right = Vx + Vw;
        V_top = Vy;
        V_bottom = Vy + Vh;


        if(( S_bottom > V_bottom ) &&(S_top < V_top  ) &&( S_right > V_right ) && ( S_left < V_left ) )
        {
            return Static.COLLISION_2D_INSIDE;
        }
        //When the object is inside
        if(( S_bottom > V_top + 2) &&(S_top < V_bottom - 2  ) &&( S_right > V_left + 2 ) && ( S_left < V_right - 2 ) )
        {
            return Static.COLLISION_2D_PIERCE;
        }
        //When the object is against the sides
        if(( S_bottom >= V_top  ) &&(S_top <= V_bottom  ) &&( S_right >= V_left ) && ( S_left <= V_right ) )
        {
            return Static.COLLISION_2D_TOUCH;
        }

        // May be upgraded later. Collision for circles yo be added later.
        return Static.MIDAIR;
    }

    boolean[] pointOfContactDetection(int Vx, int Vy, int Vw, int Vh, int Sx, int Sy, int Sw, int Sh, int collisiondetails ) {

        boolean[] collisionsidetouched =  new boolean[4];


        //The sides of the rectangles
        int  S_right, S_bottom;
        int  V_right,  V_bottom;

        //Calculate the sides of rect S Omega
        S_right = Sx + Sw;
        S_bottom = Sy + Sh;

        //Calculate the sides of rect V Alpha
        V_right = Vx + Vw;
        V_bottom = Vy + Vh;


        switch (collisiondetails)
        {
            case Static.COLLISION_2D_PIERCE:
                //Detect whih side the player is closest too L or R

                //LEFT
                if (  V_right - (Sx+2)  <= Vw )
                {
                    collisionsidetouched[0]= true;

                    if( Vy + Vh - Sy <= Vh)
                    {
                        collisionsidetouched[1]= true;
                    }
                    else if((S_bottom - 2) - Vy  <= Vh)
                    {
                        collisionsidetouched[3]= true;
                    }
                }
                //RIGHT
                else if(( S_right -2) - Vx <= Vw )
                {
                    collisionsidetouched[2]= true;

                    if( Vy + Vh - Sy <= Vh)
                    {
                        collisionsidetouched[1]= true;
                    }
                    else if((S_bottom - 2) - Vy  <= Vh)
                    {
                        collisionsidetouched[3]= true;
                    }
                }
                //MIDDLE
                else if ((V_right < S_right) && (Vx > Sx))
                {
                    if(( Vy + Vh - Sy ) < (Vh))
                    {
                        collisionsidetouched[1]= true;
                    }
                    else if((S_bottom - 2) - Vy  <= Vh)
                    {
                        collisionsidetouched[3]= true;
                    }
                }
                break;
            case Static.COLLISION_2D_TOUCH:

                //LEFT
                if(( V_right) == (Sx))
                {
                    collisionsidetouched[0]= true;

                    if(( Vy + Vh ) == (Sy))
                    {
                        collisionsidetouched[1]= true;
                    }
                    else if( Vy == S_bottom)
                    {
                        collisionsidetouched[3]= true;
                    }
                }
                //RIGHT
                else if ((Vx) == (S_right))
                {
                    collisionsidetouched[2]= true;

                    if(( Vy + Vh ) == (Sy))
                    {
                        collisionsidetouched[1]= true;
                    }
                    else if( Vy == S_bottom)
                    {
                        collisionsidetouched[3]= true;
                    }
                }
                //MIDDLE
                else if ((V_right < S_right) && (Vx > Sx))
                {
                    if(( Vy + Vh) == (Sy))
                    {
                        collisionsidetouched[1]= true;
                    }
                    else if(( V_bottom - Vh) == (S_bottom))
                    {
                        collisionsidetouched[3]= true;
                    }
                }

                break;
        }

        // May be upgraded later. Collision for circles yo be added later.
        return collisionsidetouched;
    }

    int getScreenTouch(){
        return screen_touch;
    }
    int getLevelNum() {

        return levelNum;
    }

}
