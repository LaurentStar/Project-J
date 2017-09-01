package com.vinnstar.myfirstgame;

/**
 * Created by Laurent on 1/5/2017.
 */
public class Timer {

    long startTicks = 0;
    long pausedTicks = 0;
    boolean paused = false;
    boolean started = false;


    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    void start() {
        //Start the timer
        started = true;

        //Un-pause the timer
        paused = false;

        //Get the current clock time
        startTicks =  System.nanoTime();
    }

    void stop() {
	    //Stop the timer
	    started = false;

	    //Un-pause the timer
	    paused = false;
    }

    void pause() {
	    //If the timer is running and isn't already paused
	    if( ( started  ) && ( !paused  ) )
	    {
		    //Pause the timer
		    paused = true;

		    //Calculate the paused ticks
		    pausedTicks =  System.nanoTime() - startTicks;
	    }
    }

    void unPause() {
	    //If the timer is paused
	    if( paused )
	    {
		//Un-pause the timer
		paused = false;

		//Reset the starting ticks
		startTicks = System.nanoTime() - pausedTicks;

		//Reset the paused ticks
		pausedTicks = 0;
	    }
    }

    long getTicks() {
         //Button_Timer_Value = (SDL_GetTicks() - startTicks);

        //If the timer is running
        if( started )
        {
            //If the timer is paused
             if( paused )
            {
            //Return the number of ticks when the timer was paused
            return pausedTicks;
            }
            else
            {
            //Return the current time minus the start time
            return (System.nanoTime() - startTicks)/1000000;
            }
        }

        //If the timer isn't running
        return 0;
    }

    boolean isStarted() {
        return started;
    }

    boolean isPaused()
    {
	    return paused;
    }


}
