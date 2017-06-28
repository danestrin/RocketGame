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

    private float runTime;

    public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        int width = Gdx.graphics.getWidth()/2;
        int height = Gdx.graphics.getHeight()/2;

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
}
