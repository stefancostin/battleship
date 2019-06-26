package io.github.stefancostin.battleship.game.multiplayer;

import java.io.IOException;
import java.util.ArrayList;

import io.github.stefancostin.battleship.game.Battleship;
import io.github.stefancostin.battleship.utils.PlayerHelper;

public abstract class Player {
	public PlayerHelper abilities;
	private ArrayList<Battleship> battleshipList;
	private int numOfGuesses;
	
	public Player() {
		this.abilities = new PlayerHelper();
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
