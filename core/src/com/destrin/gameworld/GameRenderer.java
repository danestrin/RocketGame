package com.destrin.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
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

        if (world.isTitle()) {
            batch.begin();
            batch.draw(AssetLoader.title, width/2-190/4, height/5, 190/2, 49/2);

            AssetLoader.font.getData().setScale(0.5f, 0.5f);
            AssetLoader.font.draw(batch, "COLLECT THE FUEL POWERUPS BEFORE YOUR SHIP RUNS OUT OF FUEL!", 8, 2*height/5, width-8, Align.center, true);
            AssetLoader.font.draw(batch, "2017 DAN ESTRIN", 0, height-8, width, Align.center, true);
            AssetLoader.font.draw(batch, "TAP TO START", 0, 3*height/4, width, Align.center, true);
            batch.end();
        }
        else if (world.isGameOver()) {
            batch.begin();
            AssetLoader.font.getData().setScale(1, 1);
            AssetLoader.font.setColor(255, 0, 0, 255);
            AssetLoader.font.draw(batch, "GAME OVER", 0, height/5, width, Align.center, true);

            AssetLoader.font.getData().setScale(0.5f, 0.5f);
            AssetLoader.font.setColor(255, 255, 255, 255);
            AssetLoader.font.draw(batch, "YOU RAN OUT OF FUEL!", 0, height/3, width, Align.center, true);
            AssetLoader.font.draw(batch, "SCORE: " + world.getScore(), 0, height/2, width, Align.center, true);
            batch.end();
        } else if (world.isInGame()) {
            batch.begin();
            batch.draw(AssetLoader.bg, 0, 0);
            batch.draw(AssetLoader.ship, ship.getX(), ship.getY(), ship.getWidth() / 2, ship.getHeight() / 2, ship.getWidth(), ship.getHeight(), 1, 1, ship.getRotation());
            batch.draw(AssetLoader.fuel, fuel.getX(), fuel.getY(), fuel.getWidth() / 2, fuel.getHeight() / 2, fuel.getWidth(), fuel.getHeight(), 1, 1, 0);

            AssetLoader.font.getData().setScale(0.5f);

            AssetLoader.font.draw(batch, "FUEL: " + ship.getFuel() + "%", 0, 8, width-8, Align.right, true);

            AssetLoader.font.draw(batch, "SCORE: " + world.getScore(), 8, 8, width, Align.left, true);
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
}
