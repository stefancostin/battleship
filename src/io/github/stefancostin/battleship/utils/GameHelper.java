package io.github.stefancostin.battleship.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;

import io.github.stefancostin.battleship.game.Battleship;
import io.github.stefancostin.battleship.game.Map;

import java.io.IOException;

public class GameHelper {
	private Map map;
	
	public GameHelper() {
		this.map = new Map();
	}
	
    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.println(prompt);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            inputLine = in.readLine();
            if (inputLine.length() == 0) return null;
        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return inputLine.toUpperCase();
    }
    
    public ArrayList<Integer> placeBattleship(int shipSize) {
    	ArrayList<Integer> alphaCells = new ArrayList<>();
    	
    	int[] grid = map.getGrid();
    	int gridLength = map.getGridLength();
    	int gridSize = map.getGridSize();
    	
    	String temp = null;
    	int[] coords = new int[shipSize];
    	boolean success = false;
    	int attempts = 0;
    	int location = 0;
    	
    	map.incrementShipCount();
    	int incr = 1;
    	if (map.getShipCount() % 2 == 1) {
    		incr = map.getGridLength();
    	}
    	
    	while (!success && attempts++ < 200) {
    		location = (int) (Math.random() * gridSize);
//    		System.out.print(" try " + location);
    		int x = 0;
    		success = true;
    		while (success && x < shipSize) {
    			if (grid[location] == 0) {
    				coords[x++] = location;
    				location += incr;
    				if (location >= gridSize) {
    					success = false;
    				}
    				// original condition: (x > 0 && (location % gridLength == 0))
    				if (x > 0 && (incr == 1) && (location % gridLength == 0)) {
    					success = false;
    				}
    			} else {
//    				System.out.print(" used " + location);
    				success = false;
    			}
    		}
    	}
    	int x = 0;
    	while (x < shipSize) {
    		alphaCells.add(coords[x]);
    		x++;
    		System.out.println(" coord: " + coords[x -1]);
    	}
    	return alphaCells;
    }
    
    public void checkCell(String userInput, ArrayList<Battleship> battleshipList) {
    	Turn result = Turn.MISS;
    	try {    		
    		int location = this.convertToCellLocation(userInput);  		
    		map.setDisplayCell(location, ' ');
    		for (Battleship battleship : battleshipList) {
    			result = battleship.checkYourself(location);
    			if (result == Turn.HIT) {
    				map.setDisplayCell(location, '*');
    				break;
    			}
    			if (result == Turn.KILL) {
    				map.setDisplayCell(location, '*');
    				battleshipList.remove(battleship);
    				break;
    			}
    		}
    	} catch(InputMismatchException e) {
    		System.out.println(e.getMessage());
    	} finally {    		
    		System.out.println(result.toString().toLowerCase());
    	}
    	
    }
    
    public void renderMap() {
    	// First Row: 1 2 3 4 5 6
    	System.out.print("\n" + "   ");
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
    		System.out.print(map.renderDisplayCell(i) + "  ");
    	}
    	System.out.print("\n" + "\n");
    }
    
    private int convertToCellLocation(String userInput) throws InputMismatchException {
    	if (userInput.length() == 2) {
    		char letter = userInput.charAt(0);
    		char digit = userInput.charAt(1);
    		
    		if ((Character.isLetter(letter) && Map.alphabet.contains(String.valueOf(letter))) &&
    			(Character.isDigit(digit) && (Integer.parseInt(String.valueOf(digit)) < map.getGridLength()))) {
    			return Map.alphabet.indexOf(letter) * map.getGridLength() + Integer.parseInt(String.valueOf(digit));
    		} else {
    			throw new InputMismatchException();
    		}
    	} else {
    		throw new InputMismatchException();
    	}
    }
    
}
