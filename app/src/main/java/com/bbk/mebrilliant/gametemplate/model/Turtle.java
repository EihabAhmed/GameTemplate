package com.bbk.mebrilliant.gametemplate.model;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.math.Vector2;
import com.bbk.mebrilliant.gametemplate.app.GameTemplate;

public class Turtle {
    private final GameTemplate myGame;

    int x;
    int y;
    int angle;

    Turtle(GameTemplate myGame, Vector2 position, int angle) {
        this.myGame = myGame;
        x = (int)position.x;
        y = (int)position.y;
        this.angle = angle;
    }

    public void draw() {
        Graphics g = myGame.getGraphics();
        g.drawLine(
                x,
                y,
                x + (int)(30 * Math.cos((45 - angle) * Math.PI / 180)),
                y + (int)(30 * Math.sin((45 - angle) * Math.PI / 180)), Color.YELLOW);
        g.drawLine(
                x,
                y,
                x + (int)(30 * Math.cos((180 - 45 - angle) * Math.PI / 180)),
                y + (int)(30 * Math.sin((180 - 45 - angle) * Math.PI / 180)), Color.YELLOW);
        g.drawLine(
                x + (int)(30 * Math.cos((45 - angle) * Math.PI / 180)),
                y + (int)(30 * Math.sin((45 - angle) * Math.PI / 180)),
                x + (int)(30 * Math.cos((180 - 45 - angle) * Math.PI / 180)),
                y + (int)(30 * Math.sin((180 - 45 - angle) * Math.PI / 180)), Color.YELLOW);
    }
}
