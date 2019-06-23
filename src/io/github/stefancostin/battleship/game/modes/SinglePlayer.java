package io.github.stefancostin.battleship.game.modes;

import java.util.ArrayList;

import io.github.stefancostin.battleship.game.Battleship;

public class SinglePlayer extends GameMode {
	private int numOfGuesses;
	private ArrayList<Battleship> battleshipList;
	
	public SinglePlayer() {
		this.battleshipList = new ArrayList<>();
	}
	
    public void setupGame() {
    	Battleship one = new Battleship("HMS Dreadnought");
    	Battleship two = new Battleship("HMS Vanguard");
    	Battleship three = new Battleship("HMS Hood");
    	this.battleshipList.add(one);
    	this.battleshipList.add(two);
    	this.battleshipList.add(three);
    	
    	System.out.println("Your goals is to sink three battleships.");
    	System.out.println("HMS Dreadnought, HMS Vanguard and HMS Hood.");
    	System.out.println("Try to sink them all in the fewest number of guesses.");
    	
    	for (Battleship battleship : battleshipList) {
    		ArrayList<Integer> newLocation = utils.placeBattleship(3);
    		battleship.setLocationCells(newLocation);
    	}
    }
    
    public void startPlaying() {
    	do {
        	utils.renderMap();
    		String userInput = utils.getUserInput("Enter a guess: ");
    		this.checkUserGuess(userInput);
    	} while(!battleshipList.isEmpty());
    	this.finishGame();
    }
    
    public void checkUserGuess(String userInput) {
    	this.numOfGuesses++;
    	utils.checkCell(userInput, battleshipList);
    }
    
    public void finishGame() {
    	utils.renderMap();
    	System.out.println("All battleships have been sunk!");
    	if (this.numOfGuesses <= 18) {
    		System.out.println("It only took you " + this.numOfGuesses + " guesses.");
    	} else {
    		System.out.println("Took you long enough. " + this.numOfGuesses + " guesses.");
    	}
    }
    
}
