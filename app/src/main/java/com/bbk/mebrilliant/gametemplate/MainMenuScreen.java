package com.bbk.mebrilliant.gametemplate;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.KeyEvent;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

import java.util.ArrayList;
import java.util.List;

public class MainMenuScreen extends Screen {
    private GameTemplate myGame;

    //Slider p, l, a, y;
    private ArrayList<Slider> sliders = new ArrayList<>();
    private ArrayList<Button> buttons = new ArrayList<>();

    private int touchX = 0;

    MainMenuScreen(Game game) {
        super(game);
        myGame = (GameTemplate) game;

        resume();
        sliders.add(new Slider("p", 39, 399));
        sliders.add(new Slider("l", 188, 399));
        sliders.add(new Slider("a", 262, 399));
        sliders.add(new Slider("y", 336, 399));
        sliders.add(new Slider("sound", 251, 682));

        buttons.add(new Button("rateus", 190, 539));
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        List<KeyEvent> keyEvents = game.getInput().getKeyEvents();

        for (int i = 0; i < keyEvents.size(); i++) {
            KeyEvent event = keyEvents.get(i);
            if (event.keyCode == android.view.KeyEvent.KEYCODE_BACK && event.type == KeyEvent.KEY_UP) {
                Assets.playSound(Assets.clickSound);

                myGame.finish();
                return;
            }
        }

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.pointer == 0) {
                if (event.type == TouchEvent.TOUCH_DOWN) {
                    for (Slider slider : sliders) {
                        if (slider.isTouchInside(event)) {
                            slider.touched = true;
                            touchX = event.x;
                            break;
                        }
                    }

                    for (Button button : buttons) {
                        if (button.isTouchInside(event)) {
                            button.pressed = true;
                            button.dragOver = true;
                        }
                    }
                }

                if (event.type == TouchEvent.TOUCH_DRAGGED) {
                    for (Slider slider : sliders) {
                        if (slider.touched) {
                            int distance = event.x - touchX;
                            if (slider.tag.equals("p")) {
                                distance = Math.max(0, distance);
                                distance = Math.min(75, distance);
                                slider.x = slider.xDefault + distance;
                            } else if (slider.tag.equals("l") || slider.tag.equals("a") || slider.tag.equals("y")) {
                                distance = Math.min(0, distance);
                                distance = Math.max(-75, distance);
                                int index = sliders.indexOf(slider);
                                for (int j = index; j >= 1; j--) {
                                    sliders.get(j).x = sliders.get(j).xDefault + distance;
                                }
                            } else if (slider.tag.equals("sound")) {
                                if (slider.soundOn) {
                                    distance = Math.min(0, distance);
                                    distance = Math.max(-68, distance);
                                    if (distance >= -34)
                                        slider.image = Assets.soundImage;
                                    else
                                        slider.image = Assets.noSoundImage;
                                } else {
                                    distance = Math.max(0, distance);
                                    distance = Math.min(68, distance);
                                    if (distance <= 34)
                                        slider.image = Assets.noSoundImage;
                                    else
                                        slider.image = Assets.soundImage;
                                }
                                slider.x = slider.xDefault + distance;
                            }

                            break;
                        }
                    }

                    for (Button button : buttons) {
                        if (button.isTouchInside(event)) {
                            if (button.pressed)
                                button.dragOver = true;
                        } else {
                            button.dragOver = false;
                        }
                    }
                }

                if (event.type == TouchEvent.TOUCH_UP) {
                    for (Slider slider : sliders) {
                        if (slider.touched) {
                            slider.touched = false;
                            int distance = event.x - touchX;
                            if (slider.tag.equals("p")) {
                                Assets.playSound(Assets.clickSound);
                                distance = Math.max(0, distance);
                                distance = Math.min(75, distance);
                                if (distance == 75) {
                                    game.setScreen(new GameScreen(game));

                                    return;
                                }

                                slider.x = slider.xDefault;
                            } else if (slider.tag.equals("l") || slider.tag.equals("a") || slider.tag.equals("y")) {
                                Assets.playSound(Assets.clickSound);
                                int index = sliders.indexOf(slider);
                                for (int j = index; j >= 1; j--) {
                                    sliders.get(j).x = sliders.get(j).xDefault;
                                }
                            } else if (slider.tag.equals("sound")) {
                                if (slider.soundOn) {
                                    distance = Math.min(0, distance);
                                    distance = Math.max(-68, distance);
                                    if (distance < -34) {
                                        Settings.soundEnabled = false;
                                        Settings.savePrefs(myGame);
                                        slider.soundOn = false;
                                        slider.x = slider.xDefault - 68;
                                        slider.xDefault = slider.x;
                                    } else {
                                        slider.x = slider.xDefault;
                                    }
                                } else {
                                    distance = Math.max(0, distance);
                                    distance = Math.min(68, distance);
                                    if (distance > 34) {
                                        Settings.soundEnabled = true;
                                        Assets.playSound(Assets.clickSound);
                                        Settings.savePrefs(myGame);
                                        slider.soundOn = true;
                                        slider.x = slider.xDefault + 68;
                                        slider.xDefault = slider.x;
                                    } else {
                                        slider.x = slider.xDefault;
                                    }
                                }
                            }
                            break;
                        }
                    }

                    for (Button button : buttons) {
                        if (button.isTouchInside(event)) {
                            if (button.pressed) {
                                button.pressed = false;
                                button.dragOver = false;

                                Assets.playSound(Assets.clickSound);

                                if (button.tag.equals("rateus")) {
                                    Intent marketIntent = new Intent();
                                    marketIntent.setAction(Intent.ACTION_VIEW);
                                    marketIntent.setData(Uri.parse("market://details?id=com.bbk.mebrilliant.kubikrace"));
                                    try {
                                        myGame.startActivity(marketIntent);
                                    } catch (ActivityNotFoundException e) {
                                        Toast.makeText(myGame.getBaseContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                    }
                                    return;
                                }
                            }
                        } else {
                            button.pressed = false;
                            button.dragOver = false;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.mainMenuBackgroundImage, 0, 0);

        for (Slider slider : sliders)
            slider.draw();

        for (Button button : buttons)
            button.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        Graphics g = game.getGraphics();

        Assets.mainMenuBackgroundImage = g.newPixmap("mainmenubackground-452x800.png", Graphics.PixmapFormat.RGB565);
        Assets.pSliderImage = g.newPixmap("p-74x108.png", Graphics.PixmapFormat.ARGB4444);
        Assets.lSliderImage = g.newPixmap("l-74x108.png", Graphics.PixmapFormat.ARGB4444);
        Assets.aSliderImage = g.newPixmap("a-74x108.png", Graphics.PixmapFormat.ARGB4444);
        Assets.ySliderImage = g.newPixmap("y-74x108.png", Graphics.PixmapFormat.ARGB4444);
        Assets.soundImage = g.newPixmap("sound-58x58.png", Graphics.PixmapFormat.ARGB4444);
        Assets.noSoundImage = g.newPixmap("nosound-58x58.png", Graphics.PixmapFormat.ARGB4444);
        Assets.rateUsButtonPressedImage= g.newPixmap("rateuspressed-72x71.png", Graphics.PixmapFormat.ARGB4444);
    }

    @Override
    public void dispose() {
        Assets.mainMenuBackgroundImage.dispose();
        Assets.pSliderImage.dispose();
        Assets.lSliderImage.dispose();
        Assets.aSliderImage.dispose();
        Assets.ySliderImage.dispose();
        Assets.soundImage.dispose();
        Assets.noSoundImage.dispose();
        Assets.rateUsButtonPressedImage.dispose();
    }

    public class Slider {
        public int width;
        public int height;
        int xDefault;
        public int x;
        public int y;
        Pixmap image;
        String tag;
        boolean touched = false;
        boolean soundOn;

        Slider(String tag, int x, int y) {
            this.x = x;
            xDefault = x;
            this.y = y;
            this.tag = tag;
            switch (tag) {
                case "p":
                    image = Assets.pSliderImage;
                    break;
                case "l":
                    image = Assets.lSliderImage;
                    break;
                case "a":
                    image = Assets.aSliderImage;
                    break;
                case "y":
                    image = Assets.ySliderImage;
                    break;
                case "sound":
                    Settings.loadPrefs(myGame);
                    if (Settings.soundEnabled) {
                        soundOn = true;
                        image = Assets.soundImage;
                        this.x = 319;
                        xDefault = this.x;
                    }
                    else {
                        soundOn = false;
                        image = Assets.noSoundImage;
                    }
                    break;
                default:
                    image = null;
                    break;
            }

            if (image != null) {
                width = image.getWidth();
                height = image.getHeight();
            }
        }

        boolean isTouchInside(TouchEvent event) {
            if (event.x >= x && event.x <= x + width && event.y >= y && event.y <= y + height)
                return true;

            return false;
        }

        void draw() {
            Graphics g = game.getGraphics();

            g.drawPixmap(image, x, y);
        }
    }

    public class Button {
        public int width;
        public int height;
        public final int x;
        public final int y;
        final Pixmap image;
        final Pixmap imagePressed;
        String tag;
        boolean pressed = false;
        boolean dragOver = false;

        Button(String tag, int x, int y) {
            this.x = x;
            this.y = y;
            this.tag = tag;
            switch (tag) {
                case "rateus":
                    image = null;
                    imagePressed = Assets.rateUsButtonPressedImage;
                    break;
                default:
                    image = null;
                    imagePressed = null;
                    break;
            }

            if (imagePressed != null) {
                width = imagePressed.getWidth();
                height = imagePressed.getHeight();
            }
        }

        boolean isTouchInside(TouchEvent event) {
            if (event.x >= x && event.x <= x + width && event.y >= y && event.y <= y + height)
                return true;

            return false;
        }

        void draw() {
            Graphics g = game.getGraphics();

            if (dragOver) {
                g.drawPixmap(imagePressed, x, y);
            } else {
                if (image != null) {
                    g.drawPixmap(image, x, y);
                }
            }
        }
    }
}
