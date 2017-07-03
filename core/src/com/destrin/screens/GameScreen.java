package com.destrin.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.destrin.gameworld.GameRenderer;
import com.destrin.gameworld.GameWorld;
import com.destrin.helpers.InputHandler;

/**
 * Created by danestrin on 2017-05-24.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    private float scaleX;
    private float scaleY;

    private float runTime;

    public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        /*
        SCALING: This is very important for the mobile versions of the game.
                 Since the background sprite was designed at 160 x 240, and everything in the game
                 is based on that, the screen must be scaled according to that aspect ratio. Therefore,
                 the screen is divided horizontally by 160 and vertically by 240 to get the value that the
                 entire game must be scaled by.
         */

        scaleX = screenWidth/160;
        scaleY = screenHeight/240;

        float width = Gdx.graphics.getWidth()/scaleX;
        float height = Gdx.graphics.getHeight()/scaleY;

        world = new GameWorld(width, height);
        renderer = new GameRenderer(world, width, height);

        Gdx.input.setInputProcessor(new InputHandler(world, screenWidth/width, screenHeight/height));
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);

    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }

    public float getScaleX() {
        return scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }
}
