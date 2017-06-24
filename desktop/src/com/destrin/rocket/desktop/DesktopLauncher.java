package com.destrin.rocket.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.destrin.rocket.RocketGame;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Rocket";
        config.width = 320;
        config.height = 480;
        new LwjglApplication(new RocketGame(), config);
    }
}
