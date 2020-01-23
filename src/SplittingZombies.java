package com.mygdx.game;



public class SplittingZombies extends Zombie {
    //extends zombie
    int splitLevel; //init split level
    //constructor
    public SplittingZombies(float xpos, float ypos, int life){
        super(xpos, ypos, life); //super data
        speed = 1; //set speed to 1
        slowSpeed = 0.5f; //set slowSpeed to 0.5
        moneyOnDeath = 3; //set moneyOnDeath to 3
        splitLevel = 2; //set SplitLevel to 2
    }

    //constructor 2
    public SplittingZombies(float xpos, float ypos, int life, int splitLevel){
        super(xpos, ypos, life); //supers data
        speed = 1; //set speed to 1
        slowSpeed = 0.5f; //set slow speed to 0.5
        moneyOnDeath = 0; //set money on death to 0
        this.splitLevel = splitLevel; //set split level to splitlevel
    }

    public void takeDamage(float damage){
        life = life -damage; //subtract damage from lfie
        if(life<= 0){ //subtract life and check to see if dead
            active = false; //set active to false
            for(int i = 0; i < 2; i++){ //makes a cluster of explosions
                for(int j =0; j < 2; j++){ //makes a cluster of explosions
                    ZombieTD.explosionList.add(new DeathExplosion(xpos+4*i, ypos+4*j)); //makes a cluster of explosions
                    ZombieTD.explosionList.add(new DeathExplosion(xpos+-4*i, ypos-4*j));  //makes a cluster of explosions
                }
            }
            Resources.explosionEffects.play(); //play sound when zombie dies and explosion occurs
            if (splitLevel > 0){ //if splitlevel is greater than 0
                //create 2 zombies
                ZombieTD.zombieList.add(new SplittingZombies(xpos+12, ypos+12, (int)life-2, splitLevel -1));
                ZombieTD.zombieList.add(new SplittingZombies(xpos-12, ypos-12, (int)life-2, splitLevel -1));
            }
            UI.money += moneyOnDeath; //increase user money by moneyOnDeath
        }
    }

    public void initTexture(){
        zombie = Resources.splittingZombieTexture; //initializes texture for base zombie
    }
}
