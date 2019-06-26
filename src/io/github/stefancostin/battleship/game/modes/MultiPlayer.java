package io.github.stefancostin.battleship.game.modes;

import java.io.IOException;
import java.util.ArrayList;

import io.github.stefancostin.battleship.game.Battleship;
import io.github.stefancostin.battleship.game.multiplayer.Client;
import io.github.stefancostin.battleship.game.multiplayer.Player;
import io.github.stefancostin.battleship.game.multiplayer.Server;

public class MultiPlayer extends GameMode {
	private Player player;
	
	public MultiPlayer(Player player) {
		this.player = player;
	}
	
    public void setupGame() {
    	Battleship one = new Battleship("HMS Dreadnought");
    	Battleship two = new Battleship("HMS Vanguard");
    	Battleship three = new Battleship("HMS Hood");
    	player.getBattleshipList().add(one);
    	player.getBattleshipList().add(two);
    	player.getBattleshipList().add(three);
    	
    	System.out.println("Your goals is to sink three battleships.");
    	System.out.println("HMS Dreadnought, HMS Vanguard and HMS Hood.");
    	System.out.println("Try to sink them all in the fewest number of guesses.");
    	
    	for (Battleship battleship : player.getBattleshipList()) {
    		ArrayList<Integer> newLocation = utils.placeBattleship(3);
    		battleship.setLocationCells(newLocation);
    	}
    }
    
    public void startPlaying() {
		try {
			player.run();
	    	do {
	    		player.abilities.renderMultiplayerMap();
	    		if (player.getClass() == Server.class) {
	    			System.out.println("Server identified");
	    			// Server's Turn
		    		String playerInput = player.abilities.getUserInput("Enter a guess: ");
		    		this.postPlayerGuess(playerInput);
		    		this.processResultFromOpponent(playerInput);
		    		// Client's Turn
		    		String result = this.checkOpponentInput();
		    		this.postResultToOpponent(result);
		    		player.abilities.renderMap();
	    		} else if (player.getClass() == Client.class) {
	    			System.out.println("Client identified");
		    		// Client's Turn
		    		String result = this.checkOpponentInput();
		    		this.postResultToOpponent(result);
	    			// Server's Turn
		    		String playerInput = player.abilities.getUserInput("Enter a guess: ");
		    		this.postPlayerGuess(playerInput);
		    		this.processResultFromOpponent(playerInput);
	    		}
	    	} while(!player.getBattleshipList().isEmpty());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.finishGame();
		}
    }
    
    private void postPlayerGuess(String playerInput) {
    	player.incrementNumOfGuesses();
    	player.post(playerInput);
    }
    
    private String checkOpponentInput() {
    	System.out.println("Waiting for other player...");
    	String opponentInput = player.read();
    	return player.abilities.checkCell(opponentInput, player.getBattleshipList());
    }
    
    private void processResultFromOpponent(String playerInput) {
    	String result = player.read();
    	this.player.abilities.claimOpponentCell(playerInput, result);
    }
    
    private void postResultToOpponent(String resultOfHit) {
    	player.post(resultOfHit);
    }
    
    public void finishGame() {
    	try {
			player.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
	    	player.abilities.renderMap();
	    	System.out.println("All battleships have been sunk!");
	    	if (player.getNumOfGuesses() <= 18) {
	    		System.out.println("It only took you " + player.getNumOfGuesses() + " guesses.");
	    	} else {
	    		System.out.println("Took you long enough. " + player.getNumOfGuesses() + " guesses.");
	    	}
		}
    }
	
}
