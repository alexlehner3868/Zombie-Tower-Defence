package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;



public class Button {
    public Texture buttonTexture; //texture for button
    public float xpos, ypos, width, height; //coordinates and dimensions of button
    public Rectangle rect; //makes a hit box for the button
    //constructor to set vars
    public Button(float x, float y,Texture text){
        buttonTexture = text; //set image
        xpos = x; //set to x
        ypos = y; //set to y
        width = buttonTexture.getWidth();
        height = buttonTexture.getHeight();
        rect = new Rectangle(xpos, ypos, width, height); //hitbox
    }
    //draws the button picture
    public void draw(SpriteBatch batch) {
        batch.draw(buttonTexture, xpos-width/2, ypos - height/2);
    }
    //checks if the button is clicked
    public boolean isClicked(float x,float y){
        return rect.contains(x+width/2,y+height/2); //is the x and y value within the button
    }
}
