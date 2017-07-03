package com.destrin.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by danestrin on 2017-05-24.
 *
 *  This is the Ship (player) class. All the instance variables and constants are labelled clearly for easily altered
 *  game balance. The ship only has the ability to rotate clockwise on input; the thrust is constant and burns its fuel
 *  constantly.
 *
 *  For collisions, the Ship uses a Polygon shaped like an isosceles triangle.
 */
public class Ship {

    private float x;
    private float y;
    private float width;
    private float height;

    private int fuel;
    private float timer;
    private float max_speed = 100;
    private float speed;
    private float FUEL_RATE = 0.1f;

    private float rotation;     //for rotating the sprite
    private float angle;        //for the sprite's movement
    private boolean isRotating;

    private Polygon boundingPoly;

    /**
     * CONSTRUCTOR: initializes all the necessary instance variables and collision info
     * @param x
     * @param y
     * @param width
     * @param height
     */

    public Ship(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;

        this.fuel = 100;
        this.rotation = 0;
        this.angle = 90;
        this.isRotating = false;

        this.width = width;
        this.height = height;

        this.speed = max_speed;

        this.boundingPoly = new Polygon(new float[]{this.width/2, 0, this.width, this.height-6, 0, this.height-6});
    }

    /**
     * UPDATE: runs every frame, calls all the necessary helper functions for the Ship's functions
     * @param delta
     */

    public void update(float delta) {

        //check for input and rotate the ship
        rotate(delta);

        //thrust forward, and burn fuel
        thrust(delta);
        burnFuel(delta);

        screenwrap(delta);

        //collision and rectangle related functions
        updatePolygon(delta);

        //cap the fuel so it doesn't go over 100%
        capFuel();

    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getRotation() {
        return this.rotation;
    }

    public int getFuel() {
        return this.fuel;
    }

    public Polygon getBoundingPolygon() {
        return this.boundingPoly;
    }

    /**
     * ONCLICK: helper function for ship's rotation
     */
    public void onClick() {
        this.isRotating = true;
    }

    /**
     * ONRELEASE: helper function for ship's rotation
     */
    public void onRelease() {
        this.isRotating = false;
    }

    /**
     * ROTATE: rotates the ship clockwise by changing the vel's angle and the sprite's rotation
     * @param delta
     */
    public void rotate(float delta) {
        if (this.isRotating) {
            rotation += 375*delta;    //degrees
            angle -= 375*delta;
        }
    }

    /**
     * THRUST: splits the ship's rotation into x, y components and adds the result to its x,y components
     * @param delta
     */
    private void thrust(float delta) {

        // We have to split the ship's rotation into x, y components using trig
        double x_vel = speed*Math.cos(Math.toRadians(angle));
        double y_vel = speed*Math.sin(Math.toRadians(angle));

        x += x_vel*delta*2.5*fuel/100;
        y -= y_vel*delta*2.5*fuel/100;
    }

    /**
     * SCREENWRAP: loops the Sprite back if it flies off-screen, Asteroids style
     * @param delta
     */
    public void screenwrap(float delta) {
        //screenwrap horizontally
        if (x > Gdx.graphics.getWidth()/2) {
            x = 0 - width;
        }
        if (x < 0 - width) {
            x = Gdx.graphics.getWidth()/2;
        }
        if (y < 0 - height) {
            y = Gdx.graphics.getHeight()/2;
        }
        if (y > Gdx.graphics.getHeight()/2) {
            y = 0 - height;
        }
    }

    /**
     * BURNFUEL: subtracts 1 from the Ship's fuel every FUEL_RATE (seconds)
     * @param delta
     */
    public void burnFuel(float delta) {
        timer += delta;

        if (fuel > 0 && timer >= FUEL_RATE) {
            fuel -= 1*delta;
            timer -= FUEL_RATE;
        }
    }

    /**
     * UPDATEPOLYGON: update's the ship's collision polygon
     * @param delta
     */
    public void updatePolygon(float delta) {
        boundingPoly.setPosition(x, y);
        boundingPoly.setOrigin(width/2, height/2);
        boundingPoly.setRotation(rotation);
    }

    /**
     * COLLIDES: checks collision between the Ship (this) and a Fuel object passed to it. If true, adds the Fuel's
     * capacity to the Ship.
     * @param fuel
     * @return
     */
    public boolean collides(Fuel fuel) {
        if (Intersector.overlapConvexPolygons(this.boundingPoly, fuel.getBoundingPolygon())) {
            this.fuel += fuel.getCapacity();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * CAPFUEL: ensures that the Ship's fuel doesn't exceed 100%
     */
    public void capFuel() {
        if (fuel > 100) {
            fuel = 100;
        }
    }

    /**
     * ONRESTART: resets the Ship based on the Constructor upon Game Over
     * @param x
     * @param y
     */
    public void onRestart(int x, int y) {
        this.x = x;
        this.y = y;

        this.fuel = 100;
        this.rotation = 0;
        this.angle = 90;
        this.isRotating = false;

        this.speed = max_speed;

        timer = 0;
    }
}
