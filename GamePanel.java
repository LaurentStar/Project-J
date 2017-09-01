package com.vinnstar.myfirstgame;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;



/**
 * Created by Laurent on 6/30/2016.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {


    private final Context context;

    // Unchanging variables of the GamePanel class
    private static final String TAG = "GamePanel";
    public static final int WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static final int HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static final int MOVESPEED = -5;

    //Tier One Classes
    private MainThread thread;
    private ActivitySwitch activitySwitch;
    private GlobalFunctions globalFunctions = new GlobalFunctions();
    private ArrayList<GameObject> gameObject;
    private ReadFile readFile;

    //Tier Two Classes
    private Physics physics = new Physics(globalFunctions);
    private Timer timer = new Timer();
    private LevelBrain levelBrain;
    private AiControls aiControls;

    //Tier three
    //private Scenery bg;
    //private ObjectPlayer objectPlayer;
   // private ObjectFireBall objectFireBall;

    //private CystalPoint cystalPoint;
    //private ArrayList<Smokepuff> smoke ; //smoke


        public GamePanel(Context context){

        super(context);

        // add callbacks surfaceholder to intercept events
        getHolder().addCallback(this);


        thread = new MainThread(getHolder(),    this,
                                                gameObject,
                                                physics,
                                                timer,
                                                globalFunctions,
                                                levelBrain,
                                                aiControls);

        this.context = context;
        activitySwitch = new ActivitySwitch(context);

        readFile = new ReadFile(context, globalFunctions);
        levelBrain = new LevelBrain(globalFunctions, context, readFile);
        aiControls = new AiControls(levelBrain);
        //Make gamepanel focusable to perform task
        setFocusable(true);
    }






        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            //Safely end the program

            boolean retry = true;
            int counter = 0;
            while (retry == true && counter < 1000) {


                counter++;
                try {
                    thread.setRunning(false);
                    thread.join();
                    retry = false;
                    thread = null;
                }  catch (InterruptedException e) { e.printStackTrace();}

            }
            levelBrain.endMusic();
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

       // gameObject = new ArrayList<GameObject>();
            // gameObject.add(new ObjectButton(BitmapFactory.decodeResource(getResources(), R.drawable.redcyrstal), globalFunctions, 0));
            gameObject = new ArrayList<GameObject>();


            //Scenery and ObjectFireBall renders but the player does not.
           // gameObject.add(new Scenery(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1)));
            //gameObject.add(new Scenery(BitmapFactory.decodeResource(getResources(), R.drawable.button1)));
           // loadBackgrounds( gameObject);
            //gameObject.add(new ObjectPlayer(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter) ,globalFunctions, activitySwitch ));
           // gameObject.add(new ObjectFireBall(BitmapFactory.decodeResource(getResources(), R.drawable.redcyrstal)));
            loadLevels( globalFunctions.getLevelNum());




            thread = new MainThread(getHolder(), this, gameObject,  physics, timer, globalFunctions,levelBrain, aiControls);
            //Safely start game loop
            thread.setRunning(true);
            thread.start();
            /*
            bg = new BackGround(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));
            player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 100, 86, 3);

            //NO USAGE
            //smoke = new ArrayList<Smokepuff>();
            //smokeStartTime = System.nanoTime();


            //cystalPoint = new CystalPoint(BitmapFactory.decodeResource(getResources(),R.drawable.redcyrstal), 91, 86, 1);
            fireBall = new ArrayList<FireBall>();
            fireBall.add(new FireBall(BitmapFactory.decodeResource(getResources(), R.drawable.redcyrstal), 91, 86));


            thread = new MainThread(getHolder(), this);
            //Safely start game loop
            thread.setRunning(true);
            thread.start();
            */


        }

        @Override
        public boolean onTouchEvent(MotionEvent event){

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                globalFunctions.setScreenTouch(event.getAction());
                Log.d(TAG, "Laurent Key is down");
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                globalFunctions.setScreenTouch(event.getAction());
                Log.d(TAG, "Laurent Key is up");
                return false;
            }






            /*if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                if(objectPlayer.getPlaying() == false)
                {
                    objectPlayer.setPlaying(true);
                    for (ObjectFireBall sp: objectFireBall) {
                        sp.setPlaying(true);
                        //cystalPoint.setPlaying(true);
                    }
                }

                else
                {
                    objectPlayer.setUp(true);
                    for (ObjectFireBall sp: objectFireBall) {
                        sp.setUp(true);
                       // cystalPoint.setUp(true);
                    }
                }
                return true;
            }

            if(event.getAction() == MotionEvent.ACTION_UP)
            {
                objectPlayer.setUp(false);
                for (ObjectFireBall sp: objectFireBall) {
                    sp.setUp(false);
                    //cystalPoint.setUp(false);
                }
                return true;
            }
*/
            return true;//super.onTouchEvent(event);
        }


        public void loadLevels(int check)
        {
            if(check == 1)
            {
                loadLevelOne(gameObject);
            }
            if(check == 2)
            {
                loadLevelTwo(gameObject);
            }
            if(check == 3)
            {
                loadLevelThree(gameObject);
            }
        }

        public void loadLevelOne(ArrayList<GameObject> gameObject)
        {
            loadBackgroundsLV1( gameObject);
            gameObject.add(new ObjectPlayer(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter) ,globalFunctions, activitySwitch ));
            gameObject.add(new ObjectFireBall(BitmapFactory.decodeResource(getResources(), R.drawable.redcyrstal)));
            levelBrain.loadLevelOneMusic();
        }

        public void loadLevelTwo(ArrayList<GameObject> gameObject)
        {
            loadBackgroundsLV1( gameObject);
            gameObject.add(new ObjectPlayer(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter) ,globalFunctions, activitySwitch ));
            gameObject.add(new ObjectFloatingIceBlock(BitmapFactory.decodeResource(getResources(), R.drawable.iceblock)));
            gameObject.add(new ObjectFloatingIceBlock(BitmapFactory.decodeResource(getResources(), R.drawable.iceblock)));
            gameObject.add(new ObjectFloatingIceBlock(BitmapFactory.decodeResource(getResources(), R.drawable.iceblock)));
            gameObject.add(new ObjectFloatingIceBlock(BitmapFactory.decodeResource(getResources(), R.drawable.iceblock)));
            /*gameObject.add(new ObjectFloatingIceBlock(BitmapFactory.decodeResource(getResources(), R.drawable.iceblock)));
            gameObject.add(new ObjectFloatingIceBlock(BitmapFactory.decodeResource(getResources(), R.drawable.iceblock)));*/
            levelBrain.loadLevelTwoMusic();
        }

        public void loadLevelThree(ArrayList<GameObject> gameObject)
        {
        loadBackgroundsLV1( gameObject);
        //gameObject.add(new ObjectPlayer(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter) ,globalFunctions, activitySwitch ));
        gameObject.add(new ObjectBoss1(BitmapFactory.decodeResource(getResources(), R.drawable.boss1) ,globalFunctions, activitySwitch ));
        gameObject.add(new ObjectBlueFireBall(BitmapFactory.decodeResource(getResources(), R.drawable.bluebeam)));
        gameObject.add(new ObjectIceEnergySphere(BitmapFactory.decodeResource(getResources(), R.drawable.icespheres)));
        gameObject.add(new ObjectMetalBallisticBox(BitmapFactory.decodeResource(getResources(), R.drawable.blasticmetal)));
            gameObject.add(new ObjectPlayer(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter) ,globalFunctions, activitySwitch ));
        levelBrain.loadLevelThreeMusic();
    }

        public void loadBackgroundsLV1(ArrayList<GameObject> gameObject)
        {
            int pimaheHeight;
            int imageHeight;
            int imageWidth;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;


            BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebgfull, options);
            imageHeight = options.outHeight;
            imageWidth = options.outWidth;
            gameObject.add(new Scenery(BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebgfull),0,imageWidth,imageHeight, -1));
            pimaheHeight = imageHeight;

            BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebg0, options);
            imageHeight = options.outHeight;
            imageWidth = options.outWidth;
            gameObject.add(new Scenery(BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebg0),1,imageWidth,imageHeight, pimaheHeight));
            pimaheHeight = imageHeight;


            BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebg1, options);
            imageHeight = options.outHeight;
            imageWidth = options.outWidth;
            gameObject.add(new Scenery(BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebg1),2,imageWidth,imageHeight, pimaheHeight));
            pimaheHeight = imageHeight;

            BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebg2, options);
            imageHeight = options.outHeight;
            imageWidth = options.outWidth;
            gameObject.add(new Scenery(BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebg2),3,imageWidth,imageHeight, pimaheHeight));
            pimaheHeight = imageHeight;

            BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebg3, options);
            imageHeight = options.outHeight;
            imageWidth = options.outWidth;
            gameObject.add(new Scenery(BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebg3),4,imageWidth,imageHeight, pimaheHeight));
            pimaheHeight = imageHeight;

            BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebg4, options);
            imageHeight = options.outHeight;
            imageWidth = options.outWidth;
            gameObject.add(new Scenery(BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebg4),5,imageWidth,imageHeight,pimaheHeight));
            pimaheHeight = imageHeight;

            BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebg5, options);
            imageHeight = options.outHeight;
            imageWidth = options.outWidth;
            gameObject.add(new Scenery(BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebg5),6,imageWidth,imageHeight, pimaheHeight));
            pimaheHeight = imageHeight;

            BitmapFactory.decodeResource(getResources(), R.drawable.iceparadisebg5);
        }

        @Override
        public void draw(Canvas canvas){

            final float scaleFactorX =(float)getWidth()/WIDTH;
            final float scaleFactorY = (float)getHeight()/HEIGHT;

            if(canvas != null) {
                final int savedState = canvas.save();





                canvas.scale(scaleFactorX, scaleFactorY);
                /*bg.draw(canvas);
                objectPlayer.draw(canvas);

                for (ObjectFireBall sp: objectFireBall) {
                    sp.draw(canvas);
                    //  cystalPoint.draw(canvas);
                }*/
                for (GameObject sp: gameObject) {
                   sp.draw(canvas);
                }

                canvas.restoreToCount(savedState);



            }
        }

}
