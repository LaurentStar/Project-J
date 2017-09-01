package com.vinnstar.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
//Please see laurent's style guide for coding structure and naming in the DataClass.java file
/**
 * Created by Laurent on 4/1/2017.
 */
public class ObjectFloatingIceBlock extends GameObject{

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

    //Special int for the object
    //High lights an aren that is safe or unsafe for the player
    private int _get_To_Point_X;
    private int _get_To_Point_Y;
    private int _get_To_Point_H;
    private int _get_To_Point_W;
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


    //Special Boolean for the object
    private boolean _anti_Crushed_Point = false;
    private boolean _move_Faster = false;

    private Bitmap spriteSheet;

    //Testing only
    private Paint paint = new Paint();





    ///////////////
    //Constructor//
    ///////////////
    public ObjectFloatingIceBlock(Bitmap res) {

        ////////////////////////////
        // SPECIAL CLASS DEFINING //
        ////////////////////////////
        spriteSheet = res;
        spriteSheet = Bitmap.createScaledBitmap(spriteSheet,GamePanel.WIDTH/(100/50), GamePanel.WIDTH/(100/50), false);

        paint.setColor(Color.WHITE);
        ////////////////////////////SPECIAL///////////////////////////////
        _core_X_Hitbox_2 = GamePanel.WIDTH + GamePanel.WIDTH/(100/50) ;
        _core_Y_Hitbox_3 = GamePanel.HEIGHT + GamePanel.HEIGHT/(100/50);
        //////////////////////////////////////////////////////////////////

        /////////////////////
        //INTEGERS DEFINING//
        /////////////////////
        _core_W_Hitbox_6 = GamePanel.WIDTH/(100/50);
        _core_H_Hitbox_7 = GamePanel.WIDTH/(100/50);
        _core_X_Coord_0 = (int) _core_X_Hitbox_2;
        _core_Y_Coord_1 = (int) _core_Y_Hitbox_3;
        _core_Height_2 = 0;
        _core_Width_3 = 0;
        _core_Gravity_Type_4 = Static.FIXED_GRAVITY;
        _core_Object_Type_5 = Static.FLOATINGICEBLOCK;
        _core_Collision_Status_8 = Static.MIDAIR;
        _core_Object_Status_9 = Static.OBJECT_STATUS_ACTIVE;
        _core_Object_State_10 =  Static.GENERAL_OBJECT_STATE_NEUTRAL;
        _core_Degree_Gravity_11 = 0; //0 degrees on the unit circle going counter clockwise
        _core_Magnitude_12 = 0; // You didn't know what else to put
        _core_Object_Sub_Animation_State_14 = 0;
        /////////////////////
        //DOUBLE DEFINING  //
        /////////////////////
        _core_X_Vel_0 = 0;
        _core_Y_Vel_1 = 0;
        _core_X_Hitbox_2 = GamePanel.WIDTH + GamePanel.WIDTH/(100/50) ;
        _core_Y_Hitbox_3 = GamePanel.HEIGHT + GamePanel.HEIGHT/(100/50);

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

    }

    public void variableUpdate(long deltatick) {

        //Update the collision box cordinates & move the character
      if(_move_Faster != true) {
          _core_X_Hitbox_2 += _core_X_Vel_0 * (deltatick / 1000.f);
          _core_Y_Hitbox_3 += _core_Y_Vel_1 * (deltatick / 1000.f);
      }
      else{
          _core_X_Hitbox_2 += 4*_core_X_Vel_0 * (deltatick / 1000.f);
          _core_Y_Hitbox_3 += 4*_core_Y_Vel_1 * (deltatick / 1000.f);
      }
        //Place the image accordingly
        _core_X_Coord_0 = (int)_core_X_Hitbox_2 - _core_Width_3;
        _core_Y_Coord_1 = (int)_core_Y_Hitbox_3 - _core_Height_2;
    }


    //////////////////////////////////
    //   FLAGS  IMPLEMENTATION      //
    //////////////////////////////////
    //Leader method
    public void eventFlags() {

        applySpecialFlagTrigger();
    }

    public void applySpecialFlagTrigger() {

        switch (_core_Object_State_10)
        {
            case Static.GENERAL_OBJECT_STATE_ACTIVE:

                //1st FLAG///If this object goes above or below the active border
                if (_core_Y_Hitbox_3 > GamePanel.HEIGHT + _core_H_Hitbox_7 *2 ) {
                    //Bottom border
                    _core_Y_Vel_1 = 0;
                    _core_X_Vel_0 = 0;
                    _core_Object_State_10 = Static.GENERAL_OBJECT_STATE_NEUTRAL;
                    _anti_Crushed_Point = true;
                }
                else if (_core_Y_Hitbox_3 + _core_H_Hitbox_7 < 0 - _core_H_Hitbox_7 *2) {
                    //Top border
                    _core_Y_Vel_1 = 0;
                    _core_X_Vel_0 = 0;
                    _core_Object_State_10 = Static.GENERAL_OBJECT_STATE_NEUTRAL;
                    _anti_Crushed_Point = true;
                }
                if (_core_X_Hitbox_2 + _core_W_Hitbox_6 < 0 - _core_W_Hitbox_6 *2 ) {
                    //left border
                    _core_Y_Vel_1 = 0;
                    _core_X_Vel_0 = 0;
                    _core_Object_State_10 = Static.GENERAL_OBJECT_STATE_NEUTRAL;
                    _anti_Crushed_Point = true;
                }
                else if (_core_X_Hitbox_2 > GamePanel.WIDTH + _core_W_Hitbox_6 *2 ) {
                    //Top border
                    _core_Y_Vel_1 = 0;
                    _core_X_Vel_0 = 0;
                    _core_Object_State_10 = Static.GENERAL_OBJECT_STATE_NEUTRAL;
                    _anti_Crushed_Point = true;
                }


                /////////////////////////////////////////////////
                /////////////////////////////////////////////////
                if (_core_Y_Hitbox_3 > GamePanel.HEIGHT ) {
                    //Bottom border
                    _move_Faster = true;
                }
                else if (_core_Y_Hitbox_3 + _core_H_Hitbox_7 < 0) {
                    //Top border
                    _move_Faster = true;
                }
                if (_core_X_Hitbox_2 + _core_W_Hitbox_6 < 0 ) {
                    //left border
                    _move_Faster = true;
                }
                else if (_core_X_Hitbox_2 > GamePanel.WIDTH  ) {
                    //Top border
                    _move_Faster = true;
                }


                /////////////////////////////////////////////////
                /////////////////////////////////////////////////
                if ((_core_Y_Hitbox_3 < GamePanel.HEIGHT ) && (_core_Y_Hitbox_3 + _core_H_Hitbox_7 > 0)){

                    if ((_core_X_Hitbox_2 + _core_W_Hitbox_6 > 0 ) && (_core_X_Hitbox_2 < GamePanel.WIDTH )){
                        _move_Faster = false;
                    }

                }

                break;
            case Static.GENERAL_OBJECT_STATE_NEUTRAL:
                _core_X_Hitbox_2 = GamePanel.WIDTH + _core_W_Hitbox_6 *2;
                _core_Y_Hitbox_3 = GamePanel.HEIGHT + _core_H_Hitbox_7 *2;
                _core_Magnitude_12 =0;
                _core_X_Vel_0 = 0;
                _core_Y_Vel_1 = 0;
                _move_Faster = false;
                _get_To_Point_X = _core_X_Coord_0;
                _get_To_Point_Y = _core_Y_Coord_1;
                _get_To_Point_H = 0;
                _get_To_Point_W = 0;
                break;
        }
    }


    //////////////////////////////////
    //          SETTER METHODS      //
    //////////////////////////////////

    public void setSpecialBooleanVar(boolean value)
    {
        _anti_Crushed_Point = value;
    }

    public void setObjectState(int value)
    {
        _core_Object_State_10 = value;
    }


    public void setOffScreenProjectile(int borderlocation, int tuninglocation, int degree, int magnitude) {

        switch (borderlocation)
        {
            case 0: //LEFT BORDER
                _core_X_Hitbox_2 = 0 - _core_W_Hitbox_6;
                _core_Y_Hitbox_3 = tuninglocation;
                if(tuninglocation < 0){
                    _core_Y_Hitbox_3 = (GamePanel.HEIGHT + tuninglocation);
                }
                _core_X_Vel_0 = Math.cos(degree * Math.PI/180) * magnitude;
                _core_Y_Vel_1 = -Math.sin(degree * Math.PI/180) * magnitude;
                _core_Degree_Gravity_11 = degree;
                _core_Magnitude_12 = magnitude;
                break;
            case 1: //TOP BORDER
                _core_Y_Hitbox_3 = 0 - _core_H_Hitbox_7;
                _core_X_Hitbox_2 =  tuninglocation;
                if(tuninglocation < 0){_core_X_Hitbox_2 = GamePanel.WIDTH + tuninglocation;}
                _core_X_Vel_0 = Math.cos(degree * Math.PI/180) * magnitude;
                _core_Y_Vel_1 = -Math.sin(degree * Math.PI/180) * magnitude;
                _core_Degree_Gravity_11 = degree;
                _core_Magnitude_12 = magnitude;
                break;
            case 2: //RIGHT BORDER
                _core_X_Hitbox_2 = GamePanel.WIDTH + _core_W_Hitbox_6;
                _core_Y_Hitbox_3 = tuninglocation;
                if(tuninglocation < 0){_core_Y_Hitbox_3 = GamePanel.HEIGHT + tuninglocation;}
                _core_X_Vel_0 = Math.cos(degree * Math.PI/180) * magnitude;
                _core_Y_Vel_1 = -Math.sin(degree * Math.PI/180) * magnitude;
                _core_Degree_Gravity_11 = degree;
                _core_Magnitude_12 = magnitude;
                break;
            case 3: //BOTTOM BORDER
                _core_Y_Hitbox_3 = GamePanel.HEIGHT + _core_H_Hitbox_7;
                _core_X_Hitbox_2 =  tuninglocation;
                if(tuninglocation < 0){_core_X_Hitbox_2 = GamePanel.WIDTH + tuninglocation;}
                _core_X_Vel_0 = Math.cos(degree * Math.PI/180) * magnitude;
                _core_Y_Vel_1 = -Math.sin(degree * Math.PI/180) * magnitude;
                _core_Degree_Gravity_11 = degree;
                _core_Magnitude_12 = magnitude;
                break;
        }
    }

    public void setGetToPoint(int x, int y, int h, int w, int color){
        _get_To_Point_X = x;
        _get_To_Point_Y = y;
        _get_To_Point_H = h;
        _get_To_Point_W = w;

        if(x < 0){_get_To_Point_X = GamePanel.WIDTH + x;}
        if(y < 0){_get_To_Point_Y = GamePanel.HEIGHT + y;}
        paint.setColor(color);
        paint.setAlpha(100);
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

    public boolean getSpecialBooleanVar() {return _anti_Crushed_Point;}

    //////////////////////////////////
    //     Animation & Drawing      //
    //////////////////////////////////
    //Leader method
    public void draw(Canvas canvas) {
        canvas.drawBitmap( spriteSheet , _core_X_Coord_0, _core_Y_Coord_1, null);
        canvas.drawRect(_get_To_Point_X, _get_To_Point_Y, _get_To_Point_X + _get_To_Point_W,_get_To_Point_Y + _get_To_Point_H, paint);
    }
}
