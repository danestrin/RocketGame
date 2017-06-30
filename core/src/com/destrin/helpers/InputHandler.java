package com.destrin.helpers;

import com.badlogic.gdx.InputProcessor;
import com.destrin.gameobjects.Ship;
import com.destrin.gameworld.GameWorld;
import com.destrin.ui.Button;

/**
 * Created by danestrin on 2017-05-24.
 */
public class InputHandler implements InputProcessor {

    private GameWorld world;
    private Ship ship;

    private Button startButton, mmButton, taButton;

    private float scaleFactorX, scaleFactorY;

    public InputHandler(GameWorld world, float scaleX, float scaleY) {
        this.world = world;
        this.ship = world.getShip();

        this.startButton = world.getStartButton();
        this.mmButton = world.getMmButton();
        this.taButton = world.getTaButton();

        this.scaleFactorX = scaleX;
        this.scaleFactorY = scaleY;

    }

    /**
     * Called when a key was pressed
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * Called when a key was released
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * Called when a key was typed
     *
     * @param character The character
     * @return whether the input was processed
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Called when the screen was touched or a mouse button was pressed. The button parameter will be {@link Buttons#LEFT} on iOS.
     *
     * @param screenX The x coordinate, origin is in the upper left corner
     * @param screenY The y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event.
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (world.isTitle()) {
            startButton.isTouchDown(screenX, screenY);
        }

        if (world.isInGame()) {
            ship.onClick();
        }

        if (world.isGameOver()) {
            mmButton.isTouchDown(screenX, screenY);
            taButton.isTouchDown(screenX, screenY);
        }
        return true;
    }

    /**
     * Called when a finger was lifted or a mouse button was released. The button parameter will be {@link Buttons#LEFT} on iOS.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.
     * @param button  the button   @return whether the input was processed
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (world.isTitle()) {
            if (startButton.isReleased(screenX, screenY)) {
                world.start();
                return true;
            }

        }

        if(world.isInGame()) {
            ship.onRelease();
            return true;
        }

        if (world.isGameOver()) {
            if (taButton.isReleased(screenX, screenY)) {
                world.restart();
            }
            else if (mmButton.isReleased(screenX, screenY)) {
                world.returnToTitle();
            }
        }

        return false;
    }

    /**
     * Called when a finger or the mouse was dragged.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.  @return whether the input was processed
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
     *
     * @param screenX
     * @param screenY
     * @return whether the input was processed
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * Called when the mouse wheel was scrolled. Will not be called on iOS.
     *
     * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
     * @return whether the input was processed.
     */
    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public Button getStartButton() {
        return this.startButton;
    }

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }
}
