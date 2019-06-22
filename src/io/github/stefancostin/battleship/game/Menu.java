package io.github.stefancostin.battleship.game;

import io.github.stefancostin.battleship.utils.MenuOption;

public class Menu {

	public void showOptions() {
		for (MenuOption option : MenuOption.values()) {	
			System.out.println(option.getInitial() + ") " + option.getCommand());
		}
		System.out.print("\n");
	}
	
	public MenuOption checkSelection(String selectedMenuOption) {
//		try {			
//			MenuOption selectedOption = MenuOption.valueOf(userInput);
//			for (MenuOption option : MenuOption.values()) {	
//				if (option == selectedOption) {
//					return option;
//				}
//			}
//		} catch (IllegalArgumentException e) {
//		      System.out.println("Invalid menu option.");
//	    }
//		return null;
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
