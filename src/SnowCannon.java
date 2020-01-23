package com.mygdx.game;



import com.badlogic.gdx.graphics.g2d.Sprite;

public class SnowCannon extends Cannon{
    //extends cannnon
    public SnowCannon (float x, float y){
        super(x,y); //supers data
        fireDelay = 70; //set fireDelay to 70
    }
    public void initTexture(){
        spCannon = new Sprite(Resources.snowCannonTexture); //set texute
    }
    public void fire(){
        if(!ZombieTD.zombieList.isEmpty()){ //if zombie list isnt empty
            ZombieTD.bulletList.add(new SnowBullet(xpos+width/2, ypos+height/2));
        }//add bullet if there are zombies
    }
}
