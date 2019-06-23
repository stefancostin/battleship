package io.github.stefancostin.battleship.game.modes;

import io.github.stefancostin.battleship.utils.GameHelper;

public abstract class GameMode {
	protected GameHelper utils;
	
	GameMode() {
		this.utils = new GameHelper();
	}

	public abstract void setupGame();
	public abstract void startPlaying();
	public abstract void checkUserGuess(String userInput);
	public abstract void finishGame();
}
