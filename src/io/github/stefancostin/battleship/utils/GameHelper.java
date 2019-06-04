package io.github.stefancostin.battleship.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class GameHelper {
	
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
        return inputLine;
    }
    
}
