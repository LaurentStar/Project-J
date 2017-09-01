package com.vinnstar.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Laurent on 6/22/2017.
 */
public class ObjectBoss1 extends GameObject{
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
    //5 the hit box X (type cast from double)
    //6 the hit box y (type cast from double)
    //7 the hit box w
    //8 the hit box h
    //9 Object ID

    private int[] _object_Orders = new int[5]; // Used to order object and tell them how to be used
    //0 object type wanted
    //1 where to place on x coord
    //2 where to place on y coord
    //3 The direction the object should be facing
    //4 the magnitude to apply the force.

    private boolean[] _timer_Actions = new boolean[3];
    //0 Blue fire gun
    //1 Morton Cannon
    //2 Ballistic metal cannon

    /*SPECIAL ARRAYS USED INTERNALLY ONLY*/
    private int[] _special_Coords = new int[10];
    //0 + 1 Blue Fire ball coords

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
    private int _boss_Health_Points;

    private int _morton_Cannon_Timer;
    private int _homing_Ballistic_Box_Timer;
    private int _juke_Up_Down_Timer;
    private int _aim_Up_Down_Timer;
    private int _blue_Fire_Cannon_Timer;

    private int _animation_Cell_Frame;
    ///////////////////////////////////////////
    //          DOUBLE DECLARATION           //
    ///////////////////////////////////////////
    private double _core_X_Vel_0;
    private double _core_Y_Vel_1;
    private double _core_X_Hitbox_2;
    private double _core_Y_Hitbox_3;

    //Special Double for the player
    private double _timer;

    ///////////////////////////////////////////
    //          BOOLEAN DECLARATION          //
    ///////////////////////////////////////////
    private boolean _core_Collided_Bottom_0;
    private boolean _core_Collided_Top_1;
    private boolean _core_Collided_Left_2;
    private boolean _core_Collided_Right_3;
    private boolean _core_Object_State_Change_4;

    //Special Boolean
    private boolean _apply_Force;
    private boolean _juke_Action; // Used internally so it was removed from the array
    ///////////////////////////////////////////
    //          CLASS DECLARATION            //
    ///////////////////////////////////////////

    private Animation[] _animation_Cells = new Animation[5];

    //Testing only
    private Paint paint = new Paint();
    private Bitmap spriteSheet;

    // private Animation animation= new Animation();//

    ///////////////////////////////////////////
    //          High Tier classes           ///
    ///////////////////////////////////////////
    GlobalFunctions globalFunctions;




    ///////////////
    //Constructor//
    ///////////////
    public ObjectBoss1 ( Bitmap res, GlobalFunctions globalFunctions, ActivitySwitch activitySwitch ){


        ////////////////////////////
        // SPECIAL CLASS DEFINING //
        ////////////////////////////
        this.globalFunctions = globalFunctions;


        ///////////////////////SPECIAL//////////////////////
        _core_X_Hitbox_2 =  GamePanel.WIDTH - GamePanel.WIDTH/(100/60);
        _core_Y_Hitbox_3 = GamePanel.HEIGHT/2;
        ///////////////////////SPECIAL//////////////////////

        /////////////////////
        //INTEGERS DEFINING//
        /////////////////////
        _core_W_Hitbox_6 = GamePanel.WIDTH/(100/6);
        _core_H_Hitbox_7 = GamePanel.HEIGHT/(100/22);
        _core_X_Coord_0 = (int)_core_X_Hitbox_2 + GamePanel.WIDTH/(100/10);
        _core_Y_Coord_1 = (int)_core_Y_Hitbox_3 + GamePanel.WIDTH/(100/10);
        _core_Height_2 = GamePanel.HEIGHT/(100/11);
        _core_Width_3 = GamePanel.WIDTH/(100/8);
        _core_Gravity_Type_4 = Static.DYNAMIC_GRAVITY;
        _core_Object_Type_5 = Static.BOSS1;
        _core_Collision_Status_8 = Static.MIDAIR;
        _core_Object_Status_9 = Static.OBJECT_STATUS_ACTIVE;
        _core_Object_State_10 =  Static.GENERAL_OBJECT_STATE_ACTIVE;
        _core_Degree_Gravity_11 = 270; //270 degrees on the unit circle going counter clockwise
        _core_Magnitude_12 = 0; // You didn't know what else to put
        _core_ID_13 = 0;
        _core_Object_Sub_Animation_State_14 = Static.BOSS_1_ACTIVE_AIM_STRAIGHT;


        _boss_Health_Points = 6;
        _morton_Cannon_Timer = 0;
        _homing_Ballistic_Box_Timer = 0;
        _juke_Up_Down_Timer = 0;
        _aim_Up_Down_Timer = 0;
        _blue_Fire_Cannon_Timer = 0;

        _animation_Cell_Frame = 0;

        /////////////////////
        //DOUBLE DEFINING  //
        /////////////////////
        _core_X_Vel_0 = 0;
        _core_Y_Vel_1 = 0;


        _core_X_Hitbox_2 =  GamePanel.WIDTH - GamePanel.WIDTH/(100/10);
        _core_Y_Hitbox_3 = GamePanel.HEIGHT/2;



        _timer = 50;

        /////////////////////
        //BOOLEAN DEFINING //
        /////////////////////
        _core_Collided_Bottom_0 = false;
        _core_Collided_Top_1 = false;
        _core_Collided_Left_2 = false;
        _core_Collided_Right_3 = false;
        _core_Object_State_Change_4 = false;

        _juke_Action = false;




        ///////////////////////
        //  ARRAY DEFINING   //
        ///////////////////////
        _timer_Actions[0] = false;
        _timer_Actions[1] = false;
        _timer_Actions[2] = false;


        _special_Coords[0] = 0;
        _special_Coords[1] = 0;
        _special_Coords[2] = 0;
        _special_Coords[3] = 0;
        _special_Coords[4] = 0;
        _special_Coords[5] = 0;

        _object_Orders[0] = -1;
        _object_Orders[1] = 0;
        _object_Orders[2] = 0;
        _object_Orders[3] = 0;
        _object_Orders[4] = 0;

        /////////////////////
        //  MISC DEFINING  //
        /////////////////////

        spriteSheet = res;
        setAnimations();
        //startTime = System.nanoTime();
        paint.setColor(Color.WHITE);


    }

    public void variableUpdate(long deltatick) {
        //Set _animation_Cell before using it
        animationCellLogic();

        //Play Object's stateaAnimation
        _animation_Cells[_animation_Cell_Frame].update();



        //Update the collision box cordinates & move the character
        _core_X_Hitbox_2 += _core_X_Vel_0  * (deltatick/1000.f);
        _core_Y_Hitbox_3 += _core_Y_Vel_1  * (deltatick/1000.f);



        _core_Y_Hitbox_3 = GamePanel.HEIGHT/(100/50) + GamePanel.HEIGHT/(100/10);
       // _special_Coords[0] = (int)_core_X_Hitbox_2 - 50;
        //_special_Coords[1] = (int)_core_Y_Hitbox_3 + 24;

        //Blue FirE Ball
        _special_Coords[0] = (int)_core_X_Hitbox_2 -  GamePanel.WIDTH/(100/7);
        _special_Coords[1] = (int)_core_Y_Hitbox_3 + GamePanel.HEIGHT/(100/3);

        _special_Coords[6] = (int)_core_X_Hitbox_2 -  GamePanel.WIDTH/(100/7); //DOWN
        _special_Coords[7] = (int)_core_Y_Hitbox_3 + GamePanel.HEIGHT/(100/5);

        _special_Coords[8] = (int)_core_X_Hitbox_2 -  GamePanel.WIDTH/(100/6); //UP
        _special_Coords[9] = (int)_core_Y_Hitbox_3;

        //Mortan Cannon
        _special_Coords[2] = (int)_core_X_Hitbox_2 + GamePanel.WIDTH/(100/6);
        _special_Coords[3] = (int)_core_Y_Hitbox_3 - GamePanel.HEIGHT/(100/3);

        //BASLITIC METAL
        _special_Coords[4] = (int)_core_X_Hitbox_2 -  GamePanel.WIDTH/(100/20);
        _special_Coords[5] = (int)_core_Y_Hitbox_3 + GamePanel.HEIGHT/(100/3);

        //Place the image accordingly
        _core_X_Coord_0 = (int)_core_X_Hitbox_2 - _core_Width_3;
        _core_Y_Coord_1 = (int)_core_Y_Hitbox_3 - _core_Height_2;
    }

    /////////////////////////////
    //ANIMATION CELLS COMPUTING//
    /////////////////////////////
    public void animationCellLogic()
    {
        switch (_core_Object_State_10)
        {
            case Static.GENERAL_OBJECT_STATE_ACTIVE:
                switch (_core_Object_Sub_Animation_State_14)
                {
                    case Static.BOSS_1_ACTIVE_AIM_DOWN: _animation_Cell_Frame = 2; break;
                    case Static.BOSS_1_ACTIVE_AIM_UP: _animation_Cell_Frame = 1; break;
                    case Static.BOSS_1_ACTIVE_AIM_STRAIGHT: _animation_Cell_Frame = 0; break;
                    case Static.BOSS_1_ACTIVE_BALLISTIC_SHOT: _animation_Cell_Frame = 4; break;
                }
                break;
            case Static.GENERAL_OBJECT_STATE_HARMED: _animation_Cell_Frame = 3; break;
            case Static.GENERAL_OBJECT_STATE_DEAD: _animation_Cell_Frame = 3; break;
        }
    }


    ////////////////
    //AI COMPUTING//
    ////////////////
    public void aiLogic(int[] aidetails, long deltatick ){

        // Player coordinates //COMPLETE...
        // Timers// Morton shock cannon, Homing ballitics metal, Go up or down randomly, Aim up or down  middle


        //OTHER THINGS THE AI NEEDS. extra coord for placing fired projectiles, Extra coord for
        // Jet flames on boots, Extra coord for morton cannon arm.
        if(_core_Object_Sub_Animation_State_14 == Static.BOSS_1_ACTIVE_AIM_STRAIGHT)
        {
            if (aidetails[1] <= _core_Y_Hitbox_3) {
                    _apply_Force = true;
            }
            if (aidetails[1] > _core_Y_Hitbox_3) {
                _apply_Force = false;
            }
        }
        if(_core_Object_Sub_Animation_State_14 == Static.BOSS_1_ACTIVE_AIM_DOWN)
        {
            if (aidetails[1] <= _core_Y_Hitbox_3 +  GamePanel.HEIGHT/(100/30)) {
                _apply_Force = true;
            }
            if (aidetails[1] > _core_Y_Hitbox_3 +  GamePanel.HEIGHT/(100/30)) {
                _apply_Force = false;
            }
        }
        if(_core_Object_Sub_Animation_State_14 == Static.BOSS_1_ACTIVE_AIM_UP)
        {
            if (aidetails[1] <= _core_Y_Hitbox_3 -  GamePanel.HEIGHT/(100/30)) {
                _apply_Force = true;
            }
            if (aidetails[1] > _core_Y_Hitbox_3 -  GamePanel.HEIGHT/(100/30)) {
                _apply_Force = false;
            }
        }


            _morton_Cannon_Timer += _timer * (deltatick / 1000.f);
            _juke_Up_Down_Timer += _timer * (deltatick / 1000.f);
            _aim_Up_Down_Timer += _timer * (deltatick / 1000.f);
            _blue_Fire_Cannon_Timer += _timer * (deltatick / 1000.f);

        _homing_Ballistic_Box_Timer += _timer *(deltatick/1000.f);




    }


    //////////////////////////
    //PHYSICS IMPLEMENTATION//
    //////////////////////////
    //Leader method
    public void objectPhysics(int totalobjectcollided,  ArrayList<int[]> collisiondetails, long deltaticks) {
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



        //SECOND & FORTH (only consider collisions if the charcter state is right)
        if(totalobjectcollided != 0) {
            for (int i = 0; i < totalobjectcollided; i++) {
                switch (_core_Object_State_10){
                    case Static.GENERAL_OBJECT_STATE_ACTIVE:
                    case Static.GENERAL_OBJECT_STATE_HARMED:

                        applyCollisionForces(collisiondetails.get(i), deltaticks);

                }
            }
        }
        else
        {

            if (_core_Y_Vel_1 > 0){_core_Y_Vel_1 -= 5;}
            else if(_core_Y_Vel_1 < 0){_core_Y_Vel_1 += 5;}
            else if((_core_Y_Vel_1 < 10)&&(_core_Y_Vel_1 > -10)){_core_Y_Vel_1 = 0;}

            if (_core_X_Vel_0 > 0){_core_X_Vel_0 -= 5;}
            else if(_core_X_Vel_0 < 0){_core_X_Vel_0 += 5;}
            else if((_core_X_Vel_0 < 10)&&(_core_X_Vel_0> -10)){_core_X_Vel_0 = 0;}




        }
        //FOURTH


        //FIFTH
        applyMiscForces(deltaticks);
    }

    public void applyGravity(long deltaticks){
      //  _core_X_Vel_0 += Math.cos(_core_Degree_Gravity_11 * Math.PI/180) * Static.GRAVITY * (deltaticks / 1000.f);
        if (_timer_Actions[2] == false)
        {_core_Y_Vel_1 += -Math.sin(_core_Degree_Gravity_11 * Math.PI/180) * Static.GRAVITY * (deltaticks / 1000.f);}
    }//Apply gravity to an object in any direction
    public void applyCollisionForces( int collisiondetails[], long deltaticks) {
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

            case Static.COLLISION_2D_PIERCE:
                applyCollisionForcePierce(collisiondetails ,deltaticks);
                break;
        }

    }//Apply the forces from interacting with other objects
    public void applyCollisionForcePierce(int collisiondetails[], long deltaticks){
        switch (collisiondetails[1])
        {
            case Static.FLOATINGICEBLOCK:


                break;
        }
    }
    public void applyMiscForces(long deltaticks){
        if((_apply_Force == true)&&(_timer_Actions[2] == false)) {
            _core_Y_Vel_1 += -Math.sin(90 * Math.PI / 180) * Static.GRAVITY * 2 * (deltaticks / 1000.f);
        }

        if ((_juke_Action == true)&&(_timer_Actions[2] == false))
        {
            _core_Y_Vel_1 = _core_Y_Vel_1*5;
        }

        if(_timer_Actions[2] == true)
        {
            if (_core_Y_Vel_1 > 0){_core_Y_Vel_1 -= 5;}
            else if(_core_Y_Vel_1 < 0){_core_Y_Vel_1 += 5;}

            if (_core_X_Vel_0 > 0){_core_X_Vel_0 -= 5;}
            else if(_core_X_Vel_0 < 0){_core_X_Vel_0 += 5;}

            if((_core_X_Vel_0 < 10)&&(_core_X_Vel_0> -10)){_core_X_Vel_0 = 0;}
            if((_core_Y_Vel_1 < 10)&&(_core_Y_Vel_1 > -10)){_core_Y_Vel_1 = 0;}

        }
    }//Apply forces from not related to anything above


    //////////////////////////////////
    //   FLAGS  IMPLEMENTATION      //
    //////////////////////////////////
    //Leader method
    public void eventFlags(int totalobjectcollided , ArrayList<int[]> collisiondetails) {
        /*/1ST APPLY FLAG TRIGGGERS FROM COLLISIONS
        //2ND APPLY FLAG TRIGGERS FROM SPECIAL CONDITIONS
        //Sometimes 1st and 2nd work in unity*/

        //FIRST
        if(totalobjectcollided != 0)
        {
            for (int i = 0; i < totalobjectcollided; i++) {
                switch (_core_Object_State_10) {
                    case Static.GENERAL_OBJECT_STATE_ACTIVE:
                    case Static.GENERAL_OBJECT_STATE_HARMED:

                            applyCollisionFlagTrigger(collisiondetails.get(i));

                        break;
                }
            }
        }
        else
        {

        }
        //SECOND
        applySpecialFlagTrigger();

    }

    //NEW 4/28/2017
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
            case Static.FIREBALL:
                break;
            case Static.FLOATINGICEBLOCK:


                break;

        }
    }
    public void applySpecialFlagTrigger() {



        switch (_core_Object_State_10) {
            case Static.GENERAL_OBJECT_STATE_ACTIVE:
            case Static.GENERAL_OBJECT_STATE_HARMED:

                //1ST FLAG///If this object goes above or below the screen border
                if (_core_Y_Hitbox_3 + _core_H_Hitbox_7 > GamePanel.HEIGHT ) {
                    //Bottom border
                    _core_Y_Vel_1 = 0;
                    _core_Y_Hitbox_3 = GamePanel.HEIGHT - _core_H_Hitbox_7;
                    _core_Y_Coord_1 = (int)_core_Y_Hitbox_3 - _core_Height_2;


                }
                else if (_core_Y_Hitbox_3 < 0) {
                    //Top border
                    _core_Y_Vel_1 = 0;
                    _core_Y_Hitbox_3 = 0;
                    _core_Y_Coord_1 = (int)_core_Y_Hitbox_3 - _core_Height_2;


                }
                if (_core_X_Hitbox_2 + _core_W_Hitbox_6 > GamePanel.WIDTH ) {
                    //right
                    _core_X_Vel_0 = 0;
                    _core_X_Hitbox_2 = GamePanel.WIDTH - _core_W_Hitbox_6;
                    _core_X_Coord_0 = (int)_core_X_Hitbox_2 - _core_Width_3;


                }
                else if (_core_X_Hitbox_2 < 0) {
                    //left
                    _core_X_Vel_0 = 0;
                    _core_X_Hitbox_2 = 0;
                    _core_X_Coord_0 = (int)_core_X_Hitbox_2 - _core_Width_3;
                }

                //2ND FLAG. Aim
                if ((_aim_Up_Down_Timer >= 20)&&(_timer_Actions[2]==false))
                {
                    _core_Object_Sub_Animation_State_14 = rand.nextInt(Static.BOSS_1_ACTIVE_AIM_DOWN + 1);
                    _aim_Up_Down_Timer = 0;
                }


                //3RD FLAG. Juke
                if ((_juke_Up_Down_Timer >= 100/*20*/)&&(_timer_Actions[2]==false))
                {
                    _juke_Up_Down_Timer = 0;
                    _juke_Action = true;
                }
                else
                {
                    _juke_Action = false;
                }

                //4TH FLAG. Blue fire ball
                if ((_blue_Fire_Cannon_Timer >= 2000)&&(_timer_Actions[2]==false)&&(_timer_Actions[0]==false))
                {
                    _blue_Fire_Cannon_Timer = 0;
                    _timer_Actions[0] = true;
                    _object_Orders[0] = Static.BLUEFIREBALL;
                }


                //5TH FLAG. Morton Cannon
                if ((_morton_Cannon_Timer >=500)&&(_timer_Actions[2]==false)&&(_timer_Actions[1]==false))
                {
                    _morton_Cannon_Timer = 0;
                    _timer_Actions[1] = true;
                    _object_Orders[0] =  Static.ENERGYICESPHERE;
                }

                //6TH FLAG. Morton Cannon
                if ((_homing_Ballistic_Box_Timer >= 10/*30*/)&&(_timer_Actions[2]==false))
                {
                    _core_Object_Sub_Animation_State_14 = Static.BOSS_1_ACTIVE_BALLISTIC_SHOT;
                    _timer_Actions[2] = true;
                    _object_Orders[0] =  Static.BALLISTICMETALBOX;
                    if (_homing_Ballistic_Box_Timer > 40)
                    {
                        _timer_Actions[2] = false;
                        _homing_Ballistic_Box_Timer = 0;
                    }
                }

                //7TH FLAG TURN OFF OBJECT ORDERS
                if((_timer_Actions[0] == false)&&(_timer_Actions[1] == false)&&(_timer_Actions[2] == false))
                {
                    _object_Orders[0] = -1;
                }

                break;
            case Static.GENERAL_OBJECT_STATE_DEAD:

                if(_core_Y_Coord_1 > GamePanel.HEIGHT) {
                }
                break;

        }
    }



    //////////////////////////////////
    //          SETTER METHODS      //
    //////////////////////////////////

    //Leader method
    public void setObjectState(int value)
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
    public int[] getObjectOrders(){

        if(_timer_Actions[0] == true) {
           switch(_core_Object_Sub_Animation_State_14) {
               case Static.BOSS_1_ACTIVE_AIM_STRAIGHT:
                   _object_Orders[0] = Static.BLUEFIREBALL;
                   _object_Orders[1] = _special_Coords[0];
                   _object_Orders[2] = _special_Coords[1];
                   _object_Orders[3] = 180; //180 DEGREE
                   _object_Orders[4] = 1200; //50 MAGNITUDE
                   break;
               case Static.BOSS_1_ACTIVE_AIM_DOWN:
                   _object_Orders[0] = Static.BLUEFIREBALL;
                   _object_Orders[1] = _special_Coords[6];
                   _object_Orders[2] = _special_Coords[7];
                   _object_Orders[3] = 190; //180 DEGREE
                   _object_Orders[4] = 1200; //50 MAGNITUDE
                   break;
               case Static.BOSS_1_ACTIVE_AIM_UP:
                   _object_Orders[0] = Static.BLUEFIREBALL;
                   _object_Orders[1] = _special_Coords[8];
                   _object_Orders[2] = _special_Coords[9];
                   _object_Orders[3] = 170; //180 DEGREE
                   _object_Orders[4] = 1200; //50 MAGNITUDE
                   break;

           }
        }

        if(_timer_Actions[1] == true) {
            switch(_core_Object_Sub_Animation_State_14) {
                case Static.BOSS_1_ACTIVE_AIM_STRAIGHT:
                    _object_Orders[0] = Static.ENERGYICESPHERE;
                    _object_Orders[1] = _special_Coords[2];
                    _object_Orders[2] = _special_Coords[3];
                    _object_Orders[3] = 180; //180 DEGREE
                    _object_Orders[4] = 400; //50 MAGNITUDE
                    break;
                case Static.BOSS_1_ACTIVE_AIM_DOWN:
                    _object_Orders[0] = Static.ENERGYICESPHERE;
                    _object_Orders[1] = _special_Coords[2];
                    _object_Orders[2] = _special_Coords[3];
                    _object_Orders[3] = 180; //180 DEGREE
                    _object_Orders[4] = 400; //50 MAGNITUDE
                    break;
                case Static.BOSS_1_ACTIVE_AIM_UP:
                    _object_Orders[0] = Static.ENERGYICESPHERE;
                    _object_Orders[1] = _special_Coords[2];
                    _object_Orders[2] = _special_Coords[3];
                    _object_Orders[3] = 180; //180 DEGREE
                    _object_Orders[4] = 300; //50 MAGNITUDE
                    break;
            }
        }
        if(_timer_Actions[2] == true) {
            _object_Orders[0] = Static.BALLISTICMETALBOX;
            _object_Orders[1] = _special_Coords[4];
            _object_Orders[2] = _special_Coords[5];
            _object_Orders[3] = 180; //180 DEGREE
            _object_Orders[4] = 1; //50 MAGNITUDE
        }

        return _object_Orders;
    }


    public int getGravityType(){return _core_Gravity_Type_4;}
    public int getObjectType(){return _core_Object_Type_5;}
    public int getObjectStatus(){return _core_Object_Status_9;}


    //////////////////////////////////
    //          MISC METHODS       //
    //////////////////////////////////
    public void setAnimations() {
        //assign all anitions
        for(int i = 0; i < 5; i++) {
            _animation_Cells[i] = new Animation();
        }

        int scaleValue = GamePanel.WIDTH/(100/25);

        //A0 = AIM STRAIGHT
        Bitmap[] A0 = new Bitmap[1];
        A0[0] = Bitmap.createBitmap(spriteSheet, 191, 0, 191, 149);
        A0[0] = Bitmap.createScaledBitmap(A0[0], scaleValue, scaleValue, false);
        _animation_Cells[0].setFrames(A0);
        _animation_Cells[0].setDelay(500);

        //A1 = AIM UP
        Bitmap[] A1 = new Bitmap[1];
        A1[0] = Bitmap.createBitmap(spriteSheet, 191*3, 0,  191, 149);
        A1[0] = Bitmap.createScaledBitmap(A1[0], scaleValue, scaleValue, false);
        _animation_Cells[1].setFrames(A1);
        _animation_Cells[1].setDelay(500);

        //A2 = AIM DOWN
        Bitmap[] A2 = new Bitmap[1];
        A2[0] = Bitmap.createBitmap(spriteSheet, 191*2, 0,  191, 149);
        A2[0] = Bitmap.createScaledBitmap(A2[0], scaleValue, scaleValue, false);
        _animation_Cells[2].setFrames(A2);
        _animation_Cells[2].setDelay(500);

        //A3 = HARMED
        Bitmap[] A3 = new Bitmap[3];
        A3[0] = Bitmap.createBitmap(spriteSheet, 191*4, 0,  191, 149);
        A3[1] = Bitmap.createBitmap(spriteSheet, 191*5, 0,  191, 149);
        A3[2] = Bitmap.createBitmap(spriteSheet, 191*6, 0,  191, 149);
        A3[0] = Bitmap.createScaledBitmap(A3[0], scaleValue, scaleValue, false);
        A3[1] = Bitmap.createScaledBitmap(A3[1], scaleValue, scaleValue, false);
        A3[2] = Bitmap.createScaledBitmap(A3[2], scaleValue, scaleValue, false);
        _animation_Cells[3].setFrames(A3);
        _animation_Cells[3].setDelay(50);

        //A3 = BALLISTIC METAL CANNON ATTACK
        Bitmap[] A4 = new Bitmap[1];
        A4[0] = Bitmap.createBitmap(spriteSheet, 191*7, 0,  191, 149);
        A4[0] = Bitmap.createScaledBitmap(A4[0], scaleValue, scaleValue, false);
        _animation_Cells[4].setFrames(A4);
        _animation_Cells[4].setDelay(500);

    }
    public void setTurnOffTimerActions(int value){
        switch(value)
        {
            case Static.BLUEFIREBALL:   _timer_Actions[0] = false;
                break;
            case Static.ENERGYICESPHERE:   _timer_Actions[1] = false;
                break;
            case Static.BALLISTICMETALBOX:
                if (_homing_Ballistic_Box_Timer > 35)
                {
                    _timer_Actions[2] = false;
                    _homing_Ballistic_Box_Timer = 0;
                }
                break;
        }

    }

    //////////////////////////////////
    //     Animation & Drawing      //
    //////////////////////////////////
    //Leader method
    public void draw(Canvas canvas) {
        canvas.drawBitmap(_animation_Cells[_animation_Cell_Frame].getImage(), _core_X_Coord_0, _core_Y_Coord_1, null);

        //Draw the special coords to see where they should be
        canvas.drawRect(_special_Coords[0], _special_Coords[1], _special_Coords[0]+20, _special_Coords[1]+20, paint);
        canvas.drawRect(_special_Coords[2], _special_Coords[3], _special_Coords[2]+20, _special_Coords[3]+20, paint);
        canvas.drawRect(_special_Coords[4], _special_Coords[5], _special_Coords[4]+20, _special_Coords[5]+20, paint);
        canvas.drawRect(_special_Coords[6], _special_Coords[7], _special_Coords[6]+20, _special_Coords[7]+20, paint);
        canvas.drawRect(_special_Coords[8], _special_Coords[9], _special_Coords[8]+20, _special_Coords[9]+20, paint);
      // canvas.drawRect((int)_core_X_Hitbox_2, (int)_core_Y_Hitbox_3,(int)_core_X_Hitbox_2 + _core_W_Hitbox_6,(int)_core_Y_Hitbox_3 + _core_H_Hitbox_7, paint);

    }


}
