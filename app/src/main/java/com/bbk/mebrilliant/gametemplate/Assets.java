package com.bbk.mebrilliant.gametemplate;

import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Sound;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class Assets {
    static Pixmap mainMenuBackgroundImage;

    static Pixmap pSliderImage;
    static Pixmap lSliderImage;
    static Pixmap aSliderImage;
    static Pixmap ySliderImage;
    static Pixmap soundImage;
    static Pixmap noSoundImage;
    static Pixmap rateUsButtonPressedImage;
    static Pixmap gameBackgroundImage;
    static Pixmap numbers;

    static Sound clickSound;

    static void load(AndroidGame game) {

        Settings.loadPrefs(game);

        clickSound = game.getAudio().newSound("movesound.wav");
    }

    static void playSound(Sound sound) {
        if (Settings.soundEnabled)
            sound.play(1);
    }
}
