package io.github.stefancostin.battleship.game.modes;

import java.io.IOException;
import java.util.ArrayList;

import io.github.stefancostin.battleship.game.Battleship;
import io.github.stefancostin.battleship.game.multiplayer.Client;
import io.github.stefancostin.battleship.game.multiplayer.Player;
import io.github.stefancostin.battleship.game.multiplayer.Server;
import io.github.stefancostin.battleship.utils.ClosedConnectionException;
import io.github.stefancostin.battleship.utils.Turn;

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
    	System.out.println("Try to sink them all in the fewest number of guesses.\n");
    	
    	for (Battleship battleship : player.getBattleshipList()) {
    		ArrayList<Integer> newLocation = utils.placeBattleship(3);
    		battleship.setLocationCells(newLocation);
    		player.abilities.markBattleshipOnMap(newLocation);
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
		    		player.abilities.renderMultiplayerMap();
		    		// Client's Turn
		    		String result = this.checkOpponentInput();
		    		this.postResultToOpponent(result);
	    		} else if (player.getClass() == Client.class) {
	    			System.out.println("Client identified");
		    		// Client's Turn
		    		String result = this.checkOpponentInput();
		    		this.postResultToOpponent(result);
		    		player.abilities.renderMultiplayerMap();
	    			// Server's Turn
		    		String playerInput = player.abilities.getUserInput("Enter a guess: ");
		    		this.postPlayerGuess(playerInput);
		    		this.processResultFromOpponent(playerInput);
	    		}
	    	} while(!player.getBattleshipList().isEmpty() || player.isWinner());
		} catch (ClosedConnectionException e) {
			e.getMessage();
			System.out.println(e);
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
    
    private String checkOpponentInput() throws IOException {
    	System.out.println("Waiting for other player...");
    	String opponentInput = player.read();
    	System.out.print("\nOpponent striked at: " + opponentInput);
    	return player.abilities.checkCell(opponentInput, player.getBattleshipList());
    }
    
    private void processResultFromOpponent(String playerInput) throws IOException {
    	String result = player.read();
    	if (result.equals(Turn.KILL.name())) {
    		player.addKill();
    	}
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
			player.abilities.renderMultiplayerMap();
    		if (player.isWinner()) {
    			System.out.println("You are victorious!");
    		} else if (player.getBattleshipList().isEmpty()) {
    			System.out.println("Your battleships have been sunk!");
    		}
	    	System.out.println("Game terminated after " + player.getNumOfGuesses() + " turns.");
		}
    }
	
}
