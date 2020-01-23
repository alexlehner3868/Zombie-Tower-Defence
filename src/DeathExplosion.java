package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

//explosion for death of zombies which extends exploision
public class DeathExplosion extends Explosion{
     public DeathExplosion (float x, float y){
        super(x,y); //calls the constructor of Explosion
         life =  30; //sets life to 30


    }
}
