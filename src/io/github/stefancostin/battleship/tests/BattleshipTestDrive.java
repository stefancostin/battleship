package io.github.stefancostin.battleship.tests;

import java.util.ArrayList;

import io.github.stefancostin.battleship.game.Battleship;
import io.github.stefancostin.battleship.utils.Turn;

public class BattleshipTestDrive {

    public static void testBattleshipHitCase(Battleship battleship) {
        // Set battleship location
        ArrayList<String> locations = new ArrayList<>();
        locations.add("A3");
        locations.add("A4");
        locations.add("A5");
        
        battleship.setLocationCells(locations);

        // Get user guess
        String userGuess = "2";

        // Check result of checkYourself method
        Turn result = battleship.checkYourself(userGuess);
        String testResult = "failed";
        if(result == Turn.HIT) {
            testResult = "passed";
        }

        // Print
        System.out.println(testResult);
    }
    
}
