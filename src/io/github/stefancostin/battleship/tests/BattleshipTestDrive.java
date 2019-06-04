package io.github.stefancostin.battleship.tests;

import io.github.stefancostin.battleship.core.Battleship;

public class BattleshipTestDrive {

    public static void testBattleshipHitCase(Battleship battleship) {
        // Instantiation
//        Battleship battleship = new Battleship();

        // Set battleship location
        int[] locations = {2, 3, 4};
        battleship.setLocationCells(locations);

        // Get user guess
        String userGuess = "2";

        // Check result of checkYourself method
        String result = battleship.checkYourself(userGuess);
        String testResult = "failed";
        if(result.equals("hit")) {
            testResult = "passed";
        }

        // Print
        System.out.println(testResult);
    }
    
}
