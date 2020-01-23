package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


//user interface
public class UI {

    public static int money = 100; //set initialze money to 100
    public static BitmapFont font = new BitmapFont(); //declaration of font

    public static void draw(SpriteBatch batch){
        if(ZombieTD.gameStart == true) { //if game is started
            //dark gray money label
            font.setColor(Color.DARK_GRAY);
            font.draw(batch, "Money: " + money, 30, 570);
            //dark gray wave lable
            font.setColor(Color.DARK_GRAY);
            font.draw(batch, "Wave: " + ZombieTD.wave, 30, 550);
            //dark gray lives and cannon costs label
            font.setColor(Color.DARK_GRAY);
            font.draw(batch, "Lives: " + ZombieTD.playerLives, 30, 530);
            font.draw(batch, "Cannon costs:", 250, 530);
            //print the information
            font.draw(batch, "" + ZombieTD.cannonCost, 380, 510);
            font.draw(batch, "" + ZombieTD.fireCannonCost, 450, 510);
            font.draw(batch, "" + ZombieTD.laserCannonCost, 520, 510);
            font.draw(batch, "" + ZombieTD.snowCannonCost, 580, 510);
            font.draw(batch, "" + ZombieTD.bankCost, 640, 510);
            font.draw(batch, ""+ ZombieTD.omniCannonCost, 700, 510);
            //if the playerlives is less than 0
            if (ZombieTD.playerLives <= 0) {
                font.getData().setScale(2); //set scale to 2
                font.setColor(Color.RED); //set color to red
                font.draw(batch, "GAME OVER", 430, 300); //draw game over
                font.getData().setScale(1); //reset scale to 1
            }
            if (ZombieTD.wave == 21) { //if wave 21
                font.getData().setScale(2); //set scale to 2
                font.setColor(Color.YELLOW); //set color to yellow
                font.draw(batch, "You Win!", 430, 300); //display u wiin
                font.getData().setScale(1); //reset scale to 1
            }
        }
    }

}
