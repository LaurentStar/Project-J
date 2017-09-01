
package com.vinnstar.myfirstgame;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;

//Please see laurent's style guide for coding structure and naming in the DataClass.java file
/**
 * Created by Laurent on 7/6/2016.
 *
 */
public class ObjectPlayer extends GameObject {
    /*//////////////////////////////////////////
    //                  Arrays               //
    //////////////////////////////////////////*/
    private int[] _collision_Details = new int[10];
    //0 collision status
    //1 the type of object
    //2 the state of the object
    //3 Magnitude
    //4 Direction
    //5 the hit box X (type cast from double)
    //6 the hit box y (type cast from double)
    //7 the hit box w
    //8 the hit box h
    //9 Object ID

    private  int[] _basic_Ai_Info = new int [2]; // Information about the object that can be used by other objects
    //0 X coords
    //1 Y coords


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
    private int _screen_Touch;
    private int _object_Collided_Count;
    private int _health;
    private int _inviciblity_Frames_Timer;

    private int _animation_Cell_Frame;
    ///////////////////////////////////////////
    //          DOUBLE DECLARATION           //
    ///////////////////////////////////////////
    private double[] _core_Doubles_Array = new double[2];
    private double _core_X_Vel_0;
    private double _core_Y_Vel_1;
    private double _core_X_Hitbox_2;
    private double _core_Y_Hitbox_3;

    //Special Double for the player
    private double _collide_Vel_X;
    private double _collide_Vel_Y;


    ///////////////////////////////////////////
    //          BOOLEAN DECLARATION          //
    ///////////////////////////////////////////
    private boolean _core_Collided_Bottom_0;
    private boolean _core_Collided_Top_1;
    private boolean _core_Collided_Left_2;
    private boolean _core_Collided_Right_3;
    private boolean _core_Object_State_Change_4;

    //Special Boolean
    private boolean _border_Touch;
    private boolean _inviciblity_Frames; //When the player recievce damage the player will not take damage for  short period of time
    ///////////////////////////////////////////
    //          CLASS DECLARATION            //
    ///////////////////////////////////////////
    private ExtraHitbox _core_Game_Object_Hitbox;
    private ArrayList<ExtraHitbox> _Game_Object_Hitbox = new ArrayList<ExtraHitbox>();
    private DataClass _core_Data = new DataClass();
    private Animation[] _animation_Cells = new Animation[4];

    //Testing only
    private Paint paint = new Paint();
    private Bitmap spriteSheet;

   // private Animation animation= new Animation();//

    ///////////////////////////////////////////
    //          High Tier classes           ///
    ///////////////////////////////////////////
    GlobalFunctions globalFunctions;
    ActivitySwitch activitySwitch;
    ArrayList<GameObject> gameObjectHitbox = new ArrayList<GameObject>();


    ///////////////
    //Constructor//
    ///////////////
    public ObjectPlayer( Bitmap res, GlobalFunctions globalFunctions, ActivitySwitch activitySwitch ){


       ////////////////////////////
       // SPECIAL CLASS DEFINING //
       ////////////////////////////
       this.globalFunctions = globalFunctions;
       this.activitySwitch = activitySwitch;

        ///////////////////////SPECIAL//////////////////////
        _core_X_Hitbox_2 = 40;
        _core_Y_Hitbox_3 = GamePanel.HEIGHT/2;
        ///////////////////////SPECIAL//////////////////////

       /////////////////////
       //INTEGERS DEFINING//
       /////////////////////
        _core_W_Hitbox_6 = GamePanel.WIDTH/(100/4);
        _core_H_Hitbox_7 = GamePanel.HEIGHT/(100/12);
        _core_X_Coord_0 = (int)_core_X_Hitbox_2 + GamePanel.WIDTH/(100/10);
        _core_Y_Coord_1 = (int)_core_Y_Hitbox_3 + GamePanel.WIDTH/(100/10);
        _core_Height_2 = GamePanel.HEIGHT/(100/11);
        _core_Width_3 = GamePanel.WIDTH/(100/11);
        _core_Gravity_Type_4 = Static.DYNAMIC_GRAVITY;
        _core_Object_Type_5 = Static.PLAYER;
        _core_Collision_Status_8 = Static.MIDAIR;
        _core_Object_Status_9 = Static.OBJECT_STATUS_ACTIVE;
        _core_Object_State_10 =  Static.PLAYER_STATE_ACTIVE;
        _core_Degree_Gravity_11 = 270; //270 degrees on the unit circle going counter clockwise
        _core_Magnitude_12 = 0; // You didn't know what else to put
        _core_ID_13 = 0;

        _screen_Touch = MotionEvent.ACTION_UP;
        _object_Collided_Count = 0;
        _health = 10;
        _inviciblity_Frames_Timer = 0;

        _animation_Cell_Frame = 0;
        /*
        _core_Integers_Array[0] = _core_X_Coord_0;
        _core_Integers_Array[1] = _core_Y_Coord_1;
        _core_Integers_Array[2] = _core_Height_2;
        _core_Integers_Array[3] = _core_Width_3;
        _core_Integers_Array[4] = _core_Gravity_Type_4;
        _core_Integers_Array[5] = _core_Object_Type_5;
        _core_Integers_Array[6] = _core_X_Hitbox_6;
        _core_Integers_Array[7] = _core_Y_Hitbox_7;
        _core_Integers_Array[8] = _core_W_Hitbox_8;
        _core_Integers_Array[9] = _core_H_Hitbox_9;
        _core_Integers_Array[10] = _core_Collision_Status_10;
        _core_Integers_Array[11] = _core_Object_Status_11;
        _core_Integers_Array[12] = _core_Object_State_12;
        _core_Integers_Array[13] = _core_Degree_Gravity_13;
        _core_Integers_Array[14] = _core_Magnitude_14;*/


        /////////////////////
        //DOUBLE DEFINING  //
        /////////////////////
        _core_X_Vel_0 = 0;
        _core_Y_Vel_1 = 0;

        _core_Doubles_Array[0] = _core_X_Vel_0;
        _core_Doubles_Array[1] = _core_Y_Vel_1;
        _core_X_Hitbox_2 = 40;
        _core_Y_Hitbox_3 = GamePanel.HEIGHT/2;


        _collide_Vel_X = 0;
        _collide_Vel_Y = 0;


        /////////////////////
        //BOOLEAN DEFINING //
        /////////////////////
        _core_Collided_Bottom_0 = false;
        _core_Collided_Top_1 = false;
        _core_Collided_Left_2 = false;
        _core_Collided_Right_3 = false;
        _core_Object_State_Change_4 = false;

        _inviciblity_Frames = false;

        /////////////////////
        //HITBOX DEFINING  //
        /////////////////////
        //_core_Game_Object_Hitbox = new ExtraHitbox((int)_core_X_Hitbox_2, (int)_core_Y_Hitbox_3, _core_W_Hitbox_6, _core_H_Hitbox_7);





        /////////////////////
        //  MISC DEFINING  //
        /////////////////////
        /*
        Bitmap[] image = new Bitmap[3];

        image[0] = Bitmap.createBitmap(spriteSheet, 0, 0, core_width, core_height);
        image[1] = Bitmap.createBitmap(spriteSheet, 107, 0, core_width, core_height);
        image[2] = Bitmap.createBitmap(spriteSheet, 214, 0, core_width, core_height);

        animation.setFrames(image);
        animation.setDelay(10);
        */
        spriteSheet = res;
        setAnimations();
        //startTime = System.nanoTime();
        paint.setColor(Color.WHITE);

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
        _core_Data._core_X_Hitbox_2 = (int)_core_X_Hitbox_2;
        _core_Data._core_Y_Hitbox_3 = (int)_core_Y_Hitbox_3;
        //Boolean
        _core_Data._core_Collided_Bottom_0 = _core_Collided_Bottom_0;
        _core_Data._core_Collided_Top_1 = _core_Collided_Top_1;
        _core_Data._core_Collided_Left_2 = _core_Collided_Left_2;
        _core_Data._core_Collided_Right_3 = _core_Collided_Right_3;
        _core_Data._core_Object_State_Change_4 = _core_Object_State_Change_4;
    }

    public void variableUpdate(long deltatick) {

        animationCellLogic();
        //Play Object's stateaAnimation
        _animation_Cells[_animation_Cell_Frame].update();
        _screen_Touch = globalFunctions.getScreenTouch();

        //_final_Sum_X_Vel = _core_X_Vel_0 + _collide_Vel_X;
        //_final_Sum_Y_Vel = _core_Y_Vel_1 + _collide_Vel_Y;

        //Update the collision box cordinates & move the character
        _core_X_Hitbox_2 += (_core_X_Vel_0 + _collide_Vel_X) * (deltatick/1000.f);
        _core_Y_Hitbox_3 += (_core_Y_Vel_1 + _collide_Vel_Y) * (deltatick/1000.f);

        //Place the image accordingly
        _core_X_Coord_0 = (int)_core_X_Hitbox_2 - _core_Width_3;
        _core_Y_Coord_1 = (int)_core_Y_Hitbox_3 - _core_Height_2;


        if(_inviciblity_Frames == true)
        {
            _inviciblity_Frames_Timer += 50 * (deltatick / 1000.f);
        }

        //Update Hit box variables
       // _core_Game_Object_Hitbox.setRectangle(_core_X_Hitbox_6, _core_Y_Hitbox_7, _core_W_Hitbox_8, _core_H_Hitbox_9);


        //2ND FLAG

        //////////////////////////////////////////////////
        //              Update Data Class               //
        //////////////////////////////////////////////////
        /*_core_Data._core_X_Coord_0 = _core_X_Coord_0;
        _core_Data._core_Y_Coord_1 = _core_Y_Coord_1;
        _core_Data._core_Height_2 = _core_Height_2;
        _core_Data._core_Width_3 = _core_Width_3;
        _core_Data._core_Gravity_Type_4 = _core_Gravity_Type_4;
        _core_Data._core_Object_Type_5 = _core_Object_Type_5;
        _core_Data._core_X_Hitbox_6 = _core_X_Hitbox_6;
        _core_Data._core_Y_Hitbox_7 = _core_Y_Hitbox_7;
        _core_Data._core_W_Hitbox_8 = _core_W_Hitbox_8;
        _core_Data._core_H_Hitbox_9 = _core_H_Hitbox_9;
        _core_Data._core_Collision_Status_10 = _core_Collision_Status_10;
        _core_Data._core_Object_Status_11 = _core_Object_Status_11;
        _core_Data._core_Object_State_12 = _core_Object_State_12;
        _core_Data._core_Degree_Gravity_13 = _core_Degree_Gravity_13;
        _core_Data._core_Magnitude_14 = _core_Magnitude_14;
        //Doubles
        _core_Data._core_X_Vel_0 = _core_X_Vel_0;
        _core_Data._core_Y_Vel_1 = _core_Y_Vel_1;
        //Boolean
        _core_Data._core_Collided_Bottom_0 = _core_Collided_Bottom_0;
        _core_Data._core_Collided_Top_1 = _core_Collided_Top_1;
        _core_Data._core_Collided_Left_2 = _core_Collided_Left_2;
        _core_Data._core_Collided_Right_3 = _core_Collided_Right_3;
        _core_Data._core_Object_State_Change_4 = _core_Object_State_Change_4;*/
        //////////////////////////////////////////////////
        //              Update Data Class               //
        //////////////////////////////////////////////////

       /* if ((playerState == Static.PLAYER_STATE_ACTIVE)|| (playerState == Static.PLAYER_STATE_HARMED)) {

            animation[0].update();
            screen_touch = globalFunctions.getScreenTouch();


            core_x_coord += x_vel * (deltatick / 1000.f);
            core_y_coord += y_vel * (deltatick / 1000.f);
            x_hitbox =(int)core_x_coord + core_width/3;
            y_hitbox =(int)core_y_coord;


            gameObjectHitbox.get(0).setRectangle(   x_hitbox, y_hitbox, w_hitbox, h_hitbox);
          //  rect.set((int) core_x_coord, (int) core_y_coord, (int) core_x_coord + core_width, (int) core_y_coord + core_height);



            //Bottom border
            if (y_hitbox > GamePanel.HEIGHT - h_hitbox) {
                y_vel = 0;

                y_hitbox = GamePanel.HEIGHT - h_hitbox;
            }
            //Top border
            if (y_hitbox < 0) {
                y_vel = 0;
                core_y_coord = 0;
            }

            if (core_x_coord > GamePanel.WIDTH) {
                core_x_coord = 10;
            }
        }

        else if (playerState == Static.PLAYER_STATE_DEAD)
        {
            animation[0].update();
            core_x_coord += x_vel * (deltatick / 1000.f);
            core_y_coord += y_vel * (deltatick / 1000.f);
            //rect.set((int) core_x_coord, (int) core_y_coord, (int) core_x_coord + core_width, (int) core_y_coord + core_height);

            deathSequence( activitySwitch);
        }

        else if (playerState == Static.PLAYER_STATE_LEVEL_COMPLETE)
        {
            animation[0].update();
            core_x_coord += x_vel * (deltatick / 1000.f);
            core_y_coord += y_vel * (deltatick / 1000.f);
            winSequence(activitySwitch);
        }*/

    }


    /////////////////////////////
    //ANIMATION CELLS COMPUTING//
    /////////////////////////////
    public void animationCellLogic()
    {
        switch (_core_Object_State_10)
        {
            case Static.PLAYER_STATE_ACTIVE: _animation_Cell_Frame = 0; break;
            case Static.PLAYER_STATE_HARMED: _animation_Cell_Frame = 2; break;
            case Static.PLAYER_STATE_DEAD: _animation_Cell_Frame = 1; break;
            case Static.PLAYER_STATE_LEVEL_COMPLETE: _animation_Cell_Frame = 3; break;
        }
    }

    //////////////////////////
    //PHYSICS IMPLEMENTATION//
    //////////////////////////
    //Leader method
    public void objectPhysics(int totalobjectcollided,  ArrayList<int[]> collisiondetails, ArrayList<boolean[]> collisiondetails2, long deltaticks) {
        //GO THROUGH A COLLECTION OF FUNCTIONS TO DETERMINE THE FINAL VELOCITY AFTER ALL EFFECTS HAVE BEEN APPLIED
        //1ST APPLY THE GRAVITY
        /*2ND DETECT COLLISION
        0 collision status
        1 the type of object
        2 the state of the object
        3 Magnitude
        4 Direction
        5 the hit box X
        6 the hit box y
        7 the hit box w
        8 the hit box h
        */
        //3TH APPLY FORCES FROM PLAYER INPUT
        //4RD APPLY COLLISIONS FORCES
        //5TH APPLY FORCES FROM SPECIAL CONDITIONS.


        //if (_core_Gravity_Type_4 == Static.FIXED_GRAVITY) {return;}//If the object shouldn't move leave this method


        //FIRST
        applyGravity(deltaticks);

        //THIRD
        applyInputForces(deltaticks);

        //SECOND & FORTH (only consider collisions if the charcter state is right)
        if(totalobjectcollided != 0) {
            _object_Collided_Count = totalobjectcollided;
            for (int i = 0; i < totalobjectcollided; i++) {
                switch (_core_Object_State_10){
                    case Static.PLAYER_STATE_ACTIVE:
                    case Static.PLAYER_STATE_HARMED:
                        if (collisiondetails2 != null) {
                            applyCollisionForces(collisiondetails.get(i), collisiondetails2.get(i), deltaticks);
                        }
                        else {
                            applyCollisionForces(collisiondetails.get(i), null, deltaticks);
                        }
                }
            }
        }
        else
        {

            if (_collide_Vel_Y > 0){_collide_Vel_Y -= 5;}
            else if(_collide_Vel_Y < 0){_collide_Vel_Y += 5;}
            else if((_collide_Vel_Y < 10)&&(_collide_Vel_Y > -10)){_collide_Vel_Y = 0;}

            if (_collide_Vel_X > 0){_collide_Vel_X -= 5;}
            else if(_collide_Vel_X < 0){_collide_Vel_X += 5;}
            else if((_collide_Vel_X < 10)&&(_collide_Vel_X > -10)){_collide_Vel_X = 0;}




        }
        //FOURTH


        //FIFTH
        applyMiscForces(deltaticks);
    }

    public void applyGravity(long deltaticks){
        _core_X_Vel_0 += Math.cos(_core_Degree_Gravity_11 * Math.PI/180) * Static.GRAVITY * (deltaticks / 1000.f);
        _core_Y_Vel_1 += -Math.sin(_core_Degree_Gravity_11 * Math.PI/180) * Static.GRAVITY * (deltaticks / 1000.f);
    }//Apply gravity to an object in any direction
    public void applyCollisionForces( int collisiondetails[], boolean collisiondetails2[],long deltaticks) {
        /*
        0 collision status
        1 the type of object
        2 the state of the object
        3 Magnitude
        4 Direction
        5 the hit box X
        6 the hit box y
        7 the hit box w
        8 the hit box h
        */


            switch (collisiondetails[0]){
                case Static.MIDAIR:
                    break;

                case Static.COLLISION_2D_TOUCH:
                    applyCollisionForcesTouch(collisiondetails, collisiondetails2, deltaticks);
                    break;

                case Static.COLLISION_2D_PIERCE:
                    applyCollisionForcePierce(collisiondetails, collisiondetails2,deltaticks);
                    break;
            }

    }//Apply the forces from interacting with other objects
    public void applyCollisionForcesTouch(int collisiondetails[], boolean collisiondetails2[],long deltaticks){

        //Base on the type of the object and other variables, this effects the object
        switch (collisiondetails[1])
        {
            case Static.FLOATINGICEBLOCK:

                // left side = 0, top = 1, right side = 2, bottom = 3

                _collide_Vel_X = Math.cos(collisiondetails[4] * Math.PI / 180) * collisiondetails[3];
                _collide_Vel_Y = -Math.sin(collisiondetails[4] * Math.PI / 180) * collisiondetails[3];



                if(collisiondetails2[0] == true) {

                    _core_X_Hitbox_2 = collisiondetails[5] - _core_W_Hitbox_6 - 1;

                    int tile = 0;

                }
                if(collisiondetails2[1] == true) {
                   _core_Y_Hitbox_3 = collisiondetails[6] - _core_H_Hitbox_7 - 1;
                }
                if(collisiondetails2[2] == true) {
                    _core_X_Hitbox_2 = collisiondetails[5] + collisiondetails[7] + 1;
                }
                if(collisiondetails2[3] == true) {
                    _core_Y_Hitbox_3 = collisiondetails[6] + collisiondetails[8] + 1;
                }


                break;
        }
    }
    public void applyCollisionForcePierce(int collisiondetails[], boolean collisiondetails2[],long deltaticks){
        switch (collisiondetails[1])
        {
            case Static.FLOATINGICEBLOCK:
                // left side = 0, top = 1, right side = 2, bottom = 3


                //Move with it
                _collide_Vel_X = Math.cos(collisiondetails[4] * Math.PI / 180) * collisiondetails[3];
                _collide_Vel_Y = -Math.sin(collisiondetails[4] * Math.PI / 180) * collisiondetails[3];
                _core_X_Vel_0 = 0;
                _core_Y_Vel_1 = 0;
                int mag = (int)Math.sqrt( Math.pow(_collide_Vel_X , 2) + Math.pow(_collide_Vel_Y , 2));


                if(collisiondetails2[0] == true) {
                    _core_X_Hitbox_2--;
                    if (_object_Collided_Count  != 1)
                    {
                        _collide_Vel_X =  Math.cos(180 * Math.PI / 180) * mag * 2;
                        _collide_Vel_Y =  -Math.sin(180 * Math.PI / 180) * mag * 2;
                        _core_X_Hitbox_2 += _collide_Vel_X * deltaticks/(1000.f);
                    }
                }
                if(collisiondetails2[1] == true) {
                    _core_Y_Hitbox_3--;
                    if (_object_Collided_Count  != 1)
                    {
                        _collide_Vel_X = Math.cos(90 * Math.PI / 180) * mag * 2;
                        _collide_Vel_Y = -Math.sin(90 * Math.PI / 180) * mag * 2;
                        _core_Y_Hitbox_3 += _collide_Vel_Y * deltaticks/(1000.f);
                    }

                }
                if(collisiondetails2[2] == true) {
                    _core_X_Hitbox_2++;

                    if (_object_Collided_Count  != 1) {
                        _collide_Vel_X = Math.cos(0 * Math.PI / 180) * mag * 2;
                        _collide_Vel_Y =  -Math.sin(0 * Math.PI / 180) * mag * 2;
                        _core_X_Hitbox_2 += _collide_Vel_X * deltaticks / (1000.f);
                    }

                }
                if(collisiondetails2[3] == true) {
                    _core_Y_Hitbox_3++;
                    if (_object_Collided_Count  != 1) {
                        _collide_Vel_X = Math.cos(270 * Math.PI / 180) * mag * 2;
                        _collide_Vel_Y = -Math.sin(270 * Math.PI / 180) * mag * 2;
                        _core_Y_Hitbox_3 += _collide_Vel_Y * deltaticks / (1000.f);
                    }
                }
                break;
        }
    }
    public void applyInputForces(long deltaticks){

        switch (_core_Object_State_10)
        {
            case Static.PLAYER_STATE_ACTIVE:
            case Static.PLAYER_STATE_HARMED:

                switch (_screen_Touch)
                {
                    case MotionEvent.ACTION_DOWN:
                        //_core_Y_Vel_1 -= 50.0;
                        _core_Y_Vel_1 += -Math.sin(90 * Static.DEG) * Static.GRAVITY * 2* (deltaticks / 1000.f);
                        break;
                }
        }
    }//Apply forces from game player inputs
    public void applyMiscForces(long deltaticks){
        switch (_core_Object_State_10)
        {
            case Static.PLAYER_STATE_DEAD:
                _core_X_Vel_0 += Math.cos(315 * Math.PI/180) * Static.GRAVITY * (deltaticks / 1000.f);
                _core_Y_Vel_1 += -Math.sin(315 * Math.PI/180) * Static.GRAVITY * (deltaticks / 1000.f);
                break;

            case Static.PLAYER_STATE_HARMED:
                break;
            case Static.PLAYER_STATE_LEVEL_COMPLETE:
                _core_X_Vel_0 += Math.cos(0 * Math.PI/180) * Static.GRAVITY * (deltaticks / 1000.f);
                _core_Y_Vel_1 += -Math.sin(90 * Math.PI/180) * Static.GRAVITY * (deltaticks / 1000.f);
                break;
        }
    }//Apply forces from not related to anything above


    //////////////////////////////////
    //   FLAGS  IMPLEMENTATION      //
    //////////////////////////////////
    //Leader method
    public void eventFlags(int totalobjectcollided , ArrayList<int[]> collisiondetails, ArrayList<boolean[]> collisiondetails2 ) {
        /*/1ST APPLY FLAG TRIGGGERS FROM COLLISIONS
        //2ND APPLY FLAG TRIGGERS FROM SPECIAL CONDITIONS
        //Sometimes 1st and 2nd work in unity*/

        //FIRST
        if(totalobjectcollided != 0)
        {
            for (int i = 0; i < totalobjectcollided; i++) {
                switch (_core_Object_State_10) {
                    case Static.PLAYER_STATE_ACTIVE:
                    case Static.PLAYER_STATE_HARMED:

                        if(collisiondetails2 != null) {
                            applyCollisionFlagTrigger(collisiondetails.get(i), collisiondetails2.get(i));
                        }
                        else {
                            applyCollisionFlagTrigger(collisiondetails.get(i), null);
                        }
                        break;
                }
            }
        }
        else
        {
            if(_border_Touch == false) {
                _core_Collided_Bottom_0 = false;
                _core_Collided_Top_1 = false;
                _core_Collided_Left_2 = false;
                _core_Collided_Right_3 = false;
            }
        }
        //SECOND
        applySpecialFlagTrigger();

    }

    public void applyCollisionFlagTrigger(int collisiondetails[], boolean collisiondetails2[]){

        switch (collisiondetails[0])
        {
            case Static.MIDAIR:
                break;

            case Static.COLLISION_2D_TOUCH:
                applyCollisionFlagTriggerTouch(collisiondetails, collisiondetails2);
                break;

            case Static.COLLISION_2D_PIERCE:
                applyCollisionFlagTriggerPierce(collisiondetails, collisiondetails2);
                break;
        }
    }
    public void applyCollisionFlagTriggerTouch(int collisiondetails[],  boolean collisiondetails2[]){

        //Base on the type of the object and other variables, this effects the object
        switch (collisiondetails[1])
        {
            case Static.FIREBALL:_core_Object_State_10 = Static.PLAYER_STATE_DEAD;

        }
    }
    public void applyCollisionFlagTriggerPierce(int collisiondetails[],  boolean collisiondetails2[]){
        switch (collisiondetails[1])
        {
            case Static.FIREBALL: _core_Object_State_10 = Static.PLAYER_STATE_DEAD;
                break;
            case Static.FLOATINGICEBLOCK:

                // left side = 0, top = 1, right side = 2, bottom = 3
                if(collisiondetails2[3] == true)
                {
                    _core_Collided_Top_1 = true;
                }
                if(collisiondetails2[1] == true)
                {
                    _core_Collided_Bottom_0 = true;
                }
                if(collisiondetails2[0] == true)
                {
                    _core_Collided_Right_3 = true;
                }
                if(collisiondetails2[2] == true)
                {
                    _core_Collided_Left_2 = true;
                }
                break;
            case Static.BLUEFIREBALL:
                if(_inviciblity_Frames == false) {
                    _health -= 1;
                    _inviciblity_Frames = true;
                    _core_Object_State_10 = Static.PLAYER_STATE_HARMED;
                }
                break;
            case Static.ENERGYICESPHERE:
                if(_inviciblity_Frames == false) {
                    _health -= 3;
                    _inviciblity_Frames = true;
                    _core_Object_State_10 = Static.PLAYER_STATE_HARMED;
                }
                break;
            case Static.BALLISTICMETALBOX:
                //_core_Object_State_10 = Static.PLAYER_STATE_DEAD;
                break;
        }
    }
    //NEW 8/12/2017
    public void applyCollisionChildFlagTrigger(int[][] childcollisiondetail){
        // 0 - 0 Collision Details
        // 0 - 1 Children Type
        // 0 - 2 Children state
        // 0 - 3 x coord
        // 0 - 4 y coord
        // 0 - 5 width
        // 0 - 6 height

        int arraylenght = childcollisiondetail.length;
        for (int i = 0; i < arraylenght; i++)
        {


                switch (childcollisiondetail[i][0]) {
                    case Static.MIDAIR:

                        break;

                    case Static.COLLISION_2D_TOUCH:
                        applyCollisionChildFlagTriggerTouch(childcollisiondetail[i]);
                        break;

                    case Static.COLLISION_2D_PIERCE:
                        applyCollisionChildFlagTriggerPierce(childcollisiondetail[i]);
                        break;
                }
        }
    }
    public void applyCollisionChildFlagTriggerTouch(int childcollisiondetail[]){
    }
    public void applyCollisionChildFlagTriggerPierce(int childcollisiondetail[]){
        switch (childcollisiondetail[4])
        {
            case Static.BALLISTICMETALBOX:
                switch (childcollisiondetail[1]) {
                    case Static.SWITCH:
                        switch(childcollisiondetail[2])
                        {
                            case Static.CHILD_OBJECT_STATE_ACTIVE: break;
                            case Static.CHILD_OBJECT_STATE_OFF: _core_Object_State_10 = Static.PLAYER_STATE_DEAD; break;
                        }
                        break;
                }
                break;
        }
    }
    public void applySpecialFlagTrigger() {
        boolean bordertouch = false;

        switch (_core_Object_State_10) {
            case Static.PLAYER_STATE_ACTIVE:
            case Static.PLAYER_STATE_HARMED:

                    //1ST FLAG//Check if player got crushed
                    if ((_core_Collided_Bottom_0 == true) && (_core_Collided_Top_1 == true)){
                        _core_Object_State_10 = Static.PLAYER_STATE_DEAD;
                    }
                    else if((_core_Collided_Left_2 == true) && (_core_Collided_Right_3 == true)){
                        _core_Object_State_10 = Static.PLAYER_STATE_DEAD;
                    }


                    //2ND FLAG///If this object goes above or below the screen border
                    if (_core_Y_Hitbox_3 + _core_H_Hitbox_7 > GamePanel.HEIGHT ) {
                        //Bottom border
                        _core_Y_Vel_1 = 0;
                        _core_Y_Hitbox_3 = GamePanel.HEIGHT - _core_H_Hitbox_7;
                        _core_Y_Coord_1 = (int)_core_Y_Hitbox_3 - _core_Height_2;
                        _core_Collided_Bottom_0 = true;
                        bordertouch = true;
                    }
                    else if (_core_Y_Hitbox_3 < 0) {
                        //Top border
                        _core_Y_Vel_1 = 0;
                        _core_Y_Hitbox_3 = 0;
                        _core_Y_Coord_1 = (int)_core_Y_Hitbox_3 - _core_Height_2;
                        _core_Collided_Top_1 = true;
                        bordertouch = true;
                    }
                    if (_core_X_Hitbox_2 + _core_W_Hitbox_6 > GamePanel.WIDTH ) {
                        //right
                        _core_X_Vel_0 = 0;
                        _core_X_Hitbox_2 = GamePanel.WIDTH - _core_W_Hitbox_6;
                        _core_X_Coord_0 = (int)_core_X_Hitbox_2 - _core_Width_3;
                        _core_Collided_Right_3 = true;
                        bordertouch = true;
                    }
                    else if (_core_X_Hitbox_2 < 0) {
                        //left
                        _core_X_Vel_0 = 0;
                        _core_X_Hitbox_2 = 0;
                        _core_X_Coord_0 = (int)_core_X_Hitbox_2 - _core_Width_3;
                        _core_Collided_Left_2 = true;
                        bordertouch = true;
                    }
                    if(bordertouch == true)
                    {
                        _border_Touch = true;
                    }
                    else {_border_Touch = false;}

                    //FLAG 3/// Player loses all their health
                    if(_health == 0)
                    {
                        _core_Object_State_10 = Static.PLAYER_STATE_DEAD;
                    }

                    //FLAG 4// Invicliblity frame timer
                    if(_inviciblity_Frames_Timer >= 5)
                    {
                        _inviciblity_Frames = false;
                        _core_Object_State_10 = Static.PLAYER_STATE_ACTIVE;
                        _inviciblity_Frames_Timer = 0;
                    }



                break;
            case Static.PLAYER_STATE_DEAD:
                    //1ST FLAG
                    //Check to see if the player died and has fallen off screen
                    if(_core_Y_Coord_1 > GamePanel.HEIGHT) {
                        activitySwitch.switchActivity();
                    }
                break;
            case Static.PLAYER_STATE_LEVEL_COMPLETE:
                    //1ST FLAG
                    //Check to see if the player won and has flew off screen
                    if(_core_X_Coord_0 > GamePanel.WIDTH){
                        _core_Object_Status_9 = Static.OBJECT_STATUS_INACTIVE;
                        _core_X_Vel_0 = 0;
                    }
                break;
        }
    }

    public void returnToMenu() {
        activitySwitch.switchActivity();
    }


    //////////////////////////////////
    //          SETTER METHODS      //
    //////////////////////////////////

    //Leader method
    public void setPlayerState(int value)
    {
        _core_Object_State_10 = value;
    }
    public void setID(int value){_core_ID_13 = value;}

    //////////////////////////////////
    //          GETTER METHODS      //
    //////////////////////////////////


    //Leader method
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
    public int[] getBasicAiInfoArray(){
        _basic_Ai_Info[0] = (int)_core_X_Hitbox_2;
        _basic_Ai_Info[1] = (int)_core_Y_Hitbox_3;
        return _basic_Ai_Info;
    }

    public int getGravityType(){return _core_Gravity_Type_4;}
    public int getObjectType(){return _core_Object_Type_5;}
    public int getObjectStatus(){return _core_Object_Status_9;}

    public DataClass getDataClass(){return _core_Data;}


    //////////////////////////////////
    //              MISC            //
    //////////////////////////////////
    /*public void ManageAnimation() {
        switch (playerState)
        {
            case Static.PLAYER_STATE_ACTIVE: playerStateChange = true;
            case Static.PLAYER_STATE_DEAD: break;
            case Static.PLAYER_STATE_ENTERING_LEVEL:
            case Static.PLAYER_STATE_HARMED:
            case Static.PLAYER_STATE_LEVEL_COMPLETE:
        }
    }*/

    public void setAnimations() {
        //assign all anitions
        for(int i = 0; i < 4; i++) {
            _animation_Cells[i] = new Animation();
        }

        int scaleValue = GamePanel.WIDTH/(100/25);

        //A0 = SURFING IDLE [(3 frames)]
        Bitmap[] A0 = new Bitmap[3];
        A0[0] = Bitmap.createBitmap(spriteSheet, 0, 0, 170, 170);
        A0[1] = Bitmap.createBitmap(spriteSheet, 170, 0, 170, 170);
        A0[2] = Bitmap.createBitmap(spriteSheet, 170*2, 0, 170, 170);
        A0[0] = Bitmap.createScaledBitmap(A0[0], scaleValue, scaleValue, false);
        A0[1] = Bitmap.createScaledBitmap(A0[1], scaleValue, scaleValue, false);
        A0[2] = Bitmap.createScaledBitmap(A0[2], scaleValue, scaleValue, false);
        _animation_Cells[0].setFrames(A0);
        _animation_Cells[0].setDelay(50);

        //A1 = DEATH FALL
        Bitmap[] A1 = new Bitmap[1];
        A1[0] = Bitmap.createBitmap(spriteSheet, 170*3, 0, 170, 170);
        A1[0] = Bitmap.createScaledBitmap(A1[0], scaleValue, scaleValue, false);
        _animation_Cells[1].setFrames(A1);
        _animation_Cells[1].setDelay(500);

        //A2 = PAIN
        Bitmap[] A2 = new Bitmap[3];
        A2[0] = Bitmap.createBitmap(spriteSheet, 170*4, 0, 170, 170);
        A2[1] = Bitmap.createBitmap(spriteSheet, 170*5, 0, 170, 170);
        A2[2] = Bitmap.createBitmap(spriteSheet, 170*6, 0, 170, 170);
        A2[0] = Bitmap.createScaledBitmap(A2[0], scaleValue, scaleValue, false);
        A2[1] = Bitmap.createScaledBitmap(A2[1], scaleValue, scaleValue, false);
        A2[2] = Bitmap.createScaledBitmap(A2[2], scaleValue, scaleValue, false);
        _animation_Cells[2].setFrames(A2);
        _animation_Cells[2].setDelay(50);

        //A3 = ENTERING LEVEL
        Bitmap[] A3 = new Bitmap[1];
        A3[0] =Bitmap.createBitmap(spriteSheet, 170*7, 0, 170, 170);
        A3[0] = Bitmap.createScaledBitmap(A3[0], scaleValue, scaleValue, false);
        _animation_Cells[3].setFrames(A3);
        _animation_Cells[3].setDelay(500);

    }


    //////////////////////////////////
    //     Animation & Drawing      //
    //////////////////////////////////
    //Leader method
    public void draw(Canvas canvas) {
        /*switch (_core_Object_State_10) {
            case Static.PLAYER_STATE_ACTIVE:   canvas.drawBitmap(_animation_Cells[0].getImage(), _core_X_Coord_0, _core_Y_Coord_1, null); break;
            case Static.PLAYER_STATE_DEAD:  canvas.drawBitmap(_animation_Cells[1].getImage(), _core_X_Coord_0, _core_Y_Coord_1, null); break;
            case Static.PLAYER_STATE_HARMED:  canvas.drawBitmap(_animation_Cells[2].getImage(), _core_X_Coord_0, _core_Y_Coord_1, null); break;
            case Static.PLAYER_STATE_ENTERING_LEVEL:  canvas.drawBitmap(_animation_Cells[3].getImage(), _core_X_Coord_0, _core_Y_Coord_1, null); break;
            case Static.PLAYER_STATE_LEVEL_COMPLETE:  canvas.drawBitmap(_animation_Cells[3].getImage(), _core_X_Coord_0, _core_Y_Coord_1, null); break;
        }*/

        canvas.drawBitmap(_animation_Cells[_animation_Cell_Frame].getImage(), _core_X_Coord_0, _core_Y_Coord_1, null);
        canvas.drawRect((int)_core_X_Hitbox_2, (int)_core_Y_Hitbox_3, (int)_core_X_Hitbox_2 + _core_W_Hitbox_6, (int)_core_Y_Hitbox_3 + _core_H_Hitbox_7, paint);
    }



}