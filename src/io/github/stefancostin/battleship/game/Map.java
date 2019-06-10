package io.github.stefancostin.battleship.game;

import java.util.Arrays;

public class Map {
	public static final String alphabet = "ABCDEFG";
	private char[] display;
	private int[] grid;
	private int gridLength;
	private int gridSize;
	private int shipCount;
	
	public Map() {
		this.gridLength = 7;
		this.gridSize = (int) Math.pow(this.gridLength, 2);
		this.grid = new int[this.gridSize];
		this.display = new char[this.gridSize];
		Arrays.fill(display, '-');
	}
	
	public int getGridLength() {
		return gridLength;
	}
	
	public void setGridLength(int length) {
		this.gridLength = length;
	}
	
	public int getGridSize() {
		return gridSize;
	}
	
	public void setGridSize(int size) {
		this.gridSize = size;
	}
	
	public int[] getGrid() {
		return grid;
	}
	
	public void setGrid(int[] grid) {
		this.grid = grid;
	}
	
	public int getShipCount() {
		return shipCount;
	}
	
	public void incrementShipCount() {
		this.shipCount++;
	}
	
	public char renderDisplayCell(int index) {
		return this.display[index]; 
	}
	
	public void setDisplayCell(int index, char symbol) {
		this.display[index] = symbol;
	}
	
}
