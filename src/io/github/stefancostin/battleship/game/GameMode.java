package io.github.stefancostin.battleship.game;

import java.util.ArrayList;

import io.github.stefancostin.battleship.utils.GameHelper;

public abstract class GameMode {
	protected int numOfGuesses;
	protected GameHelper utils;
	protected ArrayList<Battleship> battleshipList;
	
	abstract void setupGame();
	abstract void startPlaying();
	abstract void checkUserGuess(String userInput);
	abstract void finishGame();
}
