package io.github.stefancostin.battleship.utils;

import java.util.InputMismatchException;

import io.github.stefancostin.battleship.game.Battleship;
import io.github.stefancostin.battleship.game.Map;

public class PlayerHelper extends GameHelper {
	private Map oppMap; // opponent map
	
	public PlayerHelper() {
		this.oppMap = new Map();
	}
	
    public void renderMultiplayerMap() {
    	// First Row: 1 2 3 4 5 6    1 2 3 4 5 6
    	System.out.print("\n" + "   ");
    	for (int i = 0; i < map.getGridLength(); i++) {
    		System.out.print(i + "  ");
    	}
    	System.out.print("        ");
    	for (int i = 0; i < map.getGridLength(); i++) {
    		System.out.print(i + "  ");
    	}
    	System.out.print("\n");
    	
    	// Display Map
    	for (int i = 0; i < map.getGridSize(); i++) {
    		// Display Cols: A B C D E F G
    		if (i % map.getGridLength() == 0) {
    			System.out.print("\n");
    			System.out.print(Map.alphabet.charAt(i / map.getGridLength()) + "  ");
    		}
    		// Display cells: - - - - - - -
    		System.out.print(oppMap.renderDisplayCell(i) + "  ");
    		System.out.print("      ");
    		// Display Cols: A B C D E F G
    		if (i % map.getGridLength() == 0) {
    			System.out.print("\n");
    			System.out.print(Map.alphabet.charAt(i / map.getGridLength()) + "  ");
    		}
    		// Display cells: - - - - - - -
    		System.out.print(map.renderDisplayCell(i) + "  ");
    	}
    	System.out.print("\n");
    	
    	// Map legend
    	System.out.print("       Opponent Map                   My Map");
    	System.out.print("\n" + "\n");
    }
    
    public void claimOpponentCell(String playerInput, String result) {
    	char symbol = ' ';
    	if (result.equals(Turn.HIT.name()) || result.equals(Turn.KILL.name())) {
    		symbol = '*';
    	}
    	
    	try {    		
    		int location = this.convertToCellLocation(playerInput);  		
    		oppMap.setDisplayCell(location, symbol);
    	} catch(InputMismatchException e) {
    		System.out.println(e.getMessage());
    	} finally {    		
    		System.out.println(result.toString().toLowerCase());
    	}  	
    }
    
}
