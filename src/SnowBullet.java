package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;



public class SnowBullet extends Bullet {
    //extends bullet
    public SnowBullet(float xpos, float ypos) {
        super(xpos, ypos); //supers data from bullet

    }

    public void initTexture() {
        spBullet = new Sprite(Resources.snowballTexture); //sets texture

    }

    public void getAngle() {
        Random rand = new Random(); //creates and initialize a new random
        if (!ZombieTD.zombieList.isEmpty()) { //if zombie list isnot empty
            float xC, yC, xZ, yZ, angle; //declare some floats
            xC = xpos; //set cannon x
            yC = ypos; //set cannon y
            if (ZombieTD.zombieList.size() > 1) { //if there are more than 1 zombies
                int randomZombie = rand.nextInt(ZombieTD.zombieList.size() - 1); //make a random varaible between 1 and list size -1
                xZ = ZombieTD.zombieList.get(randomZombie).xpos; // set zombie x
                yZ = ZombieTD.zombieList.get(randomZombie).ypos; //set zombie y
            } else { //if less than 1 zombie
                xZ = ZombieTD.zombieList.get(0).xpos; //set x to first zombie x
                yZ = ZombieTD.zombieList.get(0).ypos; //set y to first zombie y

            }
                angle = (float) Math.atan((yC - yZ) / (xC - xZ)); //find the angle betwwen the zombie and cannon
                if (xC >= xZ) { //if the zombie is to the left of the cannon, flip it
                    angle += Math.PI;
                }
                this.angle = angle; //aims the bullet at the zombie
            }
        }
    }
