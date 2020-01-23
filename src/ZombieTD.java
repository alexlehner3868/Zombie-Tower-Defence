package com.mygdx.game;


//Imports
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import java.util.ArrayList;

public class ZombieTD extends ApplicationAdapter {
    SpriteBatch batch; //a variable used to draw
    public static ShapeRenderer SR; //a variable that is used to draw the life bar on zombies
    public static Button cannonButton; //the variable for the button of the cannon
    public static Button fireCannonButton; //a variable for the button of the fire cannon
    public static Button laserCannonButton; //a variable for the button of the laser cannon
    public static Button snowCannonButton; //a variable for the button of the snow cannon
    public static Button bankButton; //a variable for the button of the bank
    public static Button omniCannonButton; //a varaible for the button of the omni cannon
    public enum TowerType {CANNON, FIRECANNON, LASERCANNON, SNOWCANNON, BANK, OMNICANNON}//declares the enumerator with either a value of cannon or fire cannon or laser cannon
    public static TowerType selectedTower; // this is variable of tower type
    public static ArrayList<Cannon> cannonList = new ArrayList<Cannon>(); //a list used to store placed cannons
    public static ArrayList<Zombie> zombieList = new ArrayList<Zombie>(); //a list used to store zombies
    public static ArrayList<Bullet> bulletList = new ArrayList<Bullet>(); //a list used to store bullets
    public static ArrayList<Explosion> explosionList = new ArrayList<Explosion>(); //a list used to store the images for the explosion animation
    public static int wave =0; //a variable used to store the wave number or what round the player is on
    public static int playerLives=100; // a variable used to keep track of the players lives
    public static int currentCannonCost; //a variable to store the cost of the cannons
    public static int cannonCost=40; //a variable used to store the original cannon cost
    public static int fireCannonCost=70;//a variable used to store the original fire cannon cost
    public static int laserCannonCost = 60; //this is the cost of the laser cannon
    public static int snowCannonCost = 70; //this is the cost of the snow cannon
    public static int bankCost = 100; //this is the cost to buy a bank
    public static int omniCannonCost = 60; //this is the cost of an omni cannon
    public static boolean gameStart = false; //create a boolean called game start to handle the info screen


    //a method that intializes draw varaibles
    @Override
    public void create() {
        batch = new SpriteBatch(); //new batch of type Sprite batch
        SR = new ShapeRenderer(); //new shape renderer
        spawnZombies(); //call spawn zombies
        cannonButton = new Button(400, 550,Resources.cannonTexture); // initilizes the cannon button with a new button
        fireCannonButton = new Button(465, 550, Resources.firecannonTexture); //initilizes fire cannon button
        laserCannonButton = new Button( 530, 550, Resources.laserCannonTexture);//initilizes laser cannon button
        snowCannonButton = new Button(595, 550, Resources.snowCannonTexture); //initilizes snow cannon button
        bankButton = new Button(660, 550, Resources.bankTexture); //initilizes bank button
        omniCannonButton = new Button ( 725, 550, Resources.omniShooterTexture); //initilizes omni cannon button


    }

    //method  that draws all of the bullets, cannons, and explosions
    @Override
    public void render() {
        if (gameStart == true) { //if the game is started
            Gdx.gl.glClearColor(1, 0, 0, 1); //clear the screen
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            update(); //call update
            SR.begin(ShapeRenderer.ShapeType.Filled); //begin rendering the shape renderer
            SR.setColor(Color.RED); //set the color to red
            batch.begin(); //begin drawing the batch
            batch.draw(Resources.bgTexture, 0, 0); //draw the background texture with the batch
            cannonButton.draw(batch); //draw the cannon Button with the batch
            fireCannonButton.draw(batch);//draw the fire cannon Button with the batch
            laserCannonButton.draw(batch); //draw the laser cannon Button with the batch
            snowCannonButton.draw(batch); //draw the snow cannon Button with the batch
            bankButton.draw(batch); //draw bank button with the batch
            omniCannonButton.draw(batch); //draw omni cannon button with batch
            for (int i = 0; i < cannonList.size(); i++) { //for all of the cannons in the cannon list

                SR.setColor(Color.RED); //set color to red
                cannonList.get(i).draw(batch); //draw the cannon at index i with the batch

            }
            for (int i = 0; i < zombieList.size(); i++) { //for all of the zombies in the zombie list
                zombieList.get(i).draw(batch); //draw the zombie at index i with batch
                SR.setColor(Color.RED); //set color to red
                zombieList.get(i).drawLife(SR); //draw the life bar at index i with shape renderer
            }
            for (int i = 0; i < bulletList.size(); i++) { //for all of bullets in the bullet list
                bulletList.get(i).draw(batch); //draw the
            }
            for (int i = 0; i < explosionList.size(); i++) { //for all of the explosions in the explosion list
                explosionList.get(i).draw(batch); //draws the explosions at index i with batch
            }
            UI.draw(batch); //draw the user interface with batch

            batch.end(); //end the batch
            SR.end(); //shape renderer
        }else{
            batch.begin(); //begin the bathc
            batch.draw(Resources.startBackground, 0, 0); //draw the start Background before game is started
            batch.end(); //end the batch
            if (Gdx.input.justTouched()) { //is the screeen is clicked
                gameStart = true; //start game
            }
        }
    }

    //dispose method
    @Override
    public void dispose() {
        batch.dispose(); //dispose the batch
        SR.dispose(); //dispose the shape renderer
        UI.font.dispose(); //dispose the font in user dispose
    }

    //see if a the cannon can be build on a certain location
    public boolean isBuildable(float x, float y){
        boolean buildable = true; //set buildable to true
        if(y>495){ //if y is greater than 495
            buildable = false; //you cant build there, set buildable to false
            return  buildable; //return false
        }
        if(x>=700){ //first road stretch from left
            if(300<=y && y<=355){  //dont build there
                buildable = false;
            }
        }else if(700>=x && x>= 600) {//second stretch going down
            if (y <= 350 && y >= 180) {
                buildable = false; //dont build
            }

        }else if(x<=600&&x>=400 ){ //if between these parameters
            if(y<=250 && y>=200){
                buildable =false; //dont build
            }
        }else if( x<=400 && x>=350){ //if between these parameters
            if(y<=500 && y>=200 ){
                buildable =false;//dont build
            }
        }else if (x <=350 && x>200){ //if between these parameters
            if(y<=500 && y>=400){
                buildable =false; //dont build
            }

        }else if(x<=200 && x>100 ){ //if between these parameters
            if( y<=500 && y>=250){
                buildable =false; //dont build
            }
        }else if(x<=150){ //if between these parameters
            if( y<=300 && y >=250){
                buildable =false; //dont build
            }
        }
        return buildable; //if non in any of these parameters, return true

    }
    //see if you can buy a new cannon
    public void controls() {
        if (Gdx.input.justTouched()) { //if the screen is touched
            int x, y; // declare x and y
            x = Gdx.input.getX(); //get the x pos of the mouse
            y = Gdx.graphics.getHeight() - Gdx.input.getY(); //get the y pos of the mouse
            if (isBuildable(x, y) == true && UI.money >= currentCannonCost) {//if not on the road and user has sufficient money
                if(selectedTower == TowerType.CANNON) { //checks if cannon is a regular cannon
                    cannonList.add(new Cannon(x, y)); //build cannon
                    UI.money -= currentCannonCost; //subtracts money
                    removeStackedTower(); //make sure a cannon isnt built on another cannon
                }
                if(selectedTower == TowerType.FIRECANNON) { //checks if cannon is a regular cannon
                    cannonList.add(new FireCannon(x, y)); //build cannon
                    UI.money -= currentCannonCost; //subtracts money
                    removeStackedTower();//make sure a cannon isnt built on another cannon
                }
                if(selectedTower == TowerType.LASERCANNON) { //checks if cannon is a regular cannon
                    cannonList.add(new LaserCannon(x, y)); //build cannon
                    UI.money -= currentCannonCost; //subtracts money
                    removeStackedTower();//make sure a cannon isnt built on another cannon
                }
                if(selectedTower == TowerType.SNOWCANNON) { //checks if cannon is a regular cannon
                    cannonList.add(new SnowCannon(x, y)); //build cannon
                    UI.money -= currentCannonCost; //subtracts money
                    removeStackedTower(); //make sure a cannon isnt built on another cannon
                }
                if(selectedTower == TowerType.BANK) { //checks if cannon is a regular cannon
                    cannonList.add(new Bank(x, y)); //build cannon
                    UI.money -= currentCannonCost; //subtracts money
                    removeStackedTower(); //make sure a cannon isnt built on another cannon
                }
                if(selectedTower == TowerType.OMNICANNON) { //checks if cannon is a regular cannon
                    cannonList.add(new OmniShooterCannon(x, y)); //build cannon
                    UI.money -= currentCannonCost; //subtracts money
                    removeStackedTower(); //make sure a cannon isnt built on another cannon
                }
            }
            if(cannonButton.isClicked(x,y)){ //if the cannon button is clicked
                selectedTower = TowerType.CANNON; //set selected tower the the tower type cannon
                currentCannonCost = cannonCost; //set cost to the cannon cost
            }
            if(fireCannonButton.isClicked(x,y)){ //if the fire cannon button is clicked
                selectedTower = TowerType.FIRECANNON;//set selected tower the the tower type fire cannon
                currentCannonCost = fireCannonCost;//set cost to the fire cannon cost
            }
            if(laserCannonButton.isClicked(x,y)){//if the laser cannon button is clicked
                selectedTower = TowerType.LASERCANNON;//set selected tower the the tower type laser cannon
                currentCannonCost = laserCannonCost;//set cost to the  laser cannon cost
            }
            if(snowCannonButton.isClicked(x,y)){//if the snow cannon button is clicked
                selectedTower = TowerType.SNOWCANNON;//set selected tower the the tower type snow cannon
                currentCannonCost = snowCannonCost; //set cost to the snow cannon cost
            }
            if(bankButton.isClicked(x,y)){//if the bank button is clicked
                selectedTower = TowerType.BANK;//set selected tower the the tower type bank
                currentCannonCost = bankCost;//set cost to the bank cost
            }
            if(omniCannonButton.isClicked(x,y)){//if the omni cannon button is clicked
                selectedTower = TowerType.OMNICANNON;//set selected tower the the tower type omni cannon
                currentCannonCost = omniCannonCost;//set cost to the omni cannon cost
            }
            //if there are cannons in the cannon list
            if (!cannonList.isEmpty())
            {
                for (int i = 0; i < cannonList.size(); i++) //repeat the length of cannonList
                {
                    int temp = i; //set temp to i
                    if (cannonList.get(i) instanceof Bank){ //if the cannonList at index i is a bank
                        if (((Bank) cannonList.get(i)).isClicked(x,y) && ((Bank) cannonList.get(i)).hasCoin){ //and the bank is clicked with a coin
                            UI.money+=2; //increase money
                            ((Bank) cannonList.get(i)).hasCoin = false; //remove coin
                            ((Bank) cannonList.get(i)).moneyCounter=0; //reset money counter
                        }
                    }
                }
            }
        }
    }

//a method to remove and check for stacked towers
    public void removeStackedTower(){
        if(cannonList.size() > 1){ //if more than 1 cannon
            for (int i = 0; i < cannonList.size() -1; i++){ //run for the number of cannons minus the new one
                if(cannonList.get(i).xpos == cannonList.get(cannonList.size()-1).xpos &&
                        cannonList.get(i).ypos == cannonList.get(cannonList.size()-1).ypos){ //checks to see if new cannon has same coordinates as onld one
                    UI.money +=currentCannonCost; //give back money
                    cannonList.remove(cannonList.size()-1); //remove the cannon
                }
            }
        }
    }
    //a method that handles the spawning of the zombies in different waves
    public void spawnZombies() {
        if (zombieList.isEmpty()) { //checks is zombie list is empty
            wave++; //increaes number of waves
            if (wave == 1) {

                zombieList.add(new Zombie(1024, 325, 4)); //adds zombies to list

            } else if (wave == 2) {
                for (int i = 0; i < 5; i++) {

                    zombieList.add(new Zombie(1024 + i * 50, 325, 5)); //adds zombies to list
                }
            } else if (wave == 3) {
                for (int i = 0; i < 10; i++) {
                    zombieList.add(new Zombie(1024 + i * 50, 325, 5)); //adds zombies to list
                }
            } else if (wave == 4) {

                for (int i = 0; i < 6; i++) {
                    zombieList.add(new FastZombie(1024 + i * 50, 325, 7)); //adds fast zombies to list
                }
                for (int i = 0; i < 8; i++) {
                    zombieList.add(new Zombie(1024 + i * 50, 325, 6)); //adds zombies to list
                }
            } else if (wave == 5) {
                for (int i = 0; i < 8; i++) {
                    zombieList.add(new FastZombie(1024 + i * 50, 325, 10)); //adds fast zombies to list
                }
                for (int i = 0; i < 8; i++) {
                    zombieList.add(new Zombie(1024 + i * 50, 325, 8)); //adds zombies to list
                }

            } else if (wave == 6) {
                for (int i = 0; i < 12; i++) {
                    zombieList.add(new FastZombie(1024 + i * 50, 325, 12)); //adds fast zombies to list
                }
            } else if (wave == 7) {
                for (int i = 0; i < 10; i++) {
                    zombieList.add(new FastZombie(1024 + i * 50, 325, 10)); //adds fast zombies to list
                }
                for (int i = 0; i < 10; i++) {
                    zombieList.add(new Zombie(1024 + i * 50, 325, 8)); //adds zombies to list
                }

            } else if (wave == 8) {
                for (int i = 0; i < 20; i++) {
                    zombieList.add(new SwarmZombies(1024 + i * 20, 350, 10)); //adds zombies to list
                }
            } else if (wave == 9) {
                for (int i = 0; i < 5; i++) {
                    zombieList.add(new FastZombie(1024 + i * 50, 325, 12)); //adds fast zombies to list

                }
                for (int i = 0; i < 10; i++) {
                    zombieList.add(new Zombie(1024 + i * 50, 325, 12)); //adds zombies to list

                }


            } else if (wave == 10) {
                for (int i = 0; i < 5; i++) {
                    zombieList.add(new FastZombie(1024 + i * 50, 325, 15)); //adds strong zombies to list
                }
                for (int i = 0; i < 15; i++) {
                    zombieList.add(new SwarmZombies(1024 + i * 25, 350, 15)); //adds strong zombies to list
                }
                for (int i = 0; i < 5; i++) {
                    zombieList.add(new Zombie(1024 + i * 50, 325, 10)); //adds strong zombies to list
                }

                for (int i = 0; i < 8; i++) {
                    zombieList.add(new StrongZombie(1024 + i * 50, 325, 25)); //adds strong zombies to list
                }


            } else if (wave == 11) {
                for (int i = 0; i < 10; i++) {
                    zombieList.add(new FastZombie(1024 + i * 50, 325, 17)); //adds strong zombies to list
                }
                for (int i = 0; i < 8; i++) {
                    zombieList.add(new SwarmZombies(1024 + i * 25, 350, 20)); //adds strong zombies to list
                }
                for (int i = 0; i < 10; i++) {
                    zombieList.add(new StrongZombie(1024 + i * 50, 325, 25)); //adds strong zombies to list
                }

                for (int i = 0; i < 10; i++) {
                    zombieList.add(new Zombie(1024 + i * 50, 325, 15)); //adds strong zombies to list
                }
            } else if (wave == 12) {
                for (int i = 0; i < 8; i++) {
                    zombieList.add(new SuperZombie(1024 + i * 40, 325, 15)); //adds strong zombies to list
                }
                for (int i = 0; i < 8; i++) {
                    zombieList.add(new RageZombies(1024 + i * 64, 325, 12)); //adds strong zombies to list
                }


            } else if (wave == 13) {
                for (int i = 0; i < 10; i++) {
                    zombieList.add(new RegenerationZombie(1024 + i * 60, 325, 17)); //adds strong zombies to list
                }
                for (int i = 0; i < 5; i++) {
                    zombieList.add(new RageZombies(1024 + i * 50, 325, 17)); //adds strong zombies to list
                }

            } else if (wave == 14) {
                for (int i = 0; i < 40; i++) {
                    zombieList.add(new SwarmZombies(1024 + i * 25, 350, 20)); //add swarm zombies to list
                }
                for (int i = 0; i < 5; i++) {
                    zombieList.add(new SplittingZombies(1024 + i * 33, 350, 20));//add splitting zombies to list
                }
            } else if (wave == 15) {
                for (int i = 0; i < 30; i++) {
                    zombieList.add(new SplittingZombies(1024 + i * 33, 350, 20));//add splitting zombies to list
                }
            } else if (wave == 16) {
                for (int i = 0; i < 4; i++) {
                    zombieList.add(new SuperZombie(1024 + i * 40, 325, 15));//add super zombies to list
                }
                for (int i = 0; i < 8; i++) {
                    zombieList.add(new FastZombie(1024 + i * 40, 325, 15)); //add fast zombies to list
                }
                for (int i = 0; i < 12; i++) {
                    zombieList.add(new RegenerationZombie(1024 + i * 40, 325, 20)); //add regeration zombies to list
                }
            } else if (wave == 17) {
                for (int i = 0; i < 25; i++) {
                    zombieList.add(new SwarmZombies(1024 + i * 40, 325, 18)); //add swarm zombies to list
                }
                for (int i = 0; i < 10; i++) {
                    zombieList.add(new StrongZombie(1024 + i * 40, 325, 25)); //add strong zombies to list
                }
            } else if (wave == 18) {
                for (int i = 0; i < 12; i++) {
                    zombieList.add(new RegenerationZombie(1024 + i * 40, 325, 40)); //add regernation zombies to list
                }
            } else if (wave == 19) {
                for (int i = 0; i < 3; i++) {
                    zombieList.add(new Zombie(1024 + i * 40, 325, 15)); //add  zombies to list
                }
                for (int i = 0; i < 4; i++) {
                    zombieList.add(new RageZombies(1024 + i * 40, 325, 25)); //add rage zombies to list
                }

            } else if (wave == 19) {
                for (int i = 0; i < 3; i++) {
                    zombieList.add(new SuperZombie(1024 + i * 40, 325, 20)); //add super zombies to list
                }
                for (int i = 0; i < 4; i++) {
                    zombieList.add(new FastZombie(1024 + i * 40, 325, 20)); //add fast zombies to list
                }
                for (int i = 0; i < 8; i++) {
                    zombieList.add(new Zombie(1024 + i * 40, 325, 25)); //add zombies to list
                }
                for (int i = 0; i < 4; i++) {
                    zombieList.add(new RegenerationZombie(1024 + i * 40, 325, 25)); //add regernation zombies to list
                }
                for (int i = 0; i < 16; i++) {
                    zombieList.add(new SwarmZombies(1024 + i * 40, 325, 10)); //add swarm zombies to list
                }
                for (int i = 0; i < 4; i++) {
                    zombieList.add(new SplittingZombies(1024 + i * 40, 325, 20)); //add splitting zombies to list
                }
                for (int i = 0; i < 3; i++) {
                    zombieList.add(new StrongZombie(1024 + i * 40, 325, 40)); //add strong zombies to list
                }
                for (int i = 0; i < 3; i++) {
                    zombieList.add(new RageZombies(1024 + i * 40, 325, 25)); //add rage zombies to list
                }
            }
            else if (wave == 20) {
                for (int i = 0; i < 10; i++) {
                    zombieList.add(new SuperZombie(1024 + i * 40, 325, 20)); //add super zombies to list
                }
                for (int i = 0; i < 10; i++) {
                    zombieList.add(new RegenerationZombie(1024 + i * 40, 325, 25));//add regernation zombies to list
                }
                for (int i = 0; i < 80; i++) {
                    zombieList.add(new SwarmZombies(1024 + i * 40, 325, 8)); //add swarm zombies to list
                }

                for (int i = 0; i < 10; i++) {
                    zombieList.add(new RageZombies(1024 + i * 40, 325, 25)); //add rage zombies to list
                }
            }

        }
    }


///a method that remove sprites from the screen and  arrayList
    public void removeSprites() {
        for (int i = 0; i < zombieList.size(); i++) {

            if (!zombieList.get(i).active) {
                zombieList.remove(i);
            }//removes non active zombies

        }
        for (int i = 0; i < bulletList.size(); i++) {
            if (!bulletList.get(i).active) {
                bulletList.remove(i);
            }//removes non active bullets

        }
        for (int i = 0; i < explosionList.size(); i++) {
            if (!explosionList.get(i).active) {
                explosionList.remove(i);
            }//removes non active explosions
        }
    }
    //a method to check if a bullet collides with a zombie
    public void checkCollisions(){
        if(!zombieList.isEmpty()){ // if there are zombies in the list
            for(int i = 0; i < bulletList.size(); i++){ //check for all bullets
                for(int j = 0; j<zombieList.size(); j++){ //check for all zombies
                    //chekc if the bullet circle overlaps the zombie square
                    if(Intersector.overlaps(bulletList.get(i).getCircle(), zombieList.get(j).getRectangle())&& bulletList.get(i).active && zombieList.get(j).active){

                        if(bulletList.get(i) instanceof FireBullet){ //checks if the bullet used to hit the zombie was a fire bullt
                            zombieList.get(j).takeDamage(3); //takes away 3 from zombie life
                        }else if(bulletList.get(i) instanceof SnowBullet){ //if the snow bullet collides with zombies
                            zombieList.get(j).slowDown(); //slow down the zombie
                        }
                        else {

                            zombieList.get(j).takeDamage(); //gets zombie to take damage
                        }
                        explosionList.add(new Explosion(zombieList.get(j).xpos, zombieList.get(j).ypos)); //add an explosion at the zombie location
                        bulletList.get(i).active = false; //remove the bullet
                    }
                }
            }
        }
    }
//updates
    public void update() {

        controls(); //call controls
        checkCollisions(); //call checkCollisions
        if(wave<21) { //if wave is less than 21
            spawnZombies(); //call spawn zombies
        }
        //when you haven't won or lost
        if (playerLives > 0 && wave <21) { //only plays when lives are greater than 0
            for (int i = 0; i < zombieList.size(); i++) {
                zombieList.get(i).update(); //updates all zombies

            }
            for (int i = 0; i < bulletList.size(); i++) {
                bulletList.get(i).update(); //updates all bullets
            }
            for (int i = 0; i < cannonList.size(); i++) {
                cannonList.get(i).update(); //updates all cannons
            }
            for (int i = 0; i < explosionList.size(); i++) {
                explosionList.get(i).update(); //updates all explosions

            }
        }
            removeSprites(); //remove the sprites
        }



}
