package com.bbk.mebrilliant.gametemplate.model;

import com.badlogic.androidgames.framework.Sprite;
import com.badlogic.androidgames.framework.math.Rect;
import com.badlogic.androidgames.framework.math.Vector2;
import com.bbk.mebrilliant.gametemplate.app.GameTemplate;
import com.bbk.mebrilliant.gametemplate.tools.Assets;
import com.bbk.mebrilliant.gametemplate.tools.GameGenerator;

public class World {
    private GameTemplate myGame;

    public Turtle turtle;
    public Sprite car;

    Vector2 position;
    int angle;

    public World(GameTemplate myGame) {
        this.myGame = myGame;

        position = GameGenerator.generatePosition();
        angle = GameGenerator.generateAngle();

        turtle = new Turtle(myGame, position, angle);
        car = new Sprite(myGame, Assets.car);
        car.setNumFrames(2);
        car.setPosition(100, 600);
        car.setFrameDelay(2);
    }

    public void update() {
        //position += new Vector2(1, 1);
        car.update(1);
    }
}
