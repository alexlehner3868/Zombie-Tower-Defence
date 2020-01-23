package com.mygdx.game;



public class FastZombie extends Zombie {
    //extends zombie class
    public FastZombie (float xpos, float ypos, int life){
        super (xpos,ypos, life); //supers data from parent class
        speed = 2; //set speed to 2 (ie makes the zombie move faster)
        moneyOnDeath =  5; //set the moneyOnDeath to 5
        slowSpeed =1; //set the slowSpeed to 1 for when its hit by snowflake
    }
    public void initTexture(){
        zombie = Resources.fastZombieTexture; //set resource to fastZombie texture
    }

}
