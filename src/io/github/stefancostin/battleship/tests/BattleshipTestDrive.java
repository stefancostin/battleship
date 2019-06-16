package io.github.stefancostin.battleship.tests;

import java.util.ArrayList;

import io.github.stefancostin.battleship.game.Battleship;
import io.github.stefancostin.battleship.utils.Turn;

public class BattleshipTestDrive {

    public static void testBattleshipHitCase(Battleship battleship) {
        // Set battleship location
        ArrayList<Integer> locations = new ArrayList<>();
        locations.add(0);
        locations.add(1);
        locations.add(2);
        
        battleship.setLocationCells(locations);

        // Get user guess and pass it to 'checkCell' method in the GameHelper class.
        // The method converts the user input into the corresponding integer that
        // is returned here as the 'locationFromUserGuess' variable.
        int locationFromUserGuess = 1;

        // Check result of checkYourself method
        Turn result = battleship.checkYourself(locationFromUserGuess);
        String testResult = "failed";
        if(result == Turn.HIT) {
            testResult = "passed";
        }

        // Print
        System.out.println(testResult);
    }
    
}
