package com.destrin.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by danestrin on 2017-05-24.
 */
public class AssetLoader {

    public static Texture texture;
    public static TextureRegion ship1, ship2, bg, fuel, title, startButtonUp, startButtonDown, taButtonUp, taButtonDown, mmButtonUp, mmButtonDown, soundOn, soundOff;
    public static Animation shipAnimation;
    public static BitmapFont font;
    public static Preferences prefs;
    public static Sound select, pickup, gameover;

    public static void load() {
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        startButtonUp = new TextureRegion(texture, 160, 89, 67, 12);
        startButtonUp.flip(false, true);
        startButtonDown = new TextureRegion(texture, 160, 103, 67, 12);
        startButtonDown.flip(false, true);

        taButtonUp = new TextureRegion(texture, 160, 117, 102, 12);
        taButtonUp.flip(false, true);
        taButtonDown = new TextureRegion(texture, 160, 131, 102, 12);
        taButtonDown.flip(false, true);

        mmButtonUp = new TextureRegion(texture, 160, 145, 95, 12);
        mmButtonUp.flip(false, true);
        mmButtonDown = new TextureRegion(texture, 160, 159, 95, 12);
        mmButtonDown.flip(false, true);

        soundOn = new TextureRegion(texture, 160, 173, 94, 12);
        soundOn.flip(false, true);
        soundOff = new TextureRegion(texture, 160, 187, 103, 12);
        soundOff.flip(false, true);

        bg = new TextureRegion(texture, 0, 0, 160, 240);

        ship1 = new TextureRegion(texture, 160, 0, 18, 30);
        ship1.flip(false, true);

        ship2 = new TextureRegion(texture, 178, 0, 18, 30);
        ship2.flip(false, true);

        TextureRegion[] ships = {ship1, ship2};
        shipAnimation = new Animation(0.15f, ships);
        shipAnimation.setPlayMode(Animation.PlayMode.LOOP);

        fuel = new TextureRegion(texture, 160, 30, 8, 10);
        fuel.flip(false, true);

        title = new TextureRegion(texture, 160, 40, 190, 49);
        title.flip(false, true);

        font = new BitmapFont(Gdx.files.internal("data/font.fnt"), true);
        font.setUseIntegerPositions(false);

        prefs = Gdx.app.getPreferences("Rocket");

        if (!prefs.contains("hiScore")) {
            prefs.putInteger("hiScore", 0);
        }

        select = Gdx.audio.newSound(Gdx.files.internal("data/select.wav"));
        pickup = Gdx.audio.newSound(Gdx.files.internal("data/pickup.wav"));

    }

    public static void dispose() {
        texture.dispose();
        font.dispose();
    }

    public static void setHiScore(int val) {
        prefs.putInteger("hiScore", val);
        prefs.flush();
    }

    public static int getHiScore() {
        return prefs.getInteger("hiScore");
    }

}
