package io.github.stefancostin.battleship.game.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import io.github.stefancostin.battleship.utils.Constants;

public class Client extends Player {
	private BufferedReader br;
	private InputStreamReader in;
	private PrintStream ps;
	private Socket socket;
	
	public void run() {
		try {
			// Establishing connection
			socket = new Socket("localhost", Constants.port);
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
	
	public String read() {
		String input = null;
		try {
			input = br.readLine();
			System.out.println("client said: " + input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	public void post(String output) {
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
	
	@Override
	protected void finalize() {
		try {
			this.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
