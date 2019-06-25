package io.github.stefancostin.battleship.game.multiplayer;

import java.io.IOException;
import java.util.ArrayList;

import io.github.stefancostin.battleship.game.Battleship;
import io.github.stefancostin.battleship.utils.GameHelper;

public abstract class Player {
	public GameHelper abilities;
	private ArrayList<Battleship> battleshipList;
	private int numOfGuesses;
	
	public Player() {
		this.abilities = new GameHelper();
		this.battleshipList = new ArrayList<>();
	}
	
	public ArrayList<Battleship> getBattleshipList() {
		return this.battleshipList;
	}
	
	public void setBattleshipList(ArrayList<Battleship> battleshipList) {
		this.battleshipList = battleshipList;
	}
	
	public int getNumOfGuesses() {
		return this.numOfGuesses;
	}
	
	public void incrementNumOfGuesses() {
		this.numOfGuesses++;
	}
	
	public abstract void run() throws IOException;
	public abstract void close() throws IOException;
	public abstract String read();
	public abstract void post(String output);
}
