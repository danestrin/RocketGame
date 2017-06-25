package com.destrin.gameobjects;

import com.badlogic.gdx.math.Polygon;

import java.util.Random;

/**
 * Created by danestrin on 2017-06-04.
 */
public class Fuel {

    private float x;
    private float y;

    private int width;
    private int height;

    private double decay_rate = 0.25;
    private float start_cap = 20;
    private float min_value = 5;
    private float capacity;
    private float timer;

    private Polygon boundingPoly;

    public Fuel(int x, int y, int width, int height) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.capacity = start_cap;

        boundingPoly = new Polygon(new float[]{0, 0, this.width, 0, this.width, this.height, 0, this.height});
    }

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

    public void decay(float delta) {

        timer += delta;

        if (capacity > min_value && timer >= decay_rate) {
            capacity -= 1;

            timer -= decay_rate;
        }
    }

    public void updatePolygon(float delta) {
        boundingPoly.setPosition(x, y);
    }

    public void resetAfterCollision(int width, int height) {
        Random rand = new Random();
        int randX = (int) (Math.random() * width/2 + 1);
        int randY = (int) (Math.random() * height/2 + 32);  // 32 instead of 1 so that the fuel doesn't spawn behind the score meter

        x = randX;
        y = randY;
        capacity = start_cap;

        timer = 0;
    }
}
