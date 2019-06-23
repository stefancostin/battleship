package io.github.stefancostin.battleship.game;

import java.util.ArrayList;

public class MultiPlayer {
	private int numOfGuesses;
	private ArrayList<Battleship> battleshipList;
	
	public MultiPlayer() {
		this.battleshipList = new ArrayList<>();
	}
	
    public void setupGame() {
    	System.out.println("Setting up game.");
    }
	
}
