package com.destrin.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

/**
 * Created by danestrin on 2017-05-24.
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
    private int fuel_per_second = 1;

    private float rotation;     //for rotating the sprite
    private float angle;        //for the sprite's movement
    private boolean isRotating;

    private Polygon boundingPoly;

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

        this.boundingPoly = new Polygon(new float[]{this.width/2, 0, this.width, this.height, 0, this.height});
    }

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

    public void onClick() {
        this.isRotating = true;
    }

    public void onRelease() {
        this.isRotating = false;
    }

    public void rotate(float delta) {
        if (this.isRotating) {
            rotation += 250*delta;    //degrees
            angle -= 250*delta;
        }
    }

    public void thrust(float delta) {

        // We have to split the ship's rotation into x, y components using trig
        double x_vel = speed*Math.cos(Math.toRadians(angle));
        double y_vel = speed*Math.sin(Math.toRadians(angle));

        x += x_vel*delta*2*fuel/100;
        y -= y_vel*delta*2*fuel/100;
    }

    public void screenwrap(float delta) {
        //screenwrap horizontally
        if (x > Gdx.graphics.getWidth()/2) {
            x = 0;
        }
        if (x < 0 - width) {
            x = Gdx.graphics.getWidth()/2;
        }
        if (y < 0 - height) {
            y = Gdx.graphics.getHeight()/2;
        }
        if (y > Gdx.graphics.getHeight()/2) {
            y = 0;
        }
    }

    public void burnFuel(float delta) {
        timer += delta;

        if (fuel > 0 && timer >= 0.1) {
            fuel -= fuel_per_second*delta;
            timer -= 0.1;
        }
    }

    public void updatePolygon(float delta) {
        boundingPoly.setPosition(x, y);
        boundingPoly.setOrigin(width/2, height/2);
        boundingPoly.setRotation(rotation);
    }

    public boolean collides(Fuel fuel) {
        if (Intersector.overlapConvexPolygons(this.boundingPoly, fuel.getBoundingPolygon())) {
            this.fuel += fuel.getCapacity();
            return true;
        }
        else {
            return false;
        }
    }

    public void capFuel() {
        if (fuel > 100) {
            fuel = 100;
        }
    }

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
