package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class LaserCannon extends Cannon {
    //extends cannon
    public LaserCannon(float x, float y) {
        super(x, y); //supers data from parent class
        fireDelay = 50; //set fireDelay to 50
    }

    public void fire() { //add bullets to list if there are zombies in the zombieList
        if (!ZombieTD.zombieList.isEmpty()) {
            if (ZombieTD.zombieList.get(0).xpos <= 1024) //if zombies are in frame
                ZombieTD.zombieList.get(0).takeDamage(1); // tell the zombie it is aiming for that the damage is 1

        }

    }

    public void draw(SpriteBatch batch) {

        if (ZombieTD.zombieList.isEmpty() == false) { //if there are zombies
            if (ZombieTD.zombieList.get(0).xpos <= 1024) //if zombie on screen
            //make a hit box for the zombie
                ZombieTD.SR.rectLine(xpos+width/2, ypos+height/2, ZombieTD.zombieList.get(0).xpos, ZombieTD.zombieList.get(0).ypos,5);

        }
        spCannon.draw(batch); //draw the sprite to batch
    }

    public void initTexture() {
        spCannon = new Sprite(Resources.laserCannonTexture); //set texture to laserCannon
    }

    public void update() {
        getAngle(); //call getAngle
        spCannon.setRotation(angle); //rotates to angle to face the zombie it targets
        if (counter++ >= fireDelay) {//increases counter and makes sure the cannon has a delay when firing
            fire(); // calls fire
            counter = 0; //resets counter
        }

    }
}
