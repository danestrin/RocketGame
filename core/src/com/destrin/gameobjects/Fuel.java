package com.destrin.gameobjects;

import com.badlogic.gdx.math.Polygon;

import java.util.Random;

/**
 * Created by danestrin on 2017-06-04.
 *
 * This is the Fuel powerup class. It spawns at a random spot on the screen at the start of each game and teleports to a
 * new random spot upon collision with the Ship (Player). It contains START_VALUE fuel and decays at a rate of DECAY_RATE,
 * to a minimum value of MIN_VALUE.
 */
public class Fuel {

    private float x;
    private float y;

    private int width;
    private int height;

    private double DECAY_RATE= 0.10;
    private float START_VALUE = 15;
    private float MIN_VALUE = 5;
    private float capacity;
    private float timer;

    private Polygon boundingPoly;

    /*
    CONSTRUCTOR: initializes the Fuel's x, y, width, and height, as well as its capacity and collision polygon.
     */

    public Fuel(int x, int y, int width, int height) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.capacity = START_VALUE;

        boundingPoly = new Polygon(new float[]{0, 0, this.width, 0, this.width, this.height, 0, this.height});
    }

    /*
    UPDATE: runs every frame
     */

    public void update(float delta) {

        decay(delta);
        updatePolygon(delta);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public float getCapacity() {
        return this.capacity;
    }

    public Polygon getBoundingPolygon() {
        return this.boundingPoly;
    }

    /*
    DECAY: The capacity of the fuel decays by 1 based on DECAY_RATE, until it reaches MIN_VALUE.
     */
    public void decay(float delta) {

        timer += delta;

        if (capacity > MIN_VALUE && timer >= DECAY_RATE) {
            capacity -= 1;

            timer -= DECAY_RATE;
        }
    }

    /*
    UPDATEPOLYGON: updates the collision polygon of the Fuel so that it matches the Fuel's x and y.
     */

    public void updatePolygon(float delta) {
        boundingPoly.setPosition(x, y);
    }

    /*
    RESETAFTERCOLLISION: upon collision/collection by the Ship, it is moved to a new, random x and y location and its
    capacity is reset to START_VALUE.
     */

    public void resetAfterCollision(int width, int height) {
        Random rand = new Random();
        int randX = (int) (Math.random() * width/2 + 1);
        int randY = (int) (Math.random() * height/2 + 32);  // 32 instead of 1 so that the fuel doesn't spawn behind the score meter

        x = randX;
        y = randY;
        capacity = START_VALUE;

        timer = 0;
    }
}
