package com.bbk.mebrilliant.gametemplate.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;
import com.bbk.mebrilliant.gametemplate.ui.MainMenuScreen;
import com.bbk.mebrilliant.gametemplate.tools.Assets;
import com.bbk.mebrilliant.gametemplate.tools.Settings;

public class GameTemplate extends AndroidGame {
    boolean firstTimeCreate = true;

    final static int SHOW_AD = 0;
    final static int START_GAME = 1;

    /*************** Multithreading *******************/
    Handler handlerToMainThread;
    /*************** Multithreading *******************/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        worldWidth = 452;
        worldHeight = 800;

        super.onCreate(savedInstanceState);

        handlerToMainThread = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                // Show Ads
                if (message.what == SHOW_AD) {

                }
                // Receive messages from other player
                else if (message.what == START_GAME) {
                    receiveMessage((String) (message.obj));
                }
            }
        };
    }

    public void receiveMessage(String msg) {
        if (msg.equals("Pressed Start")) {

        } else if (msg.startsWith("GameSetup")) {

        }
    }

    String nextToken(String str) {
        int index = str.indexOf(" ");

        return str.substring(0, index);
    }

    @Override
    public Screen getStartScreen() {
        return new MainMenuScreen(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (firstTimeCreate) {
            Settings.loadPrefs(this);
            Assets.load(this);
            firstTimeCreate = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
