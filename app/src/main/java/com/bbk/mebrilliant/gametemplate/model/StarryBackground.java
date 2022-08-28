package com.bbk.mebrilliant.gametemplate.model;

import static java.lang.Math.min;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.math.Point;

import java.util.Random;

/**
 * Created by Eihab on 12/12/2014.
 */
public class StarryBackground {
    public int width, height;
    public int numStars;
    public int twinkleDelay;
    public Point[] stars = new Point[100];
    public int[] starColors = new int[100];
    Game game;
    Graphics g;

    public StarryBackground(Game game, int numStars, int twinkleDelay)
    {
        Random random = new Random();
        // Initialize the member variables
        this.game = game;
        g = game.getGraphics();
        width = g.getWidth();
        height = g.getHeight();
        this.numStars = min(numStars, 100);
        this.twinkleDelay = twinkleDelay;

        // Create the stars
        for (int i = 0; i < numStars; i++)
        {
            stars[i] = new Point(random.nextInt(width), random.nextInt(height));
            starColors[i] = Color.rgb(128, 128, 128);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void update()
    {
        Random random = new Random();
        // Randomly change the shade of the stars so that they twinkle
        int RGB;
        for (int i = 0; i < numStars; i++)
            if ((random.nextInt(32767) % twinkleDelay) == 0)
            {
                RGB = random.nextInt(256);
                starColors[i] = Color.rgb(RGB, RGB, RGB);
            }
    }

    public void draw()
    {
        // Draw the solid black background
        g.drawRect(0, 0, width + 1, height + 1, Color.BLACK);

        // Draw the stars
        for (int i = 0; i < numStars; i++)
            g.drawPixel(stars[i].x, stars[i].y, starColors[i]);
    }
}
