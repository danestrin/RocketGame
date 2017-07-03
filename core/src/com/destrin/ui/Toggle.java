package com.destrin.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by danestrin on 2017-07-02.
 */
public class Toggle {

    private float x, y, scale;

    private TextureRegion On, Off;
    private Rectangle boundingRectangle;

    private boolean isOn = true;

    public Toggle(float x, float y, float scale, TextureRegion On, TextureRegion Off) {

        this.x = x;
        this.y = y;
        this.scale = scale;

        this.On = On;
        this.Off = Off;

        this.boundingRectangle = new Rectangle(x, y, On.getRegionWidth()/scale, On.getRegionHeight()/scale);
    }

    public boolean isClicked(int screenX, int screenY) {
        return boundingRectangle.contains(screenX, screenY);
    }

    public void draw(SpriteBatch batch) {
        if (isOn) {
            batch.draw(On, x, y, On.getRegionWidth()/scale, On.getRegionHeight()/scale);
        }
        else {
            batch.draw(Off, x, y, Off.getRegionWidth()/scale, Off.getRegionHeight()/scale);
        }
    }

    public boolean isOn() {
        return this.isOn;
    }

    public boolean onRelease(int screenX, int screenY) {
        if (isClicked(screenX, screenY) && isOn) {
            isOn = false;
            return true;
        }
        else if (isClicked(screenX, screenY) && !isOn) {
            isOn = true;
        }
        return false;
    }
}
