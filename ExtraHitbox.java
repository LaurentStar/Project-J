package com.vinnstar.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Laurent on 3/5/2017.
 */

//Made for adding special collision detection properties to objects
public class ExtraHitbox {



    /*//////////////////////////////////////////
    //                  Arrays                //
    //////////////////////////////////////////*/
    private int[] _collision_Details = new int[10];
    //0 collision status
    //1 the type of object
    //2 the state of the object
    //3 Magnitude
    //4 Direction
    //5 the hit box X
    //6 the hit box y
    //7 the hit box w
    //8 the hit box h
    //9 object ID;

    ///////////////////////////////////////////
    //          INTEGER DECLARATION          //
    ///////////////////////////////////////////
    private int _core_X_Coord_0;
    private int _core_Y_Coord_1;
    private int _core_Height_2;
    private int _core_Width_3;
    private int _core_Gravity_Type_4;
    private int _core_Object_Type_5;
    private int _core_W_Hitbox_6;
    private int _core_H_Hitbox_7;
    private int _core_Collision_Status_8;
    private int _core_Object_Status_9;
    private int _core_Object_State_10;
    private int _core_Degree_Gravity_11;
    private int _core_Magnitude_12;
    private int _core_ID_13;
    private int _core_Object_Sub_Animation_State_14;

    //Special int variables
    //Master type
    //Master ID
    private int _master_State;//Master state
    //Master Child personal id
    //Master position set // The position set detirments the width and lenght of the child.


    ///////////////////////////////////////////
    //          DOUBLE DECLARATION           //
    ///////////////////////////////////////////
    private double _core_X_Vel_0;
    private double _core_Y_Vel_1;
    private double _core_X_Hitbox_2;
    private double _core_Y_Hitbox_3;

    ///////////////////////////////////////////
    //          BOOLEAN DECLARATION          //
    ///////////////////////////////////////////
    private boolean _core_Collided_Bottom_0;
    private boolean _core_Collided_Top_1;
    private boolean _core_Collided_Left_2;
    private boolean _core_Collided_Right_3;
    private boolean _core_Object_State_Change_4;





    //Testing only
    private Paint paint = new Paint();





    ///////////////
    //Constructor//
    ///////////////
    public ExtraHitbox() {

        ////////////////////////////
        // SPECIAL CLASS DEFINING //
        ////////////////////////////

        paint.setColor(Color.WHITE);
        ////////////////////////////SPECIAL///////////////////////////////
        _core_X_Hitbox_2 = GamePanel.WIDTH + GamePanel.WIDTH/(100/10);
        _core_Y_Hitbox_3 = GamePanel.HEIGHT + GamePanel.HEIGHT/(100/50);
        //////////////////////////////////////////////////////////////////

        /////////////////////
        //INTEGERS DEFINING//
        /////////////////////
        _core_W_Hitbox_6 = GamePanel.WIDTH/(100/11);
        _core_H_Hitbox_7 = GamePanel.WIDTH/(100/12);
        _core_X_Coord_0 = (int) _core_X_Hitbox_2;
        _core_Y_Coord_1 = (int) _core_Y_Hitbox_3;
        _core_Height_2 = GamePanel.HEIGHT/(100/16); //Only take up 10% of the screen
        _core_Width_3 = GamePanel.WIDTH/(100/11); //Only take up 10% of the screen
        _core_Gravity_Type_4 = Static.DYNAMIC_GRAVITY;
        _core_Object_Type_5 = Static.EXTRAHITBOX;
        _core_Collision_Status_8 = Static.MIDAIR;
        _core_Object_Status_9 = Static.OBJECT_STATUS_ACTIVE;
        _core_Object_State_10 =  Static.GENERAL_OBJECT_STATE_NEUTRAL;
        _core_Degree_Gravity_11 = 180; //180 degrees on the unit circle going counter clockwise
        _core_Magnitude_12 = 0;
        _core_Object_Sub_Animation_State_14 = 0;

        /////////////////////
        //DOUBLE DEFINING  //
        /////////////////////
        _core_X_Vel_0 = 0;
        _core_Y_Vel_1 = 0;
        _core_X_Hitbox_2 = GamePanel.WIDTH + GamePanel.WIDTH/(100/10) ;
        _core_Y_Hitbox_3 = GamePanel.HEIGHT + GamePanel.HEIGHT/(100/10);

        /////////////////////
        //BOOLEAN DEFINING //
        /////////////////////
        _core_Collided_Bottom_0 = false;
        _core_Collided_Top_1 = false;
        _core_Collided_Left_2 = false;
        _core_Collided_Right_3 = false;
        _core_Object_State_Change_4 = false;


        /////////////////////
        //  MISC DEFINING  //
        /////////////////////
        paint.setColor(Color.WHITE);

    }

    public void variableUpdate(int array[]) {

        _core_X_Hitbox_2 = array[0];
        _core_Y_Hitbox_3 = array[1];
        _core_Object_State_10 = array[3];
        _master_State= array[4];

    }


    //////////////////////////////////
    //   FLAGS  IMPLEMENTATION      //
    //////////////////////////////////
    //Leader method
    public void eventFlags(int totalobjectcollided , ArrayList<int[]> collisiondetails) {


        for (int i = 0; i < totalobjectcollided; i++) {
            applyCollisionFlagTrigger(collisiondetails.get(i));
        }

    }

    public void applyCollisionFlagTrigger(int collisiondetails[]){

        switch (collisiondetails[0])
        {
            case Static.MIDAIR:
                break;

            case Static.COLLISION_2D_PIERCE:
                applyCollisionFlagTriggerPierce(collisiondetails);
                break;
        }
    }
    public void applyCollisionFlagTriggerPierce(int collisiondetails[]){
        switch (collisiondetails[1])
        {
            case Static.FIREBALL: _core_Object_State_10 = Static.PLAYER_STATE_DEAD;
                break;
            case Static.FLOATINGICEBLOCK:


                break;
            case Static.BLUEFIREBALL:

                break;
            case Static.ENERGYICESPHERE:

                break;
            case Static.BALLISTICMETALBOX:
                //_core_Object_State_10 = Static.PLAYER_STATE_DEAD;
                break;
        }
    }



    //////////////////////////////////
    //          SETTER METHODS      //
    //////////////////////////////////

    public void setObjectState(int value)
    {
        _core_Object_State_10 = value;
    }


    public void setScreenProjectile(int x, int y, int degree, int force) {

        _core_X_Hitbox_2 = x;
        _core_Y_Hitbox_3 = y;

        //Place the image accordingly
        _core_X_Coord_0 = (int)_core_X_Hitbox_2 - _core_Width_3;
        _core_Y_Coord_1 = (int)_core_Y_Hitbox_3 - _core_Height_2;

        //JUMPING FORCE. This causes the project to do an initial hop.
        //_core_Y_Vel_1 -= force;

        // _core_X_Vel_0 = Math.cos(_core_Degree_Gravity_11 * Math.PI/180) * _core_Magnitude_12;
        // _core_Y_Vel_1 = -Math.sin(_core_Degree_Gravity_11 * Math.PI/180) * _core_Magnitude_12;

    }



    public void setID(int value){_core_ID_13 = value;}


    //////////////////////////////////
    //          GETTER METHODS      //
    //////////////////////////////////

    public int[] getCollisionDetailsArray(){
        _collision_Details[0] = _core_Collision_Status_8;
        _collision_Details[1] = _core_Object_Type_5;
        _collision_Details[2] = _core_Object_State_10;
        _collision_Details[3] = _core_Magnitude_12;
        _collision_Details[4] = _core_Degree_Gravity_11;
        _collision_Details[5] = (int)_core_X_Hitbox_2;
        _collision_Details[6] = (int)_core_Y_Hitbox_3;
        _collision_Details[7] = _core_W_Hitbox_6;
        _collision_Details[8] = _core_H_Hitbox_7;
        _collision_Details[9] = _core_ID_13;
        return _collision_Details;}

    public int getGravityType(){return _core_Gravity_Type_4;}
    public int getObjectType(){return _core_Object_Type_5;}
    public int getObjectStatus(){return _core_Object_Status_9;}
    public int getObjectState(){return _core_Object_State_10;}


    //////////////////////////////////
    //          MISC METHODS       //
    //////////////////////////////////


    //////////////////////////////////
    //     Animation & Drawing      //
    //////////////////////////////////
    //Leader method
    public void draw(Canvas canvas) {
        canvas.drawRect((int) _core_X_Hitbox_2, (int) _core_Y_Hitbox_3, (int) _core_X_Hitbox_2 + _core_W_Hitbox_6, (int) _core_Y_Hitbox_3 + _core_H_Hitbox_7, paint);

    }
}
