package com.destrin.rocket;

import com.badlogic.gdx.Game;
import com.destrin.helpers.AssetLoader;
import com.destrin.screens.GameScreen;

public class RocketGame extends Game {

    @Override
    public void create () {
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose () {
        super.dispose();
        AssetLoader.dispose();
    }
}
