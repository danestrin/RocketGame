package com.destrin.gameworld;

import com.destrin.gameobjects.Fuel;
import com.destrin.gameobjects.Ship;
import com.destrin.helpers.AssetLoader;
import com.destrin.ui.Button;

import java.util.Random;

/**
 * Created by danestrin on 2017-05-24.
 */
public class GameWorld {

    private Ship ship;
    private Fuel fuel;

    private int width;
    private int height;

    private int score;
    private double score_rate = 0.5;
    private double timer;

    private Button startButton, mmButton, taButton;

    private GameState currentState = GameState.TITLE;

    public enum GameState {
        TITLE, INGAME, GAMEOVER
    }

    public GameWorld(int width, int height) {

        //initialize width/height fields, they'll be needed
        this.width = width;
        this.height = height;

        //initialize the ship (player)
        ship = new Ship(width/2-12, height/2, 18, 30);    //width and height of sprite

        //create the ship fuel collectible at a random spot
        Random rand = new Random();
        int randX = (int) (Math.random() * width/2 + 1);
        int randY = (int) (Math.random() * height/2 + 32);

        fuel = new Fuel(randX, randY, 8, 10);

        //initialize the score at 0
        this.score = 0;

        //initialize the UI buttons for the title and gameover screens
        this.startButton = new Button(width/2 - AssetLoader.startButtonUp.getRegionWidth()/2, 2*height/3, 67, 12, AssetLoader.startButtonUp, AssetLoader.startButtonDown);
        this.taButton = new Button(width/2-AssetLoader.taButtonUp.getRegionWidth()/2, 6*height/10, AssetLoader.taButtonUp.getRegionWidth(), AssetLoader.taButtonUp.getRegionHeight(), AssetLoader.taButtonUp, AssetLoader.taButtonDown);
        this.mmButton = new Button(width/2-AssetLoader.mmButtonUp.getRegionWidth()/2, 7*height/10, AssetLoader.mmButtonUp.getRegionWidth(), AssetLoader.mmButtonUp.getRegionHeight(), AssetLoader.mmButtonUp, AssetLoader.mmButtonDown);
    }

    public void update(float delta) {

        switch(currentState) {
            case TITLE:
                updateTitle(delta);
                break;

            case INGAME:
            default:
                updateInGame(delta);
                break;
        }
    }

    private void updateTitle(float delta) {

    }

    public void updateInGame(float delta) {

        // Call the update functions for the gameobjects
        ship.update(delta);
        fuel.update(delta);

        // COLLISIONS
        if (ship.collides(fuel)) {
            fuel.resetAfterCollision(width, height);
        }

        // Update score (it slowly increases the longer you play
        if (ship.getFuel() > 0) {
            updateScore(delta);
        }

        // The game is over when the ship runs out of fuel
        if (ship.getFuel() == 0) {
            currentState = GameState.GAMEOVER;

            if (score > AssetLoader.getHiScore()) {
                AssetLoader.setHiScore(score);
            }
        }

    }

    public Ship getShip() {
        return ship;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getMmButton() {
        return mmButton;
    }

    public Button getTaButton() {
        return taButton;
    }

    public void updateScore(float delta) {
        timer += delta;

        if (timer >= score_rate) {
            score += 1;
            timer -= score_rate;
        }
    }

    public int getScore() {
        return this.score;
    }

    public boolean isTitle() {
        return currentState == GameState.TITLE;
    }

    public void start() {
        currentState = GameState.INGAME;
    }

    public void returnToTitle() {
        currentState = GameState.TITLE;

        //reset score to 0
        timer = 0;
        score = 0;

        //call a method to reset the Ship
        ship.onRestart(width/2-12, height/2);

        //reset the fuel, this is exactly the same as resetting it after a collision
        fuel.resetAfterCollision(width, height);
    }

    public void restart() {
        currentState = GameState.INGAME;

        //reset score to 0
        timer = 0;
        score = 0;

        //call a method to reset the Ship
        ship.onRestart(width/2-12, height/2);

        //reset the fuel, this is exactly the same as resetting it after a collision
        fuel.resetAfterCollision(width, height);
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isInGame() {
        return currentState == GameState.INGAME;
    }
}
