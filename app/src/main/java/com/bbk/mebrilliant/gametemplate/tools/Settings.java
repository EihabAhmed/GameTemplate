package com.bbk.mebrilliant.gametemplate.tools;

import android.content.SharedPreferences;

import com.badlogic.androidgames.framework.impl.AndroidGame;

import static android.content.Context.MODE_PRIVATE;

public class Settings {
	private static SharedPreferences prefs;
	private static String prefName = "GameTemplate";

	public static boolean soundEnabled = true;

	public static void loadPrefs(AndroidGame game) {
		prefs = game.getSharedPreferences(prefName, MODE_PRIVATE);

		soundEnabled = prefs.getBoolean("soundEnabled", true);
	}
	
	public static void savePrefs(AndroidGame game) {
		prefs = game.getSharedPreferences(prefName, MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();

		editor.putBoolean("soundEnabled", soundEnabled);

		editor.apply();
	}
}