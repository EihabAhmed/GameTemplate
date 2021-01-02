package com.bbk.mebrilliant.gametemplate;

import com.badlogic.androidgames.framework.math.Vector2;

public class World {
    private GameTemplate myGame;

    Turtle turtle;

    Vector2 position;

    World(GameTemplate myGame) {
        this.myGame = myGame;

        position = GameGenerator.generateGame();

        turtle = new Turtle(myGame, position);
    }

    public void update() {
        //position += new Vector2(1, 1);
    }
}
