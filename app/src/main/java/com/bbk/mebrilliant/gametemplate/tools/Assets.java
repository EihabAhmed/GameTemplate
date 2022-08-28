package com.bbk.mebrilliant.gametemplate.tools;

import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Sound;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class Assets {
    public static Pixmap mainMenuBackgroundImage;

    public static Pixmap pSliderImage;
    public static Pixmap lSliderImage;
    public static Pixmap aSliderImage;
    public static Pixmap ySliderImage;
    public static Pixmap soundImage;
    public static Pixmap noSoundImage;
    public static Pixmap rateUsButtonPressedImage;
    public static Pixmap gameBackgroundImage;
    public static Pixmap numbers;
    public static Pixmap car;

    public static Sound clickSound;

    public static void load(AndroidGame game) {

        Settings.loadPrefs(game);

        clickSound = game.getAudio().newSound("movesound.wav");
    }

    public static void playSound(Sound sound) {
        if (Settings.soundEnabled)
            sound.play(1);
    }
}
