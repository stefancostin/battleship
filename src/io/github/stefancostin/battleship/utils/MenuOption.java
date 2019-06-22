package io.github.stefancostin.battleship.utils;

public enum MenuOption {
	SINGLEPLAYER("S", "SINGLEPLAYER"), 
	MULTIPLAYER("M", "MULTIPLAYER"), 
	QUIT("Q", "QUIT");
	
	private String initial;
	private String command;
	
	MenuOption(String initial, String command) {
		this.initial = initial;
		this.command = command;
	}
	
	public String getInitial() {
		return initial;
	}
	
	public String getCommand() {
		return command;
	}
	
}
