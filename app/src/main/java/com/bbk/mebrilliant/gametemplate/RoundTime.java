package com.bbk.mebrilliant.gametemplate;

public class RoundTime {
	private int seconds;
	private int millis;

	public RoundTime() {
		seconds = 0;
		millis = 0;
	}

	RoundTime(int seconds, int millis) {
		this.seconds = seconds;
		this.millis = millis;
	}

	RoundTime(long time) {
		int trimmedResult = (int) (time / 1000000);
		seconds = (int) (trimmedResult / 1000);
		millis = trimmedResult - seconds * 1000;
	}

	boolean isLessThan(RoundTime otherTime) {
		if (seconds < otherTime.getSeconds())
			return true;
		if (seconds > otherTime.getSeconds())
			return false;
		if (millis < otherTime.getMillis())
			return true;
		return false;
	}

	boolean isGreaterThan(RoundTime otherTime) {
		if (seconds > otherTime.getSeconds())
			return true;
		if (seconds < otherTime.getSeconds())
			return false;
		if (millis > otherTime.getMillis())
			return true;
		return false;
	}

	public String toString() {
		String score = seconds + ".";
		if (millis < 100) {
			score += "0";
			if (millis < 10)
				score += "0";
		}
		score += millis;
		return score;
	}

    int getSeconds() {
        return seconds;
    }

    int getMillis() {
        return millis;
    }
}