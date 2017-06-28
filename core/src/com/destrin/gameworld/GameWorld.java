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

    private Button startButton;

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

        //this button will be needed in the title screen
        this.startButton = new Button(width/2 - AssetLoader.startButtonUp.getRegionWidth()/2, height/2, 67, 12, AssetLoader.startButtonUp, AssetLoader.startButtonDown);
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

    public void restart() {
        //after GameOver, game goes immediately back to InGame following user input
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
