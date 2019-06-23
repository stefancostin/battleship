package io.github.stefancostin.battleship.game.modes;

import io.github.stefancostin.battleship.game.Battleship;
import io.github.stefancostin.battleship.game.multiplayer.Player;
import io.github.stefancostin.battleship.utils.MultiplayerOption;

public class MultiPlayer {
	private int numOfGuesses;
	private Player player;
	
	public MultiPlayer(Player player) {
		this.player = player;
	}
	
    public void setupGame() {
    	System.out.println("Setting up game.\n");
    	player.run();
    }
	
}
