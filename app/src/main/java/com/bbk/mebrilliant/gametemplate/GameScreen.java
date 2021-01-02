package com.bbk.mebrilliant.gametemplate;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.KeyEvent;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends Screen {
    private GameTemplate myGame;

    private int touchX = 0;
    private int touchY = 0;

    enum GameState {
        Running,
        Paused,
        GameOver
    }

    GameState state;

    World world;

    GameScreen(Game game) {
        super(game);

        myGame = (GameTemplate) game;

        world = new World(myGame);

        state = GameState.Running;
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        List<KeyEvent> keyEvents = game.getInput().getKeyEvents();

        if (state == GameState.Running)
            updateRunning(touchEvents, keyEvents);
        else if (state == GameState.Paused)
            updatePaused(touchEvents);
        else if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateRunning(List<TouchEvent> touchEvents, List<KeyEvent> keyEvents) {
        for (int i = 0; i < keyEvents.size(); i++) {
            KeyEvent event = keyEvents.get(i);
            if (event.keyCode == android.view.KeyEvent.KEYCODE_BACK && event.type == KeyEvent.KEY_UP) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new MainMenuScreen(game));

                return;
            }
        }

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.pointer == 0) {
                if (event.type == TouchEvent.TOUCH_DOWN) {
                }

                if (event.type == TouchEvent.TOUCH_DRAGGED) {
                }

                if (event.type == TouchEvent.TOUCH_UP) {
                }
            }
        }

        world.update();
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.pointer == 0) {
                if (event.type == TouchEvent.TOUCH_DOWN) {
                }

                if (event.type == TouchEvent.TOUCH_DRAGGED) {
                }

                if (event.type == TouchEvent.TOUCH_UP) {
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.pointer == 0) {
                if (event.type == TouchEvent.TOUCH_DOWN) {
                }

                if (event.type == TouchEvent.TOUCH_DRAGGED) {
                }

                if (event.type == TouchEvent.TOUCH_UP) {
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        
        //g.drawPixmap(Assets.gameBackgroundImage, 0, 0);
        g.drawRect(0, 0, 453, 801, Color.BLACK);

        if (state == GameState.Running)
            drawRunningUI(g);
        else if (state == GameState.Paused)
            drawPausedUI(g);
        else if (state == GameState.GameOver)
            drawGameOverUI(g);
    }

    private void drawRunningUI(Graphics g) {
        world.turtle.draw();
    }

    private void drawPausedUI(Graphics g) {
    }

    private void drawGameOverUI(Graphics g) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
        Graphics g = game.getGraphics();

        Settings.loadPrefs(myGame);

        Assets.gameBackgroundImage = g.newPixmap("normalbackground-452x800.png", Graphics.PixmapFormat.RGB565);
    }

    @Override
    public void dispose() {
        Assets.gameBackgroundImage.dispose();
    }
}
