package com.destrin.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by danestrin on 2017-05-24.
 */
public class AssetLoader {

    public static Texture texture;
    public static TextureRegion ship, bg, fuel, title;
    public static BitmapFont font;

    public static void load() {
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 240, 320);

        ship = new TextureRegion(texture, 240, 0, 18, 24);
        ship.flip(false, true);

        fuel = new TextureRegion(texture, 240, 24, 8, 10);
        fuel.flip(false, true);

        title = new TextureRegion(texture, 240, 34, 190, 49);
        title.flip(false, true);

        font = new BitmapFont(Gdx.files.internal("data/font.fnt"), true);

    }

    public static void dispose() {
        texture.dispose();
        font.dispose();
    }

}
