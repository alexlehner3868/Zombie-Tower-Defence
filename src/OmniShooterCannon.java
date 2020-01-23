package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;


public class OmniShooterCannon extends Cannon {
    //extends cannon class
    public OmniShooterCannon(float x, float y){
        super(x,y); //supers data from canon
    }
    public void initTexture(){
        spCannon = new Sprite(Resources.omniShooterTexture); //set sprite to omniShooterCannon
    }
    public void fire(){
        if(!ZombieTD.zombieList.isEmpty()){ //if zombie list isnt empty
            for(int i =0; i< 8; i++) { //repeat 8 times
                //add 8 bullets that have angle shooting out from around the cannon
                ZombieTD.bulletList.add(new OmniBullet(xpos + width / 2, ypos + height / 2, (float)(0.25f*Math.PI*(float)i)));
            }
        }
    }
    public void update(){
        if(counter++>=fireDelay) {//increases counter and makes sure the cannon has a delay when firing
            fire(); //fire
            counter =0; //set counter to 0
        }

    }

}
