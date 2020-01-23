package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;



public class FireCannon extends Cannon{
    //extends cannon class
    public FireCannon (float x, float y){
       super(x,y); //super data from canon
        fireDelay = 30; //set fireDelay to 30
    }
    public void initTexture(){
        spCannon = new Sprite(Resources.firecannonTexture); //set rexture to fireCannon texture

    }
    public void fire(){ //add bullets if the zombieList isnt empty
        if(!ZombieTD.zombieList.isEmpty()){
            ZombieTD.bulletList.add(new FireBullet(xpos+width/2, ypos+height/2)); //add a bullet
        }
    }
}
