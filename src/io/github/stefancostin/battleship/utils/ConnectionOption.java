package io.github.stefancostin.battleship.utils;

public enum ConnectionOption {
	LOCALHOST("A", "LOCALHOST"), 
	INTERNET("B", "INTERNET"); 
	
	private String initial;
	private String command;
	
	ConnectionOption(String initial, String command) {
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
