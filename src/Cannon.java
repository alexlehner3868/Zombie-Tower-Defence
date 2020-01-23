package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Cannon {
    //declare variables
    float xpos;
    float ypos;
    float angle;
    float width, height;
    Sprite spCannon; //creates a sprite called spCannon
    int fireDelay; //an int that determines when the delay between cannon firing
    int counter;

    public Cannon(float x, float y){
        initTexture(); //call initTexture
        fireDelay = 30; //set fireDelay to 30
        counter = 0; //set counter to 0
        width = Resources.cannonTexture.getWidth(); //get the width of the resource
        height = Resources.cannonTexture.getHeight(); //get the height of the resource
        this.xpos = lockToGrid(x - width/2); //makes the x rounded to nearest 50
        this.ypos = lockToGrid(y - height/2);//makes y to the nearest 50
        spCannon.setPosition(lockToGrid(xpos), lockToGrid(ypos)); //set the positionf of the cannon to the result of locktogrid
    }
    //draws the cannon
    public void draw(SpriteBatch batch){
        spCannon.draw(batch); //draws the sprite cannon to batch

    }
    //fire class
    public void fire(){
        if(!ZombieTD.zombieList.isEmpty()){ //if there are zombies in the zombie list
            if (ZombieTD.zombieList.get(0).xpos <= 1024) //if the zombie's xpos is less than 1024
                ZombieTD.bulletList.add(new Bullet(xpos+width/2, ypos+height/2)); //add a new bullet
        }
    }


    public void getAngle(){
        if(!ZombieTD.zombieList.isEmpty()){ //if there are zombies in the zombieList
            float xC, yC, xZ, yZ, angle; //creates floats
            xC = xpos; //set to cannon's xpos
            yC = ypos; //set to cannon's ypos
            xZ = ZombieTD.zombieList.get(0).xpos; //set to zombie's x
            yZ = ZombieTD.zombieList.get(0).ypos; //set to zombie's y
            angle = (float) Math.atan((yC-yZ)/(xC-xZ)); //set angle to the tan of the dif between cannon's and zombie's position
            if(xC >= xZ){ //if the cannon's x is greater than or equal ot the zombie's x (ie bullet zombie is to the left of bullet
                angle +=Math.PI; //add pi to the angle to reflected it over the y axis
            }
            this.angle = (float)Math.toDegrees(angle); //converts radians to degrees

        }
    }

    //this method locks the cannon to a grid
    public float lockToGrid(float pos){
        return ((int)(pos + 25)/50) *50; //rounds the pos to the nearest fifty
    }
    //sets texture
    public void initTexture(){
        spCannon = new Sprite(Resources.cannonTexture); //sets it to cannon texture
    }


    public void update(){
        getAngle(); //call getAngle
        spCannon.setRotation(angle); //rotates to angle to face the zombie it targets
        if(counter++>=fireDelay) {//increases counter and makes sure the cannon has a delay when firing
            fire(); //call fire
            counter =0; //reset counter to 0
        }

    }
}
