package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class Zombie {
    //declare variables
    float xpos;
    float ypos;
    float speed;
    float width, height;
    float life;
    int moneyOnDeath;
    Texture zombie;
    int originalLife;
    float slowSpeed;
    public boolean active;

    //animations variables
    public int numCol, numRow; //for splitting a 2d sprite
    public Animation walkAnim;  //animations we load it in to
    public TextureRegion[] walkFrame;  //an array of the frames that are being cycled
    public TextureRegion currentFrame;  //the frame that is atively being drawn
    public float frameTime; //how long it takes per frame (how fast the animations cycles)


    public Zombie(float xpos, float ypos, int life){
        //declare vars
       initTexture();
        this.xpos = xpos;
        this.ypos= ypos;
        speed = 1;
        this.life = life;
        width = Resources.zombieTexture.getWidth()/4;
        height = Resources.zombieTexture.getHeight();
        initAnimations();
        active = true;
        moneyOnDeath = 2;
        originalLife = life;
        slowSpeed = 0.5f;

    }
    public void initTexture(){
        zombie = Resources.zombieTexture; //initializes texture for base zombie
    }
    public void draw(SpriteBatch batch){
        frameTime += Gdx.graphics.getDeltaTime(); //checks how long it has been seen program lasted updated
        currentFrame = (TextureRegion)walkAnim.getKeyFrame(frameTime, true);//getting the current frame of the animation
        batch.draw(currentFrame, xpos -width/2, ypos - height/2); //draws the zombie to the screen

    }
    public Rectangle getRectangle(){
        //gives a zombie hit box
        return new Rectangle(xpos- width/2, ypos-height/2, width, height);
    }


    public void takeDamage(){
        if(life--<= 0){ //subtract life and check to see if dead
            active = false; //active is set to false
            for(int i = 0; i < 2; i++){ //makes a cluster of explosions
                for(int j =0; j < 2; j++){ //makes a cluster of explosions
                    ZombieTD.explosionList.add(new DeathExplosion(xpos+4*i, ypos+4*j)); //makes a cluster of explosions
                    ZombieTD.explosionList.add(new DeathExplosion(xpos+-4*i, ypos-4*j));  //makes a cluster of explosions
                }
            }
            Resources.explosionEffects.play(); //play sound when zombie dies and explosion occurs

            UI.money += moneyOnDeath; //increase user money
        }
    }
    public void takeDamage(float damage){
        life = life -damage; //decrease life by damage
        if(life<= 0){ //subtract life and check to see if dead
            active = false; //set active to false
            for(int i = 0; i < 2; i++){ //makes a cluster of explosions
                for(int j =0; j < 2; j++){ //makes a cluster of explosions
                    ZombieTD.explosionList.add(new DeathExplosion(xpos+4*i, ypos+4*j)); //makes a cluster of explosions
                    ZombieTD.explosionList.add(new DeathExplosion(xpos+-4*i, ypos-4*j));  //makes a cluster of explosions
                }
            }
            Resources.explosionEffects.play(); //play sound when zombie dies and explosion occurs

            UI.money += moneyOnDeath; //increase user money
        }
    }
    public void slowDown() {
        speed = slowSpeed; //set speed to slowSpeed when hit by snowbullet

    }

public void initAnimations (){
    numRow = 1; //1 row
    numCol =4; //1 col

    //temporary 2d array to map a sprite sheet onto
    TextureRegion[][] temp = TextureRegion.split(zombie, zombie.getWidth()/numCol, zombie.getHeight()/numRow);
    walkFrame = new TextureRegion[numRow *numCol];

    int frameIndex = 0; //frame index =0
    for(int i=0; i < numRow; i++){ //repeat row times
        for(int j=0; j<numCol; j++){ //repeat col times
            walkFrame[frameIndex++] = temp[i][j]; //rotates frames


        }
    }

        walkAnim = new Animation(0.2f, walkFrame); //makes the animation wiht 0.2 seconds

}
public void move(){
    //get the zombies to move on the track
    if(xpos > 650){
        xpos -=speed;
    }else if(xpos > 370 &&ypos > 215){
        ypos -=speed;
    }else if(xpos>370){
        xpos -= speed;
    }else if( xpos<=370 && xpos >=170 && ypos <445){
        ypos +=speed;
    }else if( xpos>=170&& ypos>= 445){
        xpos -=speed;
    }else if(xpos < 170 && ypos >=270){
        ypos -= speed;
    }else if(xpos < 170 && ypos < 270){
        xpos -=speed;
    }
}

    public void update(){
        move(); //call move
        if(xpos <-20){ //if off the screen
            active = false; //active = false
            ZombieTD.playerLives -= 1; //subtracts a life from the player because the zombie crossed the screen
        }
    }
    public void drawLife (ShapeRenderer SR){

        SR.rect(this.xpos+15 , this.ypos+20, 20*life/originalLife, 5); //draw the life bar of the zombie underneath
    }


}
