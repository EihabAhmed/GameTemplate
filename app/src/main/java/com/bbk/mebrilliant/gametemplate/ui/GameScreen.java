package com.bbk.mebrilliant.gametemplate.ui;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.KeyEvent;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;
import com.bbk.mebrilliant.gametemplate.app.GameTemplate;
import com.bbk.mebrilliant.gametemplate.model.World;
import com.bbk.mebrilliant.gametemplate.tools.Assets;
import com.bbk.mebrilliant.gametemplate.tools.Settings;

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

    String score = "0";

    GameScreen(Game game) {
        super(game);

        myGame = (GameTemplate) game;

        world = new World(myGame);

        state = GameState.Running;

        score = "123";
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
        drawText(g, score, g.getWidth() / 2 - score.length() * 20 / 2, g.getHeight() - 69);

        world.turtle.draw();
    }

    private void drawPausedUI(Graphics g) {
    }

    private void drawGameOverUI(Graphics g) {
    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            int srcX = (character - '0') * 20;
            int srcWidth = 20;

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
        Graphics g = game.getGraphics();

        Settings.loadPrefs(myGame);

        Assets.gameBackgroundImage = g.newPixmap("normalbackground-452x800.png", Graphics.PixmapFormat.RGB565);
        Assets.numbers = g.newPixmap("numbers.png", Graphics.PixmapFormat.ARGB4444);
    }

    @Override
    public void dispose() {
        Assets.gameBackgroundImage.dispose();
        Assets.numbers.dispose();
    }
}
