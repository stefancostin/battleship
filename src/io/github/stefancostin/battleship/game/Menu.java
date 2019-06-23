package io.github.stefancostin.battleship.game;

import io.github.stefancostin.battleship.utils.MenuOption;
import io.github.stefancostin.battleship.utils.MultiplayerOption;

public class Menu {

	public void displayMenuOptions() {
		for (MenuOption option : MenuOption.values()) {	
			System.out.println(option.getInitial() + ") " + option.getCommand());
		}
		System.out.print("\n");
	}
	
	public void displayMultiplayerOptions() {
		for (MultiplayerOption option : MultiplayerOption.values()) {
			System.out.println("   " + option.getInitial() + ") " + option.getCommand());
		}
		System.out.print("\n");
	}
	
	public MenuOption checkMenuSelection(String selectedMenuOption) {
		if (selectedMenuOption.equals(MenuOption.SINGLEPLAYER.getInitial()) || selectedMenuOption.equals(MenuOption.SINGLEPLAYER.getCommand())) {
	    	return MenuOption.SINGLEPLAYER;
		} else if (selectedMenuOption.equals(MenuOption.MULTIPLAYER.getInitial()) || selectedMenuOption.equals(MenuOption.MULTIPLAYER.getCommand())) {
	    	return MenuOption.MULTIPLAYER;
		} else if (selectedMenuOption.equals(MenuOption.QUIT.getInitial()) || selectedMenuOption.equals(MenuOption.QUIT.getCommand())) {
			return MenuOption.QUIT;
		} else {
			System.out.println("Invalid menu option.\n");
		}
		return null;
	}
	
	public MultiplayerOption checkMultiplayerRole(String selectedMultiplayerRole) {
		if (selectedMultiplayerRole.equals(MultiplayerOption.CLIENT.getInitial()) || selectedMultiplayerRole.equals(MultiplayerOption.CLIENT.getCommand())) {
			return MultiplayerOption.CLIENT;
		} else if (selectedMultiplayerRole.equals(MultiplayerOption.SERVER.getInitial()) || selectedMultiplayerRole.equals(MultiplayerOption.SERVER.getCommand())) {
			return MultiplayerOption.SERVER;
		} else {
			System.out.println("Invalid multiplayer option.\n");
		}
		return null;
	}
	
}
