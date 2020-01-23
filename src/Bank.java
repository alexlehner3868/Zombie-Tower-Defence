package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Bank extends Cannon {
    int moneyDelay; //a delay for when the money pop up
    public int moneyCounter; //a moneycounter int
    public boolean hasCoin; //a boolean if the bank has a coin available
    int coinRadius; //the radius of the coin

    public Rectangle rect; //a rectangle for the size of the bank

    //construcor
    public Bank(float x, float y){
        super(x, y); //super the x and y from cannon
        moneyDelay = 200; //set money delay to 200
        moneyCounter = 0; //set the counter to 0
        hasCoin = false; //set hasCoint to false
        coinRadius = 6; //set the radius to 6
        rect = new Rectangle(xpos+25, ypos+25, 50, 50); //make a rectangle the shape of bank

        }
        //set texture to the bank texture
    public void initTexture(){
        spCannon = new Sprite(Resources.bankTexture);
    }

    public void fire(){
     //fire is empty because banks do not fire any bullets
    }
    //draw the coin
    public void draw(SpriteBatch batch){
        spCannon.draw(batch);
        if(hasCoin) { //if the bank
            ZombieTD.SR.setColor(Color.YELLOW); //set color to yellow
            ZombieTD.SR.circle(xpos+25, ypos+25, coinRadius); //draw a coin
        }
    }

    //update the bank
    public void update(){
        if (moneyCounter++ > moneyDelay){ //if counter incremented is greater than delay
            moneyCounter = 0; //set counter to 0
            hasCoin = true; //the bank has the coin
        }

    }

    //if the bank is clicked
    public boolean isClicked(float x,float y){
        return rect.contains(x+width/2,y+height/2); //is the x and y value within the button
    }

}
