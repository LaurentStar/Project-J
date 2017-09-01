package com.vinnstar.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Laurent on 6/29/2017.
 */
public class ObjectMetalBallisticBox extends GameObject {


    /*////////////////////////////////////////////
    //               Jagged Array               //
    ////////////////////////////////////////////*/
    private int[][] _extra_Hitbox_Order = new int[5][];
    // The instructions for setting up other objects. [0][n] row is used for the initial instruction with [n][n] row + colums
    // is being used for the finer details for each objects that needs to be spawned as a child.
    //First Array of the jagged arrays
    // 0 - 0 the number of objects to spawn
    // 0 - 1 the master's type
    // 0 - 2 the master's ID number
    // 0 - 3 the master's state
    // 0 - 4 th3 master's status
    //The other Arrays
    // 1 - 0 x coord
    // 1 - 1 y coord
    // 1 - 2 position dimension set.
    // 1 - 3 child state
    // 1 - 4 Child Personal ID



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


    //Special Boolean for the object

    //INTERNAL
    private int _animation_Cell_Frame;
    private int _position_Dimension_Set;
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
    private boolean _move_Faster = false;



    private Animation[] _animation_Cells = new Animation[6];


    private Bitmap spriteSheet;

    //Testing only
    private Paint paint = new Paint();





    ///////////////
    //Constructor//
    ///////////////
    public ObjectMetalBallisticBox(Bitmap res) {

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
        _core_Object_Type_5 = Static.BALLISTICMETALBOX;
        _core_Collision_Status_8 = Static.MIDAIR;
        _core_Object_Status_9 = Static.OBJECT_STATUS_ACTIVE;
        _core_Object_State_10 =  Static.GENERAL_OBJECT_STATE_NEUTRAL;
        _core_Degree_Gravity_11 = 180; //180 degrees on the unit circle going counter clockwise
        _core_Magnitude_12 = 0;
        _core_Object_Sub_Animation_State_14 = 0;

        //Sepcial Int
        _position_Dimension_Set = 0;

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

        /////////////////////////////////
        //    JAGGED ARRAY DEFINING    //
        /////////////////////////////////
        _extra_Hitboxes[0] = new int[6];
        _extra_Hitboxes[1] = new int[6];
        _extra_Hitboxes[2] = new int[6];
        _extra_Hitboxes[3] = new int[6];

        //0 x coord
        // 0 - 1 y coord
        // 0 - 2 position set.
        // 0 - 3 child state
        // 0 - 4 Master state
        // 0 - 5 Child Personal ID


        _extra_Hitboxes[0][0] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6/(100/10);
        _extra_Hitboxes[0][1] = (int)_core_Y_Hitbox_3 - _core_H_Hitbox_7/(100/50) - _core_H_Hitbox_7/(100/20);
        _extra_Hitboxes[0][2] = 0;
        _extra_Hitboxes[0][3] = Static.CHILD_OBJECT_STATE_ACTIVE;
        _extra_Hitboxes[0][4] = _core_Object_State_10;
        _extra_Hitboxes[0][5] = 0;

        _extra_Hitboxes[0][7] = _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/30);
        _extra_Hitboxes[0][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/20);


        _extra_Hitboxes[1][0] = 0;
        _extra_Hitboxes[1][1] = Static.SWITCH;
        _extra_Hitboxes[1][2] = Static.CHILD_OBJECT_STATE_OFF;
        _extra_Hitboxes[1][3] = _core_ID_13;
        _extra_Hitboxes[1][4] = Static.BALLISTICMETALBOX;;
        _extra_Hitboxes[1][5] = (int)_core_X_Hitbox_2  - _core_W_Hitbox_6/(100/50) - _core_W_Hitbox_6/(100/20);;
        _extra_Hitboxes[1][6] = (int)_core_Y_Hitbox_3  + _core_H_Hitbox_7 /(100/7);
        _extra_Hitboxes[1][7] = _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/20);
        _extra_Hitboxes[1][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/30);

        _extra_Hitboxes[2][0] = 0;
        _extra_Hitboxes[2][1] = Static.SWITCH;
        _extra_Hitboxes[2][2] = Static.CHILD_OBJECT_STATE_OFF;
        _extra_Hitboxes[2][3] = _core_ID_13;
        _extra_Hitboxes[2][4] = Static.BALLISTICMETALBOX;;
        _extra_Hitboxes[2][5] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6;
        _extra_Hitboxes[2][6] = (int)_core_Y_Hitbox_3 + _core_H_Hitbox_7 /(100/7);
        _extra_Hitboxes[2][7] = _extra_Hitboxes[2][5] + _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/20);;
        _extra_Hitboxes[2][8] = _extra_Hitboxes[2][6] + _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/30);

        _extra_Hitboxes[3][0] = 0;
        _extra_Hitboxes[3][1] = Static.SWITCH;
        _extra_Hitboxes[3][2] = Static.CHILD_OBJECT_STATE_OFF;
        _extra_Hitboxes[3][3] = _core_ID_13;
        _extra_Hitboxes[3][4] = Static.BALLISTICMETALBOX;;
        _extra_Hitboxes[3][5] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6/(100/10);
        _extra_Hitboxes[3][6] = (int)_core_Y_Hitbox_3 + _core_H_Hitbox_7;
        _extra_Hitboxes[3][7] = _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/30);
        _extra_Hitboxes[3][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/20);



        /////////////////////
        //  MISC DEFINING  //
        /////////////////////
        spriteSheet = res;
        setAnimations();
        paint.setColor(Color.WHITE);

    }

    public void variableUpdate(long deltatick) {
        _animation_Cells[5].update();

        //Update the collision box cordinates & move the character
        if(_move_Faster != true) {
            _core_X_Hitbox_2 += _core_X_Vel_0 * (deltatick / 1000.f);
            _core_Y_Hitbox_3 += _core_Y_Vel_1 * (deltatick / 1000.f);
        }
        else{
            _core_X_Hitbox_2 += _core_X_Vel_0 * (deltatick / 1000.f);
            _core_Y_Hitbox_3 += 1*_core_Y_Vel_1 * (deltatick / 1000.f);
        }
        //Place the image accordingly
        _core_X_Coord_0 = (int)_core_X_Hitbox_2 - _core_Width_3;
        _core_Y_Coord_1 = (int)_core_Y_Hitbox_3 - _core_Height_2;


        //00 CALIBRATED
       /*/ _extra_Hitboxes[0][0] = 0;
        _extra_Hitboxes[0][1] = Static.SWITCH;
        _extra_Hitboxes[0][2] = Static.CHILD_OBJECT_STATE_ACTIVE;
        _extra_Hitboxes[0][3] = _core_ID_13;
        _extra_Hitboxes[0][4] = Static.BALLISTICMETALBOX;;
        _extra_Hitboxes[0][5] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6/(100/10);
        _extra_Hitboxes[0][6] = (int)_core_Y_Hitbox_3 - _core_H_Hitbox_7/(100/50) - _core_H_Hitbox_7/(100/20);
        _extra_Hitboxes[0][7] = _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/30);
        _extra_Hitboxes[0][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/20);

      //  _extra_Hitboxes[1][0] = 0;
        _extra_Hitboxes[1][1] = Static.SWITCH;
        _extra_Hitboxes[1][2] = Static.CHILD_OBJECT_STATE_OFF;
        _extra_Hitboxes[1][3] = _core_ID_13;
        _extra_Hitboxes[1][4] = Static.BALLISTICMETALBOX;;
        _extra_Hitboxes[1][5] = (int)_core_X_Hitbox_2  - _core_W_Hitbox_6/(100/50) - _core_W_Hitbox_6/(100/20);;
        _extra_Hitboxes[1][6] = (int)_core_Y_Hitbox_3  + _core_H_Hitbox_7 /(100/7);
        _extra_Hitboxes[1][7] = _core_W_Hitbox_6/(100/50) - _core_W_Hitbox_6/(100/20);
        _extra_Hitboxes[1][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/30);

      //  _extra_Hitboxes[2][0] = 0;
        _extra_Hitboxes[2][1] = Static.SWITCH;
        _extra_Hitboxes[2][2] = Static.CHILD_OBJECT_STATE_OFF;
        _extra_Hitboxes[2][3] = _core_ID_13;
        _extra_Hitboxes[2][4] = Static.BALLISTICMETALBOX;;
        _extra_Hitboxes[2][5] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6;
        _extra_Hitboxes[2][6] = (int)_core_Y_Hitbox_3 + _core_H_Hitbox_7 /(100/7);
        _extra_Hitboxes[2][7] = _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/20);;
        _extra_Hitboxes[2][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/30);

      //  _extra_Hitboxes[3][0] = 0;
        _extra_Hitboxes[3][1] = Static.SWITCH;
        _extra_Hitboxes[3][2] = Static.CHILD_OBJECT_STATE_OFF;
        _extra_Hitboxes[3][3] = _core_ID_13;
        _extra_Hitboxes[3][4] = Static.BALLISTICMETALBOX;;
        _extra_Hitboxes[3][5] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6/(100/10);
        _extra_Hitboxes[3][6] = (int)_core_Y_Hitbox_3 + _core_H_Hitbox_7;
        _extra_Hitboxes[3][7] = _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/30);
        _extra_Hitboxes[3][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/20);*/

        switch (_position_Dimension_Set)
        {
            case 0:
                _extra_Hitbox_Order[1][0] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6/(100/10);;
                _extra_Hitbox_Order[1][1] = (int)_core_Y_Hitbox_3 - _core_H_Hitbox_7/(100/50) - _core_H_Hitbox_7/(100/20);
                _extra_Hitboxes[0][7] = _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/30);
                _extra_Hitboxes[0][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/20);

                _extra_Hitbox_Order[2][0] = (int)_core_X_Hitbox_2  - _core_W_Hitbox_6/(100/50) - _core_W_Hitbox_6/(100/20);
                _extra_Hitbox_Order[2][1] = (int)_core_Y_Hitbox_3  + _core_H_Hitbox_7 /(100/7);
                _extra_Hitboxes[1][7] = _core_W_Hitbox_6/(100/50) - _core_W_Hitbox_6/(100/20);
                _extra_Hitboxes[1][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/30);

                _extra_Hitbox_Order[3][0] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6;
                _extra_Hitbox_Order[3][1] = (int)_core_Y_Hitbox_3 + _core_H_Hitbox_7 /(100/7);
                _extra_Hitboxes[2][7] = _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/20);;
                _extra_Hitboxes[2][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/30);

                _extra_Hitbox_Order[4][0] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6/(100/10);
                _extra_Hitbox_Order[4][1] = (int)_core_Y_Hitbox_3 + _core_H_Hitbox_7;
                _extra_Hitboxes[3][7] = _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/30);
                _extra_Hitboxes[3][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/20);*
                break;
        }


    }

    //////////////////////////
    //PHYSICS IMPLEMENTATION//
    //////////////////////////
    //Leader method
    public void objectPhysics( long deltaticks) {
        //FIRST
        applyGravity(deltaticks);

        //SECOND
        applyMiscForces(deltaticks);
    }

    public void applyGravity(long deltaticks){
        _core_X_Vel_0 += Math.cos(_core_Degree_Gravity_11 * Math.PI/180) * 2* (deltaticks / 100000000.f);
        _core_Y_Vel_1 += -Math.sin(_core_Degree_Gravity_11 * Math.PI/180) * Static.SUPER_SOFT_GRAVITY * (deltaticks / 1000.f);
    }//Apply gravity to an object in any direction
    public void applyMiscForces(long deltaticks){

        _core_X_Vel_0 += Math.cos(180 * Math.PI/180) * Static.LIGHTER_GRAVITY * (deltaticks / 1000.f);
        _core_Y_Vel_1 += -Math.sin(180 * Math.PI/180) * Static.LIGHTER_GRAVITY  *  (deltaticks / 1000.f);

    }//Apply forces from not related to anything above


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
                }
                else if (_core_Y_Hitbox_3 + _core_H_Hitbox_7 < 0 - _core_H_Hitbox_7 *2) {
                    //Top border
                    _core_Y_Vel_1 = 0;
                    _core_X_Vel_0 = 0;
                    _core_Object_State_10 = Static.GENERAL_OBJECT_STATE_NEUTRAL;
                }
                if (_core_X_Hitbox_2 + _core_W_Hitbox_6 < 0 - _core_W_Hitbox_6 *2 ) {
                    //left border
                    _core_Y_Vel_1 = 0;
                    _core_X_Vel_0 = 0;
                    _core_Object_State_10 = Static.GENERAL_OBJECT_STATE_NEUTRAL;
                }
                else if (_core_X_Hitbox_2 > GamePanel.WIDTH + _core_W_Hitbox_6 *2 ) {
                    //Top border
                    _core_Y_Vel_1 = 0;
                    _core_X_Vel_0 = 0;
                    _core_Object_State_10 = Static.GENERAL_OBJECT_STATE_NEUTRAL;

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

                break;
        }
    }



    //////////////////////////////////
    //          SETTER METHODS      //
    //////////////////////////////////
    //Leader Method
    public void setIntialOptions()
    {

        // 0 - 0 the number of objects to spawn
        // 0 - 1 the master's type
        // 0 - 2 the master's ID number
        // 0 - 3 the master's state
        // 0 - 4 th3 master's status

        //The other Arrays
        // 1 - 0 x coord
        // 1 - 1 y coord
        // 1 - 2 Width
        // 1 - 3 Height
        // 1 - 4 child state
        // 1 - 5 Child Personal ID

        _extra_Hitbox_Order[0] =  new int[5];
        _extra_Hitbox_Order[1] =  new int[6];
        _extra_Hitbox_Order[2] =  new int[6];
        _extra_Hitbox_Order[3] =  new int[6];
        _extra_Hitbox_Order[4] =  new int[6];


        _extra_Hitbox_Order[0][0] = 4;
        _extra_Hitbox_Order[0][1] = Static.BALLISTICMETALBOX;
        _extra_Hitbox_Order[0][2] = _core_ID_13;
        _extra_Hitbox_Order[0][3] = _core_Object_State_10;
        _extra_Hitbox_Order[0][4] = _core_Object_Status_9;

        _extra_Hitbox_Order[1][0] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6/(100/10);;
        _extra_Hitbox_Order[1][1] = (int)_core_Y_Hitbox_3 - _core_H_Hitbox_7/(100/50) - _core_H_Hitbox_7/(100/20);
        _extra_Hitbox_Order[1][2] = 0;
        _extra_Hitbox_Order[1][3] = Static.GENERAL_OBJECT_STATE_ACTIVE;
        _extra_Hitbox_Order[1][4] = 1;

        _extra_Hitbox_Order[2][0] = (int)_core_X_Hitbox_2  - _core_W_Hitbox_6/(100/50) - _core_W_Hitbox_6/(100/20);
        _extra_Hitbox_Order[2][1] = (int)_core_Y_Hitbox_3  + _core_H_Hitbox_7 /(100/7);
        _extra_Hitbox_Order[2][2] = 0;
        _extra_Hitbox_Order[2][3] = Static.GENERAL_OBJECT_STATE_ACTIVE;
        _extra_Hitbox_Order[2][4] = 2;

        _extra_Hitbox_Order[3][0] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6;
        _extra_Hitbox_Order[3][1] = (int)_core_Y_Hitbox_3 + _core_H_Hitbox_7 /(100/7);
        _extra_Hitbox_Order[3][2] = 0;
        _extra_Hitbox_Order[3][3] = Static.GENERAL_OBJECT_STATE_ACTIVE;
        _extra_Hitbox_Order[3][4] = 3;

        _extra_Hitbox_Order[4][0] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6/(100/10);
        _extra_Hitbox_Order[4][1] = (int)_core_Y_Hitbox_3 + _core_H_Hitbox_7;
        _extra_Hitbox_Order[4][2] = 0;
        _extra_Hitbox_Order[4][3] = Static.GENERAL_OBJECT_STATE_ACTIVE;
        _extra_Hitbox_Order[4][4] = 4;
    }


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
    public int[] getExtraHitboxArray(int value){


        //_extra_Hitboxes[0][0] = 0;
        //_extra_Hitboxes[0][1] = Static.SWITCH;
        _extra_Hitboxes[0][2] = Static.CHILD_OBJECT_STATE_OFF;
        _extra_Hitboxes[0][3] = _core_ID_13;
        _extra_Hitboxes[0][4] = Static.BALLISTICMETALBOX;;
        _extra_Hitboxes[0][5] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6/(100/10);
        _extra_Hitboxes[0][6] = (int)_core_Y_Hitbox_3 - _core_H_Hitbox_7/(100/50) - _core_H_Hitbox_7/(100/20);
        _extra_Hitboxes[0][7] = _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/30);
        _extra_Hitboxes[0][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/20);


       // _extra_Hitboxes[1][0] = 0;
       // _extra_Hitboxes[1][1] = Static.SWITCH;
        //_extra_Hitboxes[1][2] = Static.CHILD_OBJECT_STATE_OFF;
        _extra_Hitboxes[1][3] = _core_ID_13;
        _extra_Hitboxes[1][4] = Static.BALLISTICMETALBOX;;
        _extra_Hitboxes[1][5] = (int)_core_X_Hitbox_2  - _core_W_Hitbox_6/(100/50) - _core_W_Hitbox_6/(100/20);;
        _extra_Hitboxes[1][6] = (int)_core_Y_Hitbox_3  + _core_H_Hitbox_7 /(100/7);
        _extra_Hitboxes[1][7] = _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/20);
        _extra_Hitboxes[1][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/30);

       // _extra_Hitboxes[2][0] = 0;
        //_extra_Hitboxes[2][1] = Static.SWITCH;
        //_extra_Hitboxes[2][2] = Static.CHILD_OBJECT_STATE_OFF;
        _extra_Hitboxes[2][3] = _core_ID_13;
        _extra_Hitboxes[2][4] = Static.BALLISTICMETALBOX;;
        _extra_Hitboxes[2][5] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6;
        _extra_Hitboxes[2][6] = (int)_core_Y_Hitbox_3 + _core_H_Hitbox_7 /(100/7);
        _extra_Hitboxes[2][7] = _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/20);
        _extra_Hitboxes[2][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/30);

        //_extra_Hitboxes[3][0] = 0;
        //_extra_Hitboxes[3][1] = Static.SWITCH;
        //_extra_Hitboxes[3][2] = Static.CHILD_OBJECT_STATE_OFF;
        _extra_Hitboxes[3][3] = _core_ID_13;
        _extra_Hitboxes[3][4] = Static.BALLISTICMETALBOX;;
        _extra_Hitboxes[3][5] = (int)_core_X_Hitbox_2 + _core_W_Hitbox_6/(100/10);
        _extra_Hitboxes[3][6] = (int)_core_Y_Hitbox_3 + _core_H_Hitbox_7;
        _extra_Hitboxes[3][7] = _core_W_Hitbox_6/(100/50) + _core_W_Hitbox_6/(100/30);
        _extra_Hitboxes[3][8] = _core_H_Hitbox_7/(100/50) + _core_H_Hitbox_7/(100/20);

        return _extra_Hitbox_Order[value];
    }

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



    public void setAnimations() {
        //assign all anitions
        for(int i = 0; i < 6; i++) {
            _animation_Cells[i] = new Animation();
        }

        int scaleValue = GamePanel.WIDTH/(100/30);

        //A0 = NO WEAK POINTS
        Bitmap[] A0 = new Bitmap[1];
        A0[0] = Bitmap.createBitmap(spriteSheet, 0, 0, 148, 138);
        A0[0] = Bitmap.createScaledBitmap(A0[0], scaleValue, scaleValue, false);
        _animation_Cells[0].setFrames(A0);
        _animation_Cells[0].setDelay(500);

        //A1 = WEAK POINT RIGHT
        Bitmap[] A1 = new Bitmap[1];
        A1[0] = Bitmap.createBitmap(spriteSheet, 148, 0, 148, 138);
        A1[0] = Bitmap.createScaledBitmap(A1[0], scaleValue, scaleValue, false);
        _animation_Cells[1].setFrames(A1);
        _animation_Cells[1].setDelay(500);

        //A2 = WEAK POINT UP
        Bitmap[] A2 = new Bitmap[1];
        A2[0] = Bitmap.createBitmap(spriteSheet, 148, 0, 148, 138);
        A2[0] = Bitmap.createScaledBitmap(A2[0], scaleValue, scaleValue, false);
        _animation_Cells[2].setFrames(A2);
        _animation_Cells[2].setDelay(500);

        //A3 = WEAK POINT LEFT
        Bitmap[] A3 = new Bitmap[1];
        A3[0] = Bitmap.createBitmap(spriteSheet, 148, 0, 148, 138);
        A3[0] = Bitmap.createScaledBitmap(A3[0], scaleValue, scaleValue, false);
        _animation_Cells[3].setFrames(A3);
        _animation_Cells[3].setDelay(500);

        //A4 = WEAK POINT DOWN
        Bitmap[] A4 = new Bitmap[1];
        A4[0] = Bitmap.createBitmap(spriteSheet, 148, 0, 148, 138);
        A4[0] = Bitmap.createScaledBitmap(A4[0], scaleValue, scaleValue, false);
        _animation_Cells[4].setFrames(A4);
        _animation_Cells[4].setDelay(500);


        //A5 = MOVING WEAK POINT
        Bitmap[] A5 = new Bitmap[4];
        A5[0] = Bitmap.createBitmap(spriteSheet, 148, 0, 148, 138);
        A5[1] = Bitmap.createBitmap(spriteSheet, 148*2, 0, 148, 138);
        A5[2] = Bitmap.createBitmap(spriteSheet, 148*3, 0, 148, 138);
        A5[3] = Bitmap.createBitmap(spriteSheet, 148*4, 0, 148, 138);
        A5[0] = Bitmap.createScaledBitmap(A5[0], scaleValue, scaleValue, false);
        A5[1] = Bitmap.createScaledBitmap(A5[1], scaleValue, scaleValue, false);
        A5[2] = Bitmap.createScaledBitmap(A5[2], scaleValue, scaleValue, false);
        A5[3] = Bitmap.createScaledBitmap(A5[3], scaleValue, scaleValue, false);
        _animation_Cells[5].setFrames(A5);
        _animation_Cells[5].setDelay(400);
    }


    //////////////////////////////////
    //     Animation & Drawing      //
    //////////////////////////////////
    //Leader method
    public void draw(Canvas canvas) {
        canvas.drawBitmap(_animation_Cells[5].getImage(), _core_X_Coord_0, _core_Y_Coord_1, null);
           // canvas.drawRect((int)_core_X_Hitbox_2, (int)_core_Y_Hitbox_3,(int)_core_X_Hitbox_2 + _core_W_Hitbox_6,(int)_core_Y_Hitbox_3 + _core_H_Hitbox_7, paint);
            canvas.drawRect(_extra_Hitboxes[0][5], _extra_Hitboxes[0][6],_extra_Hitboxes[0][5] +_extra_Hitboxes[0][7],_extra_Hitboxes[0][6]+_extra_Hitboxes[0][8], paint);
            canvas.drawRect(_extra_Hitboxes[1][5], _extra_Hitboxes[1][6],_extra_Hitboxes[1][5] +_extra_Hitboxes[1][7],_extra_Hitboxes[1][6]+_extra_Hitboxes[1][8], paint);
            canvas.drawRect(_extra_Hitboxes[2][5], _extra_Hitboxes[2][6],_extra_Hitboxes[2][5] +_extra_Hitboxes[2][7],_extra_Hitboxes[2][6]+_extra_Hitboxes[2][8], paint);
            canvas.drawRect(_extra_Hitboxes[3][5], _extra_Hitboxes[3][6],_extra_Hitboxes[3][5] +_extra_Hitboxes[3][7],_extra_Hitboxes[3][6]+_extra_Hitboxes[3][8], paint);
    }
}

