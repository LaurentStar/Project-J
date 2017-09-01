package com.vinnstar.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Laurent on 7/6/2016.
 */
public abstract class GameObject {


    DataClass nothing;



    //Default
    public GameObject(){}


    //Hit box
    public GameObject(int x , int y, int w, int h){}

    //Player
    public GameObject(Bitmap res, GlobalFunctions globalFunctions, ActivitySwitch activitySwitch){}

    //Background Simple
    public GameObject(Bitmap res){}

    //Background  horizon effect.  //Floating Ice block 3rd int = direction, 4th int = magnitude
    public GameObject(Bitmap res, int mark, int dimensionW, int dimensionH, int pdimensionH){}

    //Crystal Point
    public GameObject(Bitmap res, int w, int h){}




    /////////////////////////////////////////////////////////////////////////////
    //////////                          METHODS                      ////////////
    /////////////////////////////////////////////////////////////////////////////

    public void variableUpdate(){}
    public void variableUpdate(long deltaTicks){}

    public void objectPhysics(int totalobjectcollided, ArrayList<int[]> collisiondetails, long deltaticks){}
    public void objectPhysics(int totalobjectcollided, ArrayList<int[]> collisiondetails, ArrayList<boolean[]> collisiondetails2, long deltaticks){}
    public void objectPhysics(int totalobjectcollided, ArrayList<int[]> collisiondetails, ArrayList<boolean[]> collisiondetails2, ArrayList<int[][]> jaggedarray, long deltaticks){}

    public void objectPhysics(long deltaticks){}

    public void eventFlags(int totalobjectcollided, ArrayList<int[]> collisiondetails, ArrayList<boolean[]> collisiondetails2, ArrayList<int[][]> jaggedarray){}
    public void eventFlags(int totalobjectcollided , ArrayList<int[]> collisiondetails, ArrayList<boolean[]> collisiondetails2){}
    public void eventFlags(int totalobjectcollided , ArrayList<int[]> collisiondetails){}
    public void eventFlags(){}

    public void aiLogic(int[] aidetails , long deltatick){}

    public void returnToMenu() {}

    //////////////////////////////////////
    //          Setter methods          //
    //////////////////////////////////////
    public void setIntialOptions(){}
    public void setIntialValues(int array[]){}
    public void setSpecialBooleanVar(boolean value){}
    public void setSpecialIntVar(int value) {}
    public void setDegreeMagnitude(int degree, int magnitude){}

    public void setTurnOffTimerActions(int value){}

    //Almost the same as set Objectstate except exclusive to the player.
    public void setPlayerState(int value){}
    public void setObjectState(int value){}

    public void setOffScreenProjectile(int borderlocation, int tuninglocation, int degree, int magnitud){}

    public void setScreenProjectile(int x, int y, int degree, int magnitude){}

    public void setGetToPoint(int x, int y, int h, int w, int color){}

    public void setID(int value){}
    //////////////////////////////
    //       GET FUNCTIONS      //
    //////////////////////////////
    public int[][] getChildrenCollisionDetailsJaggedArray(){return null;}

    public int[] getCollisionDetailsArray(){return null;}
    public int[] getBasicAiInfoArray(){return null;}
    public int[] getObjectOrders(){return null;}

    public boolean[] getTimerActions() {return null;}

    public int getGravityType(){return 0;}
    public int getObjectType(){return 0;}
    public int getObjectStatus(){return 0;}
    public int getObjectState(){return 0;}

    public boolean getSpecialBooleanVar() {return false;}


    public DataClass getDataClass(){return nothing;}

    //////////////////////////////
    //       DRAW FUNCTIONS     //
    //////////////////////////////
    public void draw(Canvas canvas) {

    }
}
