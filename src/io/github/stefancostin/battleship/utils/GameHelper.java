package io.github.stefancostin.battleship.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class GameHelper {
	private static final String alphabet = "ABCDEFG";
	private int gridLength;
	private int gridSize;
	private int[] grid;
	private int shipCount;
	
	public GameHelper() {
		this.gridLength = 7;
		this.gridSize = this.gridLength ^ 2;
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
    
}
