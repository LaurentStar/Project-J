package com.vinnstar.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


import java.util.ArrayList;
import java.util.Random;
//Please see laurent's style guide for coding structure and naming in the DataClass.java file
/**
 * Created by Laurent on 7/7/2016.
 */
public class ObjectFireBall extends GameObject {

    Random rand =  new Random();
    /*//////////////////////////////////////////
    //                  Arrays               //
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
    //9 Object ID

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

    //Special Int for the player

    ///////////////////////////////////////////
    //          DOUBLE DECLARATION           //
    ///////////////////////////////////////////
    private double[] _core_Doubles_Array = new double[2];
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

    //Special Boolean for the player
    private boolean _dodge_Point = false;

    ///////////////////////////////////////////
    //          CLASS DECLARATION            //
    ///////////////////////////////////////////
    private ExtraHitbox _core_Game_Object_Hitbox;
    private ArrayList<ExtraHitbox> _Game_Object_Hitbox = new ArrayList<ExtraHitbox>();
    private DataClass _core_Data = new DataClass();
    private Bitmap spriteSheet;

    //Testing only
    private Paint paint = new Paint();





    ///////////////
    //Constructor//
    ///////////////
    public ObjectFireBall(Bitmap res){

        ////////////////////////////
        // SPECIAL CLASS DEFINING //
        ////////////////////////////
        spriteSheet = res;
        spriteSheet = Bitmap.createScaledBitmap( spriteSheet, GamePanel.WIDTH/(100/15), GamePanel.HEIGHT/(100/22), false);


        /////////////////////////////SPECIAL///////////////////////////////////////
        _core_X_Hitbox_2 = GamePanel.WIDTH + GamePanel.WIDTH/(100/10);
        _core_Y_Hitbox_3 = rand.nextInt(GamePanel.HEIGHT);
        /////////////////////////////SPECIAL//////////////////////////////////////



        /////////////////////
        //INTEGERS DEFINING//
        /////////////////////
        _core_W_Hitbox_6 = GamePanel.WIDTH/(100/10);
        _core_H_Hitbox_7 = GamePanel.HEIGHT/(100/10);
        _core_X_Coord_0 = (int) _core_X_Hitbox_2;
        _core_Y_Coord_1 = (int) _core_Y_Hitbox_3;
        _core_Height_2 = GamePanel.HEIGHT/(100/8); //Only take up 10% of the screen
        _core_Width_3 = GamePanel.WIDTH/(100/3); //Only take up 10% of the screen
        _core_Gravity_Type_4 = Static.DYNAMIC_GRAVITY;
        _core_Object_Type_5 = Static.FIREBALL;
        _core_Collision_Status_8 = Static.MIDAIR;
        _core_Object_Status_9 = Static.OBJECT_STATUS_ACTIVE;
        _core_Object_State_10 =  Static.GENERAL_OBJECT_STATE_ACTIVE;
        _core_Degree_Gravity_11 = 180; //180 degrees on the unit circle going counter clockwise
        _core_Magnitude_12 = 50;
        _core_Object_Sub_Animation_State_14 = 0;

        /////////////////////
        //DOUBLE DEFINING  //
        /////////////////////
        _core_X_Vel_0 = 0;
        _core_Y_Vel_1 = 0;
        _core_X_Hitbox_2 = GamePanel.WIDTH + GamePanel.WIDTH/(100/10);
        _core_Y_Hitbox_3 = rand.nextInt(GamePanel.HEIGHT);

        /////////////////////
        //BOOLEAN DEFINING //
        /////////////////////
        _core_Collided_Bottom_0 = false;
        _core_Collided_Top_1 = false;
        _core_Collided_Left_2 = false;
        _core_Collided_Right_3 = false;
        _core_Object_State_Change_4 = false;


        /////////////////////
        //HITBOX DEFINING  //
        /////////////////////
        _core_Game_Object_Hitbox = new ExtraHitbox((int)_core_X_Hitbox_2, (int)_core_Y_Hitbox_3, _core_W_Hitbox_6, _core_H_Hitbox_7);


        /////////////////////
        //  MISC DEFINING  //
        /////////////////////
        paint.setColor(Color.WHITE);

        ///////////////////////SPECIAL////////////////////////////////////



        ///////////////////////////
        //  DATA CLASS DEFINING  //
        ///////////////////////////
        //Integers
        _core_Data._core_X_Coord_0 = _core_X_Coord_0;
        _core_Data._core_Y_Coord_1 = _core_Y_Coord_1;
        _core_Data._core_Height_2 = _core_Height_2;
        _core_Data._core_Width_3 = _core_Width_3;
        _core_Data._core_Gravity_Type_4 = _core_Gravity_Type_4;
        _core_Data._core_Object_Type_5 = _core_Object_Type_5;
        _core_Data._core_W_Hitbox_6 = _core_W_Hitbox_6;
        _core_Data._core_H_Hitbox_7 = _core_H_Hitbox_7;
        _core_Data._core_Collision_Status_8 = _core_Collision_Status_8;
        _core_Data._core_Object_Status_9 = _core_Object_Status_9;
        _core_Data._core_Object_State_10 = _core_Object_State_10;
        _core_Data._core_Degree_Gravity_11 = _core_Degree_Gravity_11;
        _core_Data._core_Magnitude_12 = _core_Magnitude_12;
        //Doubles
        _core_Data._core_X_Vel_0 = _core_X_Vel_0;
        _core_Data._core_Y_Vel_1 = _core_Y_Vel_1;
        _core_Data._core_X_Hitbox_2 = _core_X_Hitbox_2;
        _core_Data._core_Y_Hitbox_3 = _core_Y_Hitbox_3;
        //Boolean
        _core_Data._core_Collided_Bottom_0 = _core_Collided_Bottom_0;
        _core_Data._core_Collided_Top_1 = _core_Collided_Top_1;
        _core_Data._core_Collided_Left_2 = _core_Collided_Left_2;
        _core_Data._core_Collided_Right_3 = _core_Collided_Right_3;
        _core_Data._core_Object_State_Change_4 = _core_Object_State_Change_4;

    }

    public void variableUpdate(long deltatick) {


        //Update the collision box cordinates
        _core_X_Hitbox_2 += _core_X_Vel_0 * (deltatick / 1000.f);
        _core_Y_Hitbox_3 += _core_Y_Vel_1 * (deltatick / 1000.f);

        //Move Object
        _core_X_Coord_0 = (int)_core_X_Hitbox_2 - _core_Width_3;
        _core_Y_Coord_1 = (int)_core_Y_Hitbox_3 - _core_Height_2;

    }


    //////////////////////////
    //PHYSICS IMPLEMENTATION//
    //////////////////////////
    //Leader method
    public void objectPhysics(int totalobjectcollided,  ArrayList<int[]> collisiondetails, long deltaticks) {
        //0 #Get collision status
        //1 #Get the type of object that was collided with
        //2 #Get the state of the object that collided with
        //3 #Get X Coordinates,
        //4 # Get Y Coordinates
        //5 #Get Magnitude
        //6 #Get Direction

        //This seems like the friendly case to use it.
        short collisiondetected = Static.MIDAIR;

        if(collisiondetails != null) {
            for (int i = 0; i < totalobjectcollided; i++) {
                if (collisiondetails.get(i)[0] == Static.COLLISION_2D_PIERCE) {
                    collisiondetected = Static.COLLISION_2D_PIERCE;
                    break;
                }
                if (collisiondetails.get(i)[0] == Static.COLLISION_2D_TOUCH) {
                    collisiondetected = Static.COLLISION_2D_TOUCH;
                    break;
                }
            }
        }

        switch (collisiondetected) {
            case Static.MIDAIR:
                _core_X_Vel_0 += Math.cos(_core_Degree_Gravity_11 * Static.DEG) * Static.LIGHTER_GRAVITY * (deltaticks / 1000.f);
                _core_Y_Vel_1 += -Math.sin(_core_Degree_Gravity_11 * Static.DEG) * Static.LIGHTER_GRAVITY * (deltaticks / 1000.f);
                break;

            case Static.COLLISION_2D_TOUCH:
                _core_X_Vel_0 += Math.cos(_core_Degree_Gravity_11 * Static.DEG) * Static.GRAVITY * (deltaticks / 1000.f);
                _core_Y_Vel_1 += -Math.sin(_core_Degree_Gravity_11 * Static.DEG) * Static.GRAVITY * (deltaticks / 1000.f);
                break;

            case Static.COLLISION_2D_PIERCE:
                _core_X_Vel_0 += Math.cos(_core_Degree_Gravity_11 * Static.DEG) * Static.LIGHTER_GRAVITY1 * (deltaticks / 1000.f);
                _core_Y_Vel_1 += -Math.sin(_core_Degree_Gravity_11 * Static.DEG) * Static.LIGHTER_GRAVITY1 * (deltaticks / 1000.f);
                break;
        }

    }


    //////////////////////////////////
    //   FLAGS  IMPLEMENTATION      //
    //////////////////////////////////
    //Leader method
    public void eventFlags() {
        //1ST APPLY FLAG TRIGGGERS FROM COLLISIONS


        //FIRST
        applySpecialFlagTrigger();

    }

    public void applySpecialFlagTrigger() {

        switch (_core_Object_State_10) {
            case Static.GENERAL_OBJECT_STATE_ACTIVE:
                //1ST FLAG
                //If this object goes above or below the screen border
                if (_core_Y_Hitbox_3 + _core_H_Hitbox_7 > GamePanel.HEIGHT ) {
                    //Bottom border
                    _core_Y_Vel_1 = 0;
                    _core_Y_Hitbox_3 = GamePanel.HEIGHT - _core_H_Hitbox_7;
                }
                else if (_core_Y_Hitbox_3 < 0) {
                    //Top border
                    _core_Y_Vel_1 = 0;
                    _core_Y_Hitbox_3 = 0;
                }

               //When the object goes out of sight
                if(_core_X_Hitbox_2 + _core_W_Hitbox_6 < 0)
                {
                    _core_X_Vel_0 = 0;
                    _core_X_Hitbox_2 = GamePanel.WIDTH + _core_W_Hitbox_6;
                    _core_Y_Hitbox_3 =  rand.nextInt(GamePanel.HEIGHT);

                    // One point is rewarded
                    _dodge_Point = true;
                }

                break;
        }
    }


    //////////////////////////////////
    //          SETTER METHODS      //
    //////////////////////////////////
    //NEW
    //Leader method
    public void setSpecialBooleanVar(boolean value)
    {
        _dodge_Point = value;
    }
    public void setID(int value){_core_ID_13 = value;}

    //////////////////////////////////
    //          GETTER METHODS      //
    //////////////////////////////////

    //Leader Method
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

    //Leader method
    public int getObjectType(){return _core_Object_Type_5;}

    public boolean getSpecialBooleanVar() {return _dodge_Point;}

    public DataClass getDataClass(){
        _core_Data._core_X_Coord_0 = _core_X_Coord_0;
        _core_Data._core_Y_Coord_1 = _core_Y_Coord_1;
        _core_Data._core_Height_2 = _core_Height_2;
        _core_Data._core_Width_3 = _core_Width_3;
        _core_Data._core_Gravity_Type_4 = _core_Gravity_Type_4;
        _core_Data._core_Object_Type_5 = _core_Object_Type_5;
        _core_Data._core_W_Hitbox_6 = _core_W_Hitbox_6;
        _core_Data._core_H_Hitbox_7 = _core_H_Hitbox_7;
        _core_Data._core_Collision_Status_8 = _core_Collision_Status_8;
        _core_Data._core_Object_Status_9 = _core_Object_Status_9;
        _core_Data._core_Object_State_10 = _core_Object_State_10;
        _core_Data._core_Degree_Gravity_11 = _core_Degree_Gravity_11;
        _core_Data._core_Magnitude_12 = _core_Magnitude_12;
        //Doubles
        _core_Data._core_X_Vel_0 = _core_X_Vel_0;
        _core_Data._core_Y_Vel_1 = _core_Y_Vel_1;
        _core_Data._core_X_Hitbox_2 = _core_X_Hitbox_2;
        _core_Data._core_Y_Hitbox_3 = _core_Y_Hitbox_3;
        //Boolean
        _core_Data._core_Collided_Bottom_0 = _core_Collided_Bottom_0;
        _core_Data._core_Collided_Top_1 = _core_Collided_Top_1;
        _core_Data._core_Collided_Left_2 = _core_Collided_Left_2;
        _core_Data._core_Collided_Right_3 = _core_Collided_Right_3;
        _core_Data._core_Object_State_Change_4 = _core_Object_State_Change_4;
        return _core_Data;}


    //////////////////////////////////
    //     Animation & Drawing      //
    //////////////////////////////////
    //Leader method
    public void draw(Canvas canvas) {
        canvas.drawBitmap(spriteSheet, _core_X_Coord_0, _core_Y_Coord_1, null);
        canvas.drawRect((int)_core_X_Hitbox_2, (int)_core_Y_Hitbox_3,(int)_core_X_Hitbox_2 + _core_W_Hitbox_6,(int)_core_Y_Hitbox_3 + _core_H_Hitbox_7, paint);
    }
}

