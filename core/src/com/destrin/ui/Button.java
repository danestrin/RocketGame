package com.destrin.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by danestrin on 2017-06-26.
 */
public class Button {

    private float x, y, width, height;

    private TextureRegion buttonUp;
    private TextureRegion buttonDown;

    private Rectangle boundingRectangle;

    private boolean isPressed = false;

    public Button(float x, float y, float width, float height, TextureRegion buttonUp, TextureRegion buttonDown) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;

        boundingRectangle = new Rectangle(x, y, width, height);
    }

    public boolean isClicked(int screenX, int screenY) {
        return boundingRectangle.contains(screenX, screenY);
    }

    public void draw(SpriteBatch batch) {
        if (isPressed) {
            batch.draw(buttonDown, x, y, width, height);
        } else {
            batch.draw(buttonUp, x, y, width, height);
        }
    }

    public boolean isTouchDown(int screenX, int screenY) {
        if (boundingRectangle.contains(screenX, screenY)) {
            isPressed = true;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isReleased(int screenX, int screenY) {
        if (boundingRectangle.contains(screenX, screenY) && isPressed) {
            isPressed = false;
            return true;
        } else {
            isPressed = false;
            return false;
        }
    }

    public Rectangle getBoundingRectangle() {
        return this.boundingRectangle;
    }
}
