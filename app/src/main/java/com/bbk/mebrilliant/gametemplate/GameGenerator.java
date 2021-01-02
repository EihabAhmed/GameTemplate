package com.bbk.mebrilliant.gametemplate;

import com.badlogic.androidgames.framework.math.Vector2;

import java.util.Random;

class GameGenerator {
    //static boolean multiplayer;
    //static int singlePlayerDifficulty;

    static Vector2 position = new Vector2();

    static Vector2 generateGame()
    {
        position.x = generateXPosition();
        position.y = generateYPosition();

        return position;
    }

    private static float generateXPosition()
    {
        float xPosition = new Random().nextFloat() * 452;

        return xPosition;
    }

    private static float generateYPosition()
    {
        float yPosition = new Random().nextFloat() * 800;

        return yPosition;
    }
}
