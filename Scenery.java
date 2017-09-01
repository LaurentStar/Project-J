package com.vinnstar.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Laurent on 6/30/2016.
 */
public class Scenery extends GameObject {
    ///////////////////////////////////////////
    //                  Arrays               //
    ///////////////////////////////////////////




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

    ///////////////////////////////////////////
    //          CLASS DECLARATION            //
    ///////////////////////////////////////////
    private ExtraHitbox _core_Game_Object_Hitbox;
    private DataClass _core_Data = new DataClass();
    private Bitmap spriteSheet;



    //previous dimension Height = pdimensionH
    public Scenery(Bitmap res, int mark, int dimensionW, int dimensionH,int pdimensionH) {

        double value1, value2;
        ////////////////////////////
        // SPECIAL CLASS DEFINING //
        ////////////////////////////

        spriteSheet = res;

        //GOALS SHRETCH THE IMAGE WITHOUT SQUEEZING IT.
        spriteSheet = Bitmap.createBitmap(spriteSheet, 0, 0, dimensionW, dimensionH);

        //////////////////////
        //      SPECIAL     //
        //////////////////////
        _core_X_Hitbox_2 = 0;
        _core_Y_Hitbox_3 = GamePanel.HEIGHT/2;

        /////////////////////
        //INTEGERS DEFINING//
        /////////////////////
        _core_X_Coord_0 = (int)_core_X_Hitbox_2;
        _core_Y_Coord_1 = (int)_core_Y_Hitbox_3;
        _core_Height_2 = 0;
        _core_Width_3 = 0;
        _core_Gravity_Type_4 = Static.FIXED_GRAVITY;
        _core_Object_Type_5 = Static.BACKGROUND_PIECE;
        _core_W_Hitbox_6 = 0;
        _core_H_Hitbox_7 = 0;
        _core_Collision_Status_8 = Static.MIDAIR;
        _core_Object_Status_9 = Static.OBJECT_STATUS_ACTIVE;
        _core_Object_State_10 =  Static.GENERAL_OBJECT_STATE_ACTIVE;
        _core_Degree_Gravity_11 = 180; //180 degrees on the unit circle going counter clockwise
        _core_Magnitude_12 = 0; // You didn't know what else to put
        _core_Object_Sub_Animation_State_14 = 0;

        /////////////////////
        //DOUBLE DEFINING  //
        /////////////////////
        _core_X_Vel_0 = (GamePanel.MOVESPEED - mark*2)/2;
        _core_Y_Vel_1 = 0;
        _core_X_Hitbox_2 = 0;
        _core_Y_Hitbox_3 = GamePanel.HEIGHT/2;


        /////////////////////
        //BOOLEAN DEFINING //
        /////////////////////
        _core_Collided_Bottom_0 = false;
        _core_Collided_Top_1 = false;
        _core_Collided_Left_2 = false;
        _core_Collided_Right_3 = false;
        _core_Object_State_Change_4 = false;


        switch (mark) {
            case 0:     _core_Y_Hitbox_3 = 0;
                        spriteSheet = Bitmap.createScaledBitmap(spriteSheet, GamePanel.WIDTH, GamePanel.HEIGHT, false);
                break;
            case 1:     value1 = 100.0; value2 = 30.0;
                        _core_Y_Hitbox_3 =  (int)(GamePanel.HEIGHT / (value1/value2)); //    20% Starting from the top
                        spriteSheet = Bitmap.createScaledBitmap(spriteSheet, GamePanel.WIDTH, GamePanel.HEIGHT / (100/10), false); // This image is 20%
                break;
            case 2:     value1 = 100.0; value2 = 34.0;
                        _core_Y_Hitbox_3 =  (int)(GamePanel.HEIGHT / (value1/value2)); //    20% Starting from the top
                        spriteSheet = Bitmap.createScaledBitmap(spriteSheet, GamePanel.WIDTH, GamePanel.HEIGHT / (100/15), false);
                break;
            case 3:     value1 = 100.0; value2 = 40.0;
                        _core_Y_Hitbox_3 =  (int)(GamePanel.HEIGHT / (value1/value2)); //    20% Starting from the top
                        spriteSheet = Bitmap.createScaledBitmap(spriteSheet, GamePanel.WIDTH, GamePanel.HEIGHT / (100/20), false);
                break;
            case 4:     value1 = 100.0; value2 = 48.0;
                         _core_Y_Hitbox_3 =  (int)(GamePanel.HEIGHT / (value1/value2)); //    20% Starting from the top
                        spriteSheet = Bitmap.createScaledBitmap(spriteSheet, GamePanel.WIDTH, GamePanel.HEIGHT / (100/25), false);
                break;
            case 5:     value1 = 100.0; value2 = 59.0;
                        _core_Y_Hitbox_3 =  (int)(GamePanel.HEIGHT / (value1/value2)); //    20% Starting from the top
                        spriteSheet = Bitmap.createScaledBitmap(spriteSheet, GamePanel.WIDTH, GamePanel.HEIGHT / (100/30), false);
                break;
            case 6:     value1 = 100.0; value2 = 70.0;
                        _core_Y_Hitbox_3 =  (int)(GamePanel.HEIGHT / (value1/value2)); //    20% Starting from the top
                        spriteSheet = Bitmap.createScaledBitmap(spriteSheet, GamePanel.WIDTH, GamePanel.HEIGHT / (100/35), false);
                break;
        }
    }

    public void variableUpdate(long deltatick) {
        _core_X_Hitbox_2 += _core_X_Vel_0;
        _core_X_Coord_0 = (int)_core_X_Hitbox_2 - _core_Width_3;
        _core_Y_Coord_1 = (int)_core_Y_Hitbox_3 - _core_Height_2;
        if(_core_X_Hitbox_2 < -GamePanel.WIDTH){
            _core_X_Hitbox_2 = 0;
        }


        //////////////////////////////////////////////////
        //              Update Data Class               //
        //////////////////////////////////////////////////

        //////////////////////////////////////////////////
        //              Update Data Class               //
        //////////////////////////////////////////////////
    }

    //////////////////////////////////
    //          SETTER METHODS      //
    //////////////////////////////////
    public void setID(int value){_core_ID_13 = value;}

    //////////////////////////////////
    //          GETTER METHODS      //
    //////////////////////////////////

    public int getObjectType(){return _core_Object_Type_5;}
    public DataClass getDataClass(){return _core_Data;}

    //////////////////////////////////
    //     Animation & Drawing      //
    //////////////////////////////////
    public void draw(Canvas canvas) {
        //canvas.drawBitmap(image, x, y, null);
        canvas.drawBitmap(spriteSheet,_core_X_Coord_0, _core_Y_Coord_1/*(int)core_y_coord*/, null);
        if (_core_X_Coord_0  < 0) {
            canvas.drawBitmap(spriteSheet,_core_X_Coord_0 + GamePanel.WIDTH, _core_Y_Coord_1 /*(int)core_y_coord*/, null);
           // canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
        }
    }
}
