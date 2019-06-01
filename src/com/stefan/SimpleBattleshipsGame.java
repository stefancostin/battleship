package com.stefan;

public class SimpleBattleshipsGame {

    public static void main(String[] args) {
        boolean isAlive = true;
        int numberOfGuesses = 0;

        int firstLocation  = (int) (Math.random() * 5);
        int[] locations = {firstLocation, ++firstLocation, ++firstLocation};
        SimpleBattleship battleship = new SimpleBattleship();
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
        SimpleBattleshipTestDrive testBattleship = new SimpleBattleshipTestDrive();
        testBattleship.testHitCase();
    }
}
