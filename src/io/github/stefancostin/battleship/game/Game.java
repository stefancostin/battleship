package io.github.stefancostin.battleship.game;

import java.util.ArrayList;

import io.github.stefancostin.battleship.tests.BattleshipTestDrive;
import io.github.stefancostin.battleship.utils.GameHelper;
import io.github.stefancostin.battleship.utils.Turn;

public class Game {
	private int numOfGuesses;
	private GameHelper helper;
	private ArrayList<Battleship> battleshipList;
	
	public Game() {
		this.helper = new GameHelper();
		this.battleshipList = new ArrayList<>();
	}

    public static void main(String[] args) {
    	Game game = new Game();
    	game.setupGame();
    	game.startPlaying();
    }
    
    private void setupGame() {
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
    		ArrayList<String> newLocation = helper.placeBattleship(3);
    		battleship.setLocationCells(newLocation);
    	}
    }
    
    private void startPlaying() {
    	do {
    		String userInput = helper.getUserInput("Enter a guess: ");
    		this.checkUserGuess(userInput);
    	} while(!battleshipList.isEmpty());
    	this.finishGame();
    }
    
    private void checkUserGuess(String userInput) {
    	this.numOfGuesses++;
    	Turn result = Turn.MISS;
    	for (Battleship battleship : battleshipList) {
    		result = battleship.checkYourself(userInput);
    		if (result == Turn.HIT) {
    			break;
    		}
    		if (result == Turn.KILL) {
    			this.battleshipList.remove(battleship);
    			break;
    		}
    	}
    	System.out.println(result.toString().toLowerCase());
    }
    
    private void finishGame() {
    	System.out.println("All battleships have been sunk!");
    	if (this.numOfGuesses <= 18) {
    		System.out.println("It only took you " + this.numOfGuesses + " guesses.");
    	} else {
    		System.out.println("Took you long enough. " + this.numOfGuesses + " guesses.");
    	}
    }
    
    

    private void testDrive() {
    	Battleship testBattleship = new Battleship("Test Battleship");
        BattleshipTestDrive.testBattleshipHitCase(testBattleship);
    }
    
}
