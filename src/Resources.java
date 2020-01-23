package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

//initializes and sets the textures for all objects

public class Resources {
    public static Texture bgTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/background.png"));
    public static Texture superZombieTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/superzombies.png"));
    public static Texture strongZombieTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/strongZombies.png"));
    public static Texture laserCannonTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/lasercannon.png"));
    public static Texture cannonTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/Cannon.png"));
    public static Texture firecannonTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/Firecannon.png"));
    public static Texture zombieTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/Zombies.png"));
    public static Texture fastZombieTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/Fastzombies.png"));
    public static Texture bulletTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/Bullet.png"));
    public static Texture explosionTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/Explosion.png"));
    public static Sound explosionEffects = Gdx.audio.newSound(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/explosion.mp3")); //gets sound
    public static Texture fireBullet = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/fireball.png"));
    public static Texture snowCannonTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/snowcannon.png"));
    public static Texture snowballTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/iceball.png"));
    public static Texture startBackground = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/startscreen.png"));
    public static Texture regenerationZombieTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/regeneratingzombie.png"));
    public static Texture splittingZombieTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/splittingzombies.png"));
    public static Texture rageZombieTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/ragezombies.png"));
    public static Texture swarmZombieTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/swarmzombies.png"));
    public static Texture bankTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/bank.png"));
    public static Texture omniShooterTexture = new Texture(Gdx.files.internal("/Users/alexlehner/Desktop/TD/android/assets/omnishooter.png"));
}
