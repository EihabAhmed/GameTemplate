package com.bbk.mebrilliant.gametemplate.model;

import com.badlogic.androidgames.framework.math.Vector2;
import com.bbk.mebrilliant.gametemplate.app.GameTemplate;
import com.bbk.mebrilliant.gametemplate.tools.GameGenerator;

public class World {
    private GameTemplate myGame;

    public Turtle turtle;

    Vector2 position;
    int angle;

    public World(GameTemplate myGame) {
        this.myGame = myGame;

        position = GameGenerator.generatePosition();
        angle = GameGenerator.generateAngle();

        turtle = new Turtle(myGame, position, angle);
    }

    public void update() {
        //position += new Vector2(1, 1);
    }
}
