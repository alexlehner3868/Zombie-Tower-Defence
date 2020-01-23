

package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

import java.util.Random;


public class Bullet {
    //declare varaibles
    float xpos;
    float ypos;
    float angle;
    float width, height;
    Sprite spBullet;
    int range; //how far the bullet goes
    float speed; //how fast the bullet moves
    public boolean active;

    //constructor
    public Bullet(float xpos, float ypos){
        initTexture(); //call the method to set the texture
        this.xpos = xpos; //set the x
        this.ypos= ypos; //set the  y
        speed = 5; //set speed to 5
        range = 100; //set range to 10
        //set width and height to the size of the texture
        width = Resources.bulletTexture.getWidth();
        height = Resources.bulletTexture.getHeight();

        active = true; //set active to true because it is moving
        getAngle(); //call getAngle
    }
    public void draw(SpriteBatch batch){
        spBullet.setPosition(xpos - (width/2), ypos - (height/2)); // set the position
        spBullet.draw(batch); //draw the bullet

    }
    public void getAngle(){
        Random rand = new Random(); //make a random variable
        if(!ZombieTD.zombieList.isEmpty()){ //if there are zombies in the zombielist
            float xC, yC, xZ, yZ, angle; //delcare varaibles
            xC = xpos; //set the x position of cannon
            yC = ypos; //set the y position of cannon
            xZ = ZombieTD.zombieList.get(0).xpos; //set the x position of zombie
            yZ = ZombieTD.zombieList.get(0).ypos;//set the y position of zombie
            angle = (float) Math.atan((yC-yZ)/(xC-xZ)); //get the angle between the zombie and cannon
            if(xC >= xZ){ //if the zombie is to the left of the cannon, flip it
                angle +=Math.PI;
            }
            this.angle = angle; //aims the bullet at the zombie
        }
    }
    public Circle getCircle(){
        return new Circle(xpos, ypos, width/2); // gives the circle for bullet's hit box
    }
    public void update(){
        xpos += (float)Math.cos(angle) * speed;//update bullet x position
        ypos += (float)Math.sin(angle) * speed;//update bullet y position
        if(range--<0){ //this checks and also subtracts one from range in the same line
            active = false; //set active to false if out of range
        }
    }
    //set texture to bullet texture
    public void initTexture(){
        spBullet = new Sprite(Resources.bulletTexture);
    }

}
