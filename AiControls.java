package com.vinnstar.myfirstgame;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Laurent on 6/24/2017.
 */
public class AiControls{


    private int _player_Found_Value= -1;

    //A array list of int arrarys. These store values for all the AI so they can function without interfencing each other.
    private ArrayList<int[]> _public_Array = new ArrayList<int[]>();

    private int[] _ai_Object_Orders; // The info used to order objects
    private int[] _object_Spawn_Limits = new int[2];  ///Keep track of how many objects have been spawn and limits it

    private boolean[] _ai_Actions_Trigger;
    private LevelBrain levelBrain;

    AiControls(LevelBrain levelBrain)
    {
        this.levelBrain = levelBrain;
    }


    public void findPlayer(ArrayList<GameObject> gameobject) {
        if(_player_Found_Value == -1) {
            for (int i = 0; i < gameobject.size(); i++) {
                if (gameobject.get(i).getObjectType() == Static.PLAYER) {
                    _player_Found_Value = i;
                }
            }
        }
    }



    public void aiBrainBehavoir(ArrayList<GameObject> gameobject, long deltaticks){
    //You will have an individual function for each AI handled in this function.

        for(int i = 0; i <  gameobject.size(); i++) {
            switch (gameobject.get(i).getObjectType()) {
                case Static.BOSS1: aiBoss1(gameobject, i, deltaticks); break;


            }
        }
    }


   //AI METHODS ONE FOR EACH OF THEM AND THEIR SPECIAL LOGIC. ALL DATA IS COLLECTED HERE.
    public void aiBoss1(ArrayList<GameObject> gameobject, int element, long deltaticks){

        _ai_Actions_Trigger = null;
        _ai_Object_Orders = null;

        //Get the players position, update the timers, and apply the correct forces to follow the player
        gameobject.get(element).aiLogic( gameobject.get(_player_Found_Value).getBasicAiInfoArray(), deltaticks );
        _ai_Object_Orders = gameobject.get(element).getObjectOrders();


        //If the boss wants to do an action. Get one ready and turn off this action
        if(_ai_Object_Orders[0] != -1)
        {
            for (int a = 0; a < gameobject.size(); a++)
            {
                if ((gameobject.get(a).getObjectType() == _ai_Object_Orders[0])&&(gameobject.get(a).getObjectState() == Static.GENERAL_OBJECT_STATE_NEUTRAL ))
                {
                    gameobject.get(a).setScreenProjectile(_ai_Object_Orders[1], _ai_Object_Orders[2], _ai_Object_Orders[3], _ai_Object_Orders[4]);
                    gameobject.get(a).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                    gameobject.get(element).setTurnOffTimerActions(_ai_Object_Orders[0]);
                    break;
                }
                else if(a == gameobject.size() -1 )
                {
                    switch (_ai_Object_Orders[0])
                    {
                        case Static.BLUEFIREBALL:
                            if (_object_Spawn_Limits[0] < 3) {
                                //_ai_Object_Orders = gameobject.get(element).getObjectOrders();
                                levelBrain.requestObjectCreation(gameobject, _ai_Object_Orders[0]);
                                _object_Spawn_Limits[0]++;
                            }
                            break;
                        case Static.ENERGYICESPHERE:
                            if (_object_Spawn_Limits[1] < 3) {
                               // _ai_Object_Orders = gameobject.get(element).getObjectOrders();
                                levelBrain.requestObjectCreation(gameobject, _ai_Object_Orders[0]);
                                _object_Spawn_Limits[1]++;
                            }
                            break;
                    }
                    break;
                }
            }
        }
    }
}
