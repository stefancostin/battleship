package io.github.stefancostin.battleship.game;

import java.util.ArrayList;

import io.github.stefancostin.battleship.tests.BattleshipTestDrive;
import io.github.stefancostin.battleship.utils.GameHelper;
import io.github.stefancostin.battleship.utils.MenuOption;
import io.github.stefancostin.battleship.utils.Turn;

public class Game {
	private int numOfGuesses;
	private GameHelper utils;
	private ArrayList<Battleship> battleshipList;
	
	public Game() {
		this.utils = new GameHelper();
		this.battleshipList = new ArrayList<>();
	}

    public static void main(String[] args) {
    	Game game = new Game();
    	game.selectMenuOption(game);
    }
    
    private void selectMenuOption(Game game) {
    	final int maxPasses = 20;
    	int pass = 0;
    	Menu menu = new Menu();
    	do {
    		menu.displayMenuOptions();
    		String selectedMenuOption = utils.getUserInput("Type option: ").toUpperCase();

    		MenuOption option = menu.checkSelection(selectedMenuOption);
    		if (option == null) continue;
    		switch (option) {
    			case SINGLEPLAYER:  game.setupGame();
    	    						game.startPlaying();
    	    						pass = maxPasses;
    	    						break;
    	    						
    			case MULTIPLAYER:   game.setupGame();
    								game.startPlaying();
    								pass = maxPasses;
    								break;
    								
    			case QUIT:			System.out.println("Game has ended.");
    								pass = maxPasses;
    								break;
    								
    			default: 			System.out.print("Please enter a valid option.\n");
    		}  		
    		pass++;
    	} while(pass < maxPasses);
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
    		ArrayList<Integer> newLocation = utils.placeBattleship(3);
    		battleship.setLocationCells(newLocation);
    	}
    }
    
    private void startPlaying() {
    	do {
        	utils.renderMap();
    		String userInput = utils.getUserInput("Enter a guess: ");
    		this.checkUserGuess(userInput);
    	} while(!battleshipList.isEmpty());
    	this.finishGame();
    }
    
    private void checkUserGuess(String userInput) {
    	this.numOfGuesses++;
    	utils.checkCell(userInput, battleshipList);
    }
    
    private void finishGame() {
    	utils.renderMap();
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
