package io.github.stefancostin.battleship.core;

import io.github.stefancostin.battleship.tests.BattleshipTestDrive;
import io.github.stefancostin.battleship.utils.GameHelper;

public class Game {

    public static void main(String[] args) {
        boolean isAlive = true;
        int numberOfGuesses = 0;

        int firstLocation  = (int) (Math.random() * 5);
        int[] locations = {firstLocation, ++firstLocation, ++firstLocation};
        Battleship battleship = new Battleship();
        battleship.setLocationCells(locations);

        GameHelper helper = new GameHelper();

        do {
            String guess = helper.getUserInput("Enter a number: ");
            String result = battleship.checkYourself(guess);
            numberOfGuesses++;
            if (result.equals("kill")) {
                isAlive = false;
                System.out.println("You took: " + numberOfGuesses + " guesses.");
            }
        } while (isAlive == true);
    }

    private static void testDrive() {
    	Battleship testBattleship = new Battleship();
        BattleshipTestDrive.testBattleshipHitCase(testBattleship);
    }
    
}
