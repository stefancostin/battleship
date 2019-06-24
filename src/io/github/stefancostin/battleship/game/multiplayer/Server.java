package io.github.stefancostin.battleship.game.multiplayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;

import io.github.stefancostin.battleship.utils.Constants;

public class Server extends Player {
	ServerSocket serverSocket;
	
	public void run() {
		System.out.println("Server waiting for client...");
		try {
			serverSocket = new ServerSocket(Constants.port);
			serverSocket.setSoTimeout(15000);
			socket = serverSocket.accept();
			System.out.println("Client connection established.");
		} catch (SocketTimeoutException e) {
			System.out.println("Client connection timout. Connection has ended to prevent port from being used.");
		} catch (IOException e ) {
			e.printStackTrace();
		} finally {			
			this.close();
		}
	}
	
	public void close() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
