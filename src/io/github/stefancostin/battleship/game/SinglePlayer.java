package io.github.stefancostin.battleship.game;

import java.util.ArrayList;

public class SinglePlayer extends GameMode {
    protected void setupGame() {
//    	Battleship one = new Battleship("HMS Dreadnought");
//    	Battleship two = new Battleship("HMS Vanguard");
//    	Battleship three = new Battleship("HMS Hood");
//    	this.battleshipList.add(one);
//    	this.battleshipList.add(two);
//    	this.battleshipList.add(three);
    	
    	System.out.println("Your goals is to sink three battleships.");
    	System.out.println("HMS Dreadnought, HMS Vanguard and HMS Hood.");
    	System.out.println("Try to sink them all in the fewest number of guesses.");
    	
//    	for (Battleship battleship : battleshipList) {
//    		ArrayList<Integer> newLocation = utils.placeBattleship(3);
//    		battleship.setLocationCells(newLocation);
//    	}
    }
    
    protected void startPlaying() {
//    	do {
//        	utils.renderMap();
//    		String userInput = utils.getUserInput("Enter a guess: ");
//    		this.checkUserGuess(userInput);
//    	} while(!battleshipList.isEmpty());
    	this.finishGame();
    }
    
    protected void checkUserGuess(String userInput) {
//    	this.numOfGuesses++;
//    	utils.checkCell(userInput, battleshipList);
    }
    
    protected void finishGame() {
//    	utils.renderMap();
//    	System.out.println("All battleships have been sunk!");
//    	if (this.numOfGuesses <= 18) {
//    		System.out.println("It only took you " + this.numOfGuesses + " guesses.");
//    	} else {
//    		System.out.println("Took you long enough. " + this.numOfGuesses + " guesses.");
//    	}
    }
    
}
