package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;


public class FireBullet extends Bullet {
    //fire bullet extends bullet
    public FireBullet(float xpos, float ypos) {
        super(xpos, ypos); //supers data from parent class

    }

    public void initTexture() {
        spBullet = new Sprite(Resources.fireBullet); //set sprite to fire bullet resource

    }
}