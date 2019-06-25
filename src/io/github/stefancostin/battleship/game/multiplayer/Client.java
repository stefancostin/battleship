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
			socket = new Socket("localhost", Constants.port);
			System.out.println("Client started.");
		} catch (IOException e) {
			e.printStackTrace();
		}
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
