package io.github.stefancostin.battleship.utils;

import java.util.ArrayList;
import java.util.InputMismatchException;

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
    	System.out.print("           ");
    	for (int i = 0; i < map.getGridLength(); i++) {
    		System.out.print(i + "  ");
    	}
    	System.out.print("\n");
    	
    	// Display Map
    	for (int row = 0; row < map.getGridLength(); row++) {   
    		
    		for (int i = 0; i < map.getGridLength(); i++) {
    			int playerCell = row * map.getGridLength() + i;
    			
    			// Display Cols: A B C D E F G
    			if (i % map.getGridLength() == 0) {
    				System.out.print("\n");

    				// Opponent Row   					
    				for (int j = 0; j < oppMap.getGridLength(); j++) {
    					int oppCell = row * oppMap.getGridLength() + j;

    					// Opponent Cols: A B C D E F G
    		    		if (j % map.getGridLength() == 0) {
    		    			System.out.print(Map.alphabet.charAt(row) + "  ");
    		    		}
    					// Display cells: - - - - - - -
    					System.out.print(oppMap.renderDisplayCell(oppCell) + "  ");
    				}
    				
    				// Player Cols: A B C D E F G
    				System.out.print("        ");
    				System.out.print(Map.alphabet.charAt(row) + "  ");
    			}
    			// Display cells: - - - - - - -
    			System.out.print(map.renderDisplayCell(playerCell) + "  ");
    		}
    	}
    	
    	// Map legend
    	System.out.print("\n" + "\n");
    	System.out.print("      Opponent Map                       My Map");
    	System.out.print("\n" + "\n" + "\n");
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
    
    public void markBattleshipOnMap(ArrayList<Integer> locationCells) {
    	for (int location : locationCells) {    		
    		map.setDisplayCell(location, 'b');
    	}
    }
    
}
