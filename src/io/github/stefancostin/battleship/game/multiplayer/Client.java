package io.github.stefancostin.battleship.game.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import io.github.stefancostin.battleship.game.Menu;
import io.github.stefancostin.battleship.utils.ClosedConnectionException;
import io.github.stefancostin.battleship.utils.ConnectionOption;
import io.github.stefancostin.battleship.utils.Constants;

public class Client extends Player {
	private BufferedReader br;
	private InputStreamReader in;
	private PrintStream ps;
	private Socket socket;
	private String ipAddress;
	
	public Client() {
		this.ipAddress = getAddress();
	}
	
	public void run() {
		try {
			// Establishing connection
			socket = new Socket(this.ipAddress, Constants.PORT);
			System.out.println("Client started.");
			
			// Establishing Input Drivers
			in = new InputStreamReader(socket.getInputStream());
			br = new BufferedReader(in);
						
			// Establishing Output Drivers
			ps = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String read() throws IOException {
		String input = "";
		input = br.readLine();
		if (input == null) {
			throw new ClosedConnectionException("Server has closed the connection.");
		} 
		return input;
	}
	
	public void post(String output) {
		output = output == null ? "" : output;
		ps.println(output);
	}
	
	public void close() throws IOException {
		System.out.println("Closing...");
		
		ps.close();
		try {
			br.close();
		} finally {
			try {
				in.close();
			} finally {
				socket.close();
			}
		}	
	}
	
    private String getAddress() {
    	String ipAddress = null;
    	Menu menu = new Menu();
    	menu.displayConnectionOptions();
    	
    	String selectedConnectionType = abilities.getUserInput("   What is the game's multiplayer connection?").toUpperCase();	
    	
    	ConnectionOption conn = menu.checkConnectionType(selectedConnectionType);
    	do {
        	switch (conn) {
	    		case LOCALHOST: ipAddress = Constants.LOCALHOST;
	    						break;
	    						
	    		case INTERNET:	ipAddress = this.setPublicIp();
	    						break;
	    						
	    		default:		System.out.print("Please enter a valid connection type.\n");
	    	}
    	} while (ipAddress == null);
    	return ipAddress;
    }
   
    private String setPublicIp() {
		return abilities.getUserInput("  Enter public IP: ").trim();
    }
	
	@Override
	protected void finalize() {
		try {
			this.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
