package com.vinnstar.myfirstgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * Created by Laurent on 1/5/2017.
 */
public class Physics {

    //jagged array
    //private int[][] _jagged_Array = new int[10][];

    //Regular Array
    private int _alpha_Array[], _omega_Array[];
    //Reusable int type. Used in collision detection calculation
    private int object_State_2D, _total_Objects_Collided;

    private boolean _solid_Object_Detected = false;
    private ArrayList<int[]> _jagged_Array = new ArrayList<int[]>();
    private ArrayList<boolean[]> _jagged_Boolean_Array = new ArrayList<boolean[]>();
    //private Vector<int[]> _jagged_Array = new Vector<>(10);

    private GlobalFunctions _global;


    //////////////////////////
    //      Constructor     //
    //////////////////////////
    public Physics( GlobalFunctions global){
        _global = global;
    }

    //NEW 5/7/2017
    public void gamePhysicsObjectUpdate(ArrayList<GameObject> gameobject, long deltaticks){

        //NEW
        for (GameObject sp: gameobject) {
            switch (sp.getGravityType()){
                case Static.DYNAMIC_GRAVITY:
                case Static.FIXED_GRAVITY:
                case Static.STATIC_GRAVITY:
                        sp.variableUpdate(deltaticks);
                    break;

            }
        }
    }

    //NEW 5/7/2017
    public void gamePhysics(ArrayList<GameObject> gameobject, long deltaticks){


        /*int a = 0;
        for (GameObject sp: gameObject) {
            if (sp.getGravity_type() == Static.DYNAMIC_GRAVITY) {

               physicsCalculation( gameObject, a);
               sp.objectPhysics( object_state_2D, deltaticks);

            }


            sp.gameInput();
        }*/


/*
           //NEW
        for (int i = 0; i <  gameObject.size(); i++)
        {
            if (gameObject.get(i).getGravity_type() == Static.DYNAMIC_GRAVITY)
            {
                physicsCalculation( gameObject, i, deltaticks, levelBrain);
                gameObject.get(i).objectPhysics( object_state_2D, deltaticks);
            }
            gameObject.get(i).gameInput();
        }*/

        int lenght = gameobject.size();
        for (int i = 0; i <  lenght; i++) {
                switch (gameobject.get(i).getObjectType()) {
                    case Static.PLAYER:
                        _alpha_Array = gameobject.get(i).getCollisionDetailsArray();
                        collisionCalculation(gameobject, i);
                        if (_solid_Object_Detected == true) {gameobject.get(i).objectPhysics(_total_Objects_Collided,_jagged_Array,_jagged_Boolean_Array, deltaticks);}
                        if (_solid_Object_Detected == false) {gameobject.get(i).objectPhysics(_total_Objects_Collided,_jagged_Array,null, deltaticks);}
                    break;
                    case Static.FIREBALL:
                        _alpha_Array = gameobject.get(i).getCollisionDetailsArray();
                        collisionCalculation(gameobject, i);
                        gameobject.get(i).objectPhysics(_total_Objects_Collided,_jagged_Array, deltaticks);
                    break;
                    case Static.BOSS1:
                        _alpha_Array = gameobject.get(i).getCollisionDetailsArray();
                        collisionCalculation(gameobject, i);
                        gameobject.get(i).objectPhysics(_total_Objects_Collided,_jagged_Array, deltaticks);
                    break;
                    case Static.ENERGYICESPHERE:
                    case Static.BALLISTICMETALBOX:
                        gameobject.get(i).objectPhysics(deltaticks);
                        break;

                }
        }
    }


    public void collisionCalculation(ArrayList<GameObject> gameobject, int elementcheck ) {
        //0 collision status
        //1 the type of object
        //2 the state of the object
        //3 Magnitude
        //4 Direction
        //5 the hit box X
        //6 the hit box y
        //7 the hit box w
        //8 the hit box h

        _solid_Object_Detected = false;
        _total_Objects_Collided = 0;


        int lenght = gameobject.size();
        for (int i = 0; i < lenght; i++)
        {

            if(i != elementcheck)
            {
                //Set the object equal to


                switch (gameobject.get(i).getObjectType())
                {
                    case Static.PLAYER:
                    case Static.FIREBALL:
                    case Static.FLOATINGICEBLOCK:
                    _omega_Array = gameobject.get(i).getCollisionDetailsArray();
                    object_State_2D = _global.twoDSquareObjectDetection(_alpha_Array[5], _alpha_Array[6], _alpha_Array[7], _alpha_Array[8], _omega_Array[5], _omega_Array[6], _omega_Array[7], _omega_Array[8]);



                    switch(_alpha_Array[1])
                    {
                        case Static.PLAYER:
                            switch (_omega_Array[1])
                            {
                                case Static.FLOATINGICEBLOCK:
                                    if (_total_Objects_Collided <= _jagged_Boolean_Array.size() - 1) {
                                        _jagged_Boolean_Array.set(_total_Objects_Collided, _global.pointOfContactDetection(_alpha_Array[5], _alpha_Array[6], _alpha_Array[7], _alpha_Array[8], _omega_Array[5], _omega_Array[6], _omega_Array[7], _omega_Array[8], object_State_2D));
                                    }
                                    else if (_total_Objects_Collided > _jagged_Boolean_Array.size() - 1)
                                    {
                                        _jagged_Boolean_Array.add(_global.pointOfContactDetection(_alpha_Array[5], _alpha_Array[6], _alpha_Array[7], _alpha_Array[8], _omega_Array[5], _omega_Array[6], _omega_Array[7], _omega_Array[8], object_State_2D));
                                    }
                                    _solid_Object_Detected = true;
                                    break;
                                case Static.FIREBALL:
                                    break;
                            }
                        break;
                    }
                    switch (object_State_2D)
                    {
                        case Static.COLLISION_2D_PIERCE:
                        case Static.COLLISION_2D_TOUCH:

                            //Define how many objects have been collided with
                            //_collided_Object_Location.set(0, totalobjectscollided);

                            if(_total_Objects_Collided <= _jagged_Array.size() - 1)
                            {
                                _jagged_Array.set(_total_Objects_Collided, gameobject.get(i).getCollisionDetailsArray());

                            }
                            else if(_total_Objects_Collided > _jagged_Array.size() - 1)
                            {
                                _jagged_Array.add(gameobject.get(i).getCollisionDetailsArray());
                            }
                            _jagged_Array.get(_total_Objects_Collided)[0] = object_State_2D;


                            //_jagged_Array[_total_Objects_Collided] = gameobject.get(i).getCollisionDetailsArray();

                            _total_Objects_Collided++;

                            break;
                        case Static.MIDAIR:
                            break;
                    }
                }
            }
        }


        //if (_total_Objects_Collided == 0) {

            //returnedvalue = new int[totalobjectscollided][];

            /*for (int a = 0; a < totalobjectscollided; a++) {
                _jagged_Array[a] = gameobject.get(_collided_Object_Location.get(a)).getCollisionDetailsArray();
            }*/

            //_jagged_Array = null;
       // }
    }
}
