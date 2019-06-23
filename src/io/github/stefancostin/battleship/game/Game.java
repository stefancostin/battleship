package io.github.stefancostin.battleship.game;

import java.util.ArrayList;

import io.github.stefancostin.battleship.tests.BattleshipTestDrive;
import io.github.stefancostin.battleship.utils.GameHelper;
import io.github.stefancostin.battleship.utils.MenuOption;
import io.github.stefancostin.battleship.utils.Turn;

public class Game {
	private GameHelper utils;

	public Game() {
		this.utils = new GameHelper();
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
    			case SINGLEPLAYER:  game.singlePlayerGame();
    	    						pass = maxPasses;
    	    						break;
    	    						
    			case MULTIPLAYER:   game.multiPlayerGame();
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
    
    private void singlePlayerGame() {
    	GameMode newGame = new SinglePlayer();
    	newGame.setupGame();
    	newGame.startPlaying();
    }
    
    private void multiPlayerGame() {
    	MultiPlayer newGame = new MultiPlayer();
    	newGame.setupGame();
//    	GameMode newGame = new MultiPlayer();
//    	newGame.setupGame();
//    	newGame.startPlaying();
    }
    
//    private void startGame(Class gameModeClass) {
//		try {
//			GameMode newGame = (GameMode) gameModeClass.newInstance();
//			newGame.setupGame();
//			newGame.startPlaying();
//		} catch (InstantiationException | IllegalAccessException e) {
//			e.printStackTrace();
//		}
//    }
    
}
