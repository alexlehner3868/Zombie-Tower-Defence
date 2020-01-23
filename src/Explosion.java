package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class Explosion {
    //declare variables
    float xpos;
    float ypos;
    float speed;
    float width, height;
    int life;
    Texture explosion;

    public boolean active;
    //animations variables
    public int numCol, numRow; //for splliting a 2d sprite
    public Animation explodeAnim;  //animations we load it in to
    public TextureRegion[] explodeFrame;  //an array of the frames that are being cycled
    public TextureRegion currentFrame;  //the frame that is atively being drawn
    public float frameTime; //how long it takes per frame (how fast the animations cycles)


    public Explosion(float xpos, float ypos){
        explosion = Resources.explosionTexture; //set resource to exlosionTexture
        this.xpos = xpos; //set xpos to xpos
        this.ypos= ypos; //set ypos to ypos
        speed = 1; //speed is set to 1
        life = 10; //set life to 10
        width = Resources.explosionTexture.getWidth()/6; //divide resource width by 6 to account for animation
        height = Resources.explosionTexture.getHeight(); //get image height
        initAnimations(); //initAnimations()
        active = true; //set active

    }
    public void draw(SpriteBatch batch){
        frameTime += Gdx.graphics.getDeltaTime(); //checks how long it has been seen program lasted updated
        currentFrame = (TextureRegion)explodeAnim.getKeyFrame(frameTime, true);//getting the current frame of the animation
        batch.draw(currentFrame, xpos -width/2, ypos - height/2); //draws the explosion to the screen

    }

    public void initAnimations (){
        //dimension of image
        numRow = 1; //set row to 1
        numCol = 6; //set col to 6

        //temporary 2d array to map a sprite sheet onto
        TextureRegion[][] temp = TextureRegion.split(explosion, explosion.getWidth()/numCol, explosion.getHeight()/numRow);
        explodeFrame = new TextureRegion[numRow *numCol]; //set explodeFrame to the new TextureRegion[numRow *numCol]

        int frameIndex = 0; //set frameIndex to 0
        for(int i=0; i < numRow; i++){ // a for loop that repeats numRow times
            for(int j=0; j<numCol; j++){ // a for loop that repeats numRow times
                explodeFrame[frameIndex++] = temp[i][j]; //rotates frames


            }
        }explodeAnim = new Animation(0.2f, explodeFrame); //makes the animation wiht 0.2 seconds

    }


    public void update(){
        life -= 1; //subtracts explosion life so it doesnt remain on the screen forever
        if(life <= 0){
            active=false; //when explosion runs out of life make it inactive
        }
    }


}
