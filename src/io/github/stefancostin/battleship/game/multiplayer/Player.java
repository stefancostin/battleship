package io.github.stefancostin.battleship.game.multiplayer;

import java.net.Socket;
import java.util.ArrayList;

import io.github.stefancostin.battleship.game.Battleship;

public abstract class Player {
	Socket socket;
	private ArrayList<Battleship> battleshipList;
	
	public Player() {
		this.battleshipList = new ArrayList<>();
	}
	
	public abstract void run();
	public abstract void close();
}
