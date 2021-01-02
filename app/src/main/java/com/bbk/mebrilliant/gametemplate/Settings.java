package com.bbk.mebrilliant.gametemplate;

import android.content.SharedPreferences;

import com.badlogic.androidgames.framework.impl.AndroidGame;

import static android.content.Context.MODE_PRIVATE;

class Settings {
	private static SharedPreferences prefs;
	private static String prefName = "GameTemplate";

	static boolean soundEnabled = true;

	static void loadPrefs(AndroidGame game) {
		prefs = game.getSharedPreferences(prefName, MODE_PRIVATE);

		soundEnabled = prefs.getBoolean("soundEnabled", true);
	}
	
	static void savePrefs(AndroidGame game) {
		prefs = game.getSharedPreferences(prefName, MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();

		editor.putBoolean("soundEnabled", soundEnabled);

		editor.apply();
	}
}