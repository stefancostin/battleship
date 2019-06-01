package com.stefan;

public class SimpleBattleshipTestDrive {

    public static void testHitCase() {
        // Instantiation
        SimpleBattleship battleship = new SimpleBattleship();

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
