package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class SuperZombie extends Zombie {
    //extends zombie
    public SuperZombie(float xpos, float ypos, int life){
        super(xpos, ypos, life); //super data
        speed = 3; //set speed to 3
        slowSpeed = 1.5f; //set slow speed to 1.5
        moneyOnDeath = 10; //set money on death to 10
    }
    public void initAnimations (){ //for animations
        numRow = 1; //1 row
        numCol =4; // 4 col

        //temporary 2d array to map a sprite sheet onto
        TextureRegion[][] temp = TextureRegion.split(zombie, zombie.getWidth()/numCol, zombie.getHeight()/numRow);
        walkFrame = new TextureRegion[numRow *numCol];

        int frameIndex = 0; //frame index to 0
        for(int i=0; i < numRow; i++){ //repeats  nuRow times
            for(int j=0; j<numCol; j++){ //repeats numCol times
                walkFrame[frameIndex++] = temp[i][j]; //rotates frames


            }
        }

        walkAnim = new Animation(0.4f, walkFrame); //makes the animation wiht 0.4 seconds

    }
    public void initTexture(){
        zombie = Resources.superZombieTexture; //initializes texture for base zombie
    }
}
