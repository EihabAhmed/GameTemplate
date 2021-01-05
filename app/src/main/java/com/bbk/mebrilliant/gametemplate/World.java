package com.bbk.mebrilliant.gametemplate;

import com.badlogic.androidgames.framework.math.Vector2;

public class World {
    private GameTemplate myGame;

    Turtle turtle;

    Vector2 position;
    int angle;

    World(GameTemplate myGame) {
        this.myGame = myGame;

        position = GameGenerator.generatePosition();
        angle = GameGenerator.generateAngle();

        turtle = new Turtle(myGame, position, angle);
    }

    public void update() {
        //position += new Vector2(1, 1);
    }
}
