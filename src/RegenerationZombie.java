package com.mygdx.game;



public class RegenerationZombie extends Zombie {
   // extends zombie
    int regenerationCounter; //declare regenerationCounter

    public RegenerationZombie(float xpos, float ypos, int life){
        super( xpos, ypos, life); //supers data
        speed = 1; //sets speed to 1
        slowSpeed = 0.5f; //sets slow Speed to 0.5
        moneyOnDeath = 10; //sets money on death to 10
        regenerationCounter =0; //sets regenerationCounter to 0
    }
    public void initTexture(){
        zombie = Resources.regenerationZombieTexture; //initializes texture for base zombie
    }
    public void update(){
        move(); //call move
        if(xpos <-20){ //if x is less than -20 (ie off the scree)
            active = false; //set active to false
            ZombieTD.playerLives -= 1; //subtracts a life from the player because the zombie crossed the screen
        }
        if(regenerationCounter ++> 60 && life < originalLife){  //if regeneeration counter is incremented and less than 60 and life is less than original life
            life ++; //life increase
            regenerationCounter = 0; //set to 0
        }
    }

}
