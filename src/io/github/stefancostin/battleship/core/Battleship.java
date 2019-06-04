package io.github.stefancostin.battleship.core;

import java.util.ArrayList;

public class Battleship {
    private ArrayList<String> locationCells = new ArrayList<>();

    public void setLocationCells(int[] locations) {
        for (int location : locations) {
            locationCells.add(String.valueOf(location));
        }
    }

    public String checkYourself(String userInput) {
        String result = "miss";
        if (locationCells.contains(userInput)) {
            result = "hit";
            locationCells.remove(userInput);
            if (locationCells.isEmpty()) {
                result = "kill";
            }
        }
        System.out.println(result);
        return result;
    }
}
