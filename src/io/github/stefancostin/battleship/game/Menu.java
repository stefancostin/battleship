package io.github.stefancostin.battleship.game;

import io.github.stefancostin.battleship.utils.MenuOption;

public class Menu {

	public void displayMenuOptions() {
		for (MenuOption option : MenuOption.values()) {	
			System.out.println(option.getInitial() + ") " + option.getCommand());
		}
		System.out.print("\n");
	}
	
	public MenuOption checkSelection(String selectedMenuOption) {
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
	
}
