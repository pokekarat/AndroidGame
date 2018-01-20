package com.example.pok.game1;

import android.content.Context;
import android.view.SurfaceView;

/**
 * Created by pok on 20/1/2561.
 */

public class GameView extends SurfaceView implements Runnable {

    //http://tutorials.jenkov.com/java-concurrency/volatile.html
    //https://docs.oracle.com/javase/specs/jls/se8/html/jls-17.html
    //volatile has semantics for memory visibility.
    //Basically, the value of a volatile field becomes visible to all readers
    //(other threads in particular) after a write operation completes on it.
    //Without volatile, readers could see some non-updated value.
    volatile boolean playing;

    private Thread gameThread = null;
    
    public GameView(Context context){
        super(context);
    }

    @Override
    public void run() {
        while(playing){
            
            update();

            draw();

            control();
        }
    }

    private void update() {
    }

    private void draw() {
    }

    private void control() {
        try{
            gameThread.sleep(17);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void pause(){
        playing = false;
        try{
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
