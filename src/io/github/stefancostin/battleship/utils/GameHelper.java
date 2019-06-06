package io.github.stefancostin.battleship.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

public class GameHelper {
	private static final String alphabet = "ABCDEFG";
	private int gridLength;
	private int gridSize;
	private int[] grid;
	private int shipCount;
	
	public GameHelper() {
		this.gridLength = 7;
		this.gridSize = (int) Math.pow(this.gridLength, 2);
		this.grid = new int[this.gridSize];
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
    
    public ArrayList<String> placeBattleship(int shipSize) {
    	ArrayList<String> alphaCells = new ArrayList<String>();
    	
    	String temp = null;
    	int[] coords = new int[shipSize];
    	boolean success = false;
    	int attempts = 0;
    	int location = 0;
    	
    	shipCount++;
    	int incr = 1;
    	if (shipCount % 2 == 1) {
    		incr = gridLength;
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
    	int row = 0;
    	int column = 0;
//    	System.out.print("\n");
    	while (x < shipSize) {
    		grid[coords[x]] = 1;
    		row = (int) (coords[x] / gridLength);
    		column = coords[x] % gridLength;
    		temp = String.valueOf(alphabet.charAt(column));
    		
    		alphaCells.add(temp.concat(Integer.toString(row)));
    		x++;
    		System.out.print(" coord " + x + " = " + alphaCells.get(x - 1));
    	}
    	System.out.println("\n");
    	return alphaCells;
    }
    
}
