package io.github.stefancostin.battleship.game;

import java.util.ArrayList;

import io.github.stefancostin.battleship.game.modes.GameMode;
import io.github.stefancostin.battleship.game.modes.MultiPlayer;
import io.github.stefancostin.battleship.game.modes.SinglePlayer;
import io.github.stefancostin.battleship.game.multiplayer.Client;
import io.github.stefancostin.battleship.game.multiplayer.Player;
import io.github.stefancostin.battleship.game.multiplayer.Server;
import io.github.stefancostin.battleship.tests.BattleshipTestDrive;
import io.github.stefancostin.battleship.utils.GameHelper;
import io.github.stefancostin.battleship.utils.MenuOption;
import io.github.stefancostin.battleship.utils.MultiplayerOption;
import io.github.stefancostin.battleship.utils.Turn;

public class Game {
	private GameHelper utils;
	private Menu menu;

	public Game() {
		this.utils = new GameHelper();
		this.menu = new Menu();
	}

    public static void main(String[] args) {
    	Game game = new Game();
    	game.selectMenuOption(game);
    }
    
    private void selectMenuOption(Game game) {
    	final int maxPasses = 20;
    	int pass = 0;
    	do {
    		menu.displayMenuOptions();
    		String selectedMenuOption = utils.getUserInput("Type option: ").toUpperCase();

    		MenuOption option = menu.checkMenuSelection(selectedMenuOption);
    		if (option == null) continue;
    		switch (option) {
    			case SINGLEPLAYER:  game.singlePlayerGame();
    	    						pass = maxPasses;
    	    						break;
    	    						
    			case MULTIPLAYER:   if (game.multiPlayerGame() == 0)
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
    
    private int multiPlayerGame() {
    	menu.displayMultiplayerOptions();
    	String selectedMultiplayerRole = utils.getUserInput("   Join game as client or host game as server?").toUpperCase();
    	
    	MultiplayerOption role = menu.checkMultiplayerRole(selectedMultiplayerRole);
    	if (role == null) return 1;
    	MultiPlayer newGame;
    	switch (role) {
    		case CLIENT:  	newGame = new MultiPlayer(new Client());
    						newGame.setupGame();
    						break;
    						
    		case SERVER:	newGame = new MultiPlayer(new Server());
    						newGame.setupGame();
    						break;
    						
    		default:		System.out.print("Please enter a valid multiplayer role.\n");
    	}
    	
    	
//    	GameMode newGame = new MultiPlayer();
//    	newGame.setupGame();
//    	newGame.startPlaying();
    	
    	return 0;
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
