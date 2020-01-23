package com.mygdx.game;

import java.util.Random;


public class OmniBullet extends Bullet {
    //omniBullet extends bullet class
    public OmniBullet (float xpos, float ypos){ //constructor 1
        super(xpos, ypos); //supers data from bullet class
    }
    public void getAngle(){
        //overrides getAngle from bullet as bullets shoot in all directions
    }
   public OmniBullet (float x, float y, float angle){ //constructor 2
       super(x, y); //supers data
       this.angle = angle; //sets angle to angle

   }
}
