package com.vinnstar.myfirstgame;

/**
 * Created by Laurent on 1/8/2017.
 */
public class Static {

    ///////////////////////////////////
    //   GENERAL OBJECT STATUS       //
    ///////////////////////////////////
    public static final int OBJECT_STATUS_ACTIVE = 0;
    public static final int OBJECT_STATUS_INACTIVE = 1; // When an object is ready to be deleted
    public static final int OBJECT_STATUS_FREEZE = 2; //Freeze the object in it's current state.

    ///////////////////////////////////
    //          PLAYER STATES        //
    ///////////////////////////////////
    public static final int PLAYER_STATE_ACTIVE = 0;
    public static final int PLAYER_STATE_DEAD = 1;
    public static final int PLAYER_STATE_LEVEL_COMPLETE = 2;
    public static final int PLAYER_STATE_ENTERING_LEVEL = 3;
    public static final int PLAYER_STATE_HARMED = 4;

    ///////////////////////////////////
    //   GENERAL OBJECTS STATES      //
    ///////////////////////////////////
    public static final int GENERAL_OBJECT_STATE_ACTIVE = 0;
    public static final int GENERAL_OBJECT_STATE_NEUTRAL = 1 ;
    public static final int GENERAL_OBJECT_STATE_HARMED = 2;
    public static final int GENERAL_OBJECT_STATE_DEAD = 3;

    ///////////////////////////////////////
    //   BOSS 1 SUB CATAGORY STATES      //
    ///////////////////////////////////////
    public static final int BOSS_1_ACTIVE_AIM_STRAIGHT = 0;
    public static final int BOSS_1_ACTIVE_AIM_UP = 1;
    public static final int BOSS_1_ACTIVE_AIM_DOWN = 2;
    public static final int BOSS_1_ACTIVE_BALLISTIC_SHOT = 3;

    ///////////////////////////////////
    //         OBJECT TYPE           //
    ///////////////////////////////////
    public static final int PLAYER = 0;
    public static final int FIREBALL = 1;
    public static final int FLOATINGICEBLOCK = 2;
    public static final int NOTHING = 3;
    public static final int BACKGROUND_PIECE = 4;
    public static final int BLUEFIREBALL = 5;
    public static final int BOSS1 = 6;
    public static final int ENERGYICESPHERE = 7;
    public static final int BALLISTICMETALBOX = 8;
    public static final int EXTRAHITBOX = 9;

    //////////////////////////////////////////////
    //        CHILDREN OBJECTS                  //
    //////////////////////////////////////////////
    public static final int SWITCH = 9;

    //////////////////////////////////////////////
    //        CHILDREN OBJECTS STATES           //
    //////////////////////////////////////////////
    public static final int CHILD_OBJECT_STATE_ACTIVE = 0;
    public static final int CHILD_OBJECT_STATE_OFF = 1;

    ///////////////////////////////////
    //         MISC VALUE            //
    ///////////////////////////////////
    public static final int GRAVITY = 1500; // GRAVITY UNITS ARE IN THE 1000s
    public static final int LIGHTER_GRAVITY = 300; // GRAVITY UNITS ARE IN THE 1000s
    public static final int LIGHTER_GRAVITY1 = 30;
    public static final int SUPER_SOFT_GRAVITY = 5;
    //public static double PI = 3.14;//1592;
    public static double DEG = Math.PI/180.0;

    ///////////////////////////////////
    //          GAME MODES           //
    ///////////////////////////////////
    public static final int GAME_MODE = 0;
    public static final int SAVE_MODE = 1;
    public static final int DEATH_MODE = 2;

    ///////////////////////////////////
    //          GRAVITY MODES           //
    ///////////////////////////////////
    public static final int STATIC_GRAVITY = 0;
    public static final int DYNAMIC_GRAVITY = 1;
    public static final int FIXED_GRAVITY = 2;


    ///////////////////////////////////
    //         PHYSICS STATES        //
    ///////////////////////////////////
    public static final int MIDAIR = 0;
    public static final int GROUND_OBJECT_TOUCH = 1;
    public static final int GROUND_AGAINST_FRONT = 2;
    public static final int GROUND_AGAINST_BACK = 3;
    public static final int GROUND_INSIDE_FRONT = 4;
    public static final int GROUND_INSIDE_BACK = 5;
    public static final int INSIDE_FRONT = 6;
    public static final int INSIDE_BACK = 7;
    public static final int GROUNDED = 8;
    public static final int BASE_OBJECT_TOUCH = 9;
    public static final int INSIDE_BLOCK = 10;
    public static final int STANDING_ON_TOP = 11;
    public static final int FALLING_THROUGH_FLOOR1 = 12;
    public static final int FALLING_THROUGH_FLOOR2 = 13;
    public static final int FALLING_THROUGH_FLOOR3 = 14;
    public static final int HIT_FROM_BELOW = 15;
    public static final int AGAINST_FRONT = 16;
    public static final int AGAINST_BACK = 17;
    public static final int AGAINST_LEFT_SIDE = 18;
    public static final int GOING_THROUGH_LEFT_SIDE = 19;
    public static final int AGAINST_RIGHT_SIDE = 20;
    public static final int GOING_THROUGH_RIGHT_SIDE = 21;
    public static final int COLLISION_2D_TOUCH = 22;
    public static final int COLLISION_2D_PIERCE = 23;
    public static final int COLLISION_2D_INSIDE = 24;




}
