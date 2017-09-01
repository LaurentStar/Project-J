package com.vinnstar.myfirstgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Laurent on 2/26/2017.
 This class programs the objectives of each levels
 */






public class LevelBrain {

    private ArrayList<int[]> _jagged_Array = new ArrayList<int[]>();
    private ArrayList<boolean[]> _jagged_Boolean_Array = new ArrayList<boolean[]>();
    private ArrayList<int[][]> _jagged_Super_Int_Array = new ArrayList<int[][]>(); //A jagged array that hold jagged array. not 3rd dimensional

    //Due to not having pointer these will be used to automate the process of picking locations. NEVER generate a "new" arraylist with these
    private ArrayList<boolean[]> _signal_Boolean_Array;
    private ArrayList<int[][]> _signal_Jagged_Int_Array;

    //Regular Array
    private int _alpha_Array[], _omega_Array[], _child_Omega_Array[];



    private int _lvl_1_Points = 0, _lvl_2_Points = 0;
    private int _flag = 0;
    private int _objects_Found = 0;
    private int _sound_ID;
    private final int _fx_Clips = 1;
    private int _player_Found_Value= -1;
    private int object_State_2D, _total_Objects_Collided;//Reusable int type. Used in collision detection calculation


    private boolean _win_Level = false;
    private boolean _save_Progres = false;
    private boolean _activate_Flag = false;

    private boolean _solid_Object_Detected = false;


    private MediaPlayer _music = new MediaPlayer();
    private SoundPool _fx = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

    String levelProgressFile = "LevelProgress.txt";

    private Context _context;
    private GlobalFunctions _global;
    private ReadFile _readFile;
    private DataClass _alpha_Object;


    //////////////////////////
    //      Constructor     //
    //////////////////////////
    public LevelBrain( GlobalFunctions global, Context context, ReadFile readFile) {
        _context = context;
        _readFile = readFile;
       // music = MediaPlayer.create(context, R.raw.level_1);
        _global = global;
    }


    //Calls the intialization method of an object to have it set before using it.
    public void objectIntialization(ArrayList<GameObject> gameobject)
    {

    }


    public void requestObjectCreation(ArrayList<GameObject> gameobject, int type)
    {
        switch (type)
        {
           case Static.FIREBALL: gameobject.add(new ObjectFireBall(BitmapFactory.decodeResource(_context.getResources(), R.drawable.redcyrstal))); break;
           case Static.BLUEFIREBALL: gameobject.add(new ObjectBlueFireBall(BitmapFactory.decodeResource(_context.getResources(), R.drawable.bluebeam))); break;
           case Static.ENERGYICESPHERE: gameobject.add(new ObjectIceEnergySphere(BitmapFactory.decodeResource(_context.getResources(), R.drawable.icespheres))); break;
           case Static.BALLISTICMETALBOX: gameobject.add(new ObjectMetalBallisticBox(BitmapFactory.decodeResource(_context.getResources(), R.drawable.blasticmetal))); break;
        }
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

    public void setObjectID(ArrayList<GameObject> gameobject) {

        for (int i = 0; i <  gameobject.size(); i++)
        {
          gameobject.get(i).setID(i);
        }
    }

    //NEW 5/10/2017
    public void levelBrainSelector(ArrayList<GameObject> gameobject, int levelnum) {

        switch(levelnum)
        {
            case 1: levelBrain1(gameobject); break;
            case 2: levelBrain2(gameobject); break;
        }
        gameEventTriggers(gameobject);
    }



    public void gameEventTriggers(ArrayList<GameObject> gameobject){
        for (int i = 0; i <  gameobject.size(); i++) {
            switch (gameobject.get(i).getObjectType()) {
                case Static.PLAYER:
                    _alpha_Array = gameobject.get(i).getCollisionDetailsArray();
                    collisionCalculation(gameobject, i);
                    if(_solid_Object_Detected == true){
                        _signal_Boolean_Array = _jagged_Boolean_Array;
                        gameobject.get(i).eventFlags(_total_Objects_Collided, _jagged_Array, _jagged_Boolean_Array);
                    }
                    else if(_solid_Object_Detected == false){
                        _signal_Boolean_Array = null;
                        gameobject.get(i).eventFlags(_total_Objects_Collided, _jagged_Array, null);
                    }


                    gameobject.get(i).eventFlags(_total_Objects_Collided, _jagged_Array, _signal_Boolean_Array, _signal_Jagged_Int_Array);
                    break;
                    case Static.FIREBALL:
                    case Static.FLOATINGICEBLOCK:
                    case Static.BLUEFIREBALL:
                    case Static.ENERGYICESPHERE:
                    case Static.BALLISTICMETALBOX:
                    gameobject.get(i).eventFlags();
                    break;
                case Static.BOSS1:
                    _alpha_Array = gameobject.get(i).getCollisionDetailsArray();
                    collisionCalculation(gameobject, i);
                    gameobject.get(i).eventFlags(_total_Objects_Collided, _jagged_Array);
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
                    case Static.BLUEFIREBALL:
                    case Static.ENERGYICESPHERE:
                    case Static.BALLISTICMETALBOX:
                    case Static.EXTRAHITBOX:
                        //gameobject.get(i).eventFlags();
                        //break;
                        _omega_Array = gameobject.get(i).getCollisionDetailsArray();
                        object_State_2D = _global.twoDSquareObjectDetection(_alpha_Array[5], _alpha_Array[6], _alpha_Array[7], _alpha_Array[8], _omega_Array[5], _omega_Array[6], _omega_Array[7], _omega_Array[8]);



                        //Nested Collisions for special object with controlled children
                        switch(_alpha_Array[1])
                        {
                            //Players detect children objects
                            case Static.PLAYER:
                                switch (_omega_Array[1])
                                {
                                    //Player can detect the children of all object listed below.
                                    case Static.BALLISTICMETALBOX:

                                        if(_total_Objects_Collided <= _jagged_Super_Int_Array.size() - 1)
                                        {
                                            _jagged_Super_Int_Array.set(_total_Objects_Collided, gameobject.get(i).getChildrenCollisionDetailsJaggedArray());
                                        }
                                        else if(_total_Objects_Collided > _jagged_Super_Int_Array.size() - 1)
                                        {
                                            _jagged_Super_Int_Array.add(gameobject.get(i).getChildrenCollisionDetailsJaggedArray());
                                        }


                                        //Retest the collision status with all the children
                                        for (int z = 0; z <_jagged_Super_Int_Array.size();z++)
                                        {
                                            int lenghtofarray = _jagged_Super_Int_Array.get(_total_Objects_Collided).length;
                                            for(int y = 0; y < lenghtofarray ; y++)
                                            {
                                                int _child_object_state;
                                                _child_Omega_Array = _jagged_Super_Int_Array.get(z)[y];


                                                _child_object_state = _global.twoDSquareObjectDetection(_alpha_Array[5], _alpha_Array[6], _alpha_Array[7], _alpha_Array[8], _child_Omega_Array[5], _child_Omega_Array[6], _child_Omega_Array[7], _child_Omega_Array[8]);
                                                _jagged_Super_Int_Array.get(_total_Objects_Collided)[y][0] = _child_object_state;




                                            }

                                        }
                                        _children_Object_Detected = true;
                                        break;
                                }
                                break;
                        }

                        //Solid object detector
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







                        ///////////////////////////////////////////////////////////////////////////////////////////

                        switch (object_State_2D)
                        {
                            case Static.COLLISION_2D_PIERCE:
                            case Static.COLLISION_2D_TOUCH:

                                if(_total_Objects_Collided <= _jagged_Array.size() - 1)
                                {
                                    _jagged_Array.set(_total_Objects_Collided, gameobject.get(i).getCollisionDetailsArray());

                                }
                                else if(_total_Objects_Collided > _jagged_Array.size() - 1)
                                {
                                    _jagged_Array.add(gameobject.get(i).getCollisionDetailsArray());
                                }
                                _jagged_Array.get(_total_Objects_Collided)[0] = object_State_2D;

                                _total_Objects_Collided++;

                                break;
                            case Static.MIDAIR:
                                break;
                        }
                        break;

                }
            }
        }

        //if (_total_Objects_Collided == 0) {
        //    _jagged_Array = null;
        //}
    }



    //Logic for winning levels
    public void levelBrain1(ArrayList<GameObject> gameobject) {

        //The scrub has unfortunately survived..... for now.
        if ((_win_Level == true)&&(_save_Progres == false))
        {   _save_Progres = true;
            gameobject.get(_player_Found_Value).setPlayerState(Static.PLAYER_STATE_LEVEL_COMPLETE);
        }


        // Make the scrub work harder when they hit a flag
        if((_lvl_1_Points == 10)&&(_flag == 0))
        {
            _flag++;
            gameobject.add(new ObjectFireBall(BitmapFactory.decodeResource(_context.getResources(), R.drawable.redcyrstal)));
        }
        if((_lvl_1_Points == 20)&&(_flag == 1))
        {
            _flag++;
            gameobject.add(new ObjectFireBall(BitmapFactory.decodeResource(_context.getResources(), R.drawable.redcyrstal)));
        }
        if((_lvl_1_Points == 50)&&(_flag ==2))
        {
            _flag++;
            gameobject.add(new ObjectFireBall(BitmapFactory.decodeResource(_context.getResources(), R.drawable.redcyrstal)));
        }
        //Ain't no smooth sailing from here, make sure their parting gift from level one is special.
        if((_lvl_1_Points == 70)&&(_flag == 3))
        {
            _flag++;
            gameobject.add(new ObjectFireBall(BitmapFactory.decodeResource(_context.getResources(), R.drawable.redcyrstal)));
            gameobject.add(new ObjectFireBall(BitmapFactory.decodeResource(_context.getResources(), R.drawable.redcyrstal)));
            gameobject.add(new ObjectFireBall(BitmapFactory.decodeResource(_context.getResources(), R.drawable.redcyrstal)));
            gameobject.add(new ObjectFireBall(BitmapFactory.decodeResource(_context.getResources(), R.drawable.redcyrstal)));
        }

        //THE PLAYER WON! KICK THEM THE FUCK OUT!
        if(_lvl_1_Points >= 100)
        {
            _win_Level = true;
        }

        //Check for scored points
        int lenght = gameobject.size();
        for (int i = 0; i < lenght; i++) {
           if(gameobject.get(i).getObjectType() == Static.FIREBALL) {
               if (gameobject.get(i).getSpecialBooleanVar() == true) {
                   _lvl_1_Points++;
                   gameobject.get(i).setSpecialBooleanVar(false);
                   _fx.play(_sound_ID, 1, 1, 0, 0, 1);
               }
           }
        }

        if(_save_Progres == true) {
            if(gameobject.get(_player_Found_Value).getObjectStatus() == Static.OBJECT_STATUS_INACTIVE)
            {
                //The player now has permission to go to level 2 but they won't make it much further.
                if (_readFile.returnProgress() < 2) {
                    _readFile.saveLevelProgress(2);
                    endMusic();
                    gameobject.get(_player_Found_Value).returnToMenu();
                }
                endMusic();
                gameobject.get(_player_Found_Value).returnToMenu();
            }
        }
    }
    public void levelBrain2(ArrayList<GameObject> gameobject) {
        //The scrub has unfortunately survived..... for now
        if ((_win_Level == true)&&(_save_Progres == false))
        {   _save_Progres = true;
            gameobject.get(_player_Found_Value).setPlayerState(Static.PLAYER_STATE_LEVEL_COMPLETE);
        }



        if(_flag == 0)
        {


            int objectstofind = 1;

            int lenght = gameobject.size();
            for(int i = 0; i < lenght; i++)
            {
              if(_objects_Found < objectstofind) {
                  if (gameobject.get(i).getObjectType() == Static.FLOATINGICEBLOCK) {
                      if (gameobject.get(i).getObjectState() == Static.GENERAL_OBJECT_STATE_NEUTRAL) {


                          gameobject.get(i).setOffScreenProjectile(0, GamePanel.HEIGHT / (100 / 50), 0, 150);
                          gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                          gameobject.get(i).setGetToPoint(GamePanel.WIDTH / (100 / 45), 0, GamePanel.HEIGHT, GamePanel.WIDTH / (100 / 10), Color.GREEN);
                          _objects_Found++;
                      }
                  }
              }
              else if(_objects_Found == objectstofind)
              {
                  _objects_Found = 0;
                  _flag++;
                  break;
              }
            }
        }

        if((_lvl_2_Points == 1)&&(_flag == 1))
        {

            int objectstofind = 3;

            int lenght = gameobject.size();
            for(int i = 0; i < lenght; i++)
            {
                if(_objects_Found < objectstofind) {
                    if (gameobject.get(i).getObjectType() == Static.FLOATINGICEBLOCK) {
                        if (gameobject.get(i).getObjectState() == Static.GENERAL_OBJECT_STATE_NEUTRAL) {

                            gameobject.get(i).setOffScreenProjectile(1, 0, 270, 80);
                            gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);

                            if (_objects_Found == 1) {
                                gameobject.get(i).setOffScreenProjectile(1, GamePanel.HEIGHT / (100 / 55), 270, 80);
                                gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                            }
                            if (_objects_Found == 2) {
                                gameobject.get(i).setOffScreenProjectile(0, -(GamePanel.HEIGHT / (100 / 40)), 0, 150);
                                gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                                gameobject.get(i).setGetToPoint(0, -(GamePanel.HEIGHT / (100 / 40)), GamePanel.HEIGHT/ (100 / 50), GamePanel.WIDTH, Color.RED);
                            }
                            _objects_Found++;
                        }
                    }
                }
                else if(_objects_Found == objectstofind)
                {
                    _objects_Found = 0;
                    _flag++;
                    break;
                }
            }

        }
        if((_lvl_2_Points == 4)&&(_flag ==2)) {

            int objectstofind = 1;


            int lenght = gameobject.size();
            for (int i = 0; i < lenght; i++) {
                if (_objects_Found < objectstofind) {
                    if (gameobject.get(i).getObjectType() == Static.FLOATINGICEBLOCK) {
                        if (gameobject.get(i).getObjectState() == Static.GENERAL_OBJECT_STATE_NEUTRAL) {

                            gameobject.get(i).setOffScreenProjectile(2, GamePanel.HEIGHT, 170, 40);
                            gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                            gameobject.get(i).setGetToPoint(GamePanel.WIDTH / (100 / 20), (GamePanel.HEIGHT / (100 / 30)), GamePanel.HEIGHT/ (100 / 20), GamePanel.WIDTH /(100 / 20), Color.YELLOW);
                            _objects_Found++;
                        }
                    }
                }
                else if (_objects_Found == objectstofind) {
                    _objects_Found = 0;
                    _flag++;
                    break;
                }
            }
        }
        //Ain't no smooth sailing from here, make sure their parting gift from level one is special.
        if((_lvl_2_Points == 5)&&(_flag == 3)) {
            int objectstofind = 3;


            int lenght = gameobject.size();
            for (int i = 0; i < lenght; i++) {
                if (_objects_Found < objectstofind) {
                    if (gameobject.get(i).getObjectType() == Static.FLOATINGICEBLOCK) {
                        if (gameobject.get(i).getObjectState() == Static.GENERAL_OBJECT_STATE_NEUTRAL) {

                            if (_objects_Found == 0) {

                                gameobject.get(i).setOffScreenProjectile(0, GamePanel.HEIGHT / (100 / 30) + GamePanel.HEIGHT / (100 / 20), 0, 400);
                                gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                                gameobject.get(i).setGetToPoint(0, (GamePanel.HEIGHT / (100 / 30))+ GamePanel.HEIGHT / (100 / 20), GamePanel.HEIGHT / (100 / 50), GamePanel.WIDTH, Color.RED);
                            }
                            if (_objects_Found == 1) {

                                gameobject.get(i).setOffScreenProjectile(3, GamePanel.WIDTH / (100 / 20) + GamePanel.WIDTH /(100 / 20), 90, 400);
                                gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                                gameobject.get(i).setGetToPoint(GamePanel.WIDTH / (100 / 40), (GamePanel.HEIGHT / (100 / 50)), GamePanel.HEIGHT , GamePanel.WIDTH / (100 / 20), Color.RED);
                            }
                            if (_objects_Found == 2) {

                                gameobject.get(i).setOffScreenProjectile(1, GamePanel.WIDTH / (100 / 20) + GamePanel.WIDTH /(100 / 15), 270, 500);
                                gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                            }

                            _objects_Found++;
                        }
                    }
                } else if (_objects_Found == objectstofind) {
                    _objects_Found = 0;
                    _flag++;
                    break;
                }

            }

        }
        if((_lvl_2_Points == 8)&&(_flag == 4))
        {

            int objectstofind = 2;

            int lenght = gameobject.size();
            for(int i = 0; i < lenght; i++)
            {
                if(_objects_Found < objectstofind) {
                    if (gameobject.get(i).getObjectType() == Static.FLOATINGICEBLOCK) {
                        if (gameobject.get(i).getObjectState() == Static.GENERAL_OBJECT_STATE_NEUTRAL) {


                            if (_objects_Found == 0) {
                                gameobject.get(i).setOffScreenProjectile(2, 0, 180, 80);
                                gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                            }
                            if (_objects_Found == 1) {
                                gameobject.get(i).setOffScreenProjectile(2, -(GamePanel.HEIGHT / (100 / 40)), 180, 150);
                                gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                            }
                            _objects_Found++;
                        }
                    }
                }
                else if(_objects_Found == objectstofind)
                {
                    _objects_Found = 0;
                    _flag++;
                    break;
                }
            }

        }
        if((_lvl_2_Points == 10)&&(_flag == 5))
        {
            int objectstofind = 5;

            int lenght = gameobject.size();
            for(int i = 0; i < lenght; i++)
            {
                if(_objects_Found < objectstofind) {
                    if (gameobject.get(i).getObjectType() == Static.FLOATINGICEBLOCK) {
                        if (gameobject.get(i).getObjectState() == Static.GENERAL_OBJECT_STATE_NEUTRAL) {


                            if (_objects_Found == 0) {
                                gameobject.get(i).setOffScreenProjectile(2, 0, 195, 80);
                                gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                            }
                            if (_objects_Found == 1) {
                                gameobject.get(i).setOffScreenProjectile(2, -(GamePanel.HEIGHT / (100 / 40)), 160, 400);
                                gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                            }
                            if (_objects_Found == 2) {
                                gameobject.get(i).setOffScreenProjectile(3, -(GamePanel.WIDTH/(100/1)) , 150, 200);
                                gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                            }
                            if (_objects_Found == 3) {
                                gameobject.get(i).setOffScreenProjectile(0, 0, 0, 150);
                                gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                            }
                            if (_objects_Found == 4) {
                                gameobject.get(i).setOffScreenProjectile(1, -GamePanel.WIDTH/(100/20), 270, 90);
                                gameobject.get(i).setObjectState(Static.GENERAL_OBJECT_STATE_ACTIVE);
                            }
                            _objects_Found++;
                        }
                    }
                }
                else if(_objects_Found == objectstofind)
                {
                    _objects_Found = 0;
                    _flag++;
                    break;
                }
            }

        }

        //THE PLAYER WON! KICK THEM THE FUCK OUT!
        if(_lvl_2_Points >= 15)
        {
            _win_Level = true;
        }

        //Check for scored points
        int lenght = gameobject.size();
        for (int i = 0; i < lenght; i++) {
            if(gameobject.get(i).getObjectType() == Static.FLOATINGICEBLOCK) {
                if (gameobject.get(i).getSpecialBooleanVar() == true) {
                    _lvl_2_Points++;

                    gameobject.get(i).setSpecialBooleanVar(false);
                    _fx.play(_sound_ID, 1, 1, 0, 0, 1);

                }
            }
        }

        if(_save_Progres == true) {
            if(gameobject.get(_player_Found_Value).getObjectStatus() == Static.OBJECT_STATUS_INACTIVE)
            {
                //The player now has permission to go to level 2 but they won't make it much further.
                if (_readFile.returnProgress() < 3) {
                    _readFile.saveLevelProgress(3);
                    endMusic();
                    gameobject.get(_player_Found_Value).returnToMenu();
                }
                endMusic();
                gameobject.get(_player_Found_Value).returnToMenu();
            }
        }
    }



    public void loadLevelOneMusic() {
        _music = MediaPlayer.create(_context, R.raw.level_1);
        _sound_ID = _fx.load(_context, R.raw.fireballpointchime1, 1);
    }
    public void loadLevelTwoMusic() {
        _music = MediaPlayer.create(_context, R.raw.level_2);
        _sound_ID = _fx.load(_context, R.raw.fireballpointchime1, 1);

    }
    public void loadLevelThreeMusic(){
        _music = MediaPlayer.create(_context, R.raw.level_3);
        _sound_ID = _fx.load(_context, R.raw.fireballpointchime1, 1);

    }

    void playMusic()
    {
        _music.start();
    }

    void endMusic() {
        _music.release();
        _fx.release();
    }











}
