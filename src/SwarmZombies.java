package com.mygdx.game;

import java.util.Random;


public class SwarmZombies extends Zombie{
    //extends zombies
    Random rnd = new Random(); //initialize and delcare random variable

    public SwarmZombies( float xpos, float ypos, int life){
        super( xpos, ypos, life); //super data
        this.ypos += rnd.nextInt(30)-15; //set ypos to a random num between -15 and 15
        speed = 1; //set speed to 1
        slowSpeed = 0.5f; //set slowSpeed to 0.5
        moneyOnDeath = 1; //set money to 1

    }
    public void initTexture() {
        zombie = Resources.swarmZombieTexture; //swarmZombie texture
    }

}
