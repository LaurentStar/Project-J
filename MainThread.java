package com.vinnstar.myfirstgame;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.ArrayList;

/**
 * Created by Laurent on 6/30/2016.
 */



public class MainThread extends Thread{


    private int gameMode = 0;
    private int FPS = 60;
    private int levelNum;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private Physics physics;
    private Timer timer;
    private GamePanel gamePanel;
    private ArrayList<GameObject> gameObject;
    private GlobalFunctions globalFunctions;
    private LevelBrain levelBrain;
    private AiControls aiControls;
    private boolean running;
    public static Canvas canvas;
    private static final String TAG = "GamePanel";

    public MainThread(SurfaceHolder surfaceHolder,
                      GamePanel gamePanel,
                      ArrayList<GameObject> gameObject ,
                      Physics physics,
                      Timer timer,
                      GlobalFunctions globalFunctions,
                      LevelBrain levelBrain,
                      AiControls aiControls){

        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        this.gameObject = gameObject;
        this.physics = physics;
        this.timer = timer;
        this.globalFunctions = globalFunctions;
        this.levelBrain = levelBrain;
        this.aiControls = aiControls;

    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / FPS;


        //LOAD AND SET FILES/OBJECTS HERE
        levelBrain.findPlayer(gameObject); // Find when the layer was allocated so we can quickly check collision later on
        levelBrain.playMusic();
        levelBrain.setObjectID(gameObject);
        levelNum = globalFunctions.levelNum;
        aiControls.findPlayer(gameObject);

        while (running == true) {
            startTime = System.nanoTime();
            canvas = null;


            //Try locking the canvas for pixel editing
            try {
                canvas = this.surfaceHolder.lockCanvas();

                synchronized (surfaceHolder) {

                    //////////////////////////////
                    //      GAMEPLAYMODE        //
                    //////////////////////////////

                    this.physics.gamePhysicsObjectUpdate(gameObject,timer.getTicks());
                    this.physics.gamePhysics(gameObject,timer.getTicks());



                    this.levelBrain.levelBrainSelector(gameObject, levelNum);
                    //Log.d(TAG,"Call after levelbrain");

                    this.aiControls.aiBrainBehavoir(gameObject,timer.getTicks());

                    this.timer.start();


                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) { }


            finally {
                if (canvas != null){
                    try {

                        surfaceHolder.unlockCanvasAndPost(canvas);

                    }catch (Exception e){e.printStackTrace();}
                }

            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                this.sleep(waitTime);
            } catch (Exception e) {}

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if (frameCount == 30) {
                averageFPS = 1000/ ((totalTime/frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;

                System.out.println(averageFPS);
            }
        }
    }

    public void setRunning(boolean b)
    {
        running = b;
    }


}
