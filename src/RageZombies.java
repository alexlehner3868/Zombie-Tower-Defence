package com.mygdx.game;


public class RageZombies extends Zombie {
    //extends zombie

    public RageZombies(float xpos, float ypos, int life) {
        super(xpos, ypos, life); //supers data from zombie
        speed = 1; //set speed to 1
        slowSpeed = 0.5f; //set slowSpeed to 0.5
        moneyOnDeath = 3; //set moneyOnDeath to 3
    }

    public void initTexture() {
        zombie = Resources.rageZombieTexture; //initializes texture for base zombie
    }

    public void takeDamage(float damage) {
        life = life - damage + 1;// only takes damage from cannons who do more than one damage
        if (life <= 0) { //subtract life and check to see if dead
            active = false; //set active to false
            for (int i = 0; i < 2; i++) { //makes a cluster of explosions
                for (int j = 0; j < 2; j++) { //makes a cluster of explosions
                    ZombieTD.explosionList.add(new DeathExplosion(xpos + 4 * i, ypos + 4 * j)); //makes a cluster of explosions
                    ZombieTD.explosionList.add(new DeathExplosion(xpos + -4 * i, ypos - 4 * j));  //makes a cluster of explosions
                }
            }
            Resources.explosionEffects.play(); //play sound when zombie dies and explosion occurs

            UI.money += moneyOnDeath; //increase user money by the moneyOnDeath value (ie 3)
        }
    }

    public void takeDamage() {
    //the normal take damage method is left empty becuase zombie only gets damage from stronger cannons who do more than 1 damage
    }

}
