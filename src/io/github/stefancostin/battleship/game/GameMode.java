package io.github.stefancostin.battleship.game;

import io.github.stefancostin.battleship.utils.GameHelper;

public abstract class GameMode {
	protected GameHelper utils;
	
	GameMode() {
		this.utils = new GameHelper();
	}

	abstract void setupGame();
	abstract void startPlaying();
	abstract void checkUserGuess(String userInput);
	abstract void finishGame();
}
