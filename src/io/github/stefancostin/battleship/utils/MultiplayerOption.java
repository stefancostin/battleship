package io.github.stefancostin.battleship.utils;

public enum MultiplayerOption {
	CLIENT("A", "CLIENT"),
	SERVER("B", "SERVER");
	
	private String initial;
	private String command;
	
	MultiplayerOption(String initial, String command) {
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
