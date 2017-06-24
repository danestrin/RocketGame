package com.destrin.gameworld;

import com.destrin.gameobjects.Fuel;
import com.destrin.gameobjects.Ship;

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

    private GameState currentState;

    public enum GameState {
        TITLE, INGAME, GAMEOVER
    }

    public GameWorld(int width, int height) {

        //initialize width/height fields, they'll be needed
        this.width = width;
        this.height = height;

        //initialize the ship (player)
        ship = new Ship(width/2-12, height/2, 18, 24);    //width and height of sprite

        //create the ship fuel collectible at a random spot
        Random rand = new Random();
        int randX = (int) (Math.random() * width/2 + 1);
        int randY = (int) (Math.random() * height/2 + 32);

        fuel = new Fuel(randX, randY, 8, 10);

        //initialize the score at 0
        this.score = 0;

    }

    public void update(float delta) {

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

    }

    public Ship getShip() {
        return ship;
    }

    public Fuel getFuel() {
        return fuel;
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
}
