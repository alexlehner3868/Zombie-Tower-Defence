package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class StrongZombie extends Zombie{
    //extends zombie
    int moveCounter =0; //set move counter

    public StrongZombie( float xpos, float ypos, int life){
        super(xpos, ypos, life); //super data
        double speed = 1; //speed to 1
        slowSpeed = 1; //set slowspeed to 1
        moneyOnDeath = 8; //set money on death to 8

    }
    public void initAnimations (){ //handles animations
        //for the size of the image
        numRow = 1; //1 row
        numCol =4; //4 col

        //temporary 2d array to map a sprite sheet onto
        TextureRegion[][] temp = TextureRegion.split(zombie, zombie.getWidth()/numCol, zombie.getHeight()/numRow);
        walkFrame = new TextureRegion[numRow *numCol];

        int frameIndex = 0; //frameIndex is 0
        for(int i=0; i < numRow; i++){ //repeat numRow times
            for(int j=0; j<numCol; j++){ //repear numCol times
                walkFrame[frameIndex++] = temp[i][j]; //rotates frames


            }
        }

        walkAnim = new Animation(0.4f, walkFrame); //makes the animation wiht 0.4 seconds

    }
    public void initTexture(){

        zombie = Resources.strongZombieTexture; //set texture
    }

    public void update () {
        moveCounter++; //increase move counter
        if (moveCounter >= 2) { //if moveCOunter is greater than 2
            moveCounter =0; //moveCounter to 0
            move(); //call move
            if (xpos < -20) { //if xpos is less than -20
                active = false; //set active to false
                ZombieTD.playerLives -= 1; //subtracts a life from the player because the zombie crossed the screen
            }
        }
    }

}
