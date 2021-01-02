package com.bbk.mebrilliant.gametemplate;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.math.Vector2;

public class Turtle {
    private final GameTemplate myGame;

    int x;
    int y;

    Turtle(GameTemplate myGame, Vector2 position) {
        this.myGame = myGame;
        x = (int)position.x;
        y = (int)position.y;
    }

    void draw() {
        Graphics g = myGame.getGraphics();
        g.drawLine(x, y, x - 20, y + 20, Color.YELLOW);
        g.drawLine(x, y, x + 20, y + 20, Color.YELLOW);
        g.drawLine(x - 20, y + 20, x + 20, y + 20, Color.YELLOW);
    }
}
