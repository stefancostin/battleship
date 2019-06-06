package io.github.stefancostin.battleship.game;

import java.util.ArrayList;

import io.github.stefancostin.battleship.utils.Turn;

public class Battleship {
    private ArrayList<String> locationCells = new ArrayList<>();
    private String name;
    
    public Battleship(String name) {
    	this.name = name;
    }
    
    public Battleship(String name, ArrayList<String> locs) {
    	this(name);
    	locationCells = locs;
    }

    public void setLocationCells(ArrayList<String> locations) {
    	this.locationCells = locations;
    }

    public Turn checkYourself(String userInput) {
        Turn result = Turn.MISS;
        if (locationCells.contains(userInput)) {
            locationCells.remove(userInput);
            if (locationCells.isEmpty()) {
            	System.out.println("Ouch! You have sunk " + this.getName() + ".");
                result = Turn.KILL;
            } else {
            	result = Turn.HIT;
            }
        }
        return result;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

}
