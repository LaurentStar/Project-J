package com.vinnstar.myfirstgame;

import java.util.ArrayList;

/*
]
 1)Classes Have all words with starting with upper case [EXAMPLE "MainMenu"]
 2)Methods for the first word staring with lower case and than upper case for the rest [EXAMPLE "mainMenu"]
 3)Static has all upper case [EXAMPLE "MAIN_MENU"]
 4)Local Variables to a class start with index and lower case follow by index between words with upper case [EXAMPLE "_main_Menu"]
 5)limited scope & Parameter Variables are lower case [EXAMPLE "mainmenu"]

 //QUICK REFERENCE//
 1)Class [EXAMPLE "MainMenu"]
 2)Method [EXAMPLE "mainMenu"]
 3)Static [EXAMPLE "MAIN_MENU"]
 4)Local [EXAMPLE "_main_Menu"]
 5)Limited Scope & Paremeter [EXAMPLE "mainmenu"]

 */
//Holds Core Data for Master class
public class DataClass {


    public int _total_Integers = 15;
    public int _core_X_Coord_0;
    public int _core_Y_Coord_1;
    public int _core_Height_2;
    public int _core_Width_3;
    public int _core_Gravity_Type_4;
    public int _core_Object_Type_5;
    public int _core_W_Hitbox_6;
    public int _core_H_Hitbox_7;
    public int _core_Collision_Status_8;
    public int _core_Object_Status_9;
    public int _core_Object_State_10;
    public int _core_Degree_Gravity_11;
    public int _core_Magnitude_12;
    public int _core_ID_13;
    private int _core_Object_Sub_Animation_State_14;

    public int _total_Doubles = 4;
    public double _core_X_Vel_0;
    public double _core_Y_Vel_1;
    public double _core_X_Hitbox_2;
    public double _core_Y_Hitbox_3;

    public int _total_Boolean = 5;
    public boolean _core_Collided_Bottom_0;
    public boolean _core_Collided_Top_1;
    public boolean _core_Collided_Left_2;
    public boolean _core_Collided_Right_3;
    public boolean _core_Object_State_Change_4;

    public ExtraHitbox _core_Game_Object_Hitbox;
    ArrayList<ExtraHitbox> _game_Object_Hitbox;  //Just in case we need more than one

    /////////////////////////////
    //METHOD CLUB, METHODS ONLY//
    /////////////////////////////
    public DataClass(){}

    public void createNewHitBox(int x, int y, int w, int h ) {
        _game_Object_Hitbox.add(new ExtraHitbox(x, y, w, h));
    }




};
