package com.destrin.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.destrin.gameobjects.Fuel;
import com.destrin.gameobjects.Ship;
import com.destrin.helpers.AssetLoader;

/**
 * Created by danestrin on 2017-05-24.
 */
public class GameRenderer {

    private GameWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private int width;
    private int height;

    private SpriteBatch batch;

    private Ship ship;
    private Fuel fuel;

    public GameRenderer(GameWorld world, int width, int height) {

        this.world = world;
        this.width = width;
        this.height = height;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, width, height);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {
        ship = world.getShip();
        fuel = world.getFuel();
    }

    private void initAssets() {
    }

    public void render() {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(AssetLoader.bg, 0, 0);
        batch.draw(AssetLoader.ship, ship.getX(), ship.getY(), ship.getWidth()/2, ship.getHeight()/2, ship.getWidth(), ship.getHeight(), 1, 1, ship.getRotation());
        batch.draw(AssetLoader.fuel, fuel.getX(), fuel.getY(), fuel.getWidth()/2, fuel.getHeight()/2, fuel.getWidth(), fuel.getHeight(), 1, 1, 0);

        String fuelMeter = "FUEL: 100%";
        AssetLoader.font.draw(batch, "FUEL: " + ship.getFuel() + "%", camera.viewportWidth-fuelMeter.length()-42, 8);
        AssetLoader.font.getData().setScale(0.5f);

        AssetLoader.font.draw(batch, "SCORE: " + world.getScore(), fuelMeter.length(), 8);
        AssetLoader.font.getData().setScale(0.5f);
        batch.end();


        /*
        // Debug purposes, draws out the borders of collision
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.polygon(ship.getBoundingPolygon().getTransformedVertices());
        shapeRenderer.polygon(fuel.getBoundingPolygon().getTransformedVertices());
        shapeRenderer.end();
        */

    }
}
